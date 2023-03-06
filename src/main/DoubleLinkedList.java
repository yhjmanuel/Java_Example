package main;

public class DoubleLinkedList<T> {
	public int size;
	public Node<T> head;
	
	
	public DoubleLinkedList() {
		
	}
	
	public Node<T> getFirst() {
		return this.head;
	}
	
	
	public void insert(T element) {
		Node<T> curr = this.head;
		if (curr == null) {
			this.head = new Node(element);
		} else {
			while (curr.next != null) {
				curr = curr.next;
			}
			curr.next = new Node(element);
			curr.next.prev = curr;
			curr.next.next = null;
		}
		this.size++;
	}
	
	public T delete(T key) {
		Node<T> pre = new Node(key);
		pre.setNext(this.head);
		Node<T> curr = pre;
		while (curr.next != null) {
			if (curr.next.data.equals(key)) {
				curr.next = curr.next.next;
				if (curr.next != null) {
					curr.next.prev = curr;
				}
				this.size--;
				head = pre.next;
				return key;
			}
			curr = curr.next;
		}
		return null;
	}
	
	public T get(T key) {
		Node<T> curr = head;
		while (curr != null) {
			if (curr.data.equals(key)) {
				return curr.data;
			}
			curr = curr.next;
		}
		return null;
	}
	
	public int size() {
		return this.size;
	}
	
	@Override
	public String toString() {
		if (this.head == null) {
			return "Empty list";
		}
		String rep = "";
		Node<T> curr = this.head;
		while (curr != null) {
			rep += curr;
			rep += "<->";
			curr = curr.next;
		}
		return rep.substring(0, rep.length()-3);
	}
}
