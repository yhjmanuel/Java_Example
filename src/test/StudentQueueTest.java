package test;

import static org.junit.jupiter.api.Assertions.*;
import main.*;

import org.junit.jupiter.api.Test;

class StudentQueueTest {

	@Test
	void test() throws Exception {
		Queue queue = new Queue(3);
		assertEquals(0, queue.size());
		queue.enqueue(5);
		queue.dequeue();
		queue.enqueue(4);
		queue.dequeue();
		queue.enqueue(3);
		queue.dequeue();
		queue.enqueue(2);
		queue.dequeue();
		assertEquals(0, queue.size());
		queue.enqueue(5);
		queue.enqueue(7);
		queue.enqueue(8);
		assertEquals(3, queue.size());
		boolean full = false;
		try {
			queue.enqueue(10);
		} catch (Exception e){
			full = true;
		}
		assertTrue(full);
		assertEquals(3, queue.size());
		assertEquals(5, queue.front());
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		boolean empty = false;
		try {
			queue.dequeue();
		} catch (Exception e){
			empty = true;
		}
		assertTrue(empty);
		assertEquals("The queue is empty", queue.toString());
		boolean noFront = false;
		try {
			queue.front();
		} catch (Exception e){
			noFront = true;
		}
		assertTrue(noFront);
		queue.enqueue(5);
		queue.enqueue(10);
		queue.enqueue(10);
		queue.dequeue();
		assertEquals(2, queue.size());

	}

}
