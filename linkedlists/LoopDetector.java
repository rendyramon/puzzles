public class LoopDetector{
  public static class Node {
    public Node next = null;
    public int data;

    public Node(int data){
      this.data = data;
    }

    public void print(){
      System.out.print("--->" + data);
      if(next != null)
        next.print();
      else
        System.out.println();
    }
  }

  public Node findLoop(Node head){
    Node rabbit = head.next.next;
    Node tortoise = head;

    while(rabbit != tortoise) {
      rabbit = rabbit.next.next;
      tortoise = tortoise.next;
    }

    return rabbit;
  }

  public static void main(String[] args){
    Node node = new Node(7);
    node.next = new Node(1);
    node.next.next = new Node(6);
    node.next.next.next = new Node(17);
    node.next.next.next.next = node.next.next;

    LoopDetector loopDetector = new LoopDetector();
    Node loop = loopDetector.findLoop(node);
    System.out.println(loop.data);
  }
}