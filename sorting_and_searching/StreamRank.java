import java.util.*;

public class StreamRank{
  public static class TreeNode{
    public int item;
    public TreeNode left;
    public TreeNode right;
    public int greaterThan;

    public TreeNode(int item){
      this.item = item;
      greaterThan = 0;
    }

    public int getRank(int item){
      if(this.item < item){
        if(right == null) return -1;
        else return right.getRank(item);
      } else if(this.item > item){
        if(left == null) return -1;
        else return left.getRank(item);
      } else{
        return greaterThan;
      }
    }

    private TreeNode(int item, int greaterThan){
      this.item = item;
      this.greaterThan = greaterThan;
    }

    public void insert(int item){
      insert(item, 0);
    }

    private void insert(int item, int greaterThan){
      if(this.item < item){
        if(right == null) right = new TreeNode(item, greaterThan+1);
        else
          right.insert(item, greaterThan+1);
      } else{
        this.greaterThan++;
        if(left == null) left = new TreeNode(item, greaterThan);
        else{
          if(this.item == item) {
            this.greaterThan++;
            greaterThan++;
          }
          left.insert(item, greaterThan);
        }
      }
    }
  }

  public static void main(String[] args){
    TreeNode node = new TreeNode(5);
    node.insert(1);
    node.insert(4);
    node.insert(4);
    node.insert(5);
    node.insert(9);
    node.insert(7);
    node.insert(13);
    node.insert(3);

    System.out.println(node.getRank(1));
    System.out.println(node.getRank(3));
    System.out.println(node.getRank(4));
  }
}