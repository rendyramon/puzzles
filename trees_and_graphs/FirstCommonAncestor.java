import java.util.*;

public class FirstCommonAncestor{
	public static class CommonAncestorDoesNotExistException extends Exception{
		public CommonAncestorDoesNotExistException(){
			super();
		}
	}

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

		public TreeNode getFirstCommonAncestor(TreeNode nodeOne, TreeNode nodeTwo) throws CommonAncestorDoesNotExistException{
			if(nodeOne == null || nodeTwo == null) throw new CommonAncestorDoesNotExistException();
			Set<TreeNode> nodeSet = getAncestorSet(nodeOne);
			List<TreeNode> nodeList = getAncestorList(nodeTwo);

			int idx = 0;
			TreeNode firstCommonAncestor = nodeList.get(idx);
			while(nodeSet.contains(firstCommonAncestor)){
				idx++;
				firstCommonAncestor = nodeList.get(idx);
			}
			if(idx == 0) throw new CommonAncestorDoesNotExistException();
			return nodeList.get(idx-1);
		}

		private List<TreeNode> getAncestorList(TreeNode node){
			List<TreeNode> path = new ArrayList<>();
			ancestorListStep(node, path);
			return path;
		}

		private Set<TreeNode> getAncestorSet(TreeNode node){
			Set<TreeNode> path = new HashSet<>();
			ancestorSetStep(node, path);
			return path;
		}

		private void ancestorListStep(TreeNode node, List<TreeNode> path){
			path.add(this);
			if(node == this)
				return;
			else if(this.item >= node.item){
				if(left == null) return;
				else left.ancestorListStep(node, path);
			} else{
				if(right == null) return;
				else right.ancestorListStep(node, path);
			}
		}

		private void ancestorSetStep(TreeNode node, Set<TreeNode> path){
			path.add(this);
			if(node == this)
				return;
			else if(this.item >= node.item){
				if(left == null) return;
				else left.ancestorSetStep(node, path);
			} else{
				if(right == null) return;
				else right.ancestorSetStep(node, path);
			}
		}
	}

	public static void main(String[] args){
		TreeNode root  = new TreeNode(10);
		root.insert(5);
		root.insert(16);
		root.insert(7);
		root.insert(2);

		TreeNode nodeOne = root.left.left;
		TreeNode nodeTwo = root.left.right;

		try{
			System.out.println(root.getFirstCommonAncestor(nodeOne, nodeTwo).item);	
		} catch(Exception e){
			System.out.println(e);
		}
		
	}
}