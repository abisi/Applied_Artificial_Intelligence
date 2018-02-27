package Probabilistic_Reasoning;

public class SensorModel {

	// ==========================================================
  	// Private Properties
  	// ========================================================== 
	
	private int ROWS, COLS, HEAD;
	
	// Number of states
	private int s; 
		
    // ==========================================================
  	// Constructor
  	// ========================================================== 

	public SensorModel( int rows, int cols) {
		ROWS = rows;
		COLS = cols;
		HEAD = 4;
		s = ROWS*COLS*HEAD;
		
	}	
	
	// ==========================================================
  	// Public methods
  	// ========================================================== 
	
	public double getOrXY(int rX, int rY, int x, int y, int h) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int[] getCurrentReading(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	
	// ==========================================================
  	// Private methods
  	// ========================================================== 
	
	private int stateIndex(int x, int y, int h) {
		return h + x * HEAD + y * HEAD * ROWS;
	}
	
}
