import java.util.*;
import java.math.BigInteger;

public class TripleStep{
  Map<Integer, BigInteger> tripleStepMem;
  public TripleStep(){
    tripleStepMem = new HashMap<>();
    tripleStepMem.put(0,BigInteger.ONE);
  }

  public BigInteger tripleStep(int steps){
    if(steps < 0) return BigInteger.ZERO;

    if(tripleStepMem.containsKey(steps)) return tripleStepMem.get(steps);

    tripleStepMem.put(steps, tripleStep(steps -1)
                                .add(tripleStep(steps-2)
                                  .add(tripleStep(steps-3))));
    return tripleStepMem.get(steps);
  }

  public static void main(String[] args){
    System.out.println(new TripleStep().tripleStep(3));
    System.out.println(new TripleStep().tripleStep(6));
    System.out.println(new TripleStep().tripleStep(12));
    System.out.println(new TripleStep().tripleStep(16));
    System.out.println(new TripleStep().tripleStep(100));
  }
}