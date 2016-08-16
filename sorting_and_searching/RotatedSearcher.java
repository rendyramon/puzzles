public class RotatedSearcher{
  public int searchRotated(int[] arr, int elem){
    int start = 0;
    while(start < arr.length){
      int last = start;
      while(last < arr.length-1 && arr[last] <= arr[last+1]) last++;
      // binary search
      int position = binarySearch(arr, start, last, elem);
      if(position != -1) return position;
      start = last +1;
    }

    return -1;
  }

  private int binarySearch(int[] arr, int left, int right, int elem){
    while(left <= right){
      int middle = (left + right)/2;
      if(elem > arr[middle]){
        left = middle + 1;
      } else if(elem < arr[middle]){
        right = middle -1;
      } else
        return middle;
    }

    return -1;
  }

  public static void main(String[] args){
    RotatedSearcher rotatedSearcher = new RotatedSearcher();
    System.out.println(rotatedSearcher.searchRotated(new int[]{1,2,3,4,-4,-3,-2}, -3));
  }
}