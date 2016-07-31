import java.util.*;

public class Palindrome {
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

  public boolean isPalindrome(Node head){
    // find length
    Node node = head;
    int length = 0;
    while(node != null){
      length++;
      node = node.next;
    }
      
    // store half in and array
    node = head;
    List<Integer> dataList = new ArrayList<>();
    int currentIndex = 0;
    while(currentIndex < length/2){
      dataList.add(node.data);
      node = node.next;
      currentIndex++;
    }
    if(length % 2 == 1) node = node.next;

    // check if it matches the other half
    currentIndex--;
    while(node != null && currentIndex >= 0) {
      if(node.data != dataList.get(currentIndex))
        return false;
      node = node.next;
      currentIndex--;
    }
    // make sure there are no elements remaining and the string is symmetrical
    return node == null && currentIndex == -1;
  }

  public static void main(String[] args){
    Node node = new Node(1);
    node.next = new Node(1);
    node.next.next = new Node(1);

    Node two = new Node(1);
    two.next = new Node(2);
    two.next.next = new Node(1);  

    Palindrome palindrome = new Palindrome();
    System.out.println(palindrome.isPalindrome(node));
    System.out.println(palindrome.isPalindrome(two));
  }
}