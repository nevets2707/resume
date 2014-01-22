package edu.iastate.cs228.hw4;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import edu.iastate.cs228.hw4.EntryTree.Node;

/**
 * @author Steven Monson
 *
 * An entry tree class
 * Add Javadoc comments to each method
 */
public class EntryTree<K, V> {
	/**
	 * dummy root node made public for grading
	 */
	public Node root;
	
	/**
	 * prefixlen is the largest index such that the keys in the subarray keyarr
	 * from index 0 to index prefixlen - 1 are, respectively, with the nodes on
	 * the path from the root to a node. prefixlen is computed by a private
	 * method shared by both search() and prefix() to avoid duplication of code.
	 */
	private int prefixlen;

	protected class Node implements EntryNode<K, V> {
		private Node child; // link to the first child node
		private Node parent; // link to the parent node
		private Node prev; // link to the previous sibling
		private Node next; // link to the next sibling
		private K key; // the key for this node
		private V value; // the value at this node

		
		public Node(K aKey, V aValue) {
			key = aKey;
			value = aValue;
			child = null;
			parent = null;
			prev = null;
			next = null;
		}
		
		/**
		 * Returns the parent value, null if there isn't a value
		 */
		@Override
		public EntryNode<K, V> parent() {
			if(parent == null)
				return null;
			return parent;
		}

		/**
		 * Returns the child value, null if there isn't a value
		 */
		@Override
		public EntryNode<K, V> child() {
			if(child == null)
				return null;
			return child;
		}

		/**
		 * Returns the next value, null if there isn't a value
		 */
		@Override
		public EntryNode<K, V> next() {
			if(next == null)
				return null;
			return next;
		}

		/**
		 * Returns the previous value, null if there isn't a value
		 */
		@Override
		public EntryNode<K, V> prev() {
			if(prev == null)
				return null;
			return prev;
		}
		/**
		 * Returns the key value, null if there isn't a value
		 */
		@Override
		public K key() {
			return key;
		}
		/**
		 * Returns the node's value, null if there isn't a value
		 */
		@Override
		public V value() {
			return value;
		}
	}

	public EntryTree() {
		root = new Node(null, null);
	}

	/**
	 * Returns the value of the entry with a specified key sequence, or null if
	 * this tree contains no entry with the key sequence.
	 * 
	 * @param keyarr
	 * @return
	 */
	public V search(K[] keyarr) {
		if(keyarr == null || keyarr.length < 1)
			return null;
		for(K c : keyarr)
			if(c == null)
				throw new NullPointerException();
		prefixlen = prefixlen(keyarr);
		if(prefixlen < keyarr.length)
			return null;
		Node cur = root;
		for(int i = 0; i < prefixlen; i++){
			cur = cur.child;
			while(cur.key() != keyarr[i])
				cur = cur.next;
		}
		return cur.value();
	}

	/**
	 * The method returns an array of type K[] with the longest prefix of the
	 * key sequence specified in keyarr such that the keys in the prefix label
	 * the nodes on the path from the root to a node. The length of the returned
	 * array is the length of the longest prefix.
	 * 
	 * @param keyarr
	 * @return
	 */
	public K[] prefix(K[] keyarr) {
		if(keyarr == null || keyarr.length < 1)
			return null;
		for(K c : keyarr)
			if(c == null)
				throw new NullPointerException();
		prefixlen = prefixlen(keyarr);
		if(prefixlen < 1)
			return null;
		K[] toReturn = Arrays.copyOf(keyarr, prefixlen);
		return toReturn;

	}

	/**
	 * The method returns an int that represents the depth of
	 * the keyarr already in the tree
	 * 
	 * @param keyarr
	 * @return 
	 */
	private int prefixlen(K[] keyarr) {
		EntryNode<K, V> cur = root;
		int depth = 0;
		
		while(cur.child() != null && depth < keyarr.length){
			cur = cur.child();
			if(cur.key().equals(keyarr[depth]))
				depth++;
			else{
				if(cur.next() == null){
					break;
				}
				while(cur.next() != null){
					cur = cur.next();
					if(cur.key().equals(keyarr[depth])){
						depth++;
						break;
					}else if(cur.next() == null){
						break;
					}
				}
				
				
			}	
		}	
		
		return depth;
	}


