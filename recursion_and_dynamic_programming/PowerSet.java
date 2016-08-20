import java.util.*;

public class PowerSet{
  public Set<Set<Integer>> getPowerSet(Set<Integer> elements){
    Set<Set<Integer>> powerSet = new HashSet<>();
    powerSet.add(elements);
    getPowerSet(elements, powerSet);
    return powerSet;
  }

  private void getPowerSet(Set<Integer> elements, Set<Set<Integer>> powerSet){
    Iterator setIter = elements.iterator();
    while(setIter.hasNext()){
      int element = (int)setIter.next();
      
      Set<Integer> newElements = new HashSet<>(elements);
      newElements.remove(element);
      powerSet.add(newElements);

      getPowerSet(newElements, powerSet);
    }
  }

  public static void main(String[] args){
    PowerSet powerSet = new PowerSet();
    System.out.println(powerSet.getPowerSet(new HashSet<Integer>(){{
      add(1);
      add(2);
      add(3);
      add(4);
    }}));
  }
}