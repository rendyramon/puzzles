public class Successor{

  public static class SuccessorNotFoundException extends Exception{
    public SuccessorNotFoundException(){
      super();
    }
  }

  public static class TreeNode{
    public int item;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;

    public TreeNode(int item){
      this.item = item;
    }

    public void insert(int item){
      if(this.item < item){
        if(right == null) {
          right = new TreeNode(item);
          right.parent = this;
        } else
          right.insert(item);
      }
      else{        
        if(left == null) {
          left = new TreeNode(item);
          left.parent = this;
        } else
          left.insert(item);
      }
    }

    public int getInOrderSuccessor(TreeNode node) throws Exception {
      if(node.right != null)
        return node.right.item;

      return getInOrderSuccessorStep(node);
    }

    public int getInOrderSuccessorStep(TreeNode node) throws Exception{
      TreeNode parent = node.parent;
      if(parent == null)
        throw new SuccessorNotFoundException();

      if(node == parent.left)
        return parent.item;
      return getInOrderSuccessorStep(parent);
    }

    public void inOrder(TreeNode node){
      if(node == null)
        return;
      inOrder(node.left);
      System.out.println(node.item);
      inOrder(node.right);
    }
  }

  public static void main(String[] args){
    TreeNode root = new TreeNode(10);
    root.insert(7);
    root.insert(15);
    root.insert(13);
    root.insert(14);
    root.insert(12);
    root.insert(11);

    TreeNode toFind = root.right.left.right;
    System.out.println(toFind.item);
    try{
      System.out.println(root.getInOrderSuccessor(toFind));  
    }catch(Exception e){
      System.out.println(e);
    }
  }
}