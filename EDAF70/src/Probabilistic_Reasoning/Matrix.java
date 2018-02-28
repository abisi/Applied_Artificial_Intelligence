package Probabilistic_Reasoning;

public class Matrix {
	
    // ==========================================================
  	// Private Properties
  	// ========================================================== 
	private int ROWS,COLS;
	private double[][] MATRIX;
	
    // ==========================================================
  	// Constructor
  	// ========================================================== 
	
	public Matrix(int rows, int cols) {
		ROWS = rows;
		COLS = cols;
		MATRIX = new double[rows][cols];
	}
	
	public void norm() {
		double sum = 0;
		
		// sum up all the elements
		for (int row = 0; row < ROWS; row ++)
			for (int col = 0; col < COLS; col ++) 
				sum += MATRIX[row][col];
		
		// normalize by the sum
		for (int row = 0; row < ROWS; row ++)
			for (int col = 0; col < COLS; col ++)
				MATRIX[row][col] /= sum;
	}
	
    // ==========================================================
  	// Public Methods
  	// ========================================================== 

	public int getRows() {
		return ROWS;
	}
	
	public int getCols() {
		return COLS;
	}
	
	public double[][] getMatrix() {
		return MATRIX;
	}
	
	public double getElementAt(int x, int y) {
		return MATRIX[x][y];
	}
	
	public void setElementAt(int x , int y, double value) {
		MATRIX[x][y] = value;
	}
	
	public Matrix transpose() {
		return new Matrix(1,1);
	}
	
	public Matrix multiplyByO (Matrix factor) {
		return new Matrix(1,1);
	}
	

}
