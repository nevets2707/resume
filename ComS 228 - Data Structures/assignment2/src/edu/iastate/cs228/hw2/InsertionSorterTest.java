package edu.iastate.cs228.hw2;

import java.util.Arrays;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class InsertionSorterTest
{
	private int[] arr;
	private String msg;

	@Before
	public void setup()
	{
		arr = new int[] { 4, 1, 3, 5, 2 };
	}

	@After
	public void cleanup()
	{
		arr = null;
	}

	@Test
	public void arrayElementCheck()
	{
		msg = "After initialization, the array should hold its correct elements.";
		assertEquals(msg, "[4, 1, 3, 5, 2]", Arrays.toString(arr));
	}

	@Test
	public void insertionSortTest1()
	{
		msg = "After calling insertionSort(), the array should be sorted in ascending order.";
		InsertionSorter.insertionSort(arr);
		assertEquals(msg, "[1, 2, 3, 4, 5]", Arrays.toString(arr));
	}

	@Test
	public void insertionSortTest2()
	{
		msg = "After calling insertionSort(), the array should be sorted in ascending order.";
		arr = new int[] { -25, 5, 40, 0, 1 };
		InsertionSorter.insertionSort(arr);
		assertEquals(msg, "[-25, 0, 1, 5, 40]", Arrays.toString(arr));
	}

	public void insertionSortTest3()
	{
		msg = "After calling insertionSort(), the array should be sorted in ascending order.";
		arr = new int[] { 1, 1, 5, 5, 2 };
		InsertionSorter.insertionSort(arr);
		assertEquals(msg, "[1, 1, 2, 5, 5]", Arrays.toString(arr));
	}

	public void insertionSortTest4()
	{
		msg = "After calling insertionSort(), the array should be sorted in ascending order.";
		arr = new int[] { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 };
		InsertionSorter.insertionSort(arr);
		assertEquals(msg, "[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1  ]", Arrays.toString(arr));
	}

	public void insertionSortTest5()
	{
		msg = "After calling insertionSort(), the array should be sorted in ascending order.";
		arr = new int[] { 5, 4, 3, 2, 1 };
		InsertionSorter.insertionSort(arr, 1, 3);
		assertEquals(msg, "[5, 2, 3, 4, 1]", Arrays.toString(arr));
	}
}
