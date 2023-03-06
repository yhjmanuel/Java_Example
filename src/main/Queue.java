package main;

import java.util.NoSuchElementException;

public class Queue<T> {

	public T[] q;
	public int head;
	public int tail;
	public int numEntries;

	
	@SuppressWarnings("unchecked")
	public Queue(int capacity) {
		this.q = (T[]) new Object[capacity];
	}
	
	//the throw is self-added
	public void enqueue(T element) throws Exception {
		if (this.numEntries == this.q.length) {
			throw new Exception("The queue is full");
		} else {
			this.q[this.tail] = element;
			if (this.tail == q.length - 1) {
				this.tail = 0;
			} else {
				this.tail++;
			}
			this.numEntries++;
		}
	}
	
	public void dequeue() { 
		if (this.numEntries == 0) {
			throw new NoSuchElementException();
		}
		this.q[head] = null;
		if (this.head == q.length - 1) {
			this.head = 0;
		} else {
			this.head++;
		}
		this.numEntries--;
	}
	
	public T front() {
		if (this.numEntries == 0) {
			throw new NoSuchElementException();
		}
		return this.q[this.head];
	}
	
	public int size() {
		return this.numEntries;
	}
	
	@Override
	public String toString() {
		String rep = "Elements in queue: ";
		if (this.numEntries == 0) {
			return "The queue is empty";
		} else {
			if (head < tail) {
				for (int i=head; i<tail; i++) {
					rep = rep + q[i] + " ";
				}
			} else {
				for (int i=head; i<=q.length-1; i++) {
					rep = rep + q[i] + " ";
				}
				for (int i=0; i<this.tail; i++) {
					rep = rep + q[i] + " ";
				}
			}
		}
		return rep;
	}
}
