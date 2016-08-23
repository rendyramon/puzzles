public class ProductExceptSelf{
  public int[] findProduct(int[] arr){
    int[] newArr = new int[arr.length];
    int productSoFar = 1;
    for(int i = 0; i < arr.length; i++){
      newArr[i] = productSoFar;
      productSoFar *= arr[i];
    }

    productSoFar = 1;
    for(int i = arr.length-1; i >= 0; i--){
      newArr[i] *= productSoFar;
      productSoFar *= arr[i];
    }
    return newArr;
  }

  public static void main(String[] args){
    int[] arr = new int[]{2,3,4,1,3};
    int[] newArr = new ProductExceptSelf().findProduct(arr);
    for(int i = 0; i < arr.length; i++)
      System.out.printf("%d ", newArr[i]);
    System.out.println();
  }
}