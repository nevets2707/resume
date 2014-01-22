package edu.iastate.cs228.hw1;
/**
 * 
 * @author Steven Monson
 * Actor class
 *
 */
public class Actor {
	
	/**
	 * Construct a new Actor object. It sets the initial values of its member variables. 
	 * It sets the unique ID for the object and initializes the reference to the World 
	 * object to which this Actor object belongs to null. 
	 * The ID of the first Actor object is 0. 
	 * The ID gets incremented by one each time a new Actor object is created. 
	 * Set the iteration counter to zero and initialize the location of the 
	 * object to cell (0,0). 
	 */
	
	private static int ID = 0;
	protected int iteration; 
	private int uniqID;
	private World worldLoc;
	private int xLoc, yLoc;
	
	
	public Actor() {
		uniqID = ID;
		ID++;
		
		iteration = 0;
		worldLoc = null;
		xLoc = 0;
		yLoc = 0;
		
	}
	
	public void act() {
		System.out.println("Iteration " + iteration + ": Actor " + uniqID);
		iteration++;
		
	}
	
	/**
	 * 
	 * @param x the column
	 * @param y the row
	 * 
	 * Remember the cell coordinate of this object
	 * 
	 * @throws 
	 * 
	 * 	IllegalArgumentException when x < 0 or x >= world width
	 *  IllegalArgumentException when y < 0 or y >= world height
	 *  IllegalStateException when the world is null
	 */
	public void setLocation(int x, int y) {
		if(worldLoc == null)
			throw new IllegalStateException();
		if(x < 0 || x >= worldLoc.getWidth())
			throw new IllegalArgumentException();
		if(y < 0 || y >= worldLoc.getHeight())
			throw new IllegalArgumentException();
		
		xLoc = x;
		yLoc = y;
	}
	
	/**
	 * 
	 * @param world Reference to the World object this Actor object is added
	 * @throws
	 *	NullPointerException when world is null
	 */
	protected void addedToWorld(World world) {
		if(world == null)
			throw new NullPointerException();
		worldLoc = world;
	}
	
	/**
	 * 
	 * @return the world this object belongs to
	 */
	public World getWorld() {
		return worldLoc;
	}
	
	/**
	 * 
	 * @return the x coordinate of this Actor object
	 */
	public int getX() {
		return xLoc;
	}
	
	/**
	 * 
	 * @return the y coordinate of this Actor object
	 */
	public int getY() {
		return yLoc;
	}
	
	/**
	 * @return they ID of this Actor object
	 */
	public int getID(){
		return uniqID;
	}

}
