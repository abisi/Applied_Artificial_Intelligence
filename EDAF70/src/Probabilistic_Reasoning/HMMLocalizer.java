package Probabilistic_Reasoning;

public class HMMLocalizer implements EstimatorInterface {

    // ==========================================================
  	// Private Properties
  	// ========================================================== 
	
	private int ROWS, COLS, HEAD;
		
	// Transition matrix
	private TransitionModel T;
	
    // ==========================================================
  	// Constructor
  	// ========================================================== 

	public HMMLocalizer( int rows, int cols) {
		ROWS = rows;
		COLS = cols;
		HEAD = 4;
		
		// generate transition matrix
		T = new TransitionModel(rows,cols);
		
	}	
	
	
	
	
    // ==========================================================
  	// EstimatorInterface classes
  	// ========================================================== 
	
	@Override
	public int getNumRows() {
		return ROWS;
	}

	@Override
	public int getNumCols() {
		return COLS;
	}

	@Override
	public int getNumHead() {
		return HEAD;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int[] getCurrentTruePosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getCurrentReading() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getCurrentProb(int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOrXY(int rX, int rY, int x, int y, int h) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTProb(int x, int y, int h, int nX, int nY, int nH) {
		return T.getElementAt(x,y,h,nX,nY,nH);
	}

}
