package main;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class LinearReg {
	private static final int sampleSize = 20;
	private static final boolean loosenCriteria = true;
	private static final int loosenLap = 100000;
	private static final double loosenValue = 2;
	
	private static double[] x = new double[sampleSize];
	private static double[] y = new double[sampleSize];

	private static BigDecimal m = new BigDecimal(0); //theta 1
	private static BigDecimal b = new BigDecimal(0); //theta 0

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
		long runs = 0;
		int lap = 0;
		
		System.out.println("Starting linear regression.\n");
		
		long time = System.currentTimeMillis();
		
		while(cost().compareTo(BigDecimal.valueOf(criteria)) != -1) {
			BigDecimal mDerivative = derivative(learningRate, x, y, m, false);
			BigDecimal bDerivative = derivative(learningRate, x, y, b, true);
			//System.out.println("m deriv: " + mDerivative + ", b deriv: " + bDerivative);
			m = m.subtract(mDerivative);
			b = b.subtract(bDerivative);

			runs++;
			
			System.out.println("Cost: " + cost() + ", Lap: " + (100000 * lap + runs));
			
			if(runs > loosenLap) {
				criteria = loosenCriteria ? criteria * loosenValue : criteria;
				runs = 0;
				lap += 1;
			}
			
			
		
			
		}
		System.out.println("Complete\ncost: " + cost() + ", total runs #" + (100000 * lap + runs) + ", took " + (System.currentTimeMillis() - time) + " milleseconds, criteria of " + criteria + ", criteria was " + (loosenCriteria ? "loosened by value of " + loosenValue  + " every " + loosenLap + " runs.": "not loosened."));
		DecimalFormat df = new DecimalFormat("#.###"); //Rounds to three decimals
		System.out.println(df.format(m) + "x + " + df.format(b));
		

	}

	private static BigDecimal currentFunction(double x) {
		return m.multiply(BigDecimal.valueOf(x)).add(b);
	}

	private static BigDecimal cost() {
		BigDecimal d = new BigDecimal(0);
		
		BigDecimal sum = new BigDecimal(0);
		for(int i = 0; i < x.length; i++) {
			
			//System.out.println(i + " " + (currentFunction(x[i]) - y[i]));
			
			sum = sum.add(currentFunction(x[i]).subtract(BigDecimal.valueOf(y[i])).pow(2));
		}
		
		d = sum.divide(BigDecimal.valueOf((2 * x.length)));
		
		return d;
	}
	
	private static BigDecimal derivative(double lr, double[] x, double[]y, BigDecimal v, boolean constant) {


		if(constant) {

			BigDecimal sum = new BigDecimal(0);
			for(int i = 0; i < x.length; i++) {
				
				//System.out.println(i + " " + (currentFunction(x[i]) - y[i]));
				sum = sum.add(currentFunction(x[i]).subtract(BigDecimal.valueOf(y[i])));
			}
			//System.out.println("b sum: " + sum);
			return sum.multiply(BigDecimal.valueOf(lr)).divide(BigDecimal.valueOf(x.length));

		} else {
			
			BigDecimal sum = new BigDecimal(0);
			for(int i = 0; i < x.length; i++) {
				
				//System.out.println(i + " " + (currentFunction(x[i]) - y[i]));
				sum = sum.add(currentFunction(x[i]).subtract(BigDecimal.valueOf(y[i]))).multiply(BigDecimal.valueOf(x[i]));
			}
			//System.out.println("b sum: " + sum);
			return sum.multiply(BigDecimal.valueOf(lr)).divide(BigDecimal.valueOf(x.length));

		}

	}



}
