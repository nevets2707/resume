package edu.iastate.cs228.hw2;

/**
 *
 * @author Steven Monson 
 *
*/

/**
 * This class consists of a static method for sorting a non-negative integer
 * array using the counting sort algorithm.
 */

public class CountingSorter
{
  /**
   * Sorts the given array in ascending order using the counting sort algorithm.
   * @param arr the array to be sorted
   * @throws IllegalArgumentException if arr is a null pointer or its length is 0
   *         or it has a negative element.
   */
  public static void countingSort(int[] arr)
  {
    if(arr == null || arr.length < 1)
    	throw new IllegalArgumentException();
    
    int maxkey = 0;
    
    for(int i : arr){
    	if(i < 0){
    		throw new IllegalArgumentException();
    	}
    	if(i > maxkey)
    		maxkey = i;
    }
    
    int[] keycnt = new int[maxkey + 1];
    
    
    for(int i : arr)
    	keycnt[i]++;
    
    int counter = 0;
    
    for(int i = 0; i < keycnt.length; i++){
    	for(int j = 0; j < keycnt[i]; j++){
    		arr[counter] = i;
    		counter++;
    	}
    }
  }
}
