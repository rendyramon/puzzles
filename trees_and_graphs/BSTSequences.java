import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BSTSequences{
	public static class TreeNode{
		public int item;
		public TreeNode right;
		public TreeNode left;

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

		public List<List<TreeNode>> getBSTSequences(){
			List<List<TreeNode>> result = new ArrayList<>();
			List<TreeNode> nodes = bfsExceptRoot();
			
		}

		private List<TreeNode> bfs(){
			int sequncesNumber = 0;
			Stack<TreeNode> treeStack = new Stack<>();
			List<TreeNode> nodes = new ArrayList<>();
			treeStack.push(this);
			while(!treeStack.isEmpty()){
				nodes.add(treeStack.pop());
				if(head.left != null) treeStack.push(head.left);
				if(head.right != null) treeStack.push(head.right);
			}
			return nodes;
		}

		private List<TreeNode> reverseBfs(){
			int sequncesNumber = 0;
			Stack<TreeNode> treeStack = new Stack<>();
			List<TreeNode> nodes = new ArrayList<>();
			treeStack.push(this);
			while(!treeStack.isEmpty()){
				nodes.add(treeStack.pop());
				if(head.right != null) treeStack.push(head.right);
				if(head.left != null) treeStack.push(head.left);
			}
			return nodes;
		}

		private List<TreeNode> reverseDfs(){
			Queue<TreeNode> nodeQueue = new ConcurrentLinkedQueue();
			List<TreeNode> nodes = new ArrayList<>();
			treeStack.add(this);
			while(!treeStack.isEmpty()){
				nodes.add(treeStack.remove());
				if(head.right != null) treeStack.add(head.right);
				if(head.left != null) treeStack.add(head.left);
			}
			return nodes;
		}

		private List<TreeNode> dfs(){
			Queue<TreeNode> nodeQueue = new ConcurrentLinkedQueue();
			List<TreeNode> nodes = new ArrayList<>();
			treeStack.add(this);
			while(!treeStack.isEmpty()){
				nodes.add(treeStack.remove());
				if(head.left != null) treeStack.add(head.left);
				if(head.right != null) treeStack.add(head.right);
			}
			return nodes;
		}
	}
}