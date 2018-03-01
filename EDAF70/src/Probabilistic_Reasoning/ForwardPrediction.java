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
		double sum = 0.0;
		for (int heading = 0; heading < HEAD; heading ++) {
			sum += f.getElementAt(stateIndex(new Position(pos.getX(), pos.getY(), heading)), 0);
		}
		return sum;
	}
	
	public Position bestEstimate() {
		Position best = new Position(0,0);
		double bestEstimate = 0.0;
		
		for (int row = 0; row < ROWS; row ++)
			for(int col = 0; col < COLS; col ++) {
				double estimate = probForPos(new Position(row,col));
				if (estimate > bestEstimate) {
					bestEstimate = estimate;
					best = new Position(row,col);
				}
			}
		
		return best;
	}
	
	public void forwardPrediction(Matrix O, Matrix T) {
		f = O.multiplyRightsidedByMatrix((T.transpose()).multiplyRightsidedByMatrix(f));
		f.norm();
	}
	
	// ==========================================================
  	// Public Methods
  	// ========================================================== 
	
	public void initializef() {
		f = new Matrix(s,1);
		f.setScalar(1.0/s);		
	}
	
    // ==========================================================
  	// Private Methods
  	// ========================================================== 
		
	private int stateIndex(Position pos) {
		return pos.getH() + pos.getX() * HEAD + pos.getY() * HEAD * ROWS;
	}

}
