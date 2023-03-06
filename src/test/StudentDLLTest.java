package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import main.*;

class StudentDLLTest {

	@Test
	void test() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		assertEquals(0, list.size());
		assertEquals(null, list.get(100));
		assertEquals(null, list.getFirst());
		assertEquals("Empty list", list.toString());
		list.insert(100);
		assertEquals(1, list.size());
		assertEquals("100", list.toString());
		assertEquals(100, list.getFirst().getData());
		list.insert(200);
		list.insert(300);
		assertEquals("100<->200<->300", list.toString());
		assertEquals(100, list.getFirst().getData());
		Node<Integer> curr = list.head;
		assertEquals(200, list.get(200));
		assertEquals(null, list.delete(500));
		assertEquals(100, list.delete(100));
		assertEquals("200<->300", list.toString());
		assertEquals(null, list.delete(100));
		assertEquals(300, list.delete(300));
		assertEquals("200", list.toString());
		assertEquals(200, list.delete(200));
		assertEquals("Empty list", list.toString());
		assertEquals(0, list.size());
	}

}
