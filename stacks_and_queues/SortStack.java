public class SortStack{
  public static class StackEmptyException extends Exception{
    public StackEmptyException(){
      super();
    }
  }

  public static class StackNode{
    public int item;
    public StackNode next;
    public StackNode(int item){
      this.item = item;
    }
  }

  private StackNode top;

  public void push(int item){
    StackNode node = new StackNode(item);
    if(top == null){
      top = node;
      return;
    }

    StackNode lastTop = top;
    node.next = lastTop;
    top = node;
  }

  public int pop() throws Exception{
    if(isEmpty()) throw new StackEmptyException();
    StackNode lastTop = top;
    top = top.next;
    return lastTop.item;
  }

  public boolean isEmpty(){
    return top==null;
  }

  public int peek() throws Exception {
    if(isEmpty()) throw new StackEmptyException();
    return top.item;
  }

  private int getLength(){
    int length = 0;
    StackNode node = top;
    while(node != null){
      length++;
      node = node.next;
    }
    return length;
  }

  private int moveToTemp(SortStack tempStack, int length) throws Exception{
    Integer min = pop();
    while(length > 1){
      int item = pop();
      if(min > item){
        tempStack.push(min);
        min = item;
      } else{
        tempStack.push(item);
      }
      length--;
    }

    return min;
  }

  private int moveFromTemp(SortStack tempStack, int length) throws Exception{
    Integer max = tempStack.pop();
    while(length > 1){
      int item = tempStack.pop();
      if(item > max){
        push(max);
        max = item;
      } else{
        push(item);
      }
      length--;
    }
    
    return max;
  }

  public void sort() throws Exception{
    if(top == null) return;
    // move elements between stacks till they are sorted
    SortStack tempStack = new SortStack();
    int length = getLength();
    for(int i = 0; i < length/2; i++){
      int min = moveToTemp(tempStack, length - i*2);
      push(min);
      int max = moveFromTemp(tempStack, length - i*2-1);
      tempStack.push(max);
    }

    while(!tempStack.isEmpty()){
      push(tempStack.pop());
    }
  }

  public static void main(String[] args){
    SortStack sortStack = new SortStack();
    sortStack.push(1);
    sortStack.push(3);
    sortStack.push(4);
    sortStack.push(0);
    try{
      sortStack.sort();

      System.out.println(sortStack.pop());
      System.out.println(sortStack.pop());
      System.out.println(sortStack.pop());
      System.out.println(sortStack.pop());
    }catch(Exception exp){
      System.out.println();
    }
  }
}