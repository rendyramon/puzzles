import java.util.*;

public class BSTChecker{
  public static class TreeNode{
    public int item;
    public TreeNode left;
    public TreeNode right;
    public int height;

    public TreeNode(int item){
      this.item = item;
      this.height = 0;
    }

    public TreeNode(int item, int height){
      this.item = item;
      this.height = height;
    }

    public void insert(int item){
      if(item <= this.item){
        if(left == null){
          left = new TreeNode(item);
          left.height = 1;
        }
        else left.insert(item, 2);
      } else{
        if(right == null){
          right = new TreeNode(item);
          right.height = 1;
        }
        else right.insert(item, 2);
      }
    }

    public int height(){
      int rightHeight = right == null? 0 : right.height();
      int leftHeight = left == null? 0 : left.height();
      return (leftHeight > rightHeight? leftHeight : rightHeight) + 1;
    }

    public boolean isBalanced(){
      int leftHeight = left == null? 0 : left.height;
      int rightHeight = right == null? 0: right.height;
      return Math.abs(leftHeight - rightHeight) <= 1;
    }

    private void insert(int item, int height){
      if(item <= this.item){
        if(left == null) left = new TreeNode(item, height);
        else left.insert(item, height+1);
      } else{
        if(right == null) right = new TreeNode(item, height);
        else right.insert(item, height+1);
      }
    }
  }


  public boolean isBST(TreeNode node){
    Stack<TreeNode> treeStack = new Stack<TreeNode>();
    treeStack.push(node);
    while(!treeStack.isEmpty()){
      TreeNode currentNode = treeStack.pop();
      if(!currentNode.isBalanced()) return false;
      if(currentNode.left != null){
        if(currentNode.left.item > currentNode.item) return false;
        treeStack.push(currentNode.left);
      }
      if(currentNode.right != null){
        if(currentNode.right.item <= currentNode.item) return false;
        treeStack.push(currentNode.right);
      }
    }
    return true;
  }

  public static void main(String[] args){
    BSTChecker bstChecker = new BSTChecker();
    TreeNode root = new TreeNode(10);
    root.insert(5);
    root.insert(15);
    root.insert(2);
    root.insert(7);
    root.insert(13);
    root.insert(20);

    System.out.println(bstChecker.isBST(root));
    root.insert(21);
    root.insert(22);

    System.out.println(bstChecker.isBST(root));
  }
}