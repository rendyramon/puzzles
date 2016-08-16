public class Searcher{
  public int binarySearch(int[] arr, int elem){
    int left = 0;
    int right = arr.length-1;
    int middle;

    while(left <= right){
      middle = (left+right)/2;
      if(elem > arr[middle]){
        left = middle+1;
      } else if (elem < arr[middle]){
        right = middle-1;
      } else{
        return middle;
      }
    }
    return -1;
  }
  
  public int binarySearchRecursive(int[] arr, int elem){
    int middle = (arr.length-1)/2;
    if(arr[middle] > elem)
      return binarySearchRecursive(arr, elem, 0, middle-1);
    else if(arr[middle] < elem)
      return binarySearchRecursive(arr, elem, middle+1, arr.length-1);
    else
      return middle;
  }

  private int binarySearchRecursive(int[] arr, int elem, int left, int right){
    if(left > right) return -1;
    int middle = (right + left)/2;
    if(arr[middle] > elem)
      return binarySearchRecursive(arr, elem, left, middle-1);
    else if(arr[middle] < elem)
      return binarySearchRecursive(arr, elem, middle+1, right);
    else 
      return middle;
  }

  public static void main(String[] args){
    Searcher searcher = new Searcher();
    System.out.println(searcher.binarySearch(new int[]{1,2,3,4}, 3));
    System.out.println(searcher.binarySearch(new int[]{1}, 3));

    System.out.println(searcher.binarySearchRecursive(new int[]{1,2,3,4}, 3));
    System.out.println(searcher.binarySearchRecursive(new int[]{1}, 3));
  }
}