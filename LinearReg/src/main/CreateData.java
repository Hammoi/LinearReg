package main;

import java.text.DecimalFormat;

public class CreateData {
	public static double[][] genRandData(int size) {
		
		double[] x = new double[size];
		double[] y = new double[size];
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		double m = Double.parseDouble(df.format(Math.random()*100)); //Range from 0 to 100
		double b = Double.parseDouble(df.format(Math.random()*100)); 
		
		System.out.println("Using function " + m + "x + " + b + ".");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("\nData values:");
		
		for(int i = 0; i < size; i++) {
			x[i] = Double.parseDouble(df.format(Math.random()*100));
			y[i] = m * x[i] + b;
			System.out.println(x[i] + " | " + y[i]);
			
		}
		
		
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n\n");
		
		return new double[][]{x,y};
	}
}
