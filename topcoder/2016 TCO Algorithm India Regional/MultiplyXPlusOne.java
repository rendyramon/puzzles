import java.util.*;

public class MultiplyXPlusOne{

  Map<Integer, Integer> solutionMap;
  public MultiplyXPlusOne(){
    solutionMap = new HashMap<>();
  }
  public int minimalSteps(int cardNumber, int changedCardNumber){
    if(changedCardNumber < cardNumber) return -1;
    solutionMap.put(cardNumber, 0);

    for(int i = cardNumber; i < changedCardNumber; i++){
      int xTwoPlusOne = multiplyTwoXPlusOne(i);
      int xThreePlusOne = multiplyThreeXPlusOne(i);

      if(solutionMap.containsKey(i)){
        int previousSteps = solutionMap.get(i);
        if(previousSteps != -1){
          addToMap(xTwoPlusOne, previousSteps);
          addToMap(xThreePlusOne, previousSteps);
        }
      } else{
        solutionMap.put(xTwoPlusOne, -1);
        solutionMap.put(xThreePlusOne, -1);
      }
    }
    
    return solutionMap.get(changedCardNumber);
  }

  private void addToMap(int value, int previousSteps){
    if(solutionMap.containsKey(value)){
      int previousValue = solutionMap.get(value);
      if(value > previousSteps + 1)
        solutionMap.put(value, previousSteps +1);
    } else{
      solutionMap.put(value, previousSteps +1);
    }
  }

  private int multiplyTwoXPlusOne(int currentCardNumber){
    return 2 * currentCardNumber + 1;
  }

  private int multiplyThreeXPlusOne(int currentCardNumber){
    return 3 * currentCardNumber + 1;
  }

  public static void main(String[] args){
    MultiplyXPlusOne multiplyXPlusOne = new MultiplyXPlusOne();
    System.out.println(multiplyXPlusOne.minimalSteps(1,22));
    System.out.println(multiplyXPlusOne.minimalSteps(1,31));
    System.out.println(multiplyXPlusOne.minimalSteps(100,99));
    System.out.println(multiplyXPlusOne.minimalSteps(55555,1000000));
    System.out.println(multiplyXPlusOne.minimalSteps(1,1));
  }
}