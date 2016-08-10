public class MaximumSubArray{
  public void printIndexes(int[] arr){
    int maxSoFar = 0;
    int currentMax = 0;

    int minIndex = 0;
    int maxIndex = 0;
    for(int i = 0; i < arr.length; i++){
      currentMax += arr[i];
      if(currentMax < 0){
        currentMax = 0;
        minIndex = i;
      }
      if(maxSoFar < currentMax){
        maxSoFar = currentMax;
        maxIndex = i;
      }
    }
    System.out.println(minIndex + "<...>" + maxIndex);
  }

  public static void main(String[] args){
    MaximumSubArray maxSub = new MaximumSubArray();
    maxSub.printIndexes(new int[]{0,1,0,-2,3,5,6,7});
  }
}