package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyWorldTest
{
	private static ByteArrayOutputStream outContent;
	private static PrintStream oldStream;
	
	@Before
	public void setup()
	{
		outContent = new ByteArrayOutputStream();
		oldStream = System.out;
		System.setOut(new PrintStream(outContent));
	}
	
	@After
	public void cleanup()
	{
		System.setOut(oldStream);
	}
	
	@Test
	public void initDiseasesTest()
	{
		MyWorld world = new MyWorld(0, 0);
		assertEquals("Check the NumDiseases line in simulation.config." + System.lineSeparator(), outContent.toString());
	}
}