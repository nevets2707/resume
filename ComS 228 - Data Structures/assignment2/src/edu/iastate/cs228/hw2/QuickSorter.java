package edu.iastate.cs228.hw2;

/**
 *
 * @author Steven Monson
 *
*/

/**
 * This class contains two public and two private methods:
 * One method is a wrapper method for a private recursive version of
 * the quicksort algorithm, and the other public method is a non-recursive version of
 * the quicksort algorithm. The second private method is shared by
 * the recursive and non-recursive versions of the quicksort algorithm.
 */

import java.util.Random;
import java.util.Stack;

public class QuickSorter
{ 
  /**
   * Sorts the given array in ascending order using the nonrecursive quicksort
   * algorithm that keeps a stack of integer pairs and returns the maximum number
   * of integer pairs in the stack.
   * Pushing a pair of integers start and end onto the stack means that the subarrary
   * from index start to index end needs to be sorted in the future.
   * An Integer stack is used to save pairs of integers. A pair of integers is saved
   * on the stack by performing two push operations in a row, one integer per push.
   * Similarly, a pair of integers is removed from the stack by perofrming two pop
   * operations in a row, one integer per pop.
   * Initially, the algorithm just pushes onto the stack the pair of integers that represent
   * the entire array.
   * The main body of the algorithm is a while loop conditioned on the stack being not empty.
   * In each iteration of the while loop, the algorithm pops up a pair of integers from the stack.
   * It simply continues with the next iteration if the pair of integers represents a subarray
   * with at most one element. Otherwise, if the number of elements in the subarray is
   * less than ps, the algorithm sorts the subarray with the insertion sort algorithm
   * and continue with the next iteration. Otherwise, the algorithm calls the private
   * partition() method to partition the subarray into two subarrays to be sorted, and
   * pushes the two corresponding pairs of integers into the stack, and continue with
   * the next iteration. Upon termination of the while loop, the algorithm returns
   * the maximum number of integer pairs that the stack holds over the course of the execution.
   * @param  arr array to be sorted
   * @param  ps two roles: (1) cutoff on the size of a subarray for using insertionSort() and
   *         (2) size of a pool of elements from which a pivotal element is selected.
   * @return maximum number of integer pairs in the stack
   * @throws IllegalArgumentException if arr is null pointer, its length is 0, or ps < 2.
   */
  public static int quickSortNonRec(int[] arr, int ps)
  {
	  Stack<Integer> list = new Stack<Integer>();
	  int start = 0;
	  int end = arr.length - 1;
	  int mid;
	  int maxNum = 0;
	  
	  if(arr == null || arr.length < 1 || ps < 2)
		  throw new IllegalArgumentException();
	  
	  list.push(start);
	  list.push(end);
	  
	 
	  while(!list.empty()){
		  end = (int) list.pop();
		  start = (int) list.pop();
		  
		  
		  if(end - start == 0){
			  
		  } else if(end - start < ps){
			  InsertionSorter.insertionSort(arr, start, end);
		  } else {
			  mid = partition(arr, start, end, ps);
			
			  list.push(start);
			  list.push(mid);
			  
			  list.push(mid + 1);
			  list.push(end);
			  
			  if(list.size() > maxNum)
				  maxNum = list.size();
			  
		  }		  
	  }

	return maxNum / 2;
  }
  
  /**
   * Sorts the given array in ascending order using the recursive quicksort algorithm.
   * The algorithm calls the private quickSortRecPriv() method on the entire array.
   * @param arr array to be sorted
   * @param  ps has two roles: (1) cutoff on the size of a subarray for using insertionSort() and
   *         (2) size of a pool of elements from which a pivotal element is selected.
   * @throws IllegalArgumentException if arr is null pointer, its length is 0, or ps < 2.
   */
  public static void quickSortRec(int[] arr, int ps)
  {
	  if(arr == null || arr.length < 1 || ps < 2)
		  throw new IllegalArgumentException();
	  
	  quickSortRecPriv(arr, 0, arr.length - 1, ps);
  }

