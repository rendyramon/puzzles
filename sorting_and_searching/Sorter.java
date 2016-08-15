public class Sorter{

  public void mergeSort(int[] arr){
    int[] helper = new int[arr.length];
    mergeSort(arr, helper, 0, arr.length-1);
  }

  public void quickSort(int[] arr){
    quickSort(arr, 0, arr.length-1);
  }

  private void quickSort(int[] arr, int left, int right){
    int index = partition(arr, left, right);
    if(left < index-1){
      quickSort(arr, left, index-1);
    }
    if(right > index){
      quickSort(arr, index, right);
    }
  }

  private int partition(int[] arr, int left, int right){
    int pivot = arr[(left+right)/2];
    while(left <= right){
      while(arr[left] < pivot) left++;
      while(arr[right] > pivot) right--;

      if(left <= right){
        swap(arr, left, right);
        left++;
        right--;
      }
    }
    return left;
  }

  private void swap(int[] arr, int left, int right){
    int temp = arr[left];
    arr[left] = arr[right];
    arr[right] = temp;
  }

  private void mergeSort(int[] arr, int[] helper, int low, int high){
    if(low < high){
      int middle = (low + high)/2;
      mergeSort(arr, helper, low, middle);
      mergeSort(arr, helper, middle+1, high);
      merge(arr, helper, low, high);
    }
  }

  private void merge(int[] arr, int[] helper, int low, int high){
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

    int[] arrO = new int[]{4,2,3,4};
    sorter.quickSort(arrO);
    for(int i = 0; i < arrO.length; i++){
      System.out.print(arrO[i]);
    }
    System.out.println();
  }
}