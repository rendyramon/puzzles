import java.util.*;
public class DupRemover{

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

  public Node deleteDup(Node node){
    if(node == null) return node;
    Set<Integer> occuranceSet = new HashSet<>();
    Node head = node;
    occuranceSet.add(head.data);

    while(head.next != null) {
      if(occuranceSet.contains(head.next.data)){
        head.next = head.next.next;
      } else {
        occuranceSet.add(head.next.data);
        head = head.next;
      }
    }

    return node;
  }

  public static void main(String[] args){
    DupRemover dupRemover = new DupRemover();

    Node node = new Node(1);
    node.next = new Node(1);
    node.next.next = new Node(3);
    node.next.next.next = new Node(4);

    node.print();

    node = dupRemover.deleteDup(node);
    node.print();
  }
}