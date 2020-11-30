package main;

import java.text.DecimalFormat;

public class CreateData {
	
	private static final int variableRange = 10000;
	private static final int inputRange = 100;
	
	public static double[][] genRandData(int size) {
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		double m = (Math.round(Math.random()) == 0) ? Double.parseDouble(df.format(Math.random()*variableRange)) : -Double.parseDouble(df.format(Math.random()*variableRange));
		double b = (Math.round(Math.random()) == 0) ? Double.parseDouble(df.format(Math.random()*variableRange)) : -Double.parseDouble(df.format(Math.random()*variableRange));; 
		
		return genData(m,b,size);
	}
	
	public static double[][] genData(double m, double b, int size) {
		
		System.out.println("Using function " + m + "x + " + b + ".");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		double[] x = new double[size];
		double[] y = new double[size];
		
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		System.out.println("\nData values:");
		
		for(int i = 0; i < size; i++) {
			x[i] = (Math.round(Math.random()) == 0) ? Double.parseDouble(df.format(Math.random()*inputRange)) : -Double.parseDouble(df.format(Math.random()*inputRange));
			y[i] = m * x[i] + b;
			System.out.println(x[i] + " | " + y[i]);
			
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		return new double[][] {x,y};
		
	}
}