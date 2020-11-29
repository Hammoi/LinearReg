package main;

import java.text.DecimalFormat;

public class LinearReg {
	private static final int sampleSize = 20;
	private static final boolean loosenCriteria = false;
	
	private static double[] x = new double[sampleSize];
	private static double[] y = new double[sampleSize];

	private static double m = 0; //theta 1
	private static double b = 0; //theta 0

	public static void main(String[] args) {
		//LINEAR REGRESSION
		//mx+b
		
		double[][] data = CreateData.genRandData(sampleSize);
		x = data[0];
		y = data[1];
		
		
		if(!(x.length == y.length)) {
			System.out.println("Faulty data. Abort!!");
			return;
		}

		


		double learningRate = 0.0001;
		double criteria = 0.0000000000000001;
		int runs = 0;
		int lap = 0;
		
		System.out.println("Starting linear regression.\n");
		
		long time = System.currentTimeMillis();
		
		while(cost() > criteria) {
			double mDerivative = derivative(learningRate, x, y, m, false);
			double bDerivative = derivative(learningRate, x, y, b, true);
			//System.out.println("m deriv: " + mDerivative + ", b deriv: " + bDerivative);
			m -= mDerivative;
			b -= bDerivative;

			runs++;
			
			if(runs > 100000) {
				criteria = loosenCriteria ? criteria * 2 : criteria;
				runs = 0;
				lap += 1;
			}
			
			
		
			
		}
		System.out.println("Complete\ncost: " + cost() + ", run #" + (100000 * lap + runs) + ", took " + (System.currentTimeMillis() - time) + " milleseconds.");
		DecimalFormat df = new DecimalFormat("#.###");
		System.out.println(df.format(m) + "x + " + df.format(b));
		

	}

	private static double currentFunction(double x) {
		return m*x+b;
	}

	private static double cost() {
		double d = 0;
		
		double sum = 0;
		for(int i = 0; i < x.length; i++) {
			
			//System.out.println(i + " " + (currentFunction(x[i]) - y[i]));
			sum += Math.pow(currentFunction(x[i]) - y[i], 2);
		}
		
		d = sum / (2 * x.length);
		
		return d;
	}
	
	private static double derivative(double lr, double[] x, double[]y, double m, boolean constant) {


		if(constant) {

			double sum = 0;
			for(int i = 0; i < x.length; i++) {
				
				//System.out.println(i + " " + (currentFunction(x[i]) - y[i]));
				sum += currentFunction(x[i]) - y[i];
			}
			//System.out.println("b sum: " + sum);
			double d = (lr * sum) / x.length;
			return d;

		} else {

			double sum = 0;
			for(int i = 0; i < x.length; i++) {
				//System.out.println(i + " " + (currentFunction(x[i]) - y[i]));
				sum += (currentFunction(x[i]) - y[i]) * x[i];
			}

			//System.out.println("m sum: " + sum);
			
			double d = (lr * sum) / x.length;
			return d;

		}

	}



}
