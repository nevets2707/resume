package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MyWorld extends World implements IWorld {

	
	private double[] avgTemp = new double[4];
	private int iteration = 0;
	
	
	public MyWorld(int worldWidth, int worldHeight) {
		super(worldWidth, worldHeight);
		prepare();
	}

	@Override
	public void act(){
		double strgth = this.getSumStrength();
		System.out.printf("Iteration " + iteration + ": World disease strength is %.2f \n", strgth);
		iteration++;
	}
	
	public void prepare(){
		String line;
		String[] sArr = new String[2];
		Disease[] disArr = null;
		int check = 0;
		
		FileReader inputFile;
		Scanner sc = null;
		try {
			inputFile = new FileReader(".\\simulation.config");
			sc = new Scanner(inputFile);
			
			while(sc.hasNextLine()){
			line = sc.nextLine();
			sArr = line.split("=");
			
			if(sArr[0].equalsIgnoreCase("NumDiseases"))
				disArr = initDiseases(sArr[1]);
			if(disArr == null)
				System.exit(-1);
			
			if(sArr[0].equalsIgnoreCase("Locations"))
				check = initLocations(sArr[1], disArr);
			if(check == -1)
				System.exit(-1);
			
			if(sArr[0].equalsIgnoreCase("DiseasesGrowth"))
				check = initGrowthConditions(sArr[1], disArr);
			if(check == -1)
				System.exit(-1);
			
			if(sArr[0].equalsIgnoreCase("Temperature"))
				check = initTemps(sArr[1]);	
			if(check == -1)
				System.exit(-1);
			}
		} catch (FileNotFoundException e) {
			System.out.print( e + "Terminating the Program");
			System.exit(-1);
		}finally{
			sc.close();
		}
	}
	

	@Override
	public void setTemp(int quad, double temp) {
		if(quad > 3 || quad < 0){
			throw new IllegalArgumentException();
		}
		avgTemp[quad] = temp;
		
		
	}

	@Override
	public double getTemp(int quad) {
		return avgTemp[quad];
	}

	@Override
	public double getSumStrength() {
		
		double strength = 0.0;
		
		Object[] list =  this.getObjects();
		
		for(Object dis : list)
			strength += ((Disease) dis).getStrength();
		
		return strength;
	}

	@Override
	public Disease[] initDiseases(String numDisStr) {
		int numDis = 0;
		
		if(numDisStr != null){
			try{
				numDis = Integer.parseInt(numDisStr);
			}
			catch(Exception e){
				System.out.print("Check the NumDiseases line in simulation.config");
				return null;
			}
		}else{
			System.out.print("Check the NumDiseases line in simulation.config");
			return null;
		}
		
		Disease[] disArr = new Disease[numDis];
		
		for(int i = 0; i < numDis; i++){
			disArr[i] = new Disease();
		}
		
		
		return disArr;
		
	}

	@Override
	public int initLocations(String locationsStr, Disease[] diseaseArr) {//ok to assume initDis works?
		
		if(locationsStr == null){
			System.out.println("Check the Locations line in simulation.config");
			return -1;
		}
		
		String[] values = new String[diseaseArr.length];
		
		values = locationsStr.split(";");
	
		
		for(int i = 0; i < values.length; i++){
			String[] location = new String[2];
			location = values[i].split(",");
			
			int x, y;
			
			try{
				x = Integer.parseInt(location[0]);
				y = Integer.parseInt(location[1]);
				diseaseArr[i].addedToWorld(this);
				diseaseArr[i].setLocation(x, y);
				this.addObject(diseaseArr[i], x, y);
			}
			catch(Exception e){
				System.out.println("Check the Locations line in simulation.config");
				return -1;
			}
		}
		
		
		return 0;
	}

	@Override
	public int initGrowthConditions(String growthStr, Disease[] diseaseArr) {
		
		if(growthStr == null){
			System.out.println("Check the DiseasesGrowth line in simulation.config.");
			return -1;
		}
		
		String[] values = new String[diseaseArr.length];
		
		values = growthStr.split(";");
		
		try{
			for(int i = 0; i < values.length; i++){
				String[] conditions = new String[3];
				
				conditions = values[i].split(",");
				double lowTemp, highTemp, rate;
				lowTemp = Double.parseDouble(conditions[0]);
				highTemp = Double.parseDouble(conditions[1]);
				rate = Double.parseDouble(conditions[2]);
				
				diseaseArr[i].setGrowthCondition(lowTemp, highTemp, rate);
			}
		}catch(Exception e){
			System.out.println("Check the DiseasesGrowth line in simulation.config.");
			return -1;
		}
		
		
		return 0;
	}

	@Override
	public int initTemps(String tempStr) {
		
		String[] values = new String[4];
		
		if(tempStr == null){
			System.out.println("Check the Temperature line in simulation.config");
			return -1;
		}else{
			 //check how big array should be
			values = tempStr.split(";");
		}
		for(int i = 0; i < values.length; i++){
			try{
				avgTemp[i] = Double.parseDouble(values[i]);
			}
			catch(Exception e){
				System.out.println("Check the Temperature line in simulation.config.");
				return -1;
			}
		}
			
		return 0;
	}

}
