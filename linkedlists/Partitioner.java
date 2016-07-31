public class Partitioner{
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

  public Node partition(Node node, int value){
    boolean encounteredVal = false;
    Node head = node;
    if(head.data == value)
      encounteredVal = true;

    while(head.next != null){
      if(encounteredVal && head.next.data < value) {
        // move smaller element to the begining
        Node next = head.next;
        head.next = head.next.next;
        next.next = node;
        node = next;
      } else{
        if(head.next.data == value)
          encounteredVal = true;
        head = head.next;
      }
    }

    return node;
  }

  public static void main(String[] args){
    Node node = new Node(1);
    node.next = new Node(5);
    node.next.next = new Node(3);
    node.next.next.next = new Node(4);
    node.next.next.next.next = new Node(6);  

    node.print();

    node = new Partitioner().partition(node, 5);

    node.print(); 
  }
}