	/**
	 * The method locates the node P corresponding to the longest prefix of the
	 * key sequence specified in keyarr such that the keys in the prefix label
	 * the nodes on the path from the root to the node. If the length of the
	 * prefix is equal to the length of keyarr, then the method places aValue at
	 * the node P and returns true. Otherwise, the method creates a new path of
	 * nodes (starting at a node S) labelled by the corresponding suffix for the
	 * prefix, connects the prefix path and suffix path together by making the
	 * node S a child of the node P, and returns true.
	 * 
	 * @param keyarr
	 * @param aValue
	 * @return
	 */
	public boolean add(K[] keyarr, V aValue) {
		if(keyarr == null || keyarr.length < 1 || aValue == null)
			return false;
		for(K k : keyarr){
			if(k == null)
				throw new NullPointerException();
		}
		
		
		int start = prefixlen(keyarr);
		Node cur = root, old = root;
		Node toAdd;
		for(int i = 0; i < start; i++){
			old = cur;
			cur = cur.child;
			while(cur.key() != keyarr[i] && cur.next() != null)
				cur = cur.next;
		}
		
		if(start == keyarr.length){
			toAdd = new Node(keyarr[start-1], aValue);
			if(cur.key().equals(keyarr[keyarr.length-1])){
				old.child = toAdd;
				toAdd.parent = old;
				toAdd.child = cur.child;
				toAdd.next = cur.next;
				toAdd.prev = cur.prev;
				if(cur.child() != null)
					cur.child.parent = toAdd;
			}
				
			else{
				cur.next = toAdd;
				toAdd.prev = cur;
				toAdd.parent = old;
			}
			
			return true;
		}
		for(int i = start; i < keyarr.length; i++){
			if(i == keyarr.length - 1)
				toAdd = new Node(keyarr[i], aValue);
			else
				toAdd = new Node(keyarr[i], null);
			
			if(cur.child() == null){
				cur.child = toAdd;
				toAdd.parent = cur;
				cur = cur.child;
			}else{
				cur = cur.child;
				while(cur.next() != null)
					cur = cur.next;
				cur.next = toAdd;
				toAdd.prev = cur;
				toAdd.parent = cur.parent;
				cur = cur.next;
			}
			
			
		}
		
		
		return true;
	}

	/**
	 * Removes the entry for a key sequence from this tree and returns its value
	 * if it is present. Otherwise, it makes no change to the tree and returns
	 * null.
	 * 
	 * @param keyarr
	 * @return
	 */
	public V remove(K[] keyarr) {
		if(keyarr == null || keyarr.length < 1)
			return null;
		for(K c : keyarr)
			if(c == null)
				throw new NullPointerException();
		V val = search(keyarr);
		if(val == null)
			return val;
		Node cur = root;
		
		for(int i = 0; i < keyarr.length; i++){
			cur = cur.child;
			while(cur.key() != keyarr[i])
				cur = cur.next;
		}
		
		if(valueBelow(keyarr,cur)){
			cur.value = null;
		}
		else{
			delete(cur);
		}
		
		
		return val;
	}

	/**
	 * Moves up the tree from cur and deletes all nodes 
	 * that aren't needed (no value or nodes below with value)
	 * 
	 * @param cur
	 */
	private void delete(Node cur) {
		
		while(cur != root)
			if(cur.next() != null || cur.prev() != null){
				if(cur.next() != null)					
					cur.next.prev = cur.prev;
				if(cur.prev() != null)
					cur.prev.next = cur.next;
				return;
			}
			else{
				cur = cur.parent;
				if(cur.value() != null){
					cur.child = null;
					return;
				}
			}
		
		
	}

	/**
	 * Looks for child nodes with values 
	 * 
	 * @param keyarr
	 * @param cur
	 * @return
	 */
	private boolean valueBelow(K[] keyarr, Node cur) {
		
		if(cur.child() != null){
			cur = cur.child;
			if(cur.value() != null)
				return true;
			if(!valueRight(keyarr, cur))
				return valueBelow(keyarr, cur);
			else 
				return true;
		}
		return false;
	}

	/**
	 * Looks for sibling nodes with values
	 * 
	 * @param keyarr
	 * @param cur
	 * @return
	 */
	private boolean valueRight(K[] keyarr, Node cur) {
		while(cur.next() != null){
			cur = cur.next;
			if(cur.value() != null)
				return true;
		}
		
		return false;
	}

	/**
	 * The method prints the tree on the console in the output format shown in
	 * an example output file.
	 */
	public void showTree() {
		String space = "";
		System.out.println("null->null");
		EntryNode<K, V> cur = root;
		showTreeDown(cur, space);
	}

	/**
	 * Shows the next level down on a tree
	 * 
	 * @param cur
	 * @param space
	 */
	private void showTreeDown(EntryNode<K, V> cur, String space) {
		
		if(cur.child() != null){
			cur = cur.child();
			space += "   ";
			System.out.print(space);
			System.out.print(cur.key().toString() + "->");
			if(cur.value() != null){
				System.out.println(cur.value().toString());
				showTreeDown(cur, space);
				showTreeRight(cur, space);
				
			}
			else{
				System.out.println("null");
				showTreeDown(cur, space);
				showTreeRight(cur, space);
				
				
			}
		}
		
	}

	/**
	 * Shows all sibling nodes of the current level
	 * 
	 * @param cur
	 * @param space
	 */
	private void showTreeRight(EntryNode<K, V> cur, String space) {
		
		if(cur.next() != null){
			cur = cur.next();
			System.out.print(space);
			System.out.print(cur.key().toString() + "->");
			if(cur.value() != null){
				System.out.println(cur.value().toString());
				showTreeRight(cur, space);
			}
			else{
				System.out.println("null");
				showTreeRight(cur, space);
				showTreeDown(cur, space);
			}
			
		}
		
	}

}
