import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.*;
public class PathSums{
  public static class TreeNode{

    public static class TreeNodePair{
      public TreeNode head;
      public TreeNode child;
      
      public TreeNodePair(TreeNode head, TreeNode child){
        this.head = head;
        this.child = child;
      }

      private int getPathSum(){
        List<TreeNode> path = getPath();
        int sum = 0;
        for(TreeNode node : path)
          sum += node.item;
        return sum;
      }

      private List<TreeNode> getPath(){
        List<TreeNode> nodeList = new ArrayList<>();
        TreeNode currentNode = child;
        while(currentNode != head.parent){
          nodeList.add(currentNode);
          currentNode = currentNode.parent;
        }
        return nodeList;
      }
    }

    public int item;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;

    public TreeNode(int item){
      this.item = item;
    }


    public TreeNode(int item, TreeNode parent){
      this.item = item;
      this.parent = parent;
    }

    public void insert(int item){
      if(this.item >= item){
        if(left == null) left = new TreeNode(item, this);
        else left.insert(item);
      } else{
        if(right == null) right = new TreeNode(item, this);
        else right.insert(item); 
      }
    }

    public int pathSumCount(int sum){
      List<TreeNodePair> pairs = getPairs();
      List<Integer> sums = pairs.stream()
                            .map(pair->pair.getPathSum())
                            .filter(aSum-> aSum == sum)
                            .collect(Collectors.toList());
      return sums.size();
    }

    private List<TreeNodePair> getPairs(){
      List<TreeNode> nodeList = dfs();
      List<TreeNodePair> nodePairs = new ArrayList<>();
      for(int i = 0; i < nodeList.size(); i++)
        for(int j = i; j < nodeList.size(); j++)
          nodePairs.add(new TreeNodePair(nodeList.get(i), nodeList.get(j)));
      return nodePairs;
    }

    public List<TreeNode> dfs(){
      Queue<TreeNode> nodeQueue = new ConcurrentLinkedQueue();
      List<TreeNode> nodeList = new ArrayList<>();
      nodeQueue.add(this);
      while(!nodeQueue.isEmpty()){
        TreeNode curr = nodeQueue.remove();
        nodeList.add(curr);
        if(curr.left != null) nodeQueue.add(curr.left);
        if(curr.right != null) nodeQueue.add(curr.right);
      }
      return nodeList;
    }
  }

  public static void main(String[] args){
   TreeNode root = new TreeNode(10);
    root.insert(7);
    root.insert(15);
    root.insert(17);
    root.insert(14);
    root.insert(13);
    root.insert(9);

    System.out.println(root.pathSumCount(10));
  }
}