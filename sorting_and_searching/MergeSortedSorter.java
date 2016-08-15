public class MergeSortedSorter{
  
  public void mergeSorted(int[] a, int[] b, int aLength){
    int current = aLength + b.length -1;
    int aPointer = aLength -1;
    int bPointer = b.length-1;

    while(bPointer >= 0 && aPointer >= 0){
      if(a[aPointer] > b[bPointer]){
        a[current] = a[aPointer];
        aPointer--;
      } else{
        a[current] = b[bPointer];
        bPointer--;
      }
      current--;
    }

    for(int i = bPointer; i >= 0; i--){
      a[current] = b[i];
      current--;
    }
  }

  public static void main(String[] args){
    MergeSortedSorter mergeSortedSorter = new MergeSortedSorter();
    int[] a = new int[]{4,5,6,0,0,0};
    int[] b = new int[]{1,2,3};
    mergeSortedSorter.mergeSorted(a,b,3);
    for(int i = 0; i < a.length; i++){
      System.out.print(a[i]);
    }
    System.out.println();
  }
}