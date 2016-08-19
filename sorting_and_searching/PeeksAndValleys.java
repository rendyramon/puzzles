import java.util.*;

public class PeeksAndValleys{
  
  public void peeksAndValleys(int[] arr){
    int length = arr.length;
    if(length <= 2) return;
    int pointer = 1;
    while(pointer < length-1){
      if(!(arr[pointer] <= arr[pointer-1] && arr[pointer] <= arr[pointer+1])) {
        if(arr[pointer-1] > arr[pointer+1])
          swap(arr, pointer-1, pointer);
        else
          swap(arr, pointer, pointer+1);
      }
      pointer++;
    }
  }

  private void swap(int[] arr, int left, int right){
    int temp = arr[right];
    arr[right] = arr[left];
    arr[left] = temp;
  }

  public static void main(String[] args){
    PeeksAndValleys peeksAndValleys = new PeeksAndValleys();
    int[] reversedOne = new int[]{5, 3, 1, 2, 3};
    peeksAndValleys.peeksAndValleys(reversedOne);
    int[] reversedTwo = new int[]{5, 8, 6, 3, 2, 4, 6};
    peeksAndValleys.peeksAndValleys(reversedTwo);
    for(int i = 0; i < reversedOne.length; i++)
      System.out.print(reversedOne[i]+ " ");
    System.out.println();
    for(int i = 0; i < reversedTwo.length; i++)
      System.out.print(reversedTwo[i] + " ");
    System.out.println();
  }
}