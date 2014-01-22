package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ActorTest {

	Actor test = new Actor();
	Actor test2 = new Actor();
	
	private static ByteArrayOutputStream outContent;
	private static PrintStream oldStream;
	
	@Before
	public void setup(){
		
		World testWorld = new World(10,10);
		test.addedToWorld(testWorld);
		test.setLocation(5, 4);
		
		outContent = new ByteArrayOutputStream();
		oldStream = System.out;
		System.setOut(new PrintStream(outContent));
		
	}
	
	
	@Test
	public void getTest() {
		
		
		assertEquals(5, test.getX());
		assertEquals(4, test.getY());
		
		assertEquals(0, test.getID());
		assertEquals(1, test2.getID());
		
	}
	
	@Test
	public void actTest(){
		test.act();
		assertEquals("Iteration 0: Actor 2" + System.lineSeparator(), outContent.toString());
	}
	
	@Test
	public void setLocationTest(){
		
		try{
			test2.setLocation(3, 3);
			fail();
		}catch(IllegalStateException e){
			assert(true);
		}
	
		try{
			test.setLocation(-1, 5);
			fail();
		}catch(IllegalArgumentException e){
			assertTrue(true);
		}
		
		try{
			test.setLocation(5, 9999);
			fail(); 
		}catch(IllegalArgumentException e){
			assertTrue(true);
		}
	}
	
	@Test
	public void addedToWorldTest(){
		try{
			test.addedToWorld(null);
			fail();
		}catch(NullPointerException e){
			assertTrue(true);
		}
	}
	

}