  /**
   * Sorts the subarray consisting of positions first through last
   * using a recursive quicksort algorithm. The subarray is sorted in ascending order.
   * Simply returns if the subarray has one element.
   * If the no. of elements in the subarray is less than ps, calls the insertion sort algorithm
   * to sort the subarray and returns. Otherwise, calls the partition method to get an index mid
   * and recursively sort each of the subarrays from first to mid and from mid + 1 to last.
   * @param arr array to be sorted
   * @param first index of first position in subarray
   * @param last index of last position in subarray
   * @param  ps two roles: (1) cutoff on the size of a subarray for using insertionSort() and
   *         (2) size of a pool of elements from which a pivotal element is selected.
   * @throws IllegalArgumentException if ps < 2, or first < 0, or last >= a.length, or first > last.
   * Those exceptions are useful in debugging your code.
   */
  private static void quickSortRecPriv(int[] arr, int first, int last, int ps)
  {
	  int mid;
	  
	  if(ps < 2 || first < 0 || last >= arr.length || first > last)
		  throw new IllegalArgumentException();
	  
	  if(last - first == 0)
		  return;
	  
	  if(last - first < ps){
		  InsertionSorter.insertionSort(arr, first, last);
		  return;
	  }
	  
	  mid  = partition(arr, first, last, ps);
	  quickSortRecPriv(arr, first, mid, ps);
	  quickSortRecPriv(arr, mid + 1, last, ps);
  }
  
  /**
   * Selects a pivotal element pivot and partitions the subarray
   * from index first to index last into two subarrays from first to mid
   * and from mid + 1 to last such that first <= mid < last, arr[j] <= pivot for first <= j <= mid,
   * and arr[j] >= pivot for mid + 1 <= j <= last. Returns the index mid.
   * The pivotal element pivot is found by selecting a random subarray range of size ps
   * from start to end (with end - start + 1 = ps and first <= start and end <= last)
   * and sorting the subarray from index start to index end with the insertion sort algorithm
   * and setting the pivotal element pivot to the element at index (start + end) / 2.
   * Then the method follows the partition algorithm discussed in class to partition
   * the subarray into two subarrays and returns the index mid.
   * In the last step of the method, if the partition algorithm discussed in class produces the index mid
   * that is equal to last, set mid to last - 1.
   * @param arr array to be sorted
   * @param first index of first position in subarray
   * @param last index of last position in subarray
   * @param ps size of a pool of elements from which a pivotal element is selected.
   * @return mid with first <= mid < last such that arr[j] <= pivot for first <= j <= mid and
   *         arr[j] >= pivot for mid + 1 <= j <= last.
   * @throws IllegalArgumentException if ps < 2, or first < 0, or last >= a.length, or last - first + 1 < ps.
   * @throws RuntimeException if mid < first.
   */
  private static int partition(int[] arr, int first, int last, int ps)
  {
	  int pivot;
	  int left = first;
	  int right = last;
	  int r, start, end, mid;
	  Random rand = new Random();
	  
	  if(ps < 2 || first < 0 || last >= arr.length || last - first < ps)
		  throw new IllegalArgumentException();
	  
	  int limit = last - ps + 1;
	  if(limit > first){
		  r = rand.nextInt(limit - first);
		  start = first + r;
	  }else
		  start = first;
	  end = start + ps - 1;
	  
	  InsertionSorter.insertionSort(arr, start, end);
	  pivot = arr[(start + end) / 2];
	  
	  while(true){
		  while(arr[left] < pivot)
			  left++;
		  while(arr[right] > pivot)
			  right--;
		  if(left < right){
			  int temp = arr[left];
			  arr[left] = arr[right];
			  arr[right] = temp;
			  left++;
			  right--;
		  }else{
			  break;
		  }
	  }
	  
	  mid = right;
	  if(mid == last)
		  mid--;
	  if(mid < first)
		  throw new RuntimeException();  //TODO get this checked
	  return mid;
  }

}
