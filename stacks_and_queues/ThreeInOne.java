import java.util.*;

public class ThreeInOne{
  private final Map<Integer, Integer> sizeMap = new HashMap(){{
    put(1 , -1);
    put(2 , -1);
    put(3 , -1);
  }};

  private final List<Integer> stackArr;
  private final int size;

  public ThreeInOne(int size) {
    stackArr = new ArrayList(Collections.nCopies(size*3, 0));
    this.size = size;
  }

  public static class InvalidStackNumberException extends Exception {
    public InvalidStackNumberException(String message){
      super(message);
    }
  }

  public static class StackFullException extends Exception {
    public StackFullException(String message){
      super(message);
    }
  }

  public static class StackEmptyException extends Exception {
    public StackEmptyException(String message){
      super(message);
    }
  }

  public void push(int value, int stackNum) throws Exception {
    if(stackNum > 3 || stackNum < 1) throw new InvalidStackNumberException("Invalid stack number.");
    int maxIndex = stackNum * size;
    int stackSize = sizeMap.get(stackNum);
    int currentIndex = (stackNum-1) * size + stackSize;

    if(currentIndex < maxIndex-1){
      currentIndex++;
      stackArr.add(currentIndex, value);
      sizeMap.put(stackNum, stackSize + 1);
    } else {
      throw new StackFullException("Stack is full.");
    }
  }

  public Integer pop(int stackNum) throws Exception {
    if(stackNum > 3 || stackNum < 1) throw new InvalidStackNumberException("Invalid stack number.");
    if( isEmpty(stackNum) ) throw new StackEmptyException("Stack is empty");

    int stackSize = sizeMap.get(stackNum);
    int currentIndex = (stackNum-1) * size + stackSize;
    int maxIndex = stackNum * size;

    int result = stackArr.remove(currentIndex);
    sizeMap.put(stackNum, stackSize - 1);
    return result;
  }

  public Integer peek(int stackNum) throws Exception{
    if(isEmpty(stackNum)) throw new StackEmptyException("Stack is empty");
    
    int currentIndex = (stackNum-1) * size + sizeMap.get(stackNum);
    int maxIndex = stackNum * size;
    
    return stackArr.get(currentIndex); 
  }

  public boolean isEmpty(int stackNum) {
    return sizeMap.get(stackNum) == -1;
  }

  public void print(){
    System.out.println(stackArr);
  }

  public static void main(String[] args){
    ThreeInOne threeInOne = new ThreeInOne(5);
    try{
      threeInOne.push(0,1);
      threeInOne.push(1,1);
      threeInOne.push(2,1);
      threeInOne.push(3,1);
      threeInOne.push(4,1);
      threeInOne.push(5,1);
    } catch(Exception excp){
      System.out.println(excp);
    }
    threeInOne.print();

    try{
      System.out.println(threeInOne.peek(1));
      System.out.println(threeInOne.peek(2));
    } catch(Exception excp){
      System.out.println(excp);
    }

    try{
      System.out.println(threeInOne.pop(1));
    } catch(Exception excp){
      System.out.println(excp);
    }
    threeInOne.print();
  }
}