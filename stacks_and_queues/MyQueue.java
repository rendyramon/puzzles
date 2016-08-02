public class MyQueue<T>{
  public static class Stack<T>{
    public static class StackEmptyException extends Exception{
      public StackEmptyException(){
        super();
      }
    }

    public static class StackNode<T>{
      public T item;
      public StackNode<T> next;
      public StackNode(T item){
        this.item = item;
      }
    }

    private StackNode<T> top;

    public void push(T item){
      StackNode<T> node = new StackNode<>(item);
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
      StackNode<T> lastTop = top;
      top = top.next;
      return lastTop.item;
    }

    public boolean isEmpty(){
      return top==null;
    }
 
    public T peek() throws Exception {
      if(isEmpty()) throw new StackEmptyException();
      return top.item;
    }

  }

  public static class QueueEmptyException extends Exception {
    public QueueEmptyException(){
      super();
    }
  }

  private Stack<T> addStack;
  private Stack<T> removeStack;

  public MyQueue(){
    addStack = new Stack<>();
    removeStack = new Stack<>();
  }

  public void add(T item){
    addStack.push(item);
  }

  private void loadRemoveStack() throws Exception{
    while(!addStack.isEmpty()){
      removeStack.push(addStack.pop());
    }
  }

  public T remove() throws Exception{
    if(removeStack.isEmpty())
      loadRemoveStack();
    if(isEmpty()) throw new QueueEmptyException();

    return removeStack.pop();
  }

  public T peek() throws Exception{
    if(removeStack.isEmpty())
      loadRemoveStack();
    if(isEmpty()) throw new QueueEmptyException();

    return removeStack.peek();
  }

  public boolean isEmpty(){
    return removeStack.isEmpty() && addStack.isEmpty();
  }

  public static void main(String[] args){
    MyQueue<Integer> myQueue = new MyQueue<>();
    myQueue.add(1);
    myQueue.add(2);
    myQueue.add(3);
    myQueue.add(4);

    try{
    System.out.println(myQueue.remove());
    System.out.println(myQueue.remove());
    System.out.println(myQueue.remove());
    System.out.println(myQueue.remove());
    } catch(Exception e){
      System.out.println(e);
    }
  }
}