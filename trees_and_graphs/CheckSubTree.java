import java.util.*;

public class CheckSubTree{
  public static class TreeNode{
    public int item;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int item){
      this.item = item;
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

    public boolean compareLists(List<TreeNode> nodeList, List<TreeNode> t2List){
      if(nodeList.size() != t2List.size()) return false;
      for(int i = 0; i < nodeList.size(); i++){
        TreeNode currentNode = nodeList.get(i);
        TreeNode currentT2 = t2List.get(i);
        if(currentNode.item != currentT2.item) return false;
      } 
      return true;
    }

    public boolean CheckSubTree(TreeNode t2){
      List<TreeNode> possibleRoots = findT1Root(this, t2);
      
      for(TreeNode root : possibleRoots){
        List<TreeNode> nodeList = nodeList(root);
        List<TreeNode> t2List = nodeList(t2); 
        if(compareLists(nodeList, t2List)) return true;
      }
      return false;
    }

    public List<TreeNode> nodeList(TreeNode node){
      Stack<TreeNode> nodeStack = new Stack<>();
      List<TreeNode> nodeList = new ArrayList<>();
      nodeStack.push(node);
      while(!nodeStack.isEmpty()){
        TreeNode currentNode = nodeStack.pop();
        nodeList.add(currentNode);
        if(currentNode.left != null) nodeStack.push(currentNode.left);
        if(currentNode.right != null) nodeStack.push(currentNode.right); 
      }
      return nodeList;
    }

    public List<TreeNode> findT1Root(TreeNode t1, TreeNode t2){
      Stack<TreeNode> nodeStack = new Stack<>();
      List<TreeNode> nodeList = new ArrayList<>();
      nodeStack.push(t1);
      while(!nodeStack.isEmpty()){
        TreeNode currentNode = nodeStack.pop();
        if(currentNode.item == t2.item) nodeList.add(currentNode);
        if(currentNode.left != null) nodeStack.push(currentNode.left);
        if(currentNode.right != null) nodeStack.push(currentNode.right); 
      }
      return nodeList;
    }
  }

  public static void main(String[] args){
    TreeNode node = new TreeNode(10);
    node.insert(7);
    node.insert(15);
    node.insert(13);
    node.insert(12);
    node.insert(11);
    node.insert(14);

    TreeNode subTree = new TreeNode(13);
    subTree.insert(12);
    subTree.insert(11);
    subTree.insert(14);

    System.out.println(node.CheckSubTree(subTree));
  }

}