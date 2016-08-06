import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RouteBetweenNodes<T>{
  public T item;
  // map of children to visited
  public List<RouteBetweenNodes<T>> children;
  public boolean visitedOne;
  public boolean visitedTwo;

  public RouteBetweenNodes(T item){
    this.item = item;
    children = new ArrayList<>();
  }

  public boolean isPath(RouteBetweenNodes<T> firstNode, RouteBetweenNodes<T> secondNode){
    if(firstNode == null || secondNode == null) return false;
    Queue<RouteBetweenNodes<T>> firstQueue = new ConcurrentLinkedQueue<>();
    Queue<RouteBetweenNodes<T>> secondQueue = new ConcurrentLinkedQueue<>();

    firstQueue.add(firstNode);
    secondQueue.add(secondNode);
    while(!firstQueue.isEmpty() && !secondQueue.isEmpty()){
      RouteBetweenNodes<T> headOne = firstQueue.remove();
      RouteBetweenNodes<T> headTwo = secondQueue.remove();

      headOne.visitedOne = true;
      headTwo.visitedTwo = true;
      if(headOne.visitedTwo)
        return true;
      if(headTwo.visitedOne)
        return true;

      for(RouteBetweenNodes<T> child : headOne.children)
        if(!child.visitedOne){
          child.visitedOne = true;
          firstQueue.add(child);
        }
      for(RouteBetweenNodes<T> child : headTwo.children)
        if(!child.visitedTwo){
          child.visitedTwo = true;
          secondQueue.add(child);
        }
    }
    return false;
  }

  public static void main(String[] args){
    RouteBetweenNodes<Integer> graph = new RouteBetweenNodes<>(1);
    RouteBetweenNodes<Integer> graph2 = new RouteBetweenNodes<>(2);
    RouteBetweenNodes<Integer> graph3 = new RouteBetweenNodes<>(3);

    graph2.children.add(graph3);
    graph2.children.add(graph);
    graph3.children.add(graph2);
    graph.children.add(graph2);

    System.out.println(graph.isPath(graph, graph3));
  }

}