package edu.iastate.cs228.hw2;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

// TODO - Check Exceptions

/**
 * This is my awesome unit testing program, sure to find any errors in your array sorting
 * This does not check Exceptions, or the SortClient Class so make sure those work
 * My utility methods work perfectly, and are not relevant to the assignment.   
 * @author Eric Soland
 * 
 */
public class UnitTestSort 
{
	private static final Random rand = new Random(5993);
	private static final int MAXARRAYS = 20;
	private static final int ARRAYSIZE = 1000;
	private static final int MAXINTERVAL = 5;
	private static final int STANDARDMIN = -20;
	private static final int ORIGINAL = 0;
	private static final int COPY = 1;
	private static int[][][] grid;
	
	public void Setup(int min)
	{
		grid = new int[ARRAYSIZE][MAXARRAYS][2];
		
		for(int n = 0; n < MAXARRAYS; n++)
		{
			grid[n][ORIGINAL] = createRandomSortedIntArray(MAXINTERVAL, ARRAYSIZE, 0, rand);
			grid[n][COPY] = randomize(grid[n][ORIGINAL],rand);
		}
	}
	
	@Test
	public void InsertSort_test()
	{
		Setup(STANDARDMIN);
		for(int n = 0; n < MAXARRAYS; n++)
		{
			int [] a = grid[n][ORIGINAL];
			int [] b = grid[n][COPY];
			InsertionSorter.insertionSort(b);	
			boolean isEqual = ArrayToString(a).equals(ArrayToString(b));
			if(!isEqual)System.out.println(ArrayToString(a) + "\n" +  ArrayToString(b));
		    assertEquals(true,isEqual);
		}
	}
	
	@Test
	public void CountSort_test()
	{
		Setup(0);
		for(int n = 0; n < MAXARRAYS; n++)
		{
			int [] c = grid[n][ORIGINAL];
			int [] d = grid[n][COPY];
			CountingSorter.countingSort(d);	
			boolean isEqual = ArrayToString(c).equals(ArrayToString(d));
			if(!isEqual)System.out.println(ArrayToString(c) + "\n" +  ArrayToString(d));
		    assertEquals(true,isEqual);
		}
	}
	
	@Test
	public void QuickSortNonRec_test()
	{
		Setup(STANDARDMIN);
		for(int n = 0; n < MAXARRAYS; n++)
		{
			int [] a = grid[n][ORIGINAL];
			int [] b = grid[n][COPY];
			QuickSorter.quickSortNonRec(b,2);	
			boolean isEqual = ArrayToString(a).equals(ArrayToString(b));
			if(!isEqual)System.out.println(ArrayToString(a) + "\n" +  ArrayToString(b));
		    assertEquals(true,isEqual);
		}
	}
	
	@Test
	public void QuickSortRec_test()
	{
		Setup(STANDARDMIN);
		for(int n = 0; n < MAXARRAYS; n++)
		{
			int [] a = grid[n][ORIGINAL];
			int [] b = grid[n][COPY];
			QuickSorter.quickSortRec(b,2);	
			boolean isEqual = ArrayToString(a).equals(ArrayToString(b));
			if(!isEqual)System.out.println(ArrayToString(a) + "\n" +  ArrayToString(b));
			assertEquals(true,isEqual);
		}
	}
	
	///////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////
	//
	//
	//		Awesome Util Methods
	//
	//
	///////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////
	
	/**
	 * This method creates a random sorted list
	 * @param MaxInterval
	 * this number refers to how much space you want to be possible between numbers
	 * @param size
	 * this is the size of the array
	 * @param min
	 * this is the min number of the array that you want, and it can be negative
	 * @param rand
	 * this is the random number generator that will be used in this method
	 * @return
	 * the new sorted array
	 */
	public static int[] createRandomSortedIntArray(int MaxInterval, int size, int min, Random rand)
	{
		if(size < 1|| MaxInterval < 1) throw new IllegalArgumentException();
		if(rand == null) throw new NullPointerException();
		
		int[] list = new int[size];
		
		int num = min;
		
		for(int n = 0; n < size; n++)
		{	
			int x = rand.nextInt(100);
			if(x > 20) 
				{num = num + rand.nextInt(MaxInterval)+1;}
			
			list[n] = num;
		}
		
		return list;
	}
	
	/**
	 * This method returns a randomized copy of the array given
	 * @param arr
	 * the array that we want a randomized copy of
	 * @param rand
	 * the random number generator
	 * @return 
	 * a randomized copy of the array given
	 */
	public static int[] randomize(int[] arr, Random rand)
	{
		if(rand == null || arr == null) throw new NullPointerException();
		if(arr.length < 1) throw new IllegalArgumentException();
		
		int[] list = new int[arr.length];
		
		//In a very weird and nothing like the assignment way, 
		//move all the values from the original to the new array
		int j = 0;
		for(int k = 0; k < 5; k++)
		{	
			for(int n = arr.length-1-k; n >= 0; n = n - 5)
			{
				if(j >= arr.length)break;
				
				list[n] = arr[j];
				j++;
				
			}
		}
		
		//Randomize new array some more
		for(int n = list.length - 1; n > 0 ; n--)
		{
			int num = rand.nextInt(n);
			
			int value = list[num];
			list[num] = list[n];
			list[n] = value;
		}
		
		return list;
	}
	
	/**
	 * This method returns the array in a string form
	 * @param arr
	 * the array
	 * @return
	 * string of array
	 */
	public static String ArrayToString(int[] arr)
	{
		String result = "{ ";
		
		for(int n = 0; n < arr.length; n++)
		{
			
			result = result + arr[n];
			if(n != arr.length - 1) result = result + ", ";
			
		}
		result = result + " }";
		return result;
		
	}
}