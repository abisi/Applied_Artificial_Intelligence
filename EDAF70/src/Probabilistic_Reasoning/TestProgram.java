package Probabilistic_Reasoning;

public class TestProgram {
	
	// ==========================================================
  	// Private Properties
  	// ========================================================== 
	
	private int Runs;
	private HMMLocalizer Localizer;
	
	private Matrix testData;
	
    // ==========================================================
  	// Constructor
  	// ========================================================== 
	
	public TestProgram(HMMLocalizer localizer, int runs) {
		Localizer = localizer;
		Runs = runs;
	}
	
	// ==========================================================
  	// Public Methods
  	// ========================================================== 
	
	public void testEstimator() {
		
		testData = new Matrix(5,Runs);
		
		for (int i = 0; i < Runs; i++) {
			
			// save true position
			int[] truePosition = Localizer.getCurrentTruePosition();
			testData.setElementAt(0, i, truePosition[0]);
			testData.setElementAt(1, i, truePosition[1]);
			
			// save current best estimate
			
			
			// save Manhattan distance
			int manhattan = ManhattanDistance(truePosition, truePosition);
			testData.setElementAt(4, i, manhattan);
			
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
