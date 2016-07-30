import java.util.*;

public class Palindrome {
  /*
    A palindrome is the same forward as backwards, so it must have either: 
      1. consist soley of any number of the same letter
      2. one odd numbered letter, and any number of even occuring letters
      3. any number of even occuring letters
                                 ** Spaces do not count **
  */
 
  public boolean isPalindromePermutation(String givenString) {
    Map<Character, Integer> charMap = new HashMap<>();

    // store all elements
    for(int i = 0; i < givenString.length(); i++){
      char iChar = givenString.charAt(i);
      if(iChar != ' '){
        if(charMap.containsKey(iChar))
          charMap.put(iChar, charMap.get(iChar) + 1);
        else
          charMap.put(iChar, 1);
      }
    }

    // count
    int oddNumbered = 0;
    int evenNumbered = 0;
    for(Map.Entry<Character, Integer> entry: charMap.entrySet()) {
      if(entry.getValue() % 2 == 0)
        evenNumbered++;
      else
        oddNumbered++;
    }

    return charMap.size() == 1 || oddNumbered == 1 || oddNumbered == 0;
  }

  public static void main(String[] args){
    Palindrome palindrome = new Palindrome();

    System.out.println(palindrome.isPalindromePermutation("aaaaa"));
    System.out.println(palindrome.isPalindromePermutation("aaaaaak"));
    System.out.println(palindrome.isPalindromePermutation("ba ba"));
    System.out.println(palindrome.isPalindromePermutation("a"));
    System.out.println(palindrome.isPalindromePermutation("cato tac"));

    System.out.println(palindrome.isPalindromePermutation("aaub"));
    System.out.println(palindrome.isPalindromePermutation("kua"));
    System.out.println(palindrome.isPalindromePermutation("ab"));
  }
}