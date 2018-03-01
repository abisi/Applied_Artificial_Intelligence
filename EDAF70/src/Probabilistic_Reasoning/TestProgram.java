package Probabilistic_Reasoning;

import java.io.*;

public class TestProgram {
	
	// ==========================================================
  	// Private Properties
  	// ========================================================== 
	
	private int Runs, Iterations;
	private HMMLocalizer Localizer;
	
	private Matrix testData;
	
    // ==========================================================
  	// Constructor
  	// ========================================================== 
	
	public TestProgram(HMMLocalizer localizer, int runs, int iterations) {
		Localizer = localizer;
		Runs = runs;
		Iterations = iterations;
	}
	
	// ==========================================================
  	// Public Methods
  	// ========================================================== 
	
	public void testEstimator() {
		
		testData = new Matrix(Iterations,Runs);
		
		for (int iter = 0; iter < Iterations; iter ++) {
			
			Localizer.reset();
			
			for (int i = 0; i < Runs; i++) {
				
				// save true position
				int[] truePosition = Localizer.getCurrentTruePosition();
				
				// save current best estimate
				int[] bestEstimate = Localizer.getCurrentBestEstimate();
				
				// save Manhattan distance
				int manhattan = ManhattanDistance(truePosition, bestEstimate);
				testData.setElementAt(iter, i, manhattan);
				
				// make a step
				Localizer.update();
		
			}
			
		}
		
		// safe in local file
		try {
            String str = testData.toMATLABString();
            File newTextFile = new File("src/Probabilistic_Reasoning/HMMLocalizer.txt");

            FileWriter fw = new FileWriter(newTextFile);
            fw.write(str);
            fw.close();

        } catch (IOException iox) {
            iox.printStackTrace();
        }
		
	}
	
	public void testMatrixFunctions() {
		double[][] A = new double[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
		Matrix A_mat = new Matrix(A);
		System.out.println("A:");
		System.out.println(A_mat.toString());
		
		double[][] B = new double[][] {{1,2},{3,4},{5,6},{7,8}};
		Matrix B_mat = new Matrix(B);		
		System.out.println("B:");
		System.out.println(B_mat.toString());
		
		Matrix C_mat = A_mat.multiplyRightsidedByMatrix(B_mat);
		System.out.println("C:");
		System.out.println(C_mat.toString());
		C_mat.norm();
		System.out.println(C_mat.toString());
				
	}
	
	// ==========================================================
  	// Private Methods
  	// ========================================================== 
	
	private int ManhattanDistance(int[] X, int[] Y) {
		int verticalDifference = Math.abs(X[1] - Y[1]);
		int horizontalDifference = Math.abs(X[0] - Y[0]);
		return verticalDifference + horizontalDifference;
	}
	

}
