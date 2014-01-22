package edu.iastate.cs228.hw1;

/**
 * 
 * @author Steven Monson
 * 
 * World class keeps Actor objects in some cells of the world
 *
 */

public class World {
	
	/*
	 * @param worldWidth Width in number of cells 
	 * @param worldHeight Height in number of cells
	 * 
	 *  The maximum width and height are 1000.
	 *  The maximum number of Actor objects in a cell is 5.
	 *  If worldWidth <= 0 or worldWidth > maximum width
	 *     use the maximum width instead
	 *  If worldHeight <=0 or worldHeight > maximum height
	 *     use the maximum height instead
	 * 
	 */
	
	private Actor[][][] grid;
	private int numOfObjects = 0;
	int w, h;
	
	public World(int worldWidth, int worldHeight) {
		
		if(worldWidth <= 0 || worldWidth > 1000)
			w = 1000;
		else
			w = worldWidth;
		
		if(worldHeight <= 0 || worldHeight > 1000)
			h = 1000;
		else 
			h = worldHeight;
		
		
		grid = new Actor[w][h][5];
		
		for(int i = 0; i < w; i++){
			for(int j = 0; j < h; j++){
				for(int k = 0; k < 5; k++){
					grid[i][j][k] = null;
				}
			}
		}
	
	}
	
	public void act() {
		
	}
	
	/**
	 * 
	 * @param object the object to be add at this cell (x, y)
	 * @param x the column
	 * @param y the row
	 * 
	 * The new object will be added at the cell (x,y) if there are less than 5 objects in this cell
	 * Be sure to make the added object know that it is in this world and it is at this cell.
	 * Check which methods of the Actor class to call.
	 * 
	 * @throws IllegalStateException when already max number of objects are in that cell
	 * @throws IllegalArgumentException if x or y is not in the valid range
	 * @throws NullPointerException if the object is null
	 * 
	 * 
	 */
	public void addObject(Actor object, int x, int y) {
		
		if(object == null)
			throw new NullPointerException();
		if(x > getWidth() || x < 0 || y > getHeight() || y < 0)
			throw new IllegalArgumentException();
		int open = 0;
		for(int i = 0; i < 6; i++){
			if(grid[y][x][i] == null){
				open = i;
				break;
			}
			if(i == 5)
				throw new IllegalStateException();
		}
		
			grid[y][x][open] = object; 
			grid[y][x][open].addedToWorld(this);
			grid[y][x][open].setLocation(x, y);
			numOfObjects++;
	}
	
	/**
	 * 
	 * @return the world height
	 */
	public int getHeight() {
		
		return grid[0].length;
	}
	/**
	 * 
	 * @return the world width
	 */
	public int getWidth() {
		return grid.length;
	}
	/**
	 * 
	 * @return Total number of objects in the world
	 */
	public int numberOfObjects() {
		
		return numOfObjects;
		
	}
	
	/**
	 * 
	 * @return Array of Actor objects that are in this world
	 * 
	 * up cast
	 */
	public Object[] getObjects() {
		
		int counter = 0;
		Object[] list = new Object[numOfObjects];
		
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[i].length; j++){
				for(int k = 0; k < 5; k++){
					if(grid[i][j][k] != null){
						list[counter] = grid[i][j][k];
						counter++; //didn't increment before
					}
				}
			}
		}
		
		return list; //TODO Check this
	}
	
	/** To make this method work with you code, you need to complete the TODO parts below.
	 *
	 *  It checks if aGrid is a 3D array with the same positive length in each dimension.
	 *  If so, it sets the grid to aGrid and the other private fields of class World to 
	 *  the dimension lengths of aGrid and numObjs.
	 *
	 *  Note that some checks are omitted. For example, no check is performed to make sure
	 *  that numObjs is consistent with the number of Actor objects in aGrid.
	 *
	 *  Each Actor object in aGrid has to be set to this World object.
	 *
	 * @param aGrid reference to a 3D array of Actor objects.
	 *
	 * @param numObjs the number of Actor objects in aGrid.
	 *
	 * @throws IllegalArgumentException if the length of each dimension is out of range
	 *         or 2nd/3rd dimension has different lengths.
	*/
	
	public void setGrid(Actor[][][] aGrid, int numObjs) {

		 int nrow = aGrid.length;
		 if ( nrow < 1 )
			throw new IllegalArgumentException("1stD length is out of range");

	     int ncol = aGrid[0].length;
	     if ( ncol < 1 )
			 throw new IllegalArgumentException("2ndD length is out of range");

		 int ncel = aGrid[0][0].length;
		 if ( ncel != 5 )
			 throw new IllegalArgumentException("3rdD length is not " + 5);

		 for ( int j = 0; j < nrow; j++ ) {

		    if ( aGrid[j].length != ncol )
		       throw new IllegalArgumentException("Different 2ndD lengths");

		    for ( int k = 0; k < ncol; k++ )
		        if ( aGrid[j][k].length != ncel )
			       throw new IllegalArgumentException("Different 3rdD lengths");
		  } // checks if each dimension is of the same length.
		 
		 grid = aGrid;
		 numOfObjects = numObjs;
		 w = nrow;
		 h = ncol;

	} 
	
}
