import java.util.*;

public class Compressor{
  public String compressString(String givenString){
    StringBuilder builder = new StringBuilder();
    int i = 0;
    
    // compress
    char currentLetter = givenString.charAt(i);
    int occurance = 0;
    while(i < givenString.length()) {
      char iChar = givenString.charAt(i);
      i++;
      if(iChar == currentLetter)
        occurance++;
      else{
        builder.append(currentLetter +""+ occurance);
        currentLetter = iChar;
        occurance = 1;
      }
    }
    builder.append(currentLetter +""+ occurance);

    // return if compressed is smaller
    String compressed = builder.toString();
    if(compressed.length() >= givenString.length())
      return givenString;
    return compressed;
  }

  public static void main(String[] args){
    Compressor compressor = new Compressor();

    System.out.println(compressor.compressString("aaaaabbbbbc"));
    System.out.println(compressor.compressString("abc"));
    System.out.println(compressor.compressString("bbbbbc"));
  }
}