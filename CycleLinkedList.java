/**
 * 
 */
package a2;

import java.util.*;
import static org.junit.Assert.*;



public class CycleLinkedList {
	// bool to check Node, returns true if a cycle exists
	public static boolean hasCycle(Node head) {
		// creates stack
		Stack<Node> nodeStack = new Stack<Node>();
		// while loop continues till head is null
		while (head != null) {
			// if checks if nodeStack contains head Node value and returns true
			if (nodeStack.contains(head)) {
				return true;
			}
			// adds head to nodeStack and sets head as next value in the list.
			nodeStack.add(head);
			head = head.getNext();
		}
		// returns false when if in while is not satisfied
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node head = new Node("start");
		Node prev = head;
		for (int i = 0; i < 4; i++) {
			Node temp = new Node(Integer.toString(i));
			prev.setNext(temp);
			prev = temp;
		}

		boolean b = hasCycle(head);
		System.out.println("Testing...");
		assertEquals(b, false);
		System.out.println("Success!");

		prev.setNext(head.getNext());

		b = hasCycle(head);
		System.out.println("Testing...");
		assertEquals(b, true);
		System.out.println("Success!");

	}

}
