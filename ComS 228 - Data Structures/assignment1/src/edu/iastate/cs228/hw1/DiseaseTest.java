package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DiseaseTest {

	Disease test = new Disease();
	MyWorld world = new MyWorld(500, 500);
	final double DELTA = .001;
	
	@Before
	public void setUp(){
		test.addedToWorld(world);
		test.setGrowthCondition(0, 30, 3);
	}
	
	
	@Test
	public void actTest(){
		test.act();
		
		assertEquals(0, Double.compare(3, test.getStrength()));
	}
	
	@Test
	public void getTest() {
		
		assertEquals(1, test.getStrength(), DELTA);
		
	}

}
