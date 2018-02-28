package Probabilistic_Reasoning;

import java.util.Random;

public class HMMLocalizer implements EstimatorInterface {

    // ==========================================================
  	// Private Properties
  	// ========================================================== 
	
	// number of rows, columns and headings
	private int ROWS, COLS, HEAD;
		
	// current true position
	private Position TruePosition;
	
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
		TruePosition = new Position(new Random().nextInt(ROWS),new Random().nextInt(COLS),new Random().nextInt(HEAD));
		System.out.println("The initial position is: " + TruePosition.toString());
		
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
		// make move and update true position
		TruePosition = T.nextPosition(TruePosition);
		System.out.println(TruePosition.toString());
		
		// update sensor reading
		
		
		// update probability for current position
		
		
	}

	@Override
	public int[] getCurrentTruePosition() {
		return new int[] {TruePosition.getX(),TruePosition.getY()};
	}

	@Override
	public int[] getCurrentReading() {
		Position currReading = O.currentReading(TruePosition);
		return new int[] {currReading.getX(), currReading.getY() };
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
		return T.getElementAt(new Position(x,y,h),new Position(nX,nY,nH));
	}

}
