package Search;
/*
 * A class that represents the gameboard, stones and possible moves.
 */

import java.util.ArrayList;

public class GameBoard {

	public static int EMPTY = 0;
	public static int BLACK = 1;
	public static int WHITE = 2;
	
	public int[][] Board;
	

	public GameBoard() {
		Board = new int[8][8];
	}
	
	public int getSize() {
		return Board.length;
	}
	
	public boolean isFull() {
		for(int i = 0; i < Board.length; i++) {
			for(int j = 0; j < Board[i].length; j++) {
				if (Board[i][j] == GameBoard.EMPTY) return false;
			}
		}
		return true;
	}
	
	public int countStones(int Player) {
		int count = 0;
		for(int i = 0; i < Board.length; i++) 
			for(int j = 0; j < Board[i].length; j++) 
				if (Board[i][j] == Player) count++;
		return count;
	}
	
	private boolean isOnBoard(Coordinates coord) {
		if(coord.X > 7 | coord.Y > 7) return false;
		if(coord.X < 0 | coord.Y < 0) return false;
		return true;
	}
	
	private ArrayList<Coordinates> neighboursOf(Coordinates coord, int Color) { //Find all empty cases around one case
		ArrayList<Coordinates> result = new ArrayList<Coordinates>();
		
		if (isOnBoard(new Coordinates(coord.X-1,coord.Y))) 
			if(Board[coord.X-1][coord.Y] == Color ) result.add(new Coordinates(coord.X-1,coord.Y));
		if (isOnBoard(new Coordinates(coord.X+1,coord.Y))) 
			if(Board[coord.X+1][coord.Y] == Color)result.add(new Coordinates(coord.X+1,coord.Y));
		if (isOnBoard(new Coordinates(coord.X,coord.Y-1))) 		
			if (Board[coord.X][coord.Y-1] == Color) result.add(new Coordinates(coord.X,coord.Y-1));
		if (isOnBoard(new Coordinates(coord.X,coord.Y+1))) 	
			if(Board[coord.X][coord.Y+1] == Color) result.add(new Coordinates(coord.X,coord.Y+1));	
		if (isOnBoard(new Coordinates(coord.X-1,coord.Y-1))) 
			if(Board[coord.X-1][coord.Y-1] == Color) result.add(new Coordinates(coord.X-1,coord.Y-1));
		if (isOnBoard(new Coordinates(coord.X+1,coord.Y+1))) 	
			if(Board[coord.X+1][coord.Y+1] == Color) result.add(new Coordinates(coord.X+1,coord.Y+1));
		if (isOnBoard(new Coordinates(coord.X+1,coord.Y-1))) 
			if(Board[coord.X+1][coord.Y-1] == Color) result.add(new Coordinates(coord.X+1,coord.Y-1));
		if (isOnBoard(new Coordinates(coord.X-1,coord.Y+1))) 
			if(Board[coord.X-1][coord.Y+1] == Color) result.add(new Coordinates(coord.X-1,coord.Y+1));
		return result;
		}
	
	private ArrayList<Coordinates> opponentStones(int player){
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
		ArrayList<Coordinates> opp = opponentStones(player);  //opponent stones
		
		for(int i = 0; i < opp.size(); i++) {
			Coordinates opponent = opp.get(i);
			potentialMoves = neighboursOf(opponent,EMPTY);  //all the EMPTY cases around an opponent stones
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
						break;
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
		// Check whether the move is a legal move
		System.out.println("makeMove");
		//Place the new stone
		Board[move.X][move.Y] = player;
		
		//Neighbouring opponent stones
		int opponent = (player == WHITE) ? BLACK : WHITE;
		ArrayList<Coordinates> closeOpponents = neighboursOf(move, opponent);
		
		//Flip stones in between move and the current player's own stones
		for(int i = 0; i < closeOpponents.size(); i++) {
			// opponent
			Coordinates oppPos = closeOpponents.get(i);
			
			//directing vectors
			int d_x = oppPos.X-move.X;
			int d_y = oppPos.Y-move.Y;
			
			for(int lambda = 1; lambda < Board.length; lambda++) {
				Coordinates pos = new Coordinates(move.X + lambda*d_x, move.Y + lambda*d_y);
				if (!isOnBoard(pos)) break;
				if (Board[pos.X][pos.Y] == EMPTY) break; 
				if (Board[pos.X][pos.Y] == opponent) continue;
				if (Board[pos.X][pos.Y] == player) {
					for(int j = 1; j < lambda; j++) {
						Board[pos.X - j*d_x][pos.Y - j*d_y] = player;
					}
				}
			}
		}
	}
	
}
