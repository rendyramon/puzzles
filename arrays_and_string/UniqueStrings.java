import java.util.*;

public class UniqueStrings{
  
  public boolean isUnique(String givenString){
    Set<Character> charSet = new HashSet<>();
    for(int i = 0; i < givenString.length(); i++){
      char character = givenString.charAt(i);
      if(character != ' ' && charSet.contains(character)) return false; 
      charSet.add(character);
    }
    return true;
  }

  public static void main(String[] args){
    UniqueStrings uniqueStrings = new UniqueStrings();

    System.out.println(uniqueStrings.isUnique("1235"));
    System.out.println(uniqueStrings.isUnique("Test"));
    System.out.println(uniqueStrings.isUnique("SHould be false a"));
    System.out.println(uniqueStrings.isUnique("SHould b t"));
  }
}