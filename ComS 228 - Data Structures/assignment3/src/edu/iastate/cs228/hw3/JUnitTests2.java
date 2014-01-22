package edu.iastate.cs228.hw3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

import edu.iastate.cs228.hw3.PriorityDoublyLinkedList;

import org.junit.Before;
import org.junit.Test;
/**
 * Have fun and tell me if these work right.
 * Fix the Lvl1 tests before the Lvl2 tests, and certainly before the lvl3 or lvl4.
 * @author Eric Soland
 *
 */
public class JUnitTests2 
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
	public void lvl1_testSize()
	{
		
		String msg = "list2.size() "+list2.size()+" != list.size()" +list.size(); 
		assertEquals(msg,true,list2.size()==list.size());
	}
	
	@Test
	public void lvl1_testEquals()
	{
		
		String msg = "list2 != list"; 
		assertEquals(msg,true,list.equals(list));
		assertEquals(msg,true,list.equals(list2));
		assertEquals(msg,true,list2.equals(list));
	}
	
	@Test
	public void lvl1_testContains()
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
	public void lvl1_testGet()
	{
		for(int n = 0; n < list2.size(); n++)
		{
			String msg = "list2.get(n) "+list2.get(n)+" != list.get(n)" + list.get(n); 
			assertEquals(msg,true,list.get(n)==list2.get(n));
		}
	}
	
	@Test
	public void lvl2_testRemove()
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
	public void lvl2_testIsEmpty()
	{
		assertEquals("Both should be filled, so it should be true",true,list.isEmpty()==list2.isEmpty());
		
		for(int n = 0; n < list2.size(); n++)
		{
			list.remove(new Integer(arr[n]));
			list2.remove(new Integer(arr[n]));
		}
		
		assertEquals("Both should be empty, so it should be true",true,list.isEmpty()==list2.isEmpty());
		
	}
	
	@Test
	public void lvl2_testClear()
	{
		list.clear();
		list2.clear();
		
		String msg = "list2 != list"; 
		assertEquals(msg,true,list.equals(list2));
	}
	
	@Test
	public void lvl2_testSet()
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
	public void lvl2_testAddPos()
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
	public void lvl2_testNext()
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
	
	@Test
	public void lvl3_test_AddWithPriority()
	{
		int[] arr2 = {7,7,6,5,5,4,4,3,2,1,0};
		
		PriorityDoublyLinkedList<Integer> list = new PriorityDoublyLinkedList<Integer>(arr2[0]+1,1);
		
		//Fill ArrayList with new Array
		randomizeArray(arr2);
		fillArrayList(list2,arr2);
		
		for(int n = 0; n < arr2.length; n++)
		{list.addWithPriority(arr2[n], new Integer(arr2[n]));}
		
		String msg = "list2 != list";
		boolean isGood = list.equals(list2);
		if(!isGood) System.out.println(PriorityDoublyLinkedListToString(list,"LinkedList") 
				+ "\n" + ArrayListToString(list2, "ArrayList"));
		assertEquals(msg,true,isGood);
	}
	/**/
	@Test
	public void lvl4_testAddFirstWithPriority()
	{
		int[] arr2 = {7,7,6,5,5,4,4,3,2,1,0};
		
		int p = 0;
		int a = 1;
		int[][] arrP = {{6,5,4,3,2,1},
				        {0,1,2,8,4,5}};
		int[] result = {5,4,8,2,1,0,7,7,6,5,5,4,4,3,2,1,0};
		
		//Create and fill List - in a fun fashion
		PriorityDoublyLinkedList<Integer> list = new PriorityDoublyLinkedList<Integer>(arr2[0]+1,1);
		
		//Fill ArrayList with Result
		fillArrayList(list2,result);
		
		for(int n = 0; n < arr2.length; n++)
		{list.addWithPriority(arr2[n], new Integer(arr2[n]));}
		
		//Now add new values first with different priorities
		for(int n = 0; n < arrP[p].length; n++)
		{list.addFirstWithPriority(arrP[p][n], new Integer(arrP[a][n]));}
		
		//Check for Equality
		String msg = "list2 != list";
		boolean isGood = list.equals(list2);
		if(!isGood) System.out.println(PriorityDoublyLinkedListToString(list,"LinkedList") 
				+ "\n" + ArrayListToString(list2, "ArrayList"));
		assertEquals(msg,true,isGood);
	}/**/
	
	@Test  //This tests a list when priorities are not in order
	public void lvl4_test_AddPriority_WithMixedPriorities()
	{
		int[] arr2 = {3,3,3,3,2,1,1,0};
		
		PriorityDoublyLinkedList<Integer> list = new PriorityDoublyLinkedList<Integer>(arr2[0]+1,1);
		
		//Fill ArrayList with new Array
		fillArrayList(list2,arr2);
		//randomizeArray(arr2);
		
		//Fill list and have priorities
		for(int n = 0; n < arr2.length; n++)  
		{list.addWithPriority(arr2[n], new Integer(arr2[n]));}  //Current list = {3,3,3,3,2,1,1,0}
		 														//Priorities   = {3,3,3,3,2,1,1,0}
		//get more values to add
		int[] arr3 = {7,8,9,7,8,9};
	
		//this adds those values based on position
		for(int n = 0; n < arr3.length; n++)
		{  list.add(2, new Integer(arr3[n])); }  //Current list = {3,3,9,8,7,9,8,7,3,3,2,1,1,0} ???
												 //Priorities   = {3,3,1,1,1,1,1,1,3,3,2,1,1,0}
		//Create Iterators
		ListIterator<Integer> iter1 = list.iteratorWithPriority(1); //Should use only {9,8,7,9,8,7,1,1}
		ListIterator<Integer> iter3 = list.iteratorWithPriority(3); //Should use only {3,3,3,3}
		
		//Test priority 1
		int[] R1arr = {9,8,7,9,8,7,1,1};
		for(int n = 0; n < R1arr.length; n++)
		{
			int value = iter1.next();
			String msg = "Index "+n+" - Expected: " + R1arr[n] + "; Result: " + value;
			assertEquals(msg,true,R1arr[n] == value);
		}
		
		assertEquals("Should not have more",false,iter1.hasNext());
		
		//Test priority 3
		int[] R3arr = {3,3,3,3};
		for(int n = 0; n < R3arr.length; n++)
		{
			int value = iter3.next();
			String msg = "Expected: " + R3arr[n] + "; Result: " + value;
			assertEquals(msg,true,R3arr[n] == value);
		}
		
		assertEquals("Should not have more",false,iter3.hasNext());

	}
	
	/**
	 * This method randomizes an int array
	 * @param arr
	 * the array of ints
	 */
	private void randomizeArray(int[] givenAwesomeArray)
	{
		Random rand = new Random(System.currentTimeMillis());
		
		for(int n = givenAwesomeArray.length-1; n > 0 ; n--)
		{
			int die = rand.nextInt(n);
			int value = givenAwesomeArray[n];
			givenAwesomeArray[n] = givenAwesomeArray[die];
			givenAwesomeArray[die] = value;
		}
	}
	
	/**
	 * This method fills an ArrayList with a given array
	 * @param list
	 * the arrayList
	 * @param arr
	 * the array
	 */
	public static void fillArrayList(ArrayList<Integer> list, int[] arr)
	{
		list.clear();
		for(int n = 0; n < arr.length; n++)
		{
			list.add(arr[n]);
		}
		
	}
	
	public static String ArrayListToString(ArrayList<Integer> list,String title)
	{
		String result = title + ": { ";
		for(int n = 0; n < list.size(); n++)
		{
			if(n!=0) result = result + " , ";
			result = result + list.get(n);
			
		}
		result = result + " }";
		return result;
	}
	
	public static String PriorityDoublyLinkedListToString(PriorityDoublyLinkedList<Integer> list,String title)
	{
		String result = title + ": { ";
		for(int n = 0; n < list.size(); n++)
		{
			if(n!=0) result = result + " , ";
			result = result + list.get(n);
			
		}
		result = result + " }";
		return result;
	}
	
}