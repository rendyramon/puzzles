import java.util.*;

public class PermuteUniqueString{
  public List<String> permute(String permuteString){
    return permute(permuteString, permuteString.length()-1);
  }

  private List<String> permute(final String current, int finish){
    if(finish == 0) return new ArrayList<String>(){{ add(current.charAt(0) + ""); }};
    List<String> permutations = permute(current, finish-1);
    List<String> newPermutation = new ArrayList<String>();
    char newChar = current.charAt(finish);
    for(int i = 0; i < permutations.size(); i++){
      String currentString = permutations.get(i);
      int currentStringLength = currentString.length();
      for(int j = 0; j <= currentStringLength; j++){
        String begining = currentString.substring(0, j);
        String end = j > currentStringLength? "" : currentString.substring(j, currentStringLength);
        newPermutation.add(begining + newChar +  end);
      }
    }
    return newPermutation;
  }

  public static void main(String[] args){
    PermuteUniqueString permuteUnique = new PermuteUniqueString();
    System.out.println(permuteUnique.permute("abc"));
  }
}