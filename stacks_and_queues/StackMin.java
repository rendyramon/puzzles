import java.util.*;

public class StackMin<T extends Comparable<T>>{
	public static class StackNode<T>{
		public StackNode<T> next;
		public T item;
		
		public StackNode(T item){
			this.item = item;
		}

		public StackNode<T> copy(){
			return new StackNode(item);
		}
	}
	
	public static class StackEmptyException extends Exception {
		public StackEmptyException(){
			super();
		}
	}

	private StackNode<T> top;
	private StackNode<T> min;
	
	public void push(T item){
		StackNode<T> node = new StackNode(item);
		if(top == null){
			top = node;
			min = node.copy();
			return;
		}

		StackNode<T> lastTop = top;
		node.next = lastTop;
		top = node;
		
		StackNode<T> lastMin = min;
		StackNode<T> newMin;
		if(node.item.compareTo(min.item) < 0){
			newMin = node.copy();
		} else{
			newMin = min.copy();
		}

		newMin.next = lastMin;
		min = newMin;
	}

	public T pop() throws Exception{
		if(isEmpty()) throw new StackEmptyException();

		StackNode<T> lastTop = top;
		top = top.next;

		StackNode<T> lastMin = min;
		min = min.next;

		return lastTop.item;
	}

	public T min() throws Exception{
		if(isEmpty()) throw new StackEmptyException();
		return min.item;
	}

	public T peek() throws Exception{
		if(isEmpty()) throw new StackEmptyException();
		return top.item;
	}

	public boolean isEmpty(){
		return top == null;
	}

	public static void main(String[] args){
		StackMin<Integer> stackInt = new StackMin();
		try{
			stackInt.push(1);
			stackInt.push(2);
			stackInt.push(3);
			stackInt.push(0);

			System.out.println(stackInt.min());
			System.out.println(stackInt.pop());
			System.out.println(stackInt.min());
			System.out.println(stackInt.pop());
			System.out.println(stackInt.min());
		} catch(Exception e){
			System.out.println(e);
		}
	}
}