package Probabilistic_Reasoning;

import java.util.ArrayList;


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
		for (int row = 0; row < ROWS; row ++)
			for (int col = 0; col < COLS; col ++)
				MATRIX[row][col] = 0.0;
	}
	
	public Matrix(double[][] matrix) {
		ROWS = matrix.length;
		COLS = matrix[0].length;
		MATRIX = matrix;
	}
	
	public Matrix(ArrayList<Double> diag) {
		ROWS = diag.size();
		COLS = diag.size();
		MATRIX = new double[ROWS][COLS];
		
		for (int row = 0; row < ROWS; row ++)
			for (int col = 0; col < COLS; col ++)
				MATRIX[row][col] = (row == col) ? diag.get(row) : 0.0;
		
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
	
	public void plusSetElementAt(int x, int y, double value) {
		MATRIX[x][y] += value;
	}
	
	public void norm() {
		double sum = 0.0;
		
		// sum up all the elements
		for (int row = 0; row < ROWS; row ++)
			for (int col = 0; col < COLS; col ++) 
				sum += MATRIX[row][col];
		
		// normalize by the sum
		for (int row = 0; row < ROWS; row ++)
			for (int col = 0; col < COLS; col ++)
				MATRIX[row][col] /= sum;
	}
	
	public void multiplyByScalar(double scalar) {
		for (int row = 0; row < ROWS; row ++)
			for (int col = 0; col < COLS; col ++)
				MATRIX[row][col] *= scalar;
	}
	
	public void setScalar(double scalar) {
		for (int row = 0; row < ROWS; row ++)
			for (int col = 0; col < COLS; col ++)
				MATRIX[row][col] = scalar;
	}
	
	public String toString() {
		
		String s = "";
		
		for (int row = 0; row < ROWS; row ++) {
			s += "|";
			for (int col = 0; col < COLS; col ++) {
				s += " " + MATRIX[row][col] + " ";
			}
			
			s += "| \n";
		}
		
		return s;
	}
	
	public Matrix transpose() {
		Matrix transpose = new Matrix(COLS,ROWS);
		
		for (int row = 0; row < ROWS; row ++)
			for (int col = 0; col < COLS; col ++) 
				transpose.setElementAt(col, row, MATRIX[row][col]);
		
		return transpose;
	}
	
	public Matrix multiplyRightsidedByMatrix(Matrix multi) {		
		Matrix product = new Matrix(ROWS,multi.getCols());
		
		for (int i = 0; i < product.getRows(); i ++)
			for (int j = 0; j < product.getCols(); j++)
				for (int k = 0; k < COLS; k++)
					product.plusSetElementAt(i, j, MATRIX[i][k] * multi.getElementAt(k, j));
		
		return product;
	}
		

}
