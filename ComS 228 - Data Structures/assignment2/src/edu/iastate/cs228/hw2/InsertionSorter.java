package edu.iastate.cs228.hw2;

/**
 *
 * @author Steven Monson
 *
*/


/**
 * This class consists of two static methods for sorting an integer
 * array using the insertion sort algorithm.
 */

public class InsertionSorter
{
  /**
   * Sorts the given array in ascending order using the insertion sort algorithm.
   * To avoid code duplication, the method calls the next method on the whole array.
   * @param arr the array to be sorted
   * @throws IllegalArgumentException if arr is a null pointer or its length is 0.
   */
  public static void insertionSort(int[] arr)
  {
    if(arr == null || arr.length < 1)
    	throw new IllegalArgumentException();
    
    insertionSort(arr, 0, arr.length - 1);
  }

  /**
   * Sorts the subarray between first and last (inclusive) in ascending order
   * using the insertion sort algorithm.
   * @param arr the array to be sorted
   * @param first index of first position in subarray
   * @param last index of last position in subarray
   * @throws IllegalArgumentException if arr is null pointer, its length is 0,
   *         first < 0, last >= a.length, or first > last. 
   */
  public static void insertionSort(int[] arr, int first, int last)
  {
    if(arr == null || arr.length < 1 || first < 0 || last >= arr.length || first > last){
    	throw new IllegalArgumentException();
    }
    
    int temp;
    int j;
    
    for(int i = first + 1; i <= last; i++){
    	
    	temp = arr[i];
    	
    	
    	for(j = i-1; j >= first && arr[j] > temp; j--)
    		arr[j+1] = arr[j];
    	arr[j + 1] = temp;
    		
    	
    	
    }
  }

}
