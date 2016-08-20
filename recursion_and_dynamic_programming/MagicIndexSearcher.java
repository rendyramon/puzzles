public class MagicIndexSearcher{
  
  public int findMagixIndexDistinct(int[] arr){
    int left = 0;
    int right = arr.length-1;

    while(left <= right){
      int middle = (left + right)/2;
      if(arr[middle] > middle){
        right = middle-1;
      } else if(arr[middle] < middle){
        left = middle+1;
      } else{
        return middle;
      }
    }
    return -1;
  }

  public int findMagicIndexDuplicated(int[] arr){
    return magicSearchDups(arr, 0, arr.length-1);
  }

  private int magicSearchDups(int[] arr, int left, int right){
    while(left <= right){
      int middle = (left+right)/2;
      if(arr[middle] > middle){
        left = arr[middle];
      } else if(arr[middle] < middle){
        int belowMid = magicSearchDups(arr, 0, arr[middle]);
        if(belowMid != -1) return belowMid;
        left = middle +1;
      } else{
        return middle;
      }
    }
    return -1;
  }

  public static void main(String[] args){
    MagicIndexSearcher magicSearcher = new MagicIndexSearcher();
    System.out.println(magicSearcher.findMagixIndexDistinct(new int[]{-1,-2,-3,-4,4}));
    System.out.println(magicSearcher.findMagicIndexDuplicated(new int[]{-1,0,1,5,5,5}));

  }
}