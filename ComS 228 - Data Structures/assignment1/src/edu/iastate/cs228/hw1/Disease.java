package edu.iastate.cs228.hw1;

/**
 * 
 * @author Steven Monson
 * 
 * This Disease class is a sub-class of the Actor class.
 *
 */

public class Disease extends Actor implements IDisease {
	
	// Declare fields to store the temperatures and the growth rate
	// Add other fields as necessary
	
	private double growth = 0.0;
	private double lowBound, upBound;
	private double strength;
	
	public Disease() {
		super();
		lowBound = 0.0;
		upBound = 0.0;
		strength = 1.0;
		
	}
	
	/**
	 * @param lTemp Lower bound temperature for the disease to grow at this gRate
	 * @param hTemp Upper bound temperature for the disease to grow at this gRate
	 * @param gRate The growth rate
	 */
	public void setGrowthCondition(double lTemp, double hTemp, double gRate) {
		
		growth = gRate;
		lowBound = lTemp;
		upBound = hTemp;
	}
	
	
	/**
	 * Print on screen in the format “Iteration <ID>: Actor <Actor ID>.” 
	 * The <ID> is replaced by the current iteration number. 
	 * <Actor ID> is replaced by the unique ID of the Actor object that performs 
	 * the act() method. 
	 */
	@Override
	public void act() {
		
		// TO DO: 
		// 1. Compute the region of the world this Actor object is in
		//    Get the world width and world height
		// 2. Print the iteration number and the actor ID in the required format
		// 3. If the world temperature is in the range that the disease grows, update the strength
		
		MyWorld w = (MyWorld) this.getWorld();
		int quad;
		
		if(this.getX() < w.getWidth() / 2){
			if(this.getY() < w.getHeight() / 2)
				quad = 0;
			else
				quad = 2;
		}else{
			if(this.getY() < w.getHeight() / 2)
				quad = 1;
			else
				quad = 3;
		}
		
		if(lowBound < w.getTemp(quad) && upBound > w.getTemp(quad)){
			strength *= growth;
		}
		
	}

	@Override
	public double getStrength() {
		
		return strength;
	}

}
