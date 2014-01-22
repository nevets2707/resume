package edu.iastate.cs228.hw1;

public class Simulator {
	public static void main(String args[]){
		int run = 5;
		if(args.length > 0)
			run = Integer.parseInt(args[0]);
	
		
		System.out.println("Simulation of My World");
		MyWorld sim = new MyWorld(720,640);
		for(int i = 0; i < run; i++){
			sim.act();
			for(Object obj : sim.getObjects()){
				((Disease) obj).act();
			}
		}
			
		
		System.out.println();
		
		System.out.println("Simulation of World");
		World sim2 = new World(100,100);
		Actor act1 = new Actor();
		Actor act2 = new Actor();
		sim2.addObject(act1, 10, 10);
		sim2.addObject(act2, 90, 90);
		
		for(int i = 0; i < run; i++){
			sim2.act();
			for(Object obj: sim2.getObjects()){
				((Actor) obj).act();
				
			}
		}
		
	}
}
