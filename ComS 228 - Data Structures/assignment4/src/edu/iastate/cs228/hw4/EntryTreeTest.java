package edu.iastate.cs228.hw4;

/**
 * @author Steven Monson
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EntryTreeTest {
	
	Character[] cat = {'c', 'a', 't'};
	Character[] cab = {'c', 'a', 'b'};
	Character[] can = {'c', 'a', 'n'};
	Character[] cats = {'c', 'a', 't', 's'};
	Character[] apple = {'a','p','p','l','e'};
	Character[] empty = {};
	Character[] broke = {'a', null, 'b'};
	Object NullPointerException = new NullPointerException();	
	
	EntryTree<Character, String> tree = new EntryTree<>();

	@Before
	public void setUp(){
		tree.add(cats, "meows");
		tree.add(cat, "meow");
		tree.add(cab, "honk");
		tree.add(can, "tink");
	
	}
	
	@Test(expected = NullPointerException.class)
	public void testAdd() {
		Character[] ca = {'c','a'};
		Character[] catss = {'c','a','t','s','s'};
		assertEquals("meows", tree.search(cats));
		assertEquals(true,tree.add(cats, "lol"));
		assertEquals("lol", tree.search(cats));
		assertEquals(true, tree.add(ca, "ca"));
		assertEquals("ca", tree.search(ca));
		assertEquals(true, tree.add(catss, "meowss"));
		assertEquals("meowss", tree.search(catss));
		assertEquals(true, tree.add(apple, "bob"));
		assertEquals("bob", tree.search(apple));
		
		assertEquals(false, tree.add(null, "abc"));
		assertEquals(false, tree.add(cats, null));
		assertEquals(false, tree.add(empty, "abc"));
		assertEquals(NullPointerException, tree.add(broke, "abc"));
	}
	
	@Test(expected = NullPointerException.class)
	public void testRemove(){
		assertEquals("honk", tree.search(cab));
		assertEquals("honk", tree.remove(cab));
		assertEquals(null, tree.search(cab));
		assertEquals(null, tree.remove(apple));
		assertEquals("meow", tree.remove(cat));
		assertEquals("meows", tree.search(cats));
		assertEquals("tink", tree.remove(can));
		
		assertEquals(null, tree.remove(empty));
		assertEquals(null, tree.remove(null));
		assertEquals(null, tree.remove(apple));
		assertEquals(NullPointerException, tree.remove(broke));
	}
	
	@SuppressWarnings("deprecation")
	@Test(expected = NullPointerException.class)
	public void testPrefix(){
		Character[] cam = {'c', 'a', 'm'};
		Character[] ca = {'c','a'};
		assertEquals(ca, tree.prefix(cam));
		assertEquals(cat, tree.prefix(cat));
		assertEquals(null, tree.prefix(apple));
		
		assertEquals(null, tree.prefix(empty));
		assertEquals(null, tree.prefix(null));
		assertEquals(NullPointerException, tree.prefix(broke));

	}
	
	@Test(expected = NullPointerException.class)
	public void testSearch(){
		Character[] cabs = {'c','a','b','s'};
		assertEquals(null, tree.search(cabs));
		assertEquals("tink", tree.search(can));
		assertEquals(null, tree.search(apple));
		
		assertEquals(null, tree.search(null));
		assertEquals(null, tree.search(empty));
		assertEquals(NullPointerException, tree.search(broke));

	}

}
