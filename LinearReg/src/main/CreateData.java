package main;

import java.text.DecimalFormat;

public class CreateData {
	
	private static final int variableRange = 10000;
	private static final int inputRange = 100;
	
	public static double[][] genRandData(int size) {
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		double m = Double.parseDouble(df.format(Math.random()*variableRange));
		double b = Double.parseDouble(df.format(Math.random()*variableRange)); 
		
		return genData(m,b,size);
	}
	
	public static double[][] genData(double m, double b, int size) {
		
		System.out.println("Using function " + m + "x + " + b + ".");
		
		double[] x = new double[size];
		double[] y = new double[size];
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		System.out.println("\nData values:");
		
		for(int i = 0; i < size; i++) {
			x[i] = Double.parseDouble(df.format(Math.random()*inputRange));
			y[i] = m * x[i] + b;
			System.out.println(x[i] + " | " + y[i]);
			
		}
		
		return new double[][] {x,y};
		
	}
}