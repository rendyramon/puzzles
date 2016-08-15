public class Sorter{

  public void mergeSort(int[] arr){
    int[] helper = new int[arr.length];
    merge(arr, helper, 0, arr.length-1);
  }

  private void merge(int[] arr, int[] helper, int low, int high){
    if(low < high){
      int middle = (low + high)/2;
      merge(arr, helper, low, middle);
      merge(arr, helper, middle+1, high);
      mergeSort(arr, helper, low, high);
    }
  }

  private void mergeSort(int[] arr, int[] helper, int low, int high){
    for(int i = low; i <= high; i++){
      helper[i] = arr[i];
    }
    
    int middle = (low + high)/2;
    int leftPointer = low, current = low;
    int rightPointer = middle +1;

    while(leftPointer <= middle && rightPointer <= high){
      if(helper[leftPointer] <= helper[rightPointer]){
        arr[current] = helper[leftPointer];
        leftPointer++;
      } else{
        arr[current] = helper[rightPointer];
        rightPointer++;
      }
      current++;
    }

    int remaining = middle - leftPointer;
    for(int i = 0; i <= remaining; i++){
      arr[current +i] = helper[leftPointer + i];
    }
  }

  public static void main(String[] args){
    Sorter sorter = new Sorter();
    int[] arr = new int[]{4,2,3,4};
    sorter.mergeSort(arr);
    for(int i = 0; i < arr.length; i++){
      System.out.print(arr[i]);
    }
    System.out.println();
  }
}