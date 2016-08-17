public class SparseSearcher{
  
  public int sparseSearch(String[] arr, String elem){
    int left= 0;
    int right = arr.length-1;

    while(left <= right){
      int middle = (left + right)/2;
      int lastMiddle = middle;
      while(middle >= left && (arr[middle].compareTo("") == 0)){
        middle--;
      }
      int comparasion = arr[middle].compareTo(elem); 
      if(middle < left || comparasion < 0){
        left = lastMiddle+1;
      } else if(comparasion > 0){
        right = middle -1;
      } else {
        return middle;
      }
    }

    return -1;
  }
  public static void main(String[] args){
    SparseSearcher sparseSearcher = new SparseSearcher();
    System.out.println(sparseSearcher.sparseSearch(new String[]{"baba", "", "mama"}, "mama"));
  }
}