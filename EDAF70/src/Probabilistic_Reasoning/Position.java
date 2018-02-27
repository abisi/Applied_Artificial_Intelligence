package Probabilistic_Reasoning;

public class Position {

    // ==========================================================
  	// Private Properties
  	// ========================================================== 
	
	// number of rows, columns and headings
	private int X, Y, H;
			
    // ==========================================================
  	// Constructors
  	// ========================================================== 

	public Position( int x, int y, int h) {
		X = x;
		Y = y;
		H = h;
	}	
	
	public Position(int x, int y) {
		X = x;
		Y = y;
		H = 0;
	}
	
	// ==========================================================
  	// Public Methods
  	// ========================================================== 

	public int getX() {
		return X;
	}
	
	public int getY() {
		return Y;
	}
	
	public int getH() {
		return H;
	}

	
}
