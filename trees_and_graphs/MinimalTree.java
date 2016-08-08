public class MinimalTree<T extends Comparable<T>>{  
  public T item;
  public MinimalTree<T> left;
  public MinimalTree<T> right;
  public MinimalTree<T> parent;

  public MinimalTree(T item){
    this.item = item;
  }

  public void insert(T item){
    if(this.item.compareTo(item) > 0)
      left = insert(item, left);
    else
      right = insert(item, right);
  }

  private MinimalTree<T> insert(T item, MinimalTree<T> node){
    MinimalTree<T> newNode;
    if(node == null){
      newNode = new MinimalTree(item);
      newNode.parent = node;
      return newNode;
    }

    if(node.item.compareTo(item) > 0)
        newNode = insert(item, node.left);
    else
        newNode = insert(item, node.right);
    node.balance();
    return newNode;
  }
  
  public int height(){
    int leftHeight = left == null? 0 : left.height();
    int rightHeight = right == null? 0 : right.height();
    return (leftHeight > rightHeight? leftHeight : rightHeight) + 1;
  }

  private void balance(){
    int leftHeight = left == null? 0 : left.height();
    int rightHeight = right == null? 0 : right.height();
    if(Math.abs(leftHeight - rightHeight) > 1){
      if(leftHeight > rightHeight){
        leftRotate();
      } else{
        rightRotate();
      }
    }
    if(parent != null)
      parent.balance();
  }

  private void leftRotate(){
    // swap item
    T lastItem = item;
    item = left.item;
    left.item = lastItem;

    // move left right to left
    MinimalTree<T> lastLeftRight = left.right;
    left.right = null; 
    left.right = lastLeftRight;
  }

  private void rightRotate(){
    // swap item
    T lastItem = item;
    item = right.item;
    right.item = lastItem;

    // move right left to right
    MinimalTree<T> lastRightLeft = right.left;
    right.left = null; 
    right.right = lastRightLeft;
  }

  public static void main(String[] args){
    MinimalTree<Integer> minimalTree = new MinimalTree(5);
    minimalTree.insert(6);
    minimalTree.insert(7);
    minimalTree.insert(8);
    minimalTree.insert(9);

    System.out.println(minimalTree.height());
  }
}