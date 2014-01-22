package edu.iastate.cs228.hw5;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class DiGraphTest {

	DiGraph<String> graph = new DiGraph<>();
	
	@Before
	public void setUp(){
		assertEquals(true, graph.addEdge("LOL", "WAT", 3));
	}
	
	@Test
	public void addEdgeTest(){
		assertEquals(false, graph.addEdge(null, "2", 1));
		assertEquals(false, graph.addEdge("1", null, 1));
		assertEquals(false, graph.addEdge("1", "1", 1));
		assertEquals(false, graph.addEdge("1", "2", 0));
		assertEquals(true, graph.addEdge("1", "2", 1));
		assertEquals(true, graph.hasVertex("1"));
		assertEquals(true, graph.hasEdge("1", "2"));
	}
	
	@Test
	public void addVertexTest(){
		assertEquals(false, graph.addVertex(null));
		assertEquals(true, graph.addVertex("1"));
		assertEquals(false, graph.addVertex("1"));
		assertEquals(true, graph.hasVertex("1"));
	}

	@Test
	public void numVerticesTest(){
		assertEquals(2, graph.numVertices());
		graph.addVertex("1");
		assertEquals(3, graph.numVertices());
	}
	
	@Test
	public void verticesTest(){//TODO test empty set
		Set<String> list = new HashSet<String>();
		list.add("LOL");
		list.add("WAT");
		assertEquals(list, graph.vertices());
	}
	
	@Test
	public void adjacentToTest(){
		Set<Edge<String, Integer>> list = new HashSet<>();
		Edge<String, Integer> e = new Edge<String, Integer>("WAT", 1);
		list.add(e);
		assertEquals(list, graph.adjacentTo("LOL"));
		graph.addEdge("LOL", "OK", 1);
		e = new Edge<>("OK", 1);
		list.add(e);
		assertEquals(list, graph.adjacentTo("LOL"));
		list.clear();
		assertEquals(list, graph.adjacentTo("OK"));
		assertEquals(list, graph.adjacentTo(null));
		assertEquals(list, graph.adjacentTo("LAWL"));
	}
	
	@Test
	public void hasVertexTest() {
		assertEquals(true ,graph.hasVertex("LOL"));
		assertEquals(true, graph.hasVertex("WAT"));
		assertEquals(false, graph.hasVertex(""));
		assertEquals(false, graph.hasVertex(null));
	}
	
	@Test
	public void numEdgesTest(){
		assertEquals(1, graph.numEdges());
		graph.addEdge("LOL", "OK", 1);
		graph.addEdge("WAT", "YEA", 1);
		assertEquals(3, graph.numEdges());
		graph.removeVertex("WAT");
		assertEquals(1, graph.numEdges());
	}
	
	@Test
	public void hasEdgeTest(){
		assertEquals(false, graph.hasEdge(null, "LOL"));
		assertEquals(false, graph.hasEdge("WAT", null));
		assertEquals(false, graph.hasEdge("WAT", "2"));
		assertEquals(false, graph.hasEdge("1", "LOL"));
		assertEquals(true, graph.hasEdge("LOL", "WAT"));
		assertEquals(false, graph.hasEdge("WAT", "LOL"));
	}
	
	@Test
	public void removeVertexTest(){
		assertEquals(false, graph.removeVertex(null));
		assertEquals(false, graph.removeVertex("1"));
		assertEquals(true, graph.removeVertex("LOL"));
		assertEquals(0, graph.numEdges());
	}
	
	@Test
	public void incomingEdgesTest(){
		Set<String> list = new HashSet<>();
		assertEquals(list, graph.incomingEdges(null));
		assertEquals(list, graph.incomingEdges("1"));
		assertEquals(list, graph.incomingEdges("LOL"));
		list.add("LOL");
		assertEquals(list, graph.incomingEdges("WAT"));
		list.add("YEA");
		list.add("OK");
		graph.addEdge("YEA", "WAT", 1);
		graph.addEdge("OK", "WAT", 1);
		assertEquals(list, graph.incomingEdges("WAT"));
		
	}

	@Test
	public void dijkstraTest(){
		Map<String, Integer> list = new HashMap<>();
		assertEquals(list, graph.Dijkstra(null));
		assertEquals(list, graph.Dijkstra("!"));
		
		list.put("WAT", 0);
		assertEquals(list, graph.Dijkstra("WAT"));
		
		graph.addEdge("WAT", "OK", 2);
		list.put("LOL", 0);
		list.put("WAT", 3);
		list.put("OK", 5);
		assertEquals(list, graph.Dijkstra("LOL"));
		
		graph.addEdge("LOL", "YEA", 5);
		graph.addEdge("WAT", "YEA", 1);
		list.put("YEA", 4);
		assertEquals(list, graph.Dijkstra("LOL"));
		
		graph.addEdge("NOPE", "WAT", 3);
		assertEquals(list, graph.Dijkstra("LOL"));
		
		list.clear();
		list.put("NOPE", 0);
		list.put("WAT", 3);
		list.put("YEA", 4);
		list.put("OK", 5);
		assertEquals(list, graph.Dijkstra("NOPE"));
	}
	
}
