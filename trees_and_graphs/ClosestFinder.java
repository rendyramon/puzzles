public class ClosestFinder{
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
  }
  
  public static class NodeCantBeNullException extends Exception{
    public NodeCantBeNullException(){
      super();
    }
  }

  public int findClostest(TreeNode node, int elem) throws NodeCantBeNullException{
    if(node == null) throw new NodeCantBeNullException();
    int least = Math.abs(node.item - elem);
    int leastElem = node.item;
    while(node != null){
      int currentLeast = Math.abs(node.item - elem);
      if(currentLeast < least){
        least = currentLeast;
        leastElem = node.item;
      }
      if(node.item > elem){
        node = node.left;
      } else{
        node = node.right;
      }
    }
    return leastElem;
  }

  public static void main(String[] args){
    TreeNode node = new TreeNode(10);
    node.insert(4);
    node.insert(2);
    node.insert(6);
    node.insert(14);
    node.insert(11);
    try{
      System.out.println(new ClosestFinder().findClostest(node, 12));
    } catch(Exception e){
      System.out.println();
    }
  }
}