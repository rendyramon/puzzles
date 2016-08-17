public class WordReverser{
  public void reverseWords(char[] wordList){
    int length = wordList.length-1;
    int start = 0;
    reverse(wordList, start, length);

    int nextSpaceIdx;
    do{
      nextSpaceIdx = findNextSpace(wordList, start, length);
      reverse(wordList, start, nextSpaceIdx);
      start = nextSpaceIdx+2;
    } while(start < length);
  }

  private void reverse(char[] wordList, int start, int end){
    while(start < end){
      char temp = wordList[end];
      wordList[end] = wordList[start];
      wordList[start] = temp;
      start++;
      end--;
    }
  }

  private int findNextSpace(char[] wordList, int start, int length){
    while(start <= length && wordList[start] != ' ') start++;
    return start-1;
  }

  public static void main(String[] args){
    WordReverser wordReverser = new WordReverser();
    char[] wayOfLife = "perfect makes practice".toCharArray();
    wordReverser.reverseWords(wayOfLife);
    for(Character aChar : wayOfLife){
      System.out.print(aChar);
    }
    System.out.println();
  }
}