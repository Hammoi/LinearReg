package main;

public class LinearReg {

	private static final int[] x = {2,4,5,8,10,12,15,17,38,58,67,73,85,94,104};
	private static final int[] y = {52,102,127,202,252,302,377,427,952,1452,1677,1827,2127,2352,2602};

	private static double m = 1; //theta 1
	private static double b = 3; //theta 0

	public static void main(String[] args) {
		//LINEAR REGRESSION
		//mx+b
		if(!(x.length == y.length)) {
			System.out.println("Faulty data. Abort!!");
			return;
		}




		double learningRate = 0.0001;
		while(cost() > 0.0001) {
			double mDerivative = derivative(learningRate, x, y, m, false);
			double bDerivative = derivative(learningRate, x, y, b, true);
			System.out.println("m deriv: " + mDerivative + ", b deriv: " + bDerivative);
			m -= mDerivative;
			b -= bDerivative;

			System.out.println("cost: " + cost());
		}
		
		System.out.println(Math.round(m) + "x + " + Math.round(b));


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
	
	private static double derivative(double lr, int[] x, int[]y, double m, boolean constant) {


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
