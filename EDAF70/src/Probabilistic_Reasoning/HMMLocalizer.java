package Probabilistic_Reasoning;

public class HMMLocalizer implements EstimatorInterface {

    // ==========================================================
  	// Private Properties
  	// ========================================================== 
	
	private int ROWS, COLS, HEAD;
	
	// Number of states
	private int s; 
	
	// Transition matrix
	private Matrix T;
	
    // ==========================================================
  	// Constructor
  	// ========================================================== 

	public HMMLocalizer( int rows, int cols) {
		ROWS = rows;
		COLS = cols;
		HEAD = 4;
		s = ROWS*COLS*HEAD;
		
		// generate transition matrix
		generateT();
	}	
	
	// ==========================================================
  	// Private methods
  	// ========================================================== 
	private void generateT() {
		
		T = new Matrix(s,s);
		
		for (int row = 1; row < ROWS-1; row ++) {
			for(int col = 1; col < COLS-1; col++) {
				for(int h = 0; h < HEAD; h ++) {
					for (int newRow = 1; newRow < ROWS-1; newRow ++) {
						for (int newCol = 1; newCol < COLS-1; newCol ++) {
							for (int newH = 0; newH < HEAD; newH ++) {
								
								// if left wall
								if (col == 1) {
									// if encountering wall
									if (h == 3) {
										
									}	
								}
								
							}
						}
					}
				}
			}
		}
		
		
		
		// if corner
		
		// left upper corner
		T.setElementAt(stateIndex(0,0,0), stateIndex(1,0,2), 0.5); // P((0,0,N)->(1,0,S))
		T.setElementAt(stateIndex(0,0,0), stateIndex(0,1,1), 0.5); // P((0,0,N)->(1,0,E))
		T.setElementAt(stateIndex(0,0,1), stateIndex(1,0,2), 0.3); // P((0,0,E)->(1,0,S))
		T.setElementAt(stateIndex(0,0,1), stateIndex(0,1,1), 0.7); // P((0,0,E)->(1,0,E))
		T.setElementAt(stateIndex(0,0,2), stateIndex(1,0,2), 0.7); // P((0,0,S)->(1,0,S))
		T.setElementAt(stateIndex(0,0,2), stateIndex(0,1,1), 0.3); // P((0,0,N)->(1,0,E))
		T.setElementAt(stateIndex(0,0,3), stateIndex(1,0,2), 0.5); // P((0,0,W)->(1,0,S))
		T.setElementAt(stateIndex(0,0,3), stateIndex(0,1,1), 0.5); // P((0,0,W)->(1,0,E))
		
		// right upper corner
		T.setElementAt(stateIndex(0,COLS-1,0), stateIndex(0,COLS-2,3), 0.5); 
		T.setElementAt(stateIndex(0,COLS-1,0), stateIndex(1,COLS-1,2), 0.5); 
		T.setElementAt(stateIndex(0,COLS-1,1), stateIndex(0,COLS-2,3), 0.5); 
		T.setElementAt(stateIndex(0,COLS-1,1), stateIndex(1,COLS-1,2), 0.5); 
		T.setElementAt(stateIndex(0,COLS-1,2), stateIndex(0,COLS-2,3), 0.3); 
		T.setElementAt(stateIndex(0,COLS-1,2), stateIndex(1,COLS-1,2), 0.7); 
		T.setElementAt(stateIndex(0,COLS-1,3), stateIndex(0,COLS-2,3), 0.7); 
		T.setElementAt(stateIndex(0,COLS-1,3), stateIndex(1,COLS-1,2), 0.3); 
		
		// left lower corner
		T.setElementAt(stateIndex(ROWS-1,0,0), stateIndex(ROWS-2,0,0), 0.7); 
		T.setElementAt(stateIndex(ROWS-1,0,0), stateIndex(ROWS-1,1,1), 0.3); 
		T.setElementAt(stateIndex(ROWS-1,0,1), stateIndex(ROWS-2,0,0), 0.3); 
		T.setElementAt(stateIndex(ROWS-1,0,1), stateIndex(ROWS-1,1,1), 0.7); 	
		T.setElementAt(stateIndex(ROWS-1,0,2), stateIndex(ROWS-2,0,0), 0.5); 
		T.setElementAt(stateIndex(ROWS-1,0,2), stateIndex(ROWS-1,1,1), 0.5); 
		T.setElementAt(stateIndex(ROWS-1,0,3), stateIndex(ROWS-2,0,0), 0.5); 
		T.setElementAt(stateIndex(ROWS-1,0,3), stateIndex(ROWS-1,1,1), 0.5); 
		
		// right lower corner
		T.setElementAt(stateIndex(ROWS-1,COLS-1,0), stateIndex(ROWS-2,COLS-1,0), 0.7); 
		T.setElementAt(stateIndex(ROWS-1,COLS-1,0), stateIndex(ROWS-1,COLS-2,3), 0.3); 
		T.setElementAt(stateIndex(ROWS-1,COLS-1,1), stateIndex(ROWS-2,COLS-1,0), 0.5); 
		T.setElementAt(stateIndex(ROWS-1,COLS-1,1), stateIndex(ROWS-1,COLS-2,3), 0.5); 
		T.setElementAt(stateIndex(ROWS-1,COLS-1,2), stateIndex(ROWS-2,COLS-1,0), 0.5); 
		T.setElementAt(stateIndex(ROWS-1,COLS-1,2), stateIndex(ROWS-1,COLS-2,3), 0.5); 
		T.setElementAt(stateIndex(ROWS-1,COLS-1,3), stateIndex(ROWS-2,COLS-1,0), 0.3); 
		T.setElementAt(stateIndex(ROWS-1,COLS-1,3), stateIndex(ROWS-1,COLS-2,3), 0.7); 

		// if next to one wall
		
		// vertical wall
		for (int row = 1; row < ROWS-1; row++ ) {
			
			T.setElementAt(stateIndex(row,0,0),stateIndex(row-1,0,0),0.7);
			
		}
		
		// horizontal wall
		
		
		// else (not next to wall)
		
		
	}
	
	
	private int stateIndex(int x, int y, int h) {
		return h + x * HEAD + y * HEAD * ROWS;
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
		// compute matrix indexes from coordinates
		int from = stateIndex(x,y,h);
		int to = stateIndex(nX,nY,nH);
		
		// return respective element in transition matrix
		return T.getElementAt(from, to);
	}

}
