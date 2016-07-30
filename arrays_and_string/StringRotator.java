public class StringRotator{
  public boolean isRotation(String s1, String s2){
    if(s1.length() != s2.length())
        return false;

    int i = 0;
    while( i < s2.length()) {
      while(i < s2.length() && s1.charAt(0) != s2.charAt(i))
        i++;
      
      int rotationPoint = i;
      while(i < s2.length() && s1.charAt(0) == s2.charAt(i))
        i++;
      
      if(i == s2.length())
        return isSubstring(s2.substring(0, rotationPoint), s1);
      else
        i = rotationPoint + 1;
    }
    return false;
  }
}