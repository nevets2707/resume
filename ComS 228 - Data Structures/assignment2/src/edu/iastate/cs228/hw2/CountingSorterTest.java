package edu.iastate.cs228.hw2;


import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class CountingSorterTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void testArgumentException() throws IllegalArgumentException {
		int[] arr = null;
		CountingSorter.countingSort(arr);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testEmptyArray() throws IllegalArgumentException {
		int[] arr = {};
		CountingSorter.countingSort(arr);
	}
	
	@Test
	public void testOneElement() {
		int[] arr = {1};
		CountingSorter.countingSort(arr);
	}
	
	@Test
	public void fullTest() {
		Random r = new Random();
		
		int[] arr1 = new int[r.nextInt(1000)+1000];
		int[] arr2 = new int[arr1.length];
		
		for(int i = 0; i < arr1.length; i++) {
			arr1[i] = r.nextInt(1000);
			arr2[i] = arr1[i];
		}
		
		CountingSorter.countingSort(arr1);
		Arrays.sort(arr2);
		
		Assert.assertArrayEquals(arr1,  arr2);
	}
}
