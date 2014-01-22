package edu.iastate.cs228.hw2;

/**
* @author Steven Monson
*
*/

/**
* The class consists of a public main() method and a few private methods.
* The main() method takes as input four parameters from the console
* and generates arrays of random integers and calls the sorting methods
* to sort those arrays and prints out the running times in milliseconds.
*
* The main() method takes as input
* four integers from the console. The method first prompts the user
* to enter the initial size isize of an array to be sorted.
* Then it prompts the user to enter the parameter ps.
* Next it prompts the user to enter a parameter seed, which
* is used as a seed parameter to the Random constructor
* for generating an array of random integers.
* Finally it prompts the user to enter a parameter fac, which
* is used to produce larger array sizes.
* The values for the four parameters must be at least CUT.
* If the user enters an item that is not an integer or
* enters an integer that is less than CUT,
* the method needs to handle those exceptions by printing
* a proper message to the console and waiting for another item.
* Those exceptions are handled by using
* a try-catch structure in a loop. To avoid duplicate code,
* the exception handling is identified as a private method.
*
* The sorting methods are tested as follows.
* In the first application, write and use a private method named isAscending
* to check if the array sorted by InsertionSorter.insertionSort() is indeed
* in ascending order. If not, throw a RuntimeException. 
* In addition, write and use a private method named isEqual to check if the arrays sorted
* by InsertionSorter.insertionSort() and QuickSorter.quickSortRec()
* are equal (i.e. of the same length and have the identical value at each index).
* If not, throw a RuntimeException. 
*
* In the second application, call isAscending to check if
* the array sorted by QuickSorter.quickSortRec() is indeed
* in ascending order. If not, throw a RuntimeException. 
* In addition, call isEqual to check if the arrays sorted
* by QuickSorter.quickSortRec() and QuickSorter.quickSortNonRec()
* are equal, If not, throw a RuntimeException. 
* Also call isEqual to check if the arrays sorted by QuickSorter.quickSortRec() and
* CountingSorter.countingSort() are equal. If not, throw a RuntimeException. 
*/

import java.util.Random;
import java.util.Scanner;

public class  SortClient
{ 
	static private final int CUT = 2;

	

  /**
  * @throws RuntimeException if the array sorted by InsertionSorter.insertionSort() 
  * is not in ascending order.
  *
  * @throws RuntimeException if the arrays sorted
  * by InsertionSorter.insertionSort() and QuickSorter.quickSortRec()
  * are not equal.
  *
  * @throws RuntimeException if the array sorted by QuickSorter.quickSortRec()
  * is not in ascending order.
  *
  * @throws RuntimeException if the arrays sorted
  * by QuickSorter.quickSortRec() and QuickSorter.quickSortNonRec()
  * are not equal.
  *
  * @throws RuntimeException if the arrays sorted by QuickSorter.quickSortRec() and
  * CountingSorter.countingSort() are not equal. 
  */

  public static void main( String[] args )
  { 
	  
	Scanner input = new Scanner(System.in);
	int iSize, ps, seed, fac, size2, maxNum;
	long start, stop, total;

	iSize = getInt(input, "Please enter an array size (>=2): ");
	ps = getInt(input, "Please enter a ps integer (>=2): ");
	seed = getInt(input, "Please enter a seed integer(>=2): ");
	fac = getInt(input, "Please enter a fac integer (>=2): ");
	
	System.out.println("\n\n");
	
	System.out.printf("SortingMethodName  ArraySize  SortingTimeInMilliseconds  DepthOfRecursion\n\n");
	
	int[] a1 = genArray(iSize, seed);
	int[] b1 = copyArray(a1);
	
	start = System.currentTimeMillis();
	InsertionSorter.insertionSort(a1);
	stop = System.currentTimeMillis();
	total = stop - start; //This sort is extremely inefficient I have no idea why. I've talked to 3 TA's and none know what is wrong with it
	System.out.printf("%-19s%-11d%-27d\n", "insertionSort", iSize, total);
	
	start = System.currentTimeMillis();
	QuickSorter.quickSortRec(b1, ps);
	stop = System.currentTimeMillis();
	total = stop - start;
	System.out.printf("%-19s%-11d%-27d\n", "quickSortRec", iSize, total);
	
	if(!isAscending(a1))
		throw new RuntimeException("a1 is not sorted correctly");
	if(!isEqual(a1, b1))
		throw new RuntimeException("a1 != b1");
	
	
	size2 = iSize * fac;
	int[] a2 = genArray(size2, seed);
	int[] b2 = copyArray(a2);
	int[] c2 = copyArray(a2);
	
	start = System.currentTimeMillis();
	CountingSorter.countingSort(a2);
	stop = System.currentTimeMillis();
	total = stop - start;
	System.out.printf("%-19s%-11d%-27d\n", "countingSort", size2, total);

	
	start = System.currentTimeMillis();
	QuickSorter.quickSortRec(b2, ps);
	stop = System.currentTimeMillis();
	total = stop - start;
	System.out.printf("%-19s%-11d%-27d\n", "quickSortRec", size2, total);
	
	start = System.currentTimeMillis();
	maxNum = QuickSorter.quickSortNonRec(c2, ps);
	stop = System.currentTimeMillis();
	total = stop - start;
	System.out.printf("%-19s%-11d%-27d%d\n", "quickSortNonRec", size2, total, maxNum);
	
	if(!isAscending(b2))
		throw new RuntimeException("b2 is not sorted correctly");
	if(!isEqual(b2, c2))
		throw new RuntimeException("b2 != c2");
	if(!isEqual(b2, a2))
		throw new RuntimeException("b2 !- a2");
	
  }


  // Gets an integer from the console.
  // If the entered item is not an integer or its value is less than CUT,
  // then prints a message to the console and waits for another item.
  private static int getInt(Scanner sysIn, String mes)
  {
	  int value;
	  
	  while(true){
		try{
			System.out.print(mes);
			if(sysIn.hasNextInt())
				value = sysIn.nextInt();
			else{
				sysIn.nextLine();
				throw new Exception();
			}
				
			if(value < CUT)
				throw new Exception();
			return value;
		} catch(Exception e){
			 System.out.print("Please enter a integer that's at least 2\n");
		}
		  
	  }
     }

  // Returns a random array of length size with seed as a parameter for Random.
  // All integer values in the array are between 0 (inclusive) and size (exclusive).
  private static int[] genArray(int size, long seed)
  {
	  Random rand = new Random(seed);
	  int[] randArr = new int[size];
	  for(int i = 0; i < randArr.length; i++)
		  randArr[i] = rand.nextInt(size);
	return randArr; 
  }

  // Returns a copy of an array.
  private static int[] copyArray(int[] arr)
  {
	  int[] b1 = new int[arr.length];
	  System.arraycopy(arr, 0, b1, 0, arr.length);
	  
	  return b1;
  }

  // Returns true if the array is sorted in ascending order and false otherwise.
  private static boolean  isAscending(int[] arr)
  {
	  for(int i = 0; i < arr.length - 1; i++){
		  if(arr[i] > arr[i + 1])
			  return false;
	  }
	  return true; 
  }

  // Returns true if the two arrays are of the same length and
  // have the identical value at each index and false otherwise.
  private static boolean  isEqual(int[] arr, int[] vec)
  {
	  if(arr.length != vec.length)
		  return false;
	  for(int i = 0; i < arr.length; i++){
		  if(arr[i] != vec[i])
			  return false;
	  }
	  return true;
  }
 
}
