public class DeleteMiddle{
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

  public void deleteMiddle(Node node){
    Node head = node;
    while(head != null && head.next != null) {
      head.data = head.next.data;

      // if next element is last element, remove it after copy.
      if(head.next.next == null)
        head.next = null;
      head = head.next;
    }
  }

  public static void main(String[] args){
    Node node = new Node(1);
    node.next = new Node(1);
    node.next.next = new Node(3);
    node.next.next.next = new Node(4);

    node.print();
    new DeleteMiddle().deleteMiddle(node.next);
    node.print();
  }
}