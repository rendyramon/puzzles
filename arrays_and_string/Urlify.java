public class Urlify{
  public char[] urlify(char[] givenArray, int length){
    // count number of spaces
    int spacesNumber = 0;
    for(int i = 0; i < length; i++) {
      if(givenArray[i] == ' ') {
        spacesNumber++;
      }
    }
      

    if(spacesNumber == 0) return givenArray;

    // shift and replace
    int currentLetter = length - 1;
    while(spacesNumber > 0) {
      while(givenArray[currentLetter] != ' ') {
        givenArray[currentLetter + (2 * spacesNumber)] = givenArray[currentLetter];
        currentLetter--;
      }
      givenArray[currentLetter + (2 * spacesNumber)] = '0';
      givenArray[currentLetter + (2 * spacesNumber)-1] = '2';
      givenArray[currentLetter + (2 * spacesNumber)-2] = '%';

      currentLetter--;
      spacesNumber--;
    }
    return givenArray;    
  }

  public static void main(String[] args){
    Urlify urlify = new Urlify();

    System.out.println(urlify.urlify("Mr John Smith    ".toCharArray(), 13));
    System.out.println(urlify.urlify("".toCharArray(), 0));
    System.out.println(urlify.urlify("a".toCharArray(), 1)); 

  }
}