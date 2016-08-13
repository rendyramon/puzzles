import java.util.*;

public class RandomNode{
  public static class TreeNode{
    private Random randomGen = new Random();
    public int item;
    public TreeNode left;
    public TreeNode right;

    public TreeNode parent;

    private List<TreeNode> nodes;

    public TreeNode(int item){
      this.item = item;
      nodes = new ArrayList<TreeNode>();
    }

    private TreeNode(int item, TreeNode parent){
      this.item = item;
      this.parent = parent;
    }

    public void insert(int item){
      if(this.item >= item){
        if(left == null){
          left = new TreeNode(item, this);
          addToNodes(left);
        }
        else left.insert(item);
      } else{
        if(right == null){
          right = new TreeNode(item, this);
          addToNodes(right);
        }
        else right.insert(item);
      }
    }


    public TreeNode getRandomNode(){
      if(nodes.size() == 0) return null;
      int random = randomGen.nextInt(nodes.size());
      return nodes.get(random);
    }

    public void delete(int item){
      Optional<TreeNode> matches = nodes.stream()
                                  .filter(node->node.item != item)
                                  .findFirst();
      if(!matches.isPresent()) return;
      TreeNode nodeDelete = matches.get();
      // delete left
      if(nodeDelete.parent.left == nodeDelete){
        nodeDelete.parent.left = nodeDelete.left;
        nodeDelete.parent.left.parent = nodeDelete.parent;

        TreeNode currentNode = nodeDelete.left;
        if(currentNode == null){
          nodeDelete.parent.left = nodeDelete.right;
          nodeDelete.right.parent = nodeDelete.parent;
        }
        while(currentNode.right != null){
          currentNode = currentNode.right;
        }
        currentNode = nodeDelete.right;
        nodeDelete.right.parent = currentNode;
      }
      // delete right
      if(nodeDelete.parent.right == nodeDelete){
        nodeDelete.parent.right = nodeDelete.right;
        nodeDelete.right.parent = nodeDelete.parent;

        TreeNode currentNode = nodeDelete.right;
        if(currentNode == null){
          nodeDelete.parent.right = nodeDelete.left;
          nodeDelete.left.parent = nodeDelete.parent;
        }
        while(currentNode.left != null){
          currentNode = currentNode.left;
        }
        currentNode = nodeDelete.left;
        nodeDelete.left.parent = currentNode;
      }
    }

    public TreeNode find(int item){
      int middle = nodes.size()/2;
      TreeNode currentNode = nodes.get(middle);
      while( currentNode.item != item){
        if(middle == 0 || middle == nodes.size()) return null;
        if(currentNode.item > item){
          middle = middle/2;
        } else{
          middle = (middle + nodes.size())/2;
        }
        currentNode = nodes.get(middle);
      }
      return currentNode;
    }

    private void addToNodes(TreeNode node){
      TreeNode root = node;
      while(root.parent != null) root = root.parent;
      if(root.nodes.size() == 0) root.nodes.add(node);

      int currentIndx = 0;
      while(currentIndx < root.nodes.size() && node.item < root.nodes.get(currentIndx).item)
        currentIndx++;
      root.nodes.add(currentIndx, node);
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

    System.out.println(root.getRandomNode().item);
  }
}