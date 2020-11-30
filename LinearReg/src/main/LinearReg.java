package main;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner; 

public class LinearReg {
	private static final boolean useRandomData = false; 				//Generate random data?
	private static final int sampleSize = 40; 							//Size of sample
	private static final boolean loosenCriteria = true;					//Loosen criteria if no equation found after every *loosenLap* runs?
	private static final int loosenLap = 100000; 						//How many runs before criteria is loosened
	private static final double loosenValue = 2; 						//Value to loosen criteria by
	private static final double learningRate = 0.0001; 					//Learning rate
	private static final double initCriteria = 0.0000000000000001; 		//Criteria to begin with

	private static double[] x = new double[sampleSize];
	private static double[] y = new double[sampleSize];

	private static double m = 0; //theta 1								//m value to begin with
	private static double b = 0; //theta 0								//b value to begin with

	public static void main(String[] args) {
		//LINEAR REGRESSION
		//mx+b

		double[][] data = new double[2][];

		if(useRandomData) {
			
			data = CreateData.genRandData(sampleSize);
			
		} else {

			double inputM = 0;
			double inputB = 0;
			
			Scanner input = new Scanner(System.in);
			System.out.println("Enter m value:");
			
			
			
			try {
				inputM = input.nextDouble();
			} catch(InputMismatchException e) {
				System.out.println("Invalid m value.");
			}

			System.out.println("Enter b value:");

			try {
				inputB = input.nextDouble();
			} catch(InputMismatchException e) {
				System.out.println("Invalid b value.");
			}


			data = CreateData.genData(inputM, inputB, sampleSize);
			input.close();
		}



		x = data[0];
		y = data[1];


		if(!(x.length == y.length)) {
			System.out.println("Faulty data. Abort!!");
			return;
		}



		int runs = 0;
		int lap = 0;
		
		double criteria = initCriteria;

		System.out.println("Starting linear regression.\n");

		long time = System.currentTimeMillis();

		while(cost() > criteria) {
			double mDerivative = derivative(learningRate, x, y, m, false);
			double bDerivative = derivative(learningRate, x, y, b, true);
			//System.out.println("m deriv: " + mDerivative + ", b deriv: " + bDerivative);
			m -= mDerivative;
			b -= bDerivative;

			runs++;

			if(runs > loosenLap) {
				criteria = loosenCriteria ? (criteria * loosenValue) : criteria;
				runs = 0;
				lap += 1;
			}




		}
		System.out.println("Complete\ncost: " + cost() + ", total runs #" + (100000 * lap + runs) + ", took " + (System.currentTimeMillis() - time) + " milleseconds, criteria of " + criteria + ", criteria was " + (loosenCriteria ? "loosened by value of " + loosenValue  + " every " + loosenLap + " runs.\n": "not loosened.\n") 
				+ "learning rate: " + learningRate + ", initial criteria: " + initCriteria);

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
