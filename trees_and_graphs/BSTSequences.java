import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BSTSequences{
  public static class TreeNode{
    public int item;
    public TreeNode right;
    public TreeNode left;

    public TreeNode(int item){
      this.item = item;
    }

    public String toString(){
      return "" + item;
    }

    public void insert(int item){
      if(this.item >= item){
        if(left == null) left = new TreeNode(item);
        else left.insert(item);
      } else{
        if(right == null) right = new TreeNode(item);
        else right.insert(item);
      }
    }

    public List<List<TreeNode>> getBSTSequences(){
      int treeSize = size();
      List<List<TreeNode>> results = new ArrayList<List<TreeNode>>();
      List<TreeNode> result = new ArrayList<TreeNode>();
      Stack<TreeNode> orderStack = new Stack<TreeNode>();
      orderStack.push(this);
      getBSTSequencesStep(results, result, orderStack, treeSize, resultsSize);
      return results;
    }

    public void getBSTSequencesStep(List<List<TreeNode>> results,
                                    List<TreeNode> result,
                                    Stack<TreeNode> orderStack,
                                    int treeSize){

      while(!orderStack.isEmpty()){
        TreeNode head = orderStack.pop();
        result.add(head);
        if(head.left != null){
          List<TreeNode> newSequence = new ArrayList<TreeNode>(result);
          orderStack.push(head.left);
          if(head.right != null)
            orderStack.push(head.right);
          getBSTSequencesStep(results, newSequence, orderStack, treeSize, resultsSize);
        }

        if(head.right != null){
          List<TreeNode> newSequence = new ArrayList<TreeNode>(result);
          orderStack.push(head.right);
          if(head.left != null)
            orderStack.push(head.left);
          getBSTSequencesStep(results, newSequence, orderStack, treeSize, resultsSize);
        }
      }
      if(result.size() == treeSize){
        results.add(result);
        return;
      }
    }

    private int size(){
      Queue<TreeNode> nodeQueue = new ConcurrentLinkedQueue();
      int size = 0;
      nodeQueue.add(this);
      while(!nodeQueue.isEmpty()){
        size++;
        TreeNode head = nodeQueue.remove();
        if(head.left != null) nodeQueue.add(head.left);
        if(head.right != null) nodeQueue.add(head.right);
      }
      return size;
    }
  }

  public static void main(String[] args){
    TreeNode root = new TreeNode(10);
    root.insert(7);
    root.insert(15);
    System.out.println(root.getBSTSequences());
    root.insert(13);
    root.insert(12);
    root.insert(11);
    root.insert(14);
    System.out.println(root.getBSTSequences());
  }
}