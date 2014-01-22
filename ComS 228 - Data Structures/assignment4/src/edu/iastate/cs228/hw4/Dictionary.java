package edu.iastate.cs228.hw4;

/*
 * @author Steven Monson
 *
 * An application class
*/

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Dictionary
{
  public static void main(String[] args) throws FileNotFoundException
  {
	  
	  File input = new File(args[0]);
	  Scanner in = new Scanner(input);
	  EntryTree<Character, String> tree = new EntryTree<>();
	  String command;
	  Character[] key = null;
	  
	  while(in.hasNext()){
		  command = in.next();
		  
		  System.out.println("Command: " + command);
		  if(command.equals("showTree")){
			  tree.showTree();
			  System.out.println();
		  }else{
			  char[] temp = in.next().toCharArray();
			  key = new Character[temp.length];
			  for(int i = 0; i < temp.length; i++)
				  key[i] = temp[i];
		  }
		  
		  if(command.equals("add")){
			 boolean result = tree.add(key, in.next());
			 System.out.println(result); 
			 System.out.println();
		  }else if(command.equals("search")){
			  String result = tree.search(key);
			  System.out.println(result);
			  System.out.println();
		  }else if(command.equals("prefix")){
			  Character[] result = tree.prefix(key);
			  String r = "";
			  if(result != null){
				  for(Character c : result)
					  r += c;
			  }
			  System.out.println(r);
			  System.out.println();
		  
		  }else if(command.equals("remove")){
			  String result = tree.remove(key);
			  System.out.println(result);
			  System.out.println();
		  }
	  }
  }
}
