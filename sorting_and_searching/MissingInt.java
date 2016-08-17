import java.util.*;

public class MissingInt{
  final BitSet tracker;

  public MissingInt(){
    tracker = new BitSet(Integer.MAX_VALUE);
  }

  /*********************************
  *      4 Billion Unsigned Ints
  *         1GB local Storage
  **********************************/

  // run for every chunk of the ints.
  public void loadInBitSet(int[] arr){  
    for(int i = 0; i < arr.length; i++){
      tracker.set(arr[i]);
    }
  }
  // loop again
  public int findMissingInt(){
    for(int i = 0; i < Integer.MAX_VALUE; i++)
      if(!tracker.get(i)) return i;
    return -1;
  }

}