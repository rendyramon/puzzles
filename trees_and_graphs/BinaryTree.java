public class BinaryTree{
  public Integer item;
  public BinaryTree left;
  public BinaryTree right;

  public BinaryTree(Integer item){
    this.item = item;
  }

  public static void inOrder(BinaryTree node){
    if(node == null) return;
    inOrder(node.left);
    System.out.println(node.item);
    inOrder(node.right);
  }

  public static void postOrder(BinaryTree node){
    if(node == null) return;
    System.out.println(node.item);
    postOrder(node.left);
    postOrder(node.right);
  }

  public static void preOrder(BinaryTree node){
    if(node == null) return;
    preOrder(node.left);
    preOrder(node.right);
    System.out.println(node.item);
  }

  public static BinaryTree delete(Integer item, BinaryTree head){
    if(head == null) return null;
    if(head.item.compareTo(item) == 0){
      BinaryTree lastHead = head;
      head = deleteSmallestGreaterThan(head);
      if(head == null){
        head = lastHead.left;
      } else{
        head.left = lastHead.left;
        if(lastHead.right != head)
          head.right = lastHead.right;
      }
    } else if(head.item.compareTo(item) > 0)
      head.left = delete(item, head.left);
    else
      head.right = delete(item, head.right);
    return head;
  }

  public static BinaryTree insert(Integer item, BinaryTree head){
    if(head == null) return new BinaryTree(item);
    if(head.item.compareTo(item) > 0)
      head.left = insert(item, head.left);
    else
      head.right = insert(item, head.right);
    return head;
  }

  private static BinaryTree deleteSmallestGreaterThan(BinaryTree node){
    if(node.right == null) return null;
    BinaryTree smallestGreaterThan = node.right;
    if(smallestGreaterThan.left == null) return smallestGreaterThan;
    
    while(smallestGreaterThan.left != null) {
      node = smallestGreaterThan;
      smallestGreaterThan = smallestGreaterThan.left;
    }
    node.left = null;
    return smallestGreaterThan;
  }

  public static void main(String[] args){
    BinaryTree binaryTree = new BinaryTree(6);
    insert(3, binaryTree);
    insert(10, binaryTree);
    inOrder(binaryTree);
    
    binaryTree = delete(3, binaryTree);
    System.out.println();
    binaryTree = delete(6, binaryTree);

    System.out.println();
    inOrder(binaryTree);
  }
}