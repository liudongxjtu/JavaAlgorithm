import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * 
 * @author Dong's surface
 * Write a generic data type for a deque and a randomized queue. 
 * The goal of this assignment is to implement elementary data structures using arrays and linked lists, 
 * and to introduce you to generics and iterators.


 * @param <Item>
 */


public class Deque<Item> implements Iterable<Item> {

	private class Node
	{
		Item item;
		Node next;
	}

	private Node first = null;
	private Node last = null;
	public Deque()							// constructor for 
	{	
		first = null;
		last = null;
		//first.next = last;
	}



	public boolean isEmpty()                 // is the deque empty?
	{
		return first == null;
	}

	public int size()                        // return the number of items on the deque
	{
		if(isEmpty()) {
			return 0;
		}
		else {
			int cnt = 1;
			Node current = first;
			while(current.next != null){
				cnt++;
				current = current.next;
			}

			return (cnt);//include 
		}

	}
	public void addFirst(Item item)          // add the item to the front
	{
		if (item == null) {
			throw new NullPointerException("Cannot add null items");
		}
		
		Node n = new Node();
		n.item = item;

		if(isEmpty()){
			last = n;
			first = n;
			n.next = null;

		}
		else{
			n.next = first;
			first = n;

		}
	}

	public void addLast(Item item)           // add the item to the end
	{
		if (item == null) {
			throw new NullPointerException("Cannot add null items");
		}

		Node n = new Node();
		n.item = item;
		
		
		if(isEmpty()) {	
			last = n;
			first = n;
			n.next = null;
		}
		else {
			last.next = n;
			last = n;
		}
	}

	public Item removeLast()                // remove and return the item from the end
	{
		if(last == null) {
			throw new NoSuchElementException("The queue is empty!");
		}
		else {
			Node current = first;

			while(current.next != last) {
				current = current.next; 
			}

			Item item = current.item;
			current.next = null;
			last = current;

			return item;
		}

	}

	public Item removeFirst()                // remove and return the item from the front
	{
		if(first != null) {
			Item item = first.item;
			first = first.next;
			return item;
		}
		else {
			throw new NoSuchElementException("The queue is empty!");
		}

	}

	public Iterator<Item> iterator()         // return an iterator over items in order from front to end
	{
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item>
	{
		private Node current = first;

		@Override
		public boolean hasNext() { return current != null; }

		@Override
		public Item next()
		{
			if (!hasNext()) {
				throw new NoSuchElementException("No more objects to iterate through");
			}

			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Iterator remove function not supported.");
		}
	}

	public static void main(String[] args)   // unit testing (optional)
	{

		Deque<Integer> deq1 = new Deque<Integer>();

		System.out.println("Deq1: " + deq1.toString() + " Size: "+deq1.size());

		deq1.addLast(1);
		deq1.addLast(2);
		deq1.addLast(3);
		deq1.addLast(4);
		deq1.addLast(5);

		Integer it = deq1.removeLast();
		System.out.println(it.toString());

//		deq1.addFirst(6);
//		deq1.addFirst(7);
//		deq1.addFirst(8);
//		deq1.addFirst(9);
		
		
		
		System.out.println("size: " + deq1.size());

		Iterator<Integer> itr = deq1.iterator();
		while(itr.hasNext())
		{
			System.out.println(itr.next());
		}


	}


}