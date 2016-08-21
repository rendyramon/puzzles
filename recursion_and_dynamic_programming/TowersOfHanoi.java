import java.util.*;

public class TowersOfHanoi{
  public void moveStacks(int n, Stack<Integer> origin, Stack<Integer> destination,
                                                       Stack<Integer> buffer){
    if(n <= 0) return;
    moveStacks(n-1, origin, buffer, destination);
    destination.push(origin.pop());
    moveStacks(n-1, buffer, destination, origin);
  }

  public static void main(String[] args){
    TowersOfHanoi hanoi = new TowersOfHanoi();
    Stack<Integer> stackOne = new Stack<>();
    Stack<Integer> stackTwo = new Stack<>();
    Stack<Integer> stackThree = new Stack<>();

    for(int i = 0; i < 10; i++)
      stackOne.push(i);
    hanoi.moveStacks(10, stackOne, stackThree, stackTwo);
    System.out.println(stackThree);
  }
}