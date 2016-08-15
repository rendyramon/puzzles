import java.util.*;

public class Sum2OneLoop{
  public static int[] find2Sum(int[] arr, int limit){
    Map<Integer, Integer> positionMap = new HashMap<>();
    
    for(int i = 0; i < arr.length; i++){
       if(!positionMap.containsKey(arr[i]))
          positionMap.put(arr[i], i);
       if(positionMap.containsKey(limit-arr[i])){
          int complementIndex = positionMap.get(limit-arr[i]);
          if(limit-arr[i] != arr[i] || (limit-arr[i] == arr[i] && complementIndex != i))
            return new int[]{ complementIndex, i };
        }
    }
    
    return new int[]{-1,-1};
  }

  public static void main(String[] args){
    int[] resultOne = find2Sum(new int[]{1,2,3,4,5}, 6);
    int[] resultTwo = find2Sum(new int[]{1,1,1,4}, 5);
    int[] resultThree = find2Sum(new int[]{1,1,1,1,1}, 2);
   
    for(int elem : resultOne){
      System.out.print(elem);
    }
    System.out.println();

    for(int elem : resultTwo){
      System.out.print(elem);
    }
    System.out.println();

    for(int elem : resultThree){
      System.out.print(elem);
    }
    System.out.println();

  }
}