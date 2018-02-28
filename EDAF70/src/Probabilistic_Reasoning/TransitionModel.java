package Probabilistic_Reasoning;

import java.util.ArrayList;
import java.util.Random;


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
	
	public double getElementAt(Position oldPos , Position newPos) {
		// compute matrix indexes from coordinates
		int from = stateIndex(oldPos.getX(),oldPos.getY(),oldPos.getH());
		int to = stateIndex(newPos.getX(),newPos.getY(),newPos.getH());
		
		// return respective element in transition matrix
		return T.getElementAt(from, to);
	}
	
	public Position nextPosition(Position oldPosition) {
	
		// get possible next Positions
		ArrayList<Position> positions = new ArrayList<Position>();
		
		int oldindex = stateIndex(oldPosition);

		for (int nextindex = 0; nextindex < s; nextindex++) {
			
			double chance = T.getElementAt(oldindex,nextindex);
			
			if(chance > 0.001) {
				int percentage = Math.round((int)(chance * 100));
				for (int i = 0; i < percentage; i++) {
					positions.add(statePosition(nextindex));
				}				
			}
		}
		
		// choose the next position according to the possibilities
		return positions.get( new Random().nextInt(positions.size()));
	}
	
	// ==========================================================
  	// Private methods
  	// ========================================================== 
	
	private int stateIndex(int x, int y, int h) {
		return h + x * HEAD + y * HEAD * ROWS;
	}

	private int stateIndex(Position pos) {
		return pos.getH() + pos.getX() * HEAD + pos.getY() * HEAD * ROWS;
	}


	private Position statePosition(int index) {
		int y = index / (HEAD * ROWS);
	    index -= (y * HEAD * ROWS);
	    int x = index / HEAD;
	    int h = index % HEAD;
	    return new Position(x,y,h);
		
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
			T.setElementAt(stateIndex(row,COLS-1,0),stateIndex(row-1,COLS-1,0),0.7);
			T.setElementAt(stateIndex(row,COLS-1,0),stateIndex(row,COLS-2,3), 0.15);
			T.setElementAt(stateIndex(row,COLS-1,0),stateIndex(row+1,COLS-1,2), 0.15);
			T.setElementAt(stateIndex(row,COLS-1,1),stateIndex(row-1,COLS-1,0),1.0/3);
			T.setElementAt(stateIndex(row,COLS-1,1),stateIndex(row,COLS-2,3), 1.0/3);
			T.setElementAt(stateIndex(row,COLS-1,1),stateIndex(row+1,COLS-1,2), 1.0/3);
			T.setElementAt(stateIndex(row,COLS-1,2),stateIndex(row-1,COLS-1,0),0.15);
			T.setElementAt(stateIndex(row,COLS-1,2),stateIndex(row,COLS-2,3), 0.15);
			T.setElementAt(stateIndex(row,COLS-1,2),stateIndex(row+1,COLS-1,2), 0.7);
			T.setElementAt(stateIndex(row,COLS-1,3),stateIndex(row-1,COLS-1,0),0.15);
			T.setElementAt(stateIndex(row,COLS-1,3),stateIndex(row,COLS-2,3), 0.7);
			T.setElementAt(stateIndex(row,COLS-1,3),stateIndex(row+1,COLS-1,2), 0.15);
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
			T.setElementAt(stateIndex(ROWS-1,col,1), stateIndex(ROWS-2,col,0), 0.15);
			T.setElementAt(stateIndex(ROWS-1,col,1), stateIndex(ROWS-1,col+1,1), 0.7);			
			T.setElementAt(stateIndex(ROWS-1,col,2), stateIndex(ROWS-1,col-1,3), 1.0/3);
			T.setElementAt(stateIndex(ROWS-1,col,2), stateIndex(ROWS-2,col,0), 1.0/3);
			T.setElementAt(stateIndex(ROWS-1,col,2), stateIndex(ROWS-1,col+1,1), 1.0/3);			
			T.setElementAt(stateIndex(ROWS-1,col,3), stateIndex(ROWS-1,col-1,3), 0.7);
			T.setElementAt(stateIndex(ROWS-1,col,3), stateIndex(ROWS-2,col,0), 0.15);
			T.setElementAt(stateIndex(ROWS-1,col,3), stateIndex(ROWS-1,col+1,1), 0.15);			
		}
		
		// else (not next to wall)
		for (int row = 1; row < ROWS-1; row++) {
			for (int col = 1; col < COLS-1; col++) {
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
