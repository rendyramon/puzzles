import java.util.*;

public class DepthLists{
  public static class TreeNode{
    public int item;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int item){
      this.item = item;
    }

    public void insert(int item){
      if(item <= this.item){
        if(left == null) left = new TreeNode(item);
        else left.insert(item);
      } else{
        if(right == null) right = new TreeNode(item);
        else right.insert(item);
      }
    }

    public int height(){
      int rightHeight = right == null? 0 : right.height();
      int leftHeight = left == null? 0 : left.height();
      return (leftHeight > rightHeight? leftHeight : rightHeight) + 1;
    }

    public String toString(){
      return this.item + "";
    }

  }

  public static class LinkedListNode{
    public TreeNode node;
    public LinkedListNode next;

    public LinkedListNode(TreeNode node){
      this.node = node;
    }

    public void append(TreeNode node){
      LinkedListNode newNode = new LinkedListNode(node);
      if(next == null) next = newNode;
      else{
        LinkedListNode nextNode = next;
        while(nextNode.next != null) nextNode = nextNode.next;
        nextNode.next = newNode;
      }
    }

    public String toString(){
      String next= this.next == null? "" : "-->" + this.next.toString();
      return node.toString() + next;
    }

  }

  public static Map<Integer, LinkedListNode> generateDepthLists(TreeNode node){
    Map<Integer, LinkedListNode> depthLists = new HashMap<>();
    dfs(node, 0, depthLists);
    return depthLists;
  }

  private static void dfs(TreeNode node, int depth, Map<Integer, LinkedListNode> depthLists){
    if(node == null) return;
    if(!depthLists.containsKey(depth)){
      LinkedListNode currentDepthList = new LinkedListNode(node);
      depthLists.put(depth, currentDepthList);
    } else{
      LinkedListNode currentDepthList = depthLists.get(depth);
      currentDepthList.append(node);
    }
    dfs(node.left, depth+1, depthLists);
    dfs(node.right, depth+1, depthLists);
  }

  public static void main(String[] args){
    TreeNode root = new TreeNode(10);
    root.insert(5);
    root.insert(15);
    root.insert(2);
    root.insert(7);
    root.insert(13);
    root.insert(20);

    System.out.println(DepthLists.generateDepthLists(root));
  }
}