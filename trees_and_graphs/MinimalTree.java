public class MinimalTree<T>{  
  public T item;
  public MinimalTree<T> left;
  public MinimalTree<T> right;
  public MinimalTree<T> parent;

  public MinimalTree(T item){
    this.item = item;
  }

  public void insert(T item){
    if(this.item > item)
      left = insert(item, node.left);
    else
      right = insert(item.node.right);
  }

  private MinimalTree<T> insert(T item, MinimalTree<T> node){
    if(node == null){
      MinimalTree<T> newNode = new MinimalTree(item);
      newNode.parent = node;
      return new MinimalTree<>(item);
    }

    if(node.item > node)
        node.left = insert(item, node.left);
    else
        node.right = insert(item, node.right);
    node.balance();
  }
  
  public int height(){
    int leftHeight = left == null? 0 : left.height();
    int rightHeight = right == null? 0 : right.height();
    return (leftHeight > rightHeight : leftHeight : rightHeight) + 1;
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
    MinimalTree<T> nodeParent = node.parent;
    MinimalTree<T> lastLeft;
    MinimalTree<T> lastNode;
    if(nodeParent.left == node){
     lastNode = nodeParent.left;     
     lastLeft = lastNode.left;
     nodeParent.left = lastLeft;
    } else{
     lastNode = nodeParent.right;
     lastLeft = lastNode.left;
     nodeParent.right = lastLeft;
    }
    
    MinimalTree<T> lastLeftRight = lastLeft.right;
    lastLeft.right = lastNode;
    lastNode.left = lastLeft;
  }

  private void rightRotate(){
    MinimalTree<T> nodeParent = node.parent;
    MinimalTree<T> lastRight;
    MinimalTree<T> lastNode;
    if(nodeParent.left == node){
     lastNode = nodeParent.left;     
     lastRight = lastNode.right;
     nodeParent.left = lastRight;
    } else{
     lastNode = nodeParent.right;
     lastRight = lastNode.right;
     nodeParent.right = lastRight;
    }

    MinimalTree<T> lastRightLeft = lastRight.left;
    lastRight.left = lastNode;
    lastNode.right = lastRightLeft;
  }

  public static void main(String[] args){
    MinimalTree<T> minimalTree = new MinimalTree(5);
    minimalTree.insert(6);
    minimalTree.insert(7);
    minimalTree.insert(8);
    minimalTree.insert(9);

    System.out.println(minimalTree.height);
  }
}