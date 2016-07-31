public class ListSum {
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

  public Node sumList(Node listOne, Node listTwo) {
    int listOneLength = 0;
    int listTwoLength = 0;

    Node headOne = listOne;
    Node headTwo = listTwo;
    // find larger

    while(headOne != null) {
      headOne = headOne.next;
      listOneLength++;
    }

    while(headTwo != null) {
      headTwo = headTwo.next;
      listTwoLength++;
    }
    // loop over larger adding in smaller with reminder calculation
    int reminder = 0;

    Node longerNumber = listOneLength > listTwoLength? listOne : listTwo;    
    Node resultHead = longerNumber;

    while(listOne != null && listTwo != null || reminder != 0) {
      int firstValue = listOne == null? 0 : listOne.data;
      int secondValue = listTwo == null? 0 : listTwo.data;

      int currentSum = firstValue + secondValue + reminder;

      if(currentSum >= 10) {
        reminder = currentSum / 10;
        currentSum = currentSum % 10;
      } else {
        reminder = 0;
      } 
      longerNumber.data = currentSum;
      if(longerNumber.next == null && reminder > 0) {
        longerNumber.next = new Node(reminder);
        reminder = 0;
      }

      longerNumber = longerNumber.next;
      if(listOne != null)
        listOne = listOne.next;
      if(listTwo != null)  
        listTwo = listTwo.next;
    }

    return resultHead;
  }

  public static void main(String[] args) {
    Node node = new Node(7);
    node.next = new Node(1);
    node.next.next = new Node(6);

    Node two = new Node(5);
    two.next = new Node(9);
    two.next.next = new Node(2);  

    ListSum listSum = new ListSum();
    Node result = listSum.sumList(node, two);
    result.print();
  }
}