public class KthFinder{
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

  public int kthToLast(Node node, int k){
    // find length
    Node head = node;
    int length = 0;
    while(head.next != null){
      length++;
      head = head.next;
    }
    // remove kth
    head = node;
    while(length > k){
      head = head.next;
      length--;
    }
    return head.data;
  }

  public static void main(String[] args){
    Node node = new Node(1);
    node.next = new Node(1);
    node.next.next = new Node(3);
    node.next.next.next = new Node(4);

    node.print();

    System.out.println(new KthFinder().kthToLast(node, 1));
  }
}