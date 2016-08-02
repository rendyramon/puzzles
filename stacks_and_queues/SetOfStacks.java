import java.util.*;

public class SetOfStacks<T>{
  public static class StackEmptyException extends Exception{
    public StackEmptyException(){
      super();
    }
  }
  public static class StackDoesNotExistException extends Exception{
    public StackDoesNotExistException(){
      super();
    }
  }

  public static class StackNode<T>{
    public StackNode<T> next;
    public T item;
    
    public StackNode(T item){
      this.item = item;
    }
  }

  public static class Stack<T>{
    private StackNode<T> top;
    private int maxSize; // must be 1 or more.
    private int currentSize;
    
    public Stack(int maxSize){
      this.maxSize = maxSize;
      currentSize = 0;
    }

    public void push(T value){
      currentSize++;
      StackNode<T> node = new StackNode(value);
      if(top == null){
        top = node;
        return;
      }

      StackNode<T> lastTop = top;
      node.next = lastTop;
      top = node;
    }

    public T pop() throws Exception{
      if(isEmpty()) throw new StackEmptyException();
      currentSize--;

      StackNode<T> lastTop = top;
      top = top.next;
      return lastTop.item;
    }

    public boolean isEmpty(){
      return currentSize == 0;
    }

    public boolean isFull(){
      return currentSize == maxSize;
    }
  }

  private int currentStackIndex;
  private Map<Integer, Stack<T>> stacks;
  private int maxSize;
  private int total;

  public SetOfStacks(int maxSize){
    this.maxSize = maxSize;
    stacks = new HashMap<>();
    currentStackIndex = 0;
    total = 0;
    stacks.put(currentStackIndex, new Stack<T>(maxSize));
  }

  public void push(T value){
    Stack<T> currentStack = stacks.get(currentStackIndex);
    if(currentStack.isFull()){
      currentStackIndex++;
      stacks.put(currentStackIndex, new Stack<T>(maxSize));
      currentStack = stacks.get(currentStackIndex);
    }
    currentStack.push(value);
    total++;
  }

  public T pop() throws Exception{
    Stack<T> currentStack = stacks.get(currentStackIndex);
    
    while(currentStack.isEmpty() && currentStackIndex > 0){
      currentStackIndex--;
      currentStack = stacks.get(currentStackIndex);
    }

    if(currentStack.isEmpty())
      throw new StackEmptyException();
    total--;
    
    return currentStack.pop();
  }

  public T popAt(int stackNum) throws Exception{
    if(isEmpty()) throw new StackEmptyException();
    if(stackNum < 0 || stackNum > currentStackIndex || !stacks.containsKey(stackNum)) throw new StackDoesNotExistException();

    Stack<T> thatStack = stacks.get(stackNum);
    total--;

    return thatStack.pop();
  }

  public boolean isEmpty(){
    return total == 0;
  }

  public static void main(String[] args){
    SetOfStacks<Integer> setOfStacks = new SetOfStacks(3);
    setOfStacks.push(1);
    setOfStacks.push(2);
    setOfStacks.push(3);
    setOfStacks.push(4);

    try{
      System.out.println(setOfStacks.popAt(0));
      System.out.println(setOfStacks.pop());
      System.out.println(setOfStacks.pop());
    } catch(Exception e){
      System.out.println(e);
    }
  }
}