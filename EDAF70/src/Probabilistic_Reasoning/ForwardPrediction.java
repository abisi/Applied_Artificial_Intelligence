package Probabilistic_Reasoning;

public class ForwardPrediction {

	
	// ==========================================================
  	// Private Properties
  	// ========================================================== 
	private int ROWS, COLS, HEAD, s;
	private Matrix f;
	
    // ==========================================================
  	// Constructor
  	// ========================================================== 
	
	public ForwardPrediction(int rows, int cols) {
		ROWS = rows;
		COLS = cols;
		HEAD = 4;
		s = ROWS * COLS * 4;
		
		// initialize f
		initializef();
	}
	
    // ==========================================================
  	// Public Methods
  	// ========================================================== 
	
	public double probForPos(Position pos) {
		return f.getElementAt(stateIndex(pos), 0);
	}
	
	public void forwardPrediction(Matrix O, Matrix T) {
		f = O.multiplyRightsidedByMatrix((T.transpose()).multiplyRightsidedByMatrix(f));
		f.norm();
	}
		
    // ==========================================================
  	// Private Methods
  	// ========================================================== 
	
	private void initializef() {
		f = new Matrix(s,1);
		f.setScalar(1.0/s);
		System.out.println(f.toString());
		
	}
	
	private int stateIndex(Position pos) {
		return pos.getH() + pos.getX() * HEAD + pos.getY() * HEAD * ROWS;
	}

}
