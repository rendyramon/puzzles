import java.util.*;

public class GroupAnagrams{
  public static class SpecialString implements Comparable<SpecialString>{
    public String item;
    private Map<Character, Integer> charCount;
    public SpecialString(String item){
      this.item = item;
      charCount = new HashMap<Character, Integer>();
      for(char aChar : item.toCharArray()){
        if(charCount.containsKey(aChar))
          charCount.put(aChar, charCount.get(aChar) +1);
        else
          charCount.put(aChar, 1);
      }
    }

    @Override
    public int compareTo(SpecialString otherString){
      if(item.length() > otherString.item.length()) return -1;
      else if(item.length() < otherString.item.length()) return 1;
      else{
        for(char aChar : item.toCharArray()){
          int thisCharCount = charCount.get(aChar);
          int otherCharCount = otherString.charCount.get(aChar);
          if(thisCharCount > otherCharCount) return -1;
          else if(otherCharCount > thisCharCount) return 1;
        }
        return 0;
      }
    }
  }

  public void groupAnagrams(String[] strArr){
    SpecialString[] specialStrArr = new SpecialString[strArr.length];
    for(int i = 0; i < strArr.length; i++){
      specialStrArr[i] = new SpecialString(strArr[i]);
    }
    Arrays.sort(specialStrArr);
    for(int i = 0; i < strArr.length; i++){
      strArr[i] = specialStrArr[i].item;
    }
  }

  public static void main(String[] args){
    GroupAnagrams groupAnagrams = new GroupAnagrams();
    String[] grouped = new String[]{"major tom", "banana", "tom major", "chaka", "major mot", "kacha"};
    groupAnagrams.groupAnagrams(grouped);
    for(String aStr : grouped){
      System.out.println(aStr);
    }
  }
}