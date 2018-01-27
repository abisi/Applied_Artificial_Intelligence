package Search;

import java.util.ArrayList;

public class GameBoard {

	public static int EMPTY = 0;
	public static int BLACK = 1;
	public static int WHITE = 2;
	
	int[][] Board = new int[8][8];
	

	public GameBoard() {
		Board = new int[8][8];
	}
	
	public int getSize() {
		return Board.length;
	}
	
	public boolean isFull() {
		for(int i = 0; i < Board.length; i++) {
			for(int j = 0; j > Board[i].length; j++) {
				if (Board[i][j] == 0) return false;
			}
		}
		return true;
	}
	
	public boolean isOnBoard(Coordinates coord) {
		if(coord.X > 7 | coord.Y > 7) return false;
		if(coord.X < 0 | coord.Y < 0) return false;
		return true;
	}
	
	public ArrayList<Coordinates> emptyNeighbours(Coordinates coord ) {
		ArrayList<Coordinates> result = new ArrayList<Coordinates>();
		
		if (Board[coord.X-1][coord.Y] == 0 && isOnBoard(new Coordinates(coord.X-1,coord.Y))) 
			result.add(new Coordinates(coord.X-1,coord.Y));
		if (Board[coord.X+1][coord.Y] == 0 && isOnBoard(new Coordinates(coord.X+1,coord.Y))) 
			result.add(new Coordinates(coord.X+1,coord.Y));
		if (Board[coord.X][coord.Y-1] == 0 && isOnBoard(new Coordinates(coord.X,coord.Y-1))) 		
			result.add(new Coordinates(coord.X,coord.Y-1));
		if (Board[coord.X][coord.Y+1] == 0 && isOnBoard(new Coordinates(coord.X,coord.Y+1))) 	
			result.add(new Coordinates(coord.X,coord.Y+1));	
		if (Board[coord.X-1][coord.Y-1] == 0 && isOnBoard(new Coordinates(coord.X-1,coord.Y-1))) 
			result.add(new Coordinates(coord.X-1,coord.Y-1));
		if (Board[coord.X+1][coord.Y+1] == 0 && isOnBoard(new Coordinates(coord.X+1,coord.Y+1))) 	
			result.add(new Coordinates(coord.X+1,coord.Y+1));
		if (Board[coord.X+1][coord.Y-1] == 0 && isOnBoard(new Coordinates(coord.X+1,coord.Y-1))) 
			result.add(new Coordinates(coord.X+1,coord.Y-1));
		if (Board[coord.X-1][coord.Y+1] == 0 && isOnBoard(new Coordinates(coord.X-1,coord.Y+1))) 
			result.add(new Coordinates(coord.X-1,coord.Y+1));
		
		return result;
		}
	
	public ArrayList<Coordinates> opponentStones(int player){
		int opponent = (player == 1) ? 2 : 1; 
		ArrayList<Coordinates> oppStones = new ArrayList<Coordinates>();
		
		for(int i = 0; i < Board.length; i++) {
			for(int j = 0; j < Board[i].length; j++) {
				if (Board[i][j] == opponent)
					oppStones.add(new Coordinates(i,j));
			}
		}
		return oppStones;	
	}
	
	public ArrayList<Coordinates> availableMoves(int player) {
		return new ArrayList<Coordinates>();
	}

	public boolean isMoveAvailable(int player) {
		return availableMoves(player).isEmpty() ? false : true;
	}	
	
}
