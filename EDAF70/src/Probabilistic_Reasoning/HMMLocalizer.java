package Probabilistic_Reasoning;

public class HMMLocalizer implements EstimatorInterface {

    // ==========================================================
  	// Private Properties
  	// ========================================================== 
	
	// number of rows, columns and headings
	private int ROWS, COLS, HEAD;
		
	// current true position
	private int TrueX, TrueY;
	
	// Transition matrix
	private TransitionModel T;
	
	// Observation matrices
	private SensorModel O;
	
    // ==========================================================
  	// Constructor
  	// ========================================================== 

	public HMMLocalizer( int rows, int cols) {
		ROWS = rows;
		COLS = cols;
		HEAD = 4;
		
		// Initialize the position
		
		// generate transition matrix
		T = new TransitionModel(rows,cols);
		
		// generate observation matrices
		O = new SensorModel(rows,cols);
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
		return new int[] {TrueX,TrueY};
	}

	@Override
	public int[] getCurrentReading() {
		return O.getCurrentReading(TrueX, TrueY);
	}

	@Override
	public double getCurrentProb(int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOrXY(int rX, int rY, int x, int y, int h) {
		return O.getOrXY(rX, rY, x, y, h);
	}

	@Override
	public double getTProb(int x, int y, int h, int nX, int nY, int nH) {
		return T.getElementAt(x,y,h,nX,nY,nH);
	}

}
