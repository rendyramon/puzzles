import java.util.*;

public class CheckPerumtation{
  public boolean isPermut(String firstString, String secondString){

    Map<Character, Integer> charMap = new HashMap<>();
    // put first string in map
    for( int i = 0; i < firstString.length(); i++){
      char characterI = firstString.charAt(i);
      if(charMap.containsKey(characterI)){
        charMap.put(characterI, charMap.get(characterI) + 1);
      } 
      else { charMap.put(characterI, 1); }
    }

    // check second string is permutation
    for(int i = 0; i < secondString.length(); i++){
      char characterI = secondString.charAt(i);
      if(charMap.containsKey(characterI)){
         int iCount = charMap.get(characterI);
         if(iCount == 0) return false;
         if(iCount == 1) charMap.remove(characterI);
         else charMap.put(characterI, iCount - 1);
       }
       else 
        return false; 
     }

     return charMap.size() == 0;
  }

  public static void main(String[] args){
    CheckPerumtation checkPermutation = new CheckPerumtation();

    System.out.println(checkPermutation.isPermut("aba", "baa"));
    System.out.println(checkPermutation.isPermut("1234", "4321"));
    System.out.println(checkPermutation.isPermut("tom", "bob"));
    System.out.println(checkPermutation.isPermut("akka", "kaka"));
    System.out.println(checkPermutation.isPermut("ak ka", "aakka"));
    System.out.println(checkPermutation.isPermut("ak ka", "aa kka"));
  } 
}