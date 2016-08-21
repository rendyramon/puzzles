import java.util.*;

public class PermuteString{
  public List<String> permute(String permuteString){
    Map<Character, Integer> charMap = new HashMap<>();
    List<String> result = new ArrayList<>();

    for(int i = 0; i < permuteString.length(); i++){
      char ithChar = permuteString.charAt(i);
      if(charMap.containsKey(ithChar)) charMap.put(ithChar, charMap.get(ithChar) +1);
      else charMap.put(ithChar, 1);
    }
    permute(charMap, result, "");
    return result;
  }

  private void permute(Map<Character, Integer> remaining, List<String> result, String current){
    if(remaining.keySet().size() == 0){
      result.add(current);
      return;
    }
    for(Character key : remaining.keySet()){
      int count = remaining.get(key);
      Map<Character, Integer> newMap = new HashMap<>(remaining);
      if(count == 1) newMap.remove(key);
      else newMap.put(key, count-1);  
      permute(newMap, result, current + key);
    }
  }

  public static void main(String[] args){
    PermuteString permuteUnique = new PermuteString();
    System.out.println(permuteUnique.permute("aba"));
  }
}