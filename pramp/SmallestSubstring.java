import java.util.*;

public class SmallestSubstring{

  public String smallestSubstring(char[] arr, String str){
    Map<Character, Integer> countMap = new HashMap<Character, Integer>();
    for(Character character : arr){
      countMap.put(character, 0);
    }

    // find first substring
    int idx = 0;
    while(!countMap.containsKey(str.charAt(idx))){
      idx++;
    }
    if(idx == str.length()) return null;

    int startIdx = idx;
    int uniqueCounter = 0;
    while(idx < str.length() && uniqueCounter < arr.length){
      char idxChar = str.charAt(idx);
      if(countMap.containsKey(idxChar)){
        int idxCount = countMap.get(idxChar);
        if(idxCount == 0) uniqueCounter++;
        countMap.put(idxChar, idxCount+1);
      }
      idx++;
    }
    if(idx == str.length()){
      if(uniqueCounter < arr.length) return null;
      else return str.substring(startIdx, idx);
    }
    int minEndIdx = idx;
    int endIdx = minEndIdx;

    /* 
      for every new character, check if its a mandotory character
      if it is a mandatory and it matches the start,
        remove as many as possible from the begining
        increment minEndIdx
      else increment the countMap
    */
    while(endIdx < str.length()){
      char endChar = str.charAt(endIdx);
      if(countMap.containsKey(endChar)){
        int endCharCount = countMap.get(endChar);
        if( str.charAt(startIdx) == endChar){
          startIdx++;
          char newStartChar = str.charAt(startIdx);
          while(!countMap.containsKey(newStartChar) || countMap.get(newStartChar) > 1){
            if(countMap.containsKey(newStartChar)){
              countMap.put(newStartChar, countMap.get(newStartChar)-1);
            }
            startIdx++;
            newStartChar = str.charAt(startIdx);
          }
          minEndIdx = endIdx+1;
        } else{
          countMap.put(endChar, endCharCount+1);
        }
      }
      endIdx++;
    }
    return str.substring(startIdx, minEndIdx);
  }

  public static void main(String[] args){
    SmallestSubstring smallestSubstring = new SmallestSubstring();
    System.out.println(smallestSubstring.smallestSubstring(new char[]{'x','y','z'}, "xyyzyzyxx"));
    System.out.println(smallestSubstring.smallestSubstring(new char[]{'x','y','z'}, "xyyzz"));
    System.out.println(smallestSubstring.smallestSubstring(new char[]{'x','y','z'}, "xyyyzz"));
    System.out.println(smallestSubstring.smallestSubstring(new char[]{'x','y','z'}, "xywxyyzwyyxxzzzzzw"));
    System.out.println(smallestSubstring.smallestSubstring(new char[]{'x','y','z'}, "xyz"));
  }
}