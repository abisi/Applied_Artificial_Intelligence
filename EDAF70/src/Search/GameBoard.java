package Search;
/*
 * A class that represents the gameboard, stones and possible moves.
 */

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
	
	public ArrayList<Coordinates> emptyNeighbours(Coordinates coord) { //Find all empty cases around one case
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
		int opponent = (player == WHITE) ? BLACK : WHITE; 
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
		
		// define the opponent player
		int op = (player == BLACK) ? WHITE : BLACK;
		
		
		ArrayList<Coordinates> finalMoves = new ArrayList<Coordinates>(); 
		ArrayList<Coordinates> potentialMoves = new ArrayList<Coordinates>(); //intermediate moves (requiring more constraints)
		ArrayList<Coordinates> opp;
		opp = opponentStones(player);  //opponent stones
		for(int i = 0; i < opp.size(); i++) {
			Coordinates opponent = opp.get(i);
			potentialMoves = emptyNeighbours(opponent);  //all the EMPTY cases around an opponent stones
			for(int k = 0; k < potentialMoves.size(); k++) { //for each empty cases we check if there is in one end of sev. directions the current player's color
				Coordinates possibleMove = potentialMoves.get(k);
				//directing vectors
				int d_x = opponent.X-possibleMove.X;
				int d_y = opponent.Y-possibleMove.Y;
				
				for (int lambda = 1; lambda < Board.length; lambda++) {
					Coordinates newpos = new Coordinates(opponent.X + lambda*d_x,opponent.Y + lambda*d_y);
					if (!isOnBoard(newpos)) break;
					if (Board[newpos.X][newpos.Y] == EMPTY) break;
					if(Board[newpos.X][newpos.Y] == op) continue;
					if(Board[newpos.X][newpos.Y] == player) {
						finalMoves.add(possibleMove);
					}
					break;
				}
			}
		}
		return finalMoves;
	}

	public boolean isMoveAvailable(int player) {
		return availableMoves(player).isEmpty() ? false : true;
	}	
	
	
	public void makeMove(int player, Coordinates move) {
		
		//Place the new stone
		Board[move.X][move.Y] = player;
		
		//Neighbouring opponent stones
		ArrayList<Coordinates> closeOpponents = oppNeighbours(move, player);
		
		//Flip stones inbetween move and the current player's own stones
		for(int i = 0; i < closeOpponents.size(); i++) {
			
			//directing vectors
			int d_x = closeOpponents.get(i).X-move.X;
			int d_y = closeOpponents.get(i).Y-move.Y;
			for(int lambda = 1; lambda < Board.length; lambda++) {
				Coordinates pos = new Coordinates(closeOpponents.get(i).X + lambda*d_x, closeOpponents.get(i).Y + lambda*d_y);
				if (!isOnBoard(pos)) break;
				if (Board[pos.X][pos.Y] == EMPTY) break; 
				if(Board[pos.X][pos.Y] != player) continue;
				if(Board[pos.X][pos.Y] == player) {
					Board[pos.X][pos.Y] = player;
					for(int j = 0; j < lambda; j++) {
						Board[pos.X - j*d_x][pos.Y - j*d_y] = player;
					}
				}
			}
		}
	}
		
		
		
	
	public ArrayList<Coordinates> oppNeighbours(Coordinates coord, int player) { //Find all opponent neighbours
		ArrayList<Coordinates> result = new ArrayList<Coordinates>();
		
		if (Board[coord.X-1][coord.Y] != player && isOnBoard(new Coordinates(coord.X-1,coord.Y))) 
			result.add(new Coordinates(coord.X-1,coord.Y));
		if (Board[coord.X+1][coord.Y] != player && isOnBoard(new Coordinates(coord.X+1,coord.Y))) 
			result.add(new Coordinates(coord.X+1,coord.Y));
		if (Board[coord.X][coord.Y-1] != player && isOnBoard(new Coordinates(coord.X,coord.Y-1))) 		
			result.add(new Coordinates(coord.X,coord.Y-1));
		if (Board[coord.X][coord.Y+1] != player && isOnBoard(new Coordinates(coord.X,coord.Y+1))) 	
			result.add(new Coordinates(coord.X,coord.Y+1));	
		if (Board[coord.X-1][coord.Y-1] != player && isOnBoard(new Coordinates(coord.X-1,coord.Y-1))) 
			result.add(new Coordinates(coord.X-1,coord.Y-1));
		if (Board[coord.X+1][coord.Y+1] != player && isOnBoard(new Coordinates(coord.X+1,coord.Y+1))) 	
			result.add(new Coordinates(coord.X+1,coord.Y+1));
		if (Board[coord.X+1][coord.Y-1] != player && isOnBoard(new Coordinates(coord.X+1,coord.Y-1))) 
			result.add(new Coordinates(coord.X+1,coord.Y-1));
		if (Board[coord.X-1][coord.Y+1] != player && isOnBoard(new Coordinates(coord.X-1,coord.Y+1))) 
			result.add(new Coordinates(coord.X-1,coord.Y+1));
		
		return result;
		}
}
