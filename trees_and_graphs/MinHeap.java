import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MinHeap<T extends Comparable<T>>{
  public static class HeapNode<T>{
    public final T item;
    public HeapNode<T> left;
    public HeapNode<T> parent;
    public HeapNode<T> right;

    public HeapNode(T item){
      this.item = item;
    }
  }

  public HeapNode<T> min;
  public HeapNode<T> lastParent;

  public void insert(T item){
    HeapNode<T> newNode = new HeapNode<T>(item);
    if(min == null){
      min = newNode;
      lastParent= min;
    } else{
      if(lastParent.left == null){
        lastParent.left = newNode;
        newNode.parent = lastParent;
      } else if(lastParent.right == null){
        lastParent.right = newNode;
        newNode.parent = lastParent;
        setNewLast();
      }
      System.out.println(newNode.parent.item);
      bubbleUp(newNode);
    }
  }

  public T remove(){
    HeapNode<T> oldMin = min;
    HeapNode<T> last = lastParent;

    if(min == lastParent){
      lastParent = null;
      min = null;
      return oldMin.item;
    }

    // swap with last element
    if(lastParent.right != null)
      last = lastParent.right;
    else if(lastParent.left != null)
      last = lastParent.left;

    min = last;
    min.parent = null;
    min.left = oldMin.left;
    min.right = oldMin.right;

    // delete last element
    if(last.parent.right == last)
      last.parent.right = null;
    else
      last.parent.left = null;

    bubbleDown(min);
    return oldMin.item;
  }

  public void dfs(){
    Stack<HeapNode<T>> searchStack = new Stack<HeapNode<T>>();
    if(min != null) searchStack.push(min);
    while(!searchStack.isEmpty()){
      HeapNode<T> head = searchStack.pop();
      System.out.println(head.item);
      if(head.right != null) searchStack.push(head.right);
      if(head.left != null) searchStack.push(head.left);
    }
  }

  public void bfs(){
    Queue<HeapNode<T>> searchQueue = new ConcurrentLinkedQueue<HeapNode<T>>();
    if(min != null) searchQueue.add(min);
    while(!searchQueue.isEmpty()){
      HeapNode<T> head = searchQueue.remove();
      System.out.println(head.item);
      if(head.right != null) searchQueue.add(head.right);
      if(head.left != null) searchQueue.add(head.left);
    }
  }

  public void dfsRecursive(HeapNode<T> node){
    if(node == null) return;
    System.out.println(node.item);
    dfsRecursive(node.left);
    dfsRecursive(node.right); 
  }

  public void bfsRecursie(HeapNode<T> node)

  /*
                 []
              /     \
            []         []
          /   \      /   \
        []    []    []     []
      /   \   / \   / \   / \
     []   [] [] [] [] []  [] []

  */
  private void setNewLast(){
    // lastParent is now node to the right or next level
    HeapNode<T> newLastParent = lastParent.parent;
    if(newLastParent == null){
      lastParent= lastParent.left;
      return;
    }

    while(newLastParent.parent != null && newLastParent.parent.right == newLastParent){
      newLastParent = newLastParent.parent;
    }
    if(newLastParent.right.left == null)
      lastParent= newLastParent.right;
    else{
      lastParent = min;
      while(lastParent.left != null){
        lastParent= lastParent.left;
      }
    }
  }

  private void bubbleDown(HeapNode<T> node){
    if(node.left == null && node.right == null) {
    }
    else if(node.left == null)
      if(node.item.compareTo(node.right.item) > 0)
        swapR(node);
      else if(node.right == null && node.left != null)
        if(node.item.compareTo(node.left.item) > 0){
          swapL(node);
          bubbleDown(node.right);
        }
        else if(node.right.item.compareTo(node.left.item) >= 0){
          swapL(node);
          bubbleDown(node.left);
        }
        else
          swapR(node);
  }

  private void swapR(HeapNode<T> node){
    HeapNode<T> lastHead = node;
    HeapNode<T> lastRight = node.right;

    node = lastRight;
    node.parent = lastHead.parent;
    node.left = lastHead.left;

    node.right = lastHead;
    node.right.left = lastRight.left;
    node.right.right = lastRight.right;
    node.right.parent = node;

    if(node.parent == null){
      min = node;
    }
  }

  private void swapL(HeapNode<T> node){
    HeapNode<T> lastHead = node;
    HeapNode<T> lastLeft = node.left;

    node = lastLeft;
    node.parent = lastHead.parent;
    node.right = lastHead.right;

    node.left = lastHead;
    node.left.left = lastLeft.left.left;
    node.left.right = lastLeft.right;
    node.left.parent = node;


    if(node.parent == null){
      min = node;
    }
  }

  private void bubbleUp(HeapNode<T> node){
    if(node.parent.item.compareTo(node.item) <= 0) return;
    HeapNode<T> currentParent = node.parent;

    while(currentParent != null && currentParent.item.compareTo(node.item) > 0){
      if(currentParent.left.item.compareTo(node.item) == 0){
        swapL(currentParent);
      }
      else{
        swapR(currentParent);
      }
      currentParent = currentParent.parent;
    }
  }

  public static void main(String[] args){
    MinHeap<Integer> minHeap = new MinHeap<Integer>();
    minHeap.insert(10);
    minHeap.insert(9);
    minHeap.insert(8);
    minHeap.insert(7);
    minHeap.insert(6);
    minHeap.insert(5);
    minHeap.insert(4);
    minHeap.insert(3);
    minHeap.insert(2);

    System.out.println(minHeap.remove());
  }
}