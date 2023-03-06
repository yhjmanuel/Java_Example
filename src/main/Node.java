package main;

public class Node<T> {
	public T data;
	public Node<T> next;
	public Node<T> prev;
	
	
	public Node(T data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}
	
	public Node<T> getNext() {
		return this.next;
	}
	
	public Node<T> getPrev() {
		return this.prev;
	}
	
	public T getData() {
		return this.data;
	}
	
	@Override
	public String toString() {
		return "" + this.data;
	}
}
