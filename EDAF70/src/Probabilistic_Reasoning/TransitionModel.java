package Probabilistic_Reasoning;

public class TransitionModel {

	
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

	public TransitionModel( int rows, int cols) {
		ROWS = rows;
		COLS = cols;
		HEAD = 4;
		s = ROWS*COLS*HEAD;
		
		// generate transition matrix
		generateT();
	}	
	
	// ==========================================================
  	// Public methods
  	// ========================================================== 
	
	public double getElementAt(int x, int y, int h, int nX, int nY, int nH) {
		// compute matrix indexes from coordinates
		int from = stateIndex(x,y,h);
		int to = stateIndex(nX,nY,nH);
		
		// return respective element in transition matrix
		return T.getElementAt(from, to);
	}
	
	// ==========================================================
  	// Private methods
  	// ========================================================== 
	
	private int stateIndex(int x, int y, int h) {
		return h + x * HEAD + y * HEAD * ROWS;
	}

	private void generateT() {
		
		T = new Matrix(s,s);
		
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
			
			// left side
			T.setElementAt(stateIndex(row,0,0),stateIndex(row-1,0,0),0.7);
			T.setElementAt(stateIndex(row,0,0),stateIndex(row,1,1), 0.15);
			T.setElementAt(stateIndex(row,0,0),stateIndex(row+1,0,2), 0.15);
			T.setElementAt(stateIndex(row,0,1),stateIndex(row-1,0,0),0.15);
			T.setElementAt(stateIndex(row,0,1),stateIndex(row,1,1), 0.7);
			T.setElementAt(stateIndex(row,0,1),stateIndex(row+1,0,2), 0.15);
			T.setElementAt(stateIndex(row,0,2),stateIndex(row-1,0,0),0.15);
			T.setElementAt(stateIndex(row,0,2),stateIndex(row,1,1), 0.15);
			T.setElementAt(stateIndex(row,0,2),stateIndex(row+1,0,2), 0.7);
			T.setElementAt(stateIndex(row,0,3),stateIndex(row-1,0,0),1.0/3);
			T.setElementAt(stateIndex(row,0,3),stateIndex(row,1,1), 1.0/3);
			T.setElementAt(stateIndex(row,0,3),stateIndex(row+1,0,2), 1.0/3);
			
			// right side
			T.setElementAt(stateIndex(row,COLS-1,0),stateIndex(row-1,0,0),0.7);
			T.setElementAt(stateIndex(row,COLS-1,0),stateIndex(row,1,3), 0.15);
			T.setElementAt(stateIndex(row,COLS-1,0),stateIndex(row+1,0,2), 0.15);
			T.setElementAt(stateIndex(row,COLS-1,1),stateIndex(row-1,0,0),1.0/3);
			T.setElementAt(stateIndex(row,COLS-1,1),stateIndex(row,1,3), 1.0/3);
			T.setElementAt(stateIndex(row,COLS-1,1),stateIndex(row+1,0,2), 1.0/3);
			T.setElementAt(stateIndex(row,COLS-1,2),stateIndex(row-1,0,0),0.15);
			T.setElementAt(stateIndex(row,COLS-1,2),stateIndex(row,1,3), 0.15);
			T.setElementAt(stateIndex(row,COLS-1,2),stateIndex(row+1,0,2), 0.7);
			T.setElementAt(stateIndex(row,COLS-1,3),stateIndex(row-1,0,0),0.15);
			T.setElementAt(stateIndex(row,COLS-1,3),stateIndex(row,1,3), 0.7);
			T.setElementAt(stateIndex(row,COLS-1,3),stateIndex(row+1,0,2), 0.15);
		}
		
