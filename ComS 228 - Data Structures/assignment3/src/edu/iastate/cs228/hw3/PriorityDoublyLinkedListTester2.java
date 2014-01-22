package edu.iastate.cs228.hw3;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.ListIterator;


import org.junit.Before;
import org.junit.Test;
/**
 * Have fun and tell me if these work right
 * @author Eric Soland
 *
 */
public class PriorityDoublyLinkedListTester2 
{

	PriorityDoublyLinkedList<Integer> list;
	
	ArrayList<Integer> list2;
	
	int[] arr = {0,1,2,3,4,5};
	
	@Before
	public void setup(){
		list = new PriorityDoublyLinkedList<Integer>(4, 1);
		list2 = new ArrayList<Integer>(10);
		
		for(int n = 0; n < arr.length; n++)
		{
			list.add(arr[n]);
			list2.add(arr[n]);
		}
	}
	
	@Test
	public void testSize()
	{
		
		String msg = "list2.size() "+list2.size()+" != list.size()" +list.size(); 
		assertEquals(msg,true,list2.size()==list.size());
	}
	
	@Test
	public void testEquals()
	{
		
		String msg = "list2 != list"; 
		assertEquals(msg,true,list.equals(list));
		assertEquals(msg,true,list.equals(list2));
		assertEquals(msg,true,list2.equals(list));
	}
	
	@Test
	public void testContains()
	{
		for(int n = 0; n < list2.size(); n++)
		{
			String msg = "list2.contains(arr[n]) "+list2.contains(arr[n])+" != list.contains(arr[n])" +list.contains(arr[n]); 
			assertEquals(msg,true,list.contains(arr[n])==list2.contains(arr[n]));
			arr[n] = arr[n] + arr.length*100;
			assertEquals(msg,true,list.contains(arr[n])==list2.contains(arr[n]));
		}
	}
	
	@Test
	public void testGet()
	{
		for(int n = 0; n < list2.size(); n++)
		{
			String msg = "list2.get(n) "+list2.get(n)+" != list.get(n)" + list.get(n); 
			assertEquals(msg,true,list.get(n)==list2.get(n));
		}
	}
	
	@Test
	public void testRemove()
	{

		list.remove(new Integer(arr[arr.length/2]));
		list2.remove(new Integer(arr[arr.length/2]));
		
		for(int n = 0; n < list2.size(); n++)
		{
			String msg = "list2.get(n) "+list2.get(n)+" != list.get(n)" + list.get(n); 
			assertEquals(msg,true,list.get(n)==list2.get(n));
		}
	}
	
	@Test
	public void testClear()
	{
		list.clear();
		list2.clear();
		
		String msg = "list2 != list"; 
		assertEquals(msg,true,list.equals(list2));
	}
	
	@Test
	public void testSet()
	{
		for(int n = 0; n < arr.length; n++)
		{   
			arr[n] = arr[n] + 3;
			list.set(n,arr[n]);
			list2.set(n,arr[n]);
		}
		
		String msg = "list2 != list"; 
		assertEquals(msg,true,list.equals(list2));
	}
	
	@Test
	public void testAddPos()
	{
		for(int n = 0; n < arr.length; n++)
		{   
			arr[n] = arr[n] + 3;
			list.add(n,arr[n]);
			list2.add(n,arr[n]);
		}
		
		String msg = "list2 != list"; 
		assertEquals(msg,true,list.equals(list2));
	}
	
	@Test
	public void testNext()
	{
		ListIterator<Integer> iter = list.listIterator();
		Integer[] array = new Integer[list.size()];
		for(int n = 0; n < arr.length; n++)
		{   
			array[n] = iter.next();
		}
		
		for(int n = 0; n < array.length; n++)
		{
			String msg = "list "+array[n] + " != list2 "+arr[n]; 
			assertEquals(msg,true,array[n].equals(arr[n]));
		}
	}
	
}