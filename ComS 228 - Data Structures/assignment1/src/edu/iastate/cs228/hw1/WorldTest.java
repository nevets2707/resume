package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WorldTest {

	World tester = new World(5, 7);
	World tester2  = new World(1001, -7);
	Actor act = new Actor();
	Actor act2  = new Actor();
	
	
	@Before
	public void setup(){
	tester.addObject(act, 3, 3);
	tester.addObject(act2, 4, 4);
	}
	
	@Test
	public void addObjectTest(){
		
	}
	
	@Test
	public void getTest() {
		assertEquals(5, tester.getWidth());
		assertEquals(7, tester.getHeight());
		
		assertEquals(1000, tester2.getWidth());
		assertEquals(1000, tester2.getHeight());
		
		assertEquals(2, tester.numberOfObjects());
		
		Object[] list = new Object[2];
		list[0] = act;
		list[1] = act2;
		assertArrayEquals(list, tester.getObjects());
	}
	
	@Test
	public void setGridTest(){
		
		try{
			Actor[][][] aGrid = new Actor[0][][];
			tester.setGrid(aGrid, 3);
			fail();
		}catch(IllegalArgumentException e){
			assert(true);
		}
		
		try{
			Actor[][][] aGrid = new Actor[2][0][];
			tester.setGrid(aGrid, 3);
			fail();
		}catch(IllegalArgumentException e){
			assert(true);
		}
		
		try{
			Actor[][][] aGrid = new Actor[2][4][3];
			tester.setGrid(aGrid, 3);
			fail();
		}catch(IllegalArgumentException e){
			assert(true);
		}
		
		try{
			Actor[][][] aGrid = new Actor[2][][];
			aGrid[0] = new Actor[2][5];
			aGrid[1] = new Actor[3][5];
			
			tester.setGrid(aGrid, 3);
			fail();
		}catch(IllegalArgumentException e){
			assert(true);
		}
		
		try{
			Actor[][][] aGrid = new Actor[2][2][];
			aGrid[0][0] = new Actor[5];
			aGrid[0][1] = new Actor[3];
			tester.setGrid(aGrid, 3);
			fail();
		}catch(IllegalArgumentException e){
			assert(true);
		}
		
		Actor[][][] aGrid = new Actor[4][4][5];
		tester.setGrid(aGrid, 3);
		
		assertEquals(4, tester.getWidth());
		assertEquals(4, tester.getHeight());
		assertEquals(3, tester.numberOfObjects());
	}

}
