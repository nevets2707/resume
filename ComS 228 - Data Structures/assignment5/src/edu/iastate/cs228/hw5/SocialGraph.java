package edu.iastate.cs228.hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

import javax.print.attribute.standard.Finishings;

/**
 * Create a graph to model a social network of friendships.
 * See the homework description.
 * 
 * @author Steven Monson
 *
 */

public class SocialGraph {
	
	/** 
	 * Create an empty graph aGraph.
	 * Parse each line, print the entire line on the console, and call the corresponding method.
	 * 
	 * The command is case sensitive.
	 * Assume that the file format is correct.
	 * 
	 * add arg1 arg2 arg3
	 *  call aGraph.addEdge(arg1, arg2, Integer.parseInt(arg3))
	 *  
	 * showFriends arg1 
	 * 	call aGraph.adjacentTo(arg1)
	 * 
	 * remove arg1 
	 * 	call aGraph.remove(arg1)
	 * 
	 * recommendFriends arg1 arg2 arg3
	 *  call the recommendFriends(aGraph, arg1, arg2, Integer.parseInt(arg3)) 
	 *  where arg1 is the name of the person to recommend new friends for;
	 *        arg2 is either "dist" or "weightedDist" indicating the method 
	 *        to select people to recommend as new friends;
	 *        Integer.parseInt(arg3) is the maximum number of new friends to recommend
	 * 
	 * @param args args[0] Input filename with all the commands and arguments
	 * @throws FileNotFoundException 
	 */
	
	
	public static void main(String[] args){
		if(args.length < 1)
			System.out.println("WAT");
		Scanner in = null;
		try{
			File input = new File(args[0]);
			in = new Scanner(input);
		}catch(FileNotFoundException e){
			System.out.println(e);
		}
		
		String nextLine;
		String[] command;
		DiGraph<String> graph = new DiGraph<>();
		
		while(in.hasNext()){
			nextLine = in.nextLine();
			command = nextLine.split(" ");
			System.out.println(nextLine);
			
			if(command[0].equals("add"))
				graph.addEdge(command[1], command[2], Integer.parseInt(command[3]));
			
			if(command[0].equals("showFriends")){
				Set<Edge<String, Integer>> s  = graph.adjacentTo(command[1]);
				System.out.println(s);
			}
			
			if(command[0].equals("recommendFriends")){
				List<Edge<String, Integer>> recommend = recommendFriends(graph, command[1], command[2], Integer.parseInt(command[3]));
				System.out.println(recommend);
			}
			
			if(command[0].equals("remove"))
				graph.removeVertex(command[1]);
		}
	}
	
	/**
	 * Recommend topK (e.g., 5) best friend candidates who are not already a friend of personOfInterest
	 * 
	 * If dist option is used, find the shortest path from personOfInterest to all the other nodes in the graph using 
	 * Dijkstra's single source shortest path algorithm and friendship distances. The smaller the distance means the 
	 * closer the relationship. 
	 * 
	 * If weightedDist option is used, after computing the shortest path like in the dist option to all the other nodes 
	 * in the graph, multiply each distance with the total number of edges in the graph less the number of incoming edges 
	 * to that node. 
	 * 
	 * For instance, suppose the graph has a total of 10 edges. 
	 * 
	 * Suppose the shortest distance from personOfInterest to node A is 5 and there are 4 incoming edges to A, 
	 * the weighted distance is 5*(10-4)=30. The lower the weighted distance, the better the candidate.
	 * 
	 * This method considers both distance and popularity. The person with a lot of incoming edges 
	 * means that the person is likely more well-liked by other people and should be recommended.
	 * 
	 * Sort the distance/weighted distance in increasing order.
	 * 
	 * If there are less than topK candidates, return only those candidates.
	 * If there are more than topK candidates, return only the topK candidates when there are no other candidates with 
	 * the same distance/weighted distance as the last candidate in the topK list.
	 * If there are other candidates with the same distance/weighted distance as the last candidate in the topK list, 
	 * return all the candidates with the same distance. In this case, more than topK candidates are included in 
	 * the list.
	 *  
	 * @param g Graph of friendships
	 * @param personOfInterest Name of the person to recommend new friend candidates for
	 * @param option Either dist or weightedDist, which indicates whether to use 
	 * 				 the friendship distance or the weighted friendship distance 
	 *          
	 * @param topK Desirable maximum number of candidate friends to recommend
	 * @return List of candidate friends
	 */
	public static List<Edge<String, Integer>> recommendFriends(DiGraph<String> g, String personOfInterest, String option, int topK) {
		List<Edge<String, Integer>> toReturn = new ArrayList<>();
		Queue<Edge<String, Integer>> order = new PriorityQueue<>();
		Map<String, Integer> dij = g.Dijkstra(personOfInterest);
		Set<String> list = dij.keySet();
		Iterator<String> iter = list.iterator();
		int numEdges = g.numEdges();
		if(option.equals("dist")){
			while(iter.hasNext()){
				String cur = iter.next();
				order.add(new Edge<String, Integer>(cur, dij.get(cur)));
			}
		}else{
			while(iter.hasNext()){
				String cur = iter.next();
				order.add(new Edge<String, Integer>(cur, dij.get(cur) * (numEdges - g.incomingEdges(cur).size())));
			}
		}
		
		while(topK > 0 && !order.isEmpty()){
			Edge<String, Integer> temp = order.poll();
			if(!g.hasEdge(personOfInterest, temp.getVertex()) && !personOfInterest.equals(temp.getVertex())){
				toReturn.add(temp);
				topK--;
			}
			
		}
		
		
		return toReturn; 
	}

}
