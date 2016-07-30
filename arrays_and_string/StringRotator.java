public class StringRotator{
  public boolean isRotation(String s1, String s2){
    if(s1.length() != s2.length() || s2.length == 0)
        return false;
  
    return isSubtring(s1 + s1, s2);
  }
}