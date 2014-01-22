package edu.iastate.cs228.hw3;

/** Add your first name and last name
 * @Auther Steven_Monson
 */

import java.awt.HeadlessException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import javax.management.RuntimeErrorException;

public class PriorityDoublyLinkedList<E> implements List<E>, IPriority<E> {
	/**
	 *  Guides on what to do
	 *  
	 *  You are asked to implement a priority doubly-linked list class that supports 
	 *  both IPriority and List interfaces.
	 *  The linked list has a dummy head node as described in the assignment document. 
	 *  The dummy head node links to the other nodes in the linked list. Be sure to 
	 *  read the overview section of the assignment before working on the code. 
	 *  
	 *  IMPORTANT: The instructions given here are incomplete and are meant to give you 
	 *  a starting point. You will need to check the Java SE 7 API document for a full 
	 *  detailed description of each of the List methods.
	 *  
	 *  Choose your own private member variables that are meaningful and document these variables.
	 *  You are allowed to have your own private methods in addition to what are given here.
	 *  Give the private methods meaningful names and Javadoc what the methods do.
	 *  
	 */
	
	/**  Guides on member variables/fields
	 * 
	 *  - a private member variable to keep the number of nodes (elements) in the list; the dummy head node is not 
	 *    counted as the number of elements in the list.
	 *    
	 *  - a private final member variable to keep an object reference to a dummy head node of an inner class DNode; 
	 *    the value of this variable must not be changed after the object is instantiated
	 *  
	 *  - a private final member variable of type int to keep track of the default priority of a node if added without 
	 *    a given priority 
	 *  
	 *  The dummy head node is of class DNode that has the following required fields. You may choose your own names for 
	 *  these fields, but Javadoc them.
	 *  
	 *  - prio, priority field that keeps the number of priority levels supported by this list 
	 *    The value of this field does not change after an object of this class has been instantiated
	 *    
	 *  - next, this next field references the first node in the list or the head node itself if the list is empty
	 *  
	 *  - prev, this previous field references the last node in the list or the head node itself if the list is empty
	 *  
	 *  - id, this id field is initially set to zero for the head node
	 *  
	 */
	
	
	/** PriorityDoublyLinkedList(int numPriorityLevelP, int defaultPriorityP)
	 * 
	 * Guides on what to do
	 * 
	 * 
	 * 1. Create the head node and initilize it to have its prev field and the next field reference to itself. 
	 *    Set its data field to null. Set the priority of the head node to the numPriorityLevelP.
	 * 2. What you think is necessary such as initializing the remaining member variables.
	 * 
	 * @param numPriorityLevelP: the number of priority levels supported by this list; keep the value in the dummy head node
	 * @param defaultPriority: A node is given the default priority if an add method does not specify a specific priority 
	 *        for that node.
	 * 		  The value of this parameter can be between 0 inclusive and numPriorityLevelP-1 inclusive.
	 * @throws IllegalArgumentException if numPriorityLevelP <= 0 || defaultPriorityP >= numPriorityLevelP 
	 *                                     || defaultPriorityP < 0
	 * 
	 */
	
	private static int idCounter = 0;
	private final int DEFAULTPRIORITY;
	private final DNode HEAD;
	private int numItems = 0;
	
	public PriorityDoublyLinkedList(int numPriorityLevelP, int defaultPriorityP) { //TODO check all exceptions. index esp
		
		if(numPriorityLevelP <= 0 || defaultPriorityP >= numPriorityLevelP || defaultPriorityP <0)
			throw new IllegalArgumentException();
		
		DEFAULTPRIORITY = defaultPriorityP;
		
		HEAD = new DNode();
		HEAD.next = HEAD;
		HEAD.prev = HEAD;
		HEAD.prio = numPriorityLevelP;
	}
	
	
	/**
	 *  Guides on what to implement for this DNode class
	 *  
	 *  Objects of this class are linked together in the list.
	 * 
	 *  This class must be a private non-static class to represent a node with the following required fields.
	 * 1. id to keep a node id that is unique among objects of this class.
	 *    You can assume that the number of nodes is less than the maximum value of an integer.
	 * 2. prio of an int type to keep the priority of this node; the value of prio never changes. 
	 *    Different nodes may have different priorities, but the priority of a node must be >= 0 and < the number of priority levels supported by the list.
	 * 3. data to contain an object reference to an object of type E or null if an object is null or if the node is the dummy head node
	 * 4. prev to keep the object reference to its previous node in the list
	 * 5. next to keep the object reference to its next node in the list
	 * 
	 * 
	 * This class has two constructors. Implement both the constructors
	 */
	private class DNode {
		
		/** Hints 
		 * Set prev, data, next to null.
		 * Set prio to the default priority of a node, defaultPriorityP, given as an argument of the PriorityDoublyLinkedList constructor
		 * Set the id for this node.
		 * 
		 */
		
		private DNode prev, next;
		private E data;
		private int prio, id;
		
		
		public DNode() {
			prev = null;
			next = null;
			data = null;
			prio = DEFAULTPRIORITY;
			id = idCounter;
			idCounter++;
			
		}
		
		/**
		 * Hints
		 * 
		 * Initialize the fields of the DNode object accordingly.
		 * 
		 * @param obj is null or an object reference to an actual object of type E this node represents
		 * @param prevP has an object reference to its previous node in the list 
		 * @param nextP has an object reference to its next node in the list
		 * @param priority, the priority of this node
		 * @throws IllegalArgumentException if 
		 * priority < 0 || priority >= numPriorityLevelP, an argument in the constructor of the PriorityDoublyLinkedList class 
		 */
		public DNode(E obj, DNode prevP, DNode nextP, int priority) {
			
			if(priority < 0 || priority >= HEAD.prio)
				throw new IllegalArgumentException();
			
			prev = prevP;
			next = nextP;
			data = obj;
			prio = priority;
			id = idCounter;
			idCounter++;
			
		}
		
	}
	
	/*
	 * To do:
	 * Implement the methods of ListIterator and additional methods from the IPriority. 
	 * The specification of the ListIerator methods are according to 
	 * what are defined in the Java API.
	 * 
	 */
	private class PriorityDoublyLinkedListIterator implements ListIterator<E>{
		
		private int index;
		private DNode current;
		private DNode previous;
		private boolean onlyPrio;
		private int iterPrio;
		
		/**
		 * Iterator initialized this way iterates through all elements in the list 
		 * regardless of priority.
		 * 
		 * Hints:
		 * 
		 * Defined private member variables (fields) for the following.
		 * 
		 * 1. Cursor to keep track of what object reference to be returned by next()
		 * 2. A variable to indicate whether to iterate all elements in the list or only 
		 *    elements with a certain priority
		 */
		private PriorityDoublyLinkedListIterator() {
					
			current = HEAD.next;
			onlyPrio = false;
			
		}

		/**
		 * The iterator object initialized this way iterates on the elements with the specified priority.
		 * 
		 *  Hints:
		 *  
		 *  1. Save the priority in a private field of this class to remember whether 
		 *     to iterate only nodes with a certain priority or not.
		 *  2. Initialize the cursor to the first node in the list with that priority.
		 *    
		 *     
		 * @param priority: only nodes with the given priority are traversed
		 * @throws IllegalArgumentException if priority < 0 || 
		 *       priority >= number of priority levels given as the argument of the constructor 
		 *               of the PriorityDoublyLinkedList class
		 */		
		private PriorityDoublyLinkedListIterator(int priority) {
			
			if(priority < 0 || priority >= HEAD.prio)
				throw new IllegalArgumentException();
			
			onlyPrio = true;
			current = HEAD;
			iterPrio = priority;
			
			while(true){
				if(current.prio == priority)
					break;
				else
					current = current.next;
					if(current == HEAD)
						break;
			}
			
		}

		
		/** 
		 * When iterating through all nodes in the list regardless of priority 
		 * (i.e., the iterator object is instantiated with a default constructor), 
		 * the iterator object behaves like a normal Java iterator.
	     *
		 *  For iteration with a certain priority, only nodes with that priority are iterated. 
		 *  Therefore, the cursor always references a node with the same priority as the priority 
		 *  given as an argument in the PriorityDoublyLinkedListIterator constructor.
		 *  
		 *  If the next() method is implemented correctly, this hasNext() method uses the same 
		 *  code regardless of whether to iterate with a specific priority or not.
	     */
		@Override
		public boolean hasNext() {
			if(current != HEAD){
				return true;
			}
			return false;
		}

		
		/**
		 *	When iterating through all nodes in the list regardless of priority, 
		 *	this method behaves as in a normal Java iterator.
	     *
		 *  However, if the iterator object is initialized with a certain priority, 
		 *  returns true if there is a node with that priority in 
		 *  the list before the node referenced by the cursor; otherwise, returns false.
		 */
		@Override
		public boolean hasPrevious() {
			if(current.prev == HEAD || current == HEAD)
				return false;
			
			return true;
			
		}

		/** 
		 * Implement according to the Java API documentation for this method if the iterator 
		 * is instantiated using a default constructor.
		 * If the iterator is instantiated with the constructor that requires a priority, 
		 * next() should return the next element in the list with that priority.
		 * 
		 * Hints:

		 * 1. Check whether to throw NoSuchElementException
		 * 2. Keep the reference of the data item at the cursor to return
		 * 3. Advance the cursor to the next element with that priority if the priority 
		 *    is given in a constructor.
		 * 
		 */
		@Override
		public E next() { 
			E temp = current.data;
			if(current.next == null){
				throw new NoSuchElementException();
			}
			
			if(!onlyPrio){
				current = current.next;
				return temp;
			}else{
				while(true){
					if(current.next == HEAD){
						current = HEAD;
						return temp;
					}
					if(current.next.prio == iterPrio){
						current = current.next;
						return temp;
					}else{
						current = current.next;
					}
				}
			}
				
		}
		
		// --------------------- Unsupported operations ------------------ 
		// throw new UnsupportedOperationException()
		@Override
		public void add(E item) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public E previous() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(E arg0) {
			throw new UnsupportedOperationException();
		}
		
	}
	
	/**
	 * See the requirement in the IPriority interface.
	 * 
	 * You may implement this method using O(1) or O(n) algorithms 
	 * where n is the number of elements in the list.
	 * 
	 */
	@Override
	public int sizeGivenPriority(int priority) {
		if(priority < 0 || priority >= HEAD.prio)
			throw new IllegalArgumentException();
		
		DNode temp = HEAD;
		int result = 0;
		for(int i = 0; i <= this.size(); i++){
			if(temp.prio == priority)
				result++;
			temp = temp.next;
		}
		return result;
	}
	
	/**
	 * See the requirement in IPriority.
	 * 
	 * Hints: Return an iterator object that supports iteration with priority.
	 */
	@Override
	public ListIterator<E> iteratorWithPriority(int priority) {
		if(priority < 0 || priority >= HEAD.prio)
			throw new IllegalArgumentException();
		
		
		return new PriorityDoublyLinkedListIterator(priority);
	}
	
	/**
	 * See the requirement in IPriority.
	 * 
	 * Hints: The number of priority levels is kept in the dummy head node.
	 */
	public int getNumPriorityLevels() {
		
		return HEAD.prio;
		
	}
	
	/**
	 * See the requirement in IPriority.
	 * 
	 * TO DO:
	 * 
	 * Create a node that contains the priority and the item.
	 * Add this node to the end of the list.
	 * Adjust the object references to keep the list in the circular doubly linked list.
	 * 
	 */
	@Override
	public void addWithPriority(int priority, E item) {
		DNode toAdd = new DNode(item, HEAD.prev, HEAD, priority);
		HEAD.prev.next = toAdd;
		HEAD.prev = toAdd;
		
		if(numItems == 0)
			HEAD.next = toAdd;
		
		numItems++;
		
	}
	
	/**
	 * See the requirement in IPriority.
	 */
	@Override
	public void addFirstWithPriority(int priority, E item) {
		DNode toAdd = new DNode(item, HEAD, HEAD.next, priority);
		HEAD.next = toAdd;
		
		numItems++;
	}

	/** 
	 * TO DO 
	 * Implement this method according to the Java API.
	 * 
	 * A null item is allowed. A duplicate item is allowed.
	 * 
	 */
	@Override
	public boolean add(E item) {
		addWithPriority(DEFAULTPRIORITY, item);
		return true;
	}

	/** 
	 * TO DO
	 *  Implement this method according to the Java API
	 *  A null item is allowed and a duplicate item is allowed.
	 */
	@Override
	public void add(int pos, E item) {
		if(pos < 0 || pos > numItems)
			throw new IllegalArgumentException();
		
		if(HEAD.next == HEAD || pos == 0)
			addFirstWithPriority(DEFAULTPRIORITY, item);
		else{
			DNode toAdd = new DNode(item, null, null, DEFAULTPRIORITY);
			DNode cur = HEAD;
			
			for(int i = 0; i < pos; i++)
				cur = cur.next;
			
			toAdd.next = cur.next;
			cur.next.prev = toAdd;
			toAdd.prev = cur;
			cur.next = toAdd;
			
			numItems++;
		}
	}

	/** 
	 * TO DO: 
	 * Implement this method according to the Java API.
	 * 
	 * Hints: 
	 *   Set the prev and next of the dummy head node to itself.
	 *   Adjust the count of the number of elements in the list.
	 * 
	*/
	@Override
	public void clear(){
		HEAD.next = HEAD;
		HEAD.prev = HEAD;
		numItems = 0;
	}

	/**
	 * TO DO 
	 * Implement this method according to the Java API
	 */
	@Override
	public E get(int pos) {
		if(pos < 0 || pos > numItems)
			throw new IllegalArgumentException();
		
		DNode cur = HEAD.next;
		
		for(int i = 0; i < pos; i++)
			cur = cur.next;
		
		return cur.data;
	}

	/**
	 *  Return a PriorityDoublyLinkedListIterator object that allows iteration regardless of priority
	 */
	@Override
	public Iterator<E> iterator() {
		
		return new PriorityDoublyLinkedListIterator();
	}

	/**
	 * return a PriorityDoublyLinkedListIterator object that allows iteration regardless of priority
	 */
	@Override
	public ListIterator<E> listIterator() {
		
		return new PriorityDoublyLinkedListIterator();
	}

	/** 
	 * Return a PriorityDoublyLinkedListIterator object that allows iteration regardless of priority
	 * Before returning it, make sure to advance the cursor to the pos argument
	 */
	@Override
	public ListIterator<E> listIterator(int pos) {
		if(pos < 0 || pos > numItems)
			throw new IllegalArgumentException();
		
		PriorityDoublyLinkedListIterator iter = new PriorityDoublyLinkedListIterator();
		for(int i = 0; i < pos; i++)
			iter.current = iter.current.next;
		
		return iter;
	}

	/**
	 * TO DO 
	 * Implement this method according to the Java API.
	 * A null item or a duplicate item is allowed.
	 */
	@Override
	public boolean remove(Object item) {
		DNode cur = HEAD;
		
		for(int i = 0; i < numItems; i++){
			cur = cur.next;
			
			if(cur.data.equals(item)){
				cur.prev.next = cur.next;
				cur.next.prev = cur.prev;
				numItems--;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * TO DO 
	 * Implement this method according to the Java API.
	 * A null item or a duplicate item is allowed.
	 */
	@Override
	public E set(int pos, E item) {
		if(pos < 0 || pos > numItems)
			throw new IndexOutOfBoundsException();
		
		DNode cur = HEAD.next;
		
		for(int i = 0; i < pos; i++)
			cur = cur.next;
		
		E temp = cur.data;
		cur.data = item;
		
		return temp;
	}

	/**
	 *  TO DO 
	 *  Implement this method according to the Java API.
	 *  This method worst time must be O(1).
	 */
	@Override
	public int size() {
		return numItems;
	}
	
	/**
	 * TO DO 
	 * Implement this method according to the Java API.
	 * A null item or a duplicate item is allowed.
	 */
	@Override
	public boolean contains(Object item) {
		
		DNode cur = HEAD.next;
		for(int i = 0; i < numItems; i++){
			if(cur.data.equals(item))
				return true;
			cur = cur.next;
		}
		return false;
		
	}

	/**
	 * TO DO 
	 * Implement this method according to the Java API.
	 * Implement this method using an O(1)algorithm.
	 */
	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}
	
	/**
	 * TO DO 
	 * 
	 * Implement this method using the guidelines discussed in class.
	 * Two list objects are equal if they are the same object or they are both empty or
	 * the elements of the two lists at the same position are equal and 
	 * the two lists have the same number of elements.
	 * 
	 */
	@Override
	public boolean equals(Object o) {
		if(o == null)
			return false;
		PriorityDoublyLinkedList<E> temp = (PriorityDoublyLinkedList<E>) o;
		if(this == o )
			return true;
		if(this.isEmpty() && temp.isEmpty())
			return true;
		if(this.size() != temp.size())
			return false;
		for(int i = 0; i < this.size(); i++){
			if(!this.get(i).equals(temp.get(i)))
				return false;
		}
		return true;
		
	}
	
    // ----------------- Unsupported Operations ------------
	/**
	 * throw new UnsupportedOperationException() for all the unsupported operations
	 */
	
	/**
	 *  TO DO
	 *  @throws UnsupportedOperationException()
	 */
	@Override
	public int indexOf(Object item) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 *  TO DO
	 *  @throws UnsupportedOperationException()
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/**
	 *  TO DO
	 *  @throws UnsupportedOperationException()
	 */
	@Override
	public boolean addAll(Collection<? extends E> item) {
		throw new UnsupportedOperationException();
	}

	/**
	 *  TO DO
	 *  @throws UnsupportedOperationException()
	 */
	@Override
	public boolean addAll(int pos, Collection<? extends E> item) {	
		throw new UnsupportedOperationException();
	}
	
	/**
	 *  TO DO
	 *  @throws UnsupportedOperationException()
	 */
	@Override
	public int lastIndexOf(Object item) {
		throw new UnsupportedOperationException();
	}

	/**
	 *  TO DO
	 *  @throws UnsupportedOperationException()
	 */
	@Override
	public E remove(int pos) {
		throw new UnsupportedOperationException();
	}

	/**
	 *  TO DO
	 *  @throws UnsupportedOperationException()
	 */
	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	/**
	 *  TO DO
	 *  @throws UnsupportedOperationException()
	 */
	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	/**
	 *  TO DO
	 *  @throws UnsupportedOperationException()
	 */
	@Override
	public List<E> subList(int arg0, int arg1) {
		throw new UnsupportedOperationException();
	}

	/**
	 *  TO DO
	 *  @throws UnsupportedOperationException()
	 */
	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	/**
	 *  TO DO
	 *  @throws UnsupportedOperationException()
	 */
	@Override
	public <T> T[] toArray(T[] arg0) {
		throw new UnsupportedOperationException();
	}

}