		// horizontal wall
		for (int col = 1; col < COLS-1; col++) {
			
			// top wall
			T.setElementAt(stateIndex(0,col,0), stateIndex(0,col-1,3), 1.0/3);
			T.setElementAt(stateIndex(0,col,0), stateIndex(1,col,2), 1.0/3);
			T.setElementAt(stateIndex(0,col,0), stateIndex(0,col+1,1), 1.0/3);			
			T.setElementAt(stateIndex(0,col,1), stateIndex(0,col-1,3), 0.15);
			T.setElementAt(stateIndex(0,col,1), stateIndex(1,col,2), 0.15);
			T.setElementAt(stateIndex(0,col,1), stateIndex(0,col+1,1), 0.7);			
			T.setElementAt(stateIndex(0,col,2), stateIndex(0,col-1,3), 0.15);
			T.setElementAt(stateIndex(0,col,2), stateIndex(1,col,2), 0.7);
			T.setElementAt(stateIndex(0,col,2), stateIndex(0,col+1,1), 0.15);			
			T.setElementAt(stateIndex(0,col,3), stateIndex(0,col-1,3), 0.7);
			T.setElementAt(stateIndex(0,col,3), stateIndex(1,col,2), 0.15);
			T.setElementAt(stateIndex(0,col,3), stateIndex(0,col+1,1), 0.15);			
			
			// bottom wall
			T.setElementAt(stateIndex(ROWS-1,col,0), stateIndex(ROWS-1,col-1,3), 0.15);
			T.setElementAt(stateIndex(ROWS-1,col,0), stateIndex(ROWS-2,col,0), 0.7);
			T.setElementAt(stateIndex(ROWS-1,col,0), stateIndex(ROWS-1,col+1,1), 0.15);			
			T.setElementAt(stateIndex(ROWS-1,col,1), stateIndex(ROWS-1,col-1,3), 0.15);
			T.setElementAt(stateIndex(ROWS-1,col,1), stateIndex(ROWS-2,col,2), 0.15);
			T.setElementAt(stateIndex(ROWS-1,col,1), stateIndex(ROWS-1,col+1,1), 0.7);			
			T.setElementAt(stateIndex(ROWS-1,col,2), stateIndex(ROWS-1,col-1,3), 1.0/3);
			T.setElementAt(stateIndex(ROWS-1,col,2), stateIndex(ROWS-2,col,2), 1.0/3);
			T.setElementAt(stateIndex(ROWS-1,col,2), stateIndex(ROWS-1,col+1,1), 1.0/3);			
			T.setElementAt(stateIndex(ROWS-1,col,3), stateIndex(ROWS-1,col-1,3), 0.7);
			T.setElementAt(stateIndex(ROWS-1,col,3), stateIndex(ROWS-2,col,2), 0.15);
			T.setElementAt(stateIndex(ROWS-1,col,3), stateIndex(ROWS-1,col+1,1), 0.15);			
		}
		
		// else (not next to wall)
		for (int row = 1; row < ROWS-2; row++) {
			for (int col = 1; col < COLS-2; col++) {
				T.setElementAt(stateIndex(row,col,0), stateIndex(row-1,col,0), 0.7);
				T.setElementAt(stateIndex(row,col,0), stateIndex(row,col+1,1), 0.1);
				T.setElementAt(stateIndex(row,col,0), stateIndex(row+1,col,2), 0.1);
				T.setElementAt(stateIndex(row,col,0), stateIndex(row,col-1,3), 0.1);
				T.setElementAt(stateIndex(row,col,1), stateIndex(row-1,col,0), 0.1);
				T.setElementAt(stateIndex(row,col,1), stateIndex(row,col+1,1), 0.7);
				T.setElementAt(stateIndex(row,col,1), stateIndex(row+1,col,2), 0.1);
				T.setElementAt(stateIndex(row,col,1), stateIndex(row,col-1,3), 0.1);
				T.setElementAt(stateIndex(row,col,2), stateIndex(row-1,col,0), 0.1);
				T.setElementAt(stateIndex(row,col,2), stateIndex(row,col+1,1), 0.1);
				T.setElementAt(stateIndex(row,col,2), stateIndex(row+1,col,2), 0.7);
				T.setElementAt(stateIndex(row,col,2), stateIndex(row,col-1,3), 0.1);
				T.setElementAt(stateIndex(row,col,3), stateIndex(row-1,col,0), 0.1);
				T.setElementAt(stateIndex(row,col,3), stateIndex(row,col+1,1), 0.1);
				T.setElementAt(stateIndex(row,col,3), stateIndex(row+1,col,2), 0.1);
				T.setElementAt(stateIndex(row,col,3), stateIndex(row,col-1,3), 0.7);
				
			}
		}
		
		
	}
	
	


	
}
