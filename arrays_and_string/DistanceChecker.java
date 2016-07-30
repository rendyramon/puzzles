import java.util.*;

public class DistanceChecker{
  
  public boolean isOneAway(String firstString, String secondString) {
    // load first string in map
    Map<Character, Integer> charMap = new HashMap<>();
    for(int i = 0; i < firstString.length(); i++){
      char iChar = firstString.charAt(i);
      if(charMap.containsKey(iChar)) charMap.put(iChar, charMap.get(iChar) + 1);
      else charMap.put(iChar, 1);
    }

    // remove every element from second string
    int differences = 0;
    for(int i = 0; i < secondString.length(); i++){
      char iChar = secondString.charAt(i);
      if(charMap.containsKey(iChar)) {
        int iCount = charMap.get(iChar);
        if(iCount == 1) charMap.remove(iChar);
        else charMap.put(iChar, iCount - 1);
      } else{
        differences++;
      }
    }

    // check that size of remaining map is zero or 1.
    Object[] keyArray = charMap.keySet().toArray();
    int onlyValueLeft = 0;
    if(keyArray.length > 0) onlyValueLeft = charMap.get(keyArray[0]);

    return charMap.size() <= 1 && onlyValueLeft <= 1 && differences <= 1;
  }

  public static void main(String[] args){
    DistanceChecker distanceChecker = new DistanceChecker();

    System.out.println(distanceChecker.isOneAway("pale", "ple"));
    System.out.println(distanceChecker.isOneAway("mat", "cat"));
    System.out.println(distanceChecker.isOneAway("bob", "boba"));
    System.out.println(distanceChecker.isOneAway("max", "max"));

    System.out.println(distanceChecker.isOneAway("pale", "bake"));
  }
}