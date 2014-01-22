package edu.iastate.cs228.hw5;

/**
 * @author Steven Monson
 */
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import javax.print.attribute.standard.QueuedJobCount;

public class DiGraph<V> {

	// A map that stores an entry of a node and a set of outgoing edges (destination node, weight) from the node.
	private HashMap<V, HashSet<Edge<V, Integer>>> map;
	// Total number of edges in the graph
	private int numEdges;
	
	/**
	 * Create an empty graph.
	 */
	public DiGraph() {
		map = new HashMap<V, HashSet<Edge<V, Integer>>>();
	}

	/**
	 * Add a directed edge from the source node to the destination node.
	 * 
	 * @param src Source node
	 * @param dst Destination node
	 * @param c Weight of the edge
	 * @return false if src or dst is null or c <= 0 or src==dst or src.equals(dst)
	 *         true if a new edge from src to dst is added with the weight  
	 *              if there is already existing edge from src to dst, replace the existing weight with the new weight
	 */
	public boolean addEdge(V src, V dst, Integer c) {
		if(src == null || dst == null || c <= 0 || src == dst || src.equals(dst))
			return false;
		if(!map.containsKey(dst))
			addVertex(dst);
		if(!map.containsKey(src))
			addVertex(src);
		
		
		Edge<V, Integer> e = new Edge<V, Integer>(dst, c);
		if(map.get(src) != null && map.get(src).contains(e)){ //Manual remove if the edge already exists
			HashSet<Edge<V, Integer>> val = map.get(src);
			val.remove(e);
			numEdges--;
		}
			
		HashSet<Edge<V, Integer>> hs;
		if(map.get(src) == null){
			hs = new HashSet<>();
		}else{
			hs = map.get(src);
		}
				
		
		
		hs.add(e);
		map.put(src, hs);
		
		numEdges++;	
		return true;
	}

	/**
	 * Add a vertex to the graph with an empty set of edges associated with it.
	 * 
	 * @param vertex Vertex to be added
	 * @return false if vertex is null or vertex is already in the graph
	 * 		   true otherwise
	 */
	public boolean addVertex(V vertex) {// YES?
		if(vertex == null)
			return false;
		if(map.containsKey(vertex))
			return false;
		else
			map.put(vertex, null);
		return true;
	}

	/**
	 * 
	 * @return Number of vertices (nodes) in the graph
	 */
	public int numVertices() {
		return vertices().size();
	}

	/**
	 * 
	 * @return A set of vertices in this graph
	 * 			When there are no vertices in the graph, return an empty set.
	 */
	public Set<V> vertices() {
		return map.keySet();
	}

	/**
	 * 
	 * @param vertex
	 * @return A set of vertices in which there is an edge from the given vertex to each of these vertices. 
	 * 		   Return an empty set if there is no adjacent node or the vertex is not in the graph or vertex is null
	 */
	
	public Set<Edge<V, Integer>> adjacentTo(V vertex) {
		Set<Edge<V, Integer>> e = new HashSet<Edge<V,Integer>>();
		
		if(vertex == null || !map.containsKey(vertex))
			return e;
		
		if(map.get(vertex) != null)
			e = map.get(vertex);
		
		return e;
	}


	/**
	 * 
	 * @param vertex 
	 * @return true if the given vertex is in the graph 
	 *         false otherwise including the case of a null vertex
	 */
	public boolean hasVertex(V vertex) {
		if(vertex == null)
			return false;
		return map.containsKey(vertex);
	}
	
	/**
	 * 
	 * @return Total number of edges in this graph
	 */
	public int numEdges() {
		return numEdges; 
	}

	/**
	 * Check whether an edge from src to dst exists
	 * @param src 
	 * @param dst
	 * @return true if there exists an edge from src to dst regardless of the weight
	 *         false otherwise (including when either src or dst is null  or src or dst is not 
	 *         in the map)
	 */
	public boolean hasEdge(V src, V dst) {
		if(src == null || dst == null || !map.containsKey(src) || !map.containsKey(dst))
			return false;
		if(map.get(src) == null)
			return false;
		
		HashSet<Edge<V, Integer>> vals = map.get(src);
		Iterator<Edge<V, Integer>> iter = vals.iterator();
		while(iter.hasNext())
			if(iter.next().getVertex().equals(dst))
				return true;
		
		return false;
	}
	
	/**
	 * Remove this vertex from the graph if possible and calculate the number of edges in 
	 * the graph accordingly. For instance, if the vertex has 4 outgoing edges and 2 incoming edges, 
	 * the total number of edges after the removal of this vertex is subtracted by 6. 
	 * 
	 * @param vertex Vertex to be removed
	 * @return false if the vertex is null or there is no such vertex in the graph
	 * 		   true if removal is successful
	 */
	public boolean removeVertex(V vertex) {
		if(vertex == null || !map.containsKey(vertex))
			return false;
		
		int removeCounter = 0;
		
		HashSet<Edge<V, Integer>> out = map.get(vertex); //Get the number of edges out
		removeCounter += out.size();
		if(map.containsKey(vertex)){
			Set<V> in = incomingEdges(vertex);
			if(in != null){
				removeCounter += in.size();
				Iterator<V> iter = in.iterator();
				while(iter.hasNext()){ //Go through and manually remove the edges that were incoming
					V cur = iter.next();
					Edge<V, Integer> e = new Edge<V, Integer>(vertex, 0);
					HashSet<Edge<V, Integer>> val = map.get(cur);
					Iterator<Edge<V, Integer>> valIter = val.iterator();
					map.remove(cur);
					HashSet<Edge<V, Integer>> newVal = new HashSet<>();
					while(valIter.hasNext()){
						Edge<V, Integer> temp = valIter.next();
						if(!temp.equals(e)){
							newVal.add(temp);
						}
					}
					map.put(cur, newVal);
				}
			}
		}
		map.remove(vertex);
		numEdges -= removeCounter;
		return true;
	}
	
	/**
	 * Return a set of nodes with edges coming to this given vertex
	 * 
	 * @param vertex
	 * @return empty set if the vertex is null or the vertex is not in this graph
	 *         otherwise, return a non-empty set consists of nodes with edges coming to this vertex
	 */

	public Set<V> incomingEdges(V vertex) {
		Set<V> toReturn = new HashSet<V>();
		if(vertex == null || !map.containsKey(vertex))
			return toReturn;
		
		Edge<V, Integer> ver = new Edge<>(vertex, 0);
		Set<Entry<V, HashSet<Edge<V, Integer>>>> keys = map.entrySet();
		Iterator<Entry<V, HashSet<Edge<V, Integer>>>> iter = keys.iterator(); 
		while(iter.hasNext()){
			Entry<V, HashSet<Edge<V, Integer>>> cur = iter.next();
			if(cur.getValue() != null){
				HashSet<Edge<V, Integer>> val = cur.getValue();
				Iterator<Edge<V, Integer>> valIter = val.iterator();
				Edge<V, Integer> temp;
				while(valIter.hasNext()){
					temp = valIter.next();
					if(temp.equals(ver))
						toReturn.add(cur.getKey());
				}
			}
		}		
		return toReturn;
	}
	
	/**
	 * Compute Dijkstra single source shortest path from the source node.
	 * Use the algorithm discussed in class
	 * 
	 * 
	 * @param source Source node
	 * @return Empty map if the source is null or it is not a vertex in the graph, or, it does not have any outgoing edges
	 *         Otherwise, return a map of entries, each having a vertex and smallest cost going from the source node to it
	 */
	public Map<V, Integer> Dijkstra(V source) {
		HashMap<V, Integer> toReturn = new HashMap<>();
		if(source == null || !map.containsKey(source))
			return toReturn;
					
		if(map.get(source) == null){
			toReturn.put(source, 0);
			return toReturn;
		}
		
		HashMap<V, Integer> dist = new HashMap<>();
		Set<V> visited = new HashSet<V>();
		HashMap<V, V> prev = new HashMap<>();
		
		Queue<Edge<V, Integer>> order = new PriorityQueue<>();
		
		Set<V> keys = map.keySet();
		Iterator<V> iter = keys.iterator();
		while(iter.hasNext()){ //Initialize all of the distances
			V temp = iter.next();
			if(temp.equals(source)){
				order.add(new Edge<V, Integer>(temp, 0));
				dist.put(temp, 0);
			}
			else{
				dist.put(temp, 999);
			}
		}
		
		while(!order.isEmpty()){
			Edge<V, Integer> cur = order.poll();
			if(!visited.contains(cur.getVertex()) && cur.getCost() < 999){
				visited.add(cur.getVertex());
				HashSet<Edge<V, Integer>> paths = map.get(cur.getVertex());
				if(paths != null){
					Iterator<Edge<V, Integer>> edgeIter = paths.iterator();
					while(edgeIter.hasNext()){
						Edge<V, Integer> temp = edgeIter.next();
						int dis = temp.getCost() + dist.get(cur.getVertex());
						if(dis < dist.get(temp.getVertex())){
							prev.put(temp.getVertex(), cur.getVertex());
							order.add(new Edge<V, Integer>(temp.getVertex(), dis));
							dist.remove(temp.getVertex());
							dist.put(temp.getVertex(), dis);
						}
					}
				}
			}
		}
		Iterator<V> iter2 = visited.iterator();
		while(iter2.hasNext()){
			V cur = iter2.next();
			toReturn.put(cur, dist.get(cur));
		}
		return toReturn;

	}

}