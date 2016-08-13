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
      List<TreeNode> validNext = new ArrayList<>();
      validNext.add(this);
      getBSTSequencesStep(results, result, validNext, treeSize);
      return results;
    }

    public void getBSTSequencesStep(List<List<TreeNode>> results,
                                    List<TreeNode> result,
                                    List<TreeNode> validNext,
                                    int treeSize){

      if(result.size() == treeSize){
        results.add(result);
        return;
      }

      for(int i = 0; i < validNext.size(); i++){
        TreeNode head = validNext.get(i);
        validNext.remove(i);
        List<TreeNode> newSequence = new ArrayList<>(result);
        List<TreeNode> newValidNext = new ArrayList<>(validNext);
        newSequence.add(head);
        if(head.left != null)
          newValidNext.add(head.left);
        if(head.right != null)
          newValidNext.add(head.right);
        getBSTSequencesStep(results, newSequence, newValidNext, treeSize);
        validNext.add(i, head);
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