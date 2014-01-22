package edu.iastate.cs228.hw3;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;

public class PriorityDoublyLinkedListTest {

	
	PriorityDoublyLinkedList<String> list;
	ListIterator<String> iter, iterP;
	public final ByteArrayOutputStream log = new ByteArrayOutputStream();
	String[] out = new String[10];
	String lol;
	
	@Before
	public void setup(){
		System.setOut(new PrintStream(log));
		list = new PriorityDoublyLinkedList<String>(4, 1);
		
		list.add("First Item");
		list.add("Second Item");
		list.add("Third Item");
		lol = "lol";
		list.add(lol);
		iter = list.listIterator();
	}
	
	@Test
	public void testNext() {
		int counter = 0;
		
		while(iter.hasNext()){
			out[counter] = iter.next();
			counter++;
		}
		
		assert(counter == list.size());
		assertEquals("First Item", out[0]);
		assertEquals("Second Item", out[1]);
		assertEquals("Third Item", out[2]);
		assertEquals(lol, out[3]);
		}
	
	@Test
	public void testClear(){
		assertEquals(false, list.isEmpty());
		list.clear();
		assertEquals(0, list.size());
		assertEquals(true, list.isEmpty());
		
		list.add("a");
		list.add("b");
		list.add("c");
		iter = list.listIterator();
		
		out[0] = iter.next();
		assertEquals("a", out[0]);
	}
	
	@Test
	public void testNextP(){
		list.clear();
		list.addWithPriority(0, "Prio 0: a");
		list.addWithPriority(0, "Prio 0: b");
		list.addWithPriority(3, "Prio 3: a");
		list.addWithPriority(3, "Prio 3: b");
		
		iterP = list.iteratorWithPriority(3);
		
		int counter = 0;
		while(iterP.hasNext())
			out[counter++] = iterP.next();
		assertEquals("Prio 3: a", out[0]);
		assertEquals("Prio 3: b", out[1]);
		
	}

	@Test
	public void testMissingPrio(){
		list.clear();
		list.addWithPriority(2, "Prio 2: a");
		
		iterP = list.iteratorWithPriority(3);
		
		assertEquals(false, iterP.hasNext());
		assertEquals(false, iterP.hasPrevious());		
	}
	
	@Test
	public void testHasPrev(){
		assertEquals(false, iter.hasPrevious());
		iter.next();
		assertEquals(true, iter.hasPrevious());
	}
	
	@Test
	public void testSizePrio(){
		list.clear();
		assertEquals(0, list.size());
		list.addWithPriority(2, "");
		list.addWithPriority(3, null);
		list.addWithPriority(2, null);
		
		assertEquals(2, list.sizeGivenPriority(2));
		assertEquals(1, list.sizeGivenPriority(3));
	}
	
	@Test
	public void testAddFirstPrio(){
		list.addFirstWithPriority(2, "New First");
		iter = list.listIterator();
		
		out[0] = iter.next();
		assertEquals("New First", out[0]);
		
		out[1] = iter.next();
		assertEquals("First Item", out[1]);	
	}
	
	@Test
	public void testIterPos(){
		iter = list.listIterator(2);
		out[0] = iter.next();
		
		assertEquals("Third Item", out[0]);
	}
	
	@Test
	public void testAddPos(){
		list.add(2, "Third In List");
		iter = list.listIterator(2);
		out[0] = iter.next();
		
		assertEquals("Third In List", out[0]);
		
	}
	
	@Test
	public void testGet(){
		out[0] = list.get(2);
		assertEquals("Third Item", out[0]);
		out[1] = list.get(0);
		assertEquals("First Item", out[1]);
	}
	
	@Test
	public void testRemove(){
		list.remove(lol);
		int counter = 0;
		while(iter.hasNext()){
			out[counter] = iter.next();
			counter++;
		}
		
		assert(counter == list.size());
		assertEquals("First Item", out[0]);
		assertEquals("Second Item", out[1]);
		assertEquals("Third Item", out[2]);
		
	}
	
	@Test
	public void testEquals(){
		PriorityDoublyLinkedList<String> list2 = new PriorityDoublyLinkedList<>(5, 0);
		list2.add("First Item");
		list2.add("Second Item");
		list2.add("Third Item");
		list2.add(lol);
		
		assertEquals(true, list.equals(list2));
	}
	
	@Test
	public void testSet(){
		list.set(1, "New Second Item");
		out[0] = list.get(1);
		assertEquals("New Second Item", out[0]);
	}
	
	@Test
	public void testContains(){
		assertEquals(true, list.contains(lol));
		assertEquals(false, list.contains("wat"));
	}
}
