import java.util.*;

public class FindDuplicatesInOneKiloByte{
  private final int n = 32000;
  private final BitSet tracker;

  public FindDuplicatesInOneKiloByte(){
    tracker = new BitSet(n);
  }

  public void loadElements(int[] arr){
    for(int elem : arr){
      if(!tracker.get(elem))
        tracker.set(elem);
      else
        System.out.print(elem + " ");
    }
    System.out.println();
  }
}