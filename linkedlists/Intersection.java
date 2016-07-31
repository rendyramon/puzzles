import java.util.*;
public class Intersection{
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
 
  public Node find(Node headOne, Node headTwo){
    // add all elements to the stacks
    Stack<Node> stackOne = new Stack();
    Stack<Node> stackTwo = new Stack();
    
    Node nodeOne = headOne;
    while(nodeOne != null) {
      stackOne.push(nodeOne);
      nodeOne = nodeOne.next;
    }

    Node nodeTwo = headTwo;
    while(nodeTwo != null) {
      stackTwo.push(nodeTwo);
      nodeTwo = nodeTwo.next;
    }
    
    // if they are never equal return null.
    nodeOne = stackOne.pop();
    nodeTwo = stackTwo.pop();

    if(nodeOne != nodeTwo) return null;
    // find node when they stop being equal
    while(nodeOne == nodeTwo) {
      Node currentOne;
      Node currentTwo;

      // If it throws, lists intersect at first element of list
      try{
        currentOne = stackOne.pop();  
      } catch(EmptyStackException exp){
        return nodeOne;
      }
      
      try{
        currentTwo = stackTwo.pop();  
      } catch(EmptyStackException exp){
        return nodeTwo;
      }

      if(currentOne == currentTwo){
        nodeOne = currentOne;
        nodeTwo = currentTwo;
      } else{
        return nodeOne;
      }
    }
    // Error case
    return null;
  }

  public static void main(String[] args){
    Node node = new Node(1);
    node.next = new Node(1);
    node.next.next = new Node(1);

    Node two = new Node(2);
    two.next = node.next;

    Intersection intersect = new Intersection();
    Node result = intersect.find(node, two);
    result.print();
  }
}