package Search;

import java.util.ArrayList;

public class GameBoard {

	// ==========================================================
	// Public Members
	// ==========================================================
	public static int EMPTY = 0;
	public static int BLACK = 1;
	public static int WHITE = 2;
	
	public int[][] Board;
	
	// ==========================================================
	// Constructors
	// ==========================================================
	public GameBoard() {
		Board = new int[8][8];
	}
	
	// ==========================================================
	// Public Methods
	// ==========================================================

	public int getSize() {
		return Board.length;
	}
	
	public GameBoard clone() {
		GameBoard clone =  new GameBoard();
		for(int i = 0; i < clone.Board.length; i++) 
			for(int j = 0; j < clone.Board[i].length; j++) 
				clone.Board[i][j] = Board[i][j];
		return clone;
	}
	
	public boolean isFull() {
		for(int i = 0; i < Board.length; i++) 
			for(int j = 0; j < Board[i].length; j++) 
				if (Board[i][j] == GameBoard.EMPTY) return false;
		return true;
	}
	
	public boolean isGameOver() {
		return isFull() || (!isMoveAvailable(WHITE) && !isMoveAvailable(BLACK)) ;
	}
	
	//counts all the Player's stones on the board
	public int countStones(int Player) {
		int count = 0;
		for(int i = 0; i < Board.length; i++) 
			for(int j = 0; j < Board[i].length; j++) 
				if (Board[i][j] == Player) count++;
		return count;
	}
	
	public ArrayList<Coordinates> availableMoves(int player) {
		
		// define the opponent player
		int op = (player == BLACK) ? WHITE : BLACK;
		
		ArrayList<Coordinates> finalMoves = new ArrayList<Coordinates>(); 
		ArrayList<Coordinates> opp = stones(op);  //opponent stones
		
		for(Coordinates opponent : opp) {
			
			ArrayList<Coordinates> potentialMoves = neighborsOf(opponent,EMPTY);  //all the EMPTY cases around an opponent stones
			
			for(Coordinates possibleMove : potentialMoves) { //for each empty cases we check if there is in one end of sev. directions the current player's color
				
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

		//Place the new stone
		Board[move.X][move.Y] = player;
		
		//Neighboring opponent stones
		int opponent = (player == WHITE) ? BLACK : WHITE;
		ArrayList<Coordinates> closeOpponents = neighborsOf(move, opponent);
		
		//Flip stones in between move and the current player's own stones
		for(Coordinates oppPos : closeOpponents) {
			
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
	
	public int evaluate(int Player) {
		//the quality of move has as a basis: (number of stones + flippable stones) = total stones after move
		int value = countStones(Player);
		
		//that basis is changed according to the type of move
		ArrayList<Coordinates> stones = stones(Player);
		
		for (Coordinates stone : stones) {
			if(isStable(stone)) value += 4;  // stable stones are +
			if(isXSquare(stone)) value -= 6; //must avoid X-squares; the disadvantage of an X-square surpasses the advantage 
											  //of putting a stable stone, hence a greater absolute change	
		}
		return value;
	}
	
	public int finalEvaluation(int player) {
		int op = (player == WHITE) ? BLACK : WHITE;
		int playerStones = countStones(player);
		int opponentStones = countStones(op);
		
		if (playerStones > opponentStones)
			return Integer.MAX_VALUE;
		if (playerStones == opponentStones)
			return 0;
		else
			return Integer.MIN_VALUE;
	}
	
	// ==========================================================
	// Private Methods
	// ==========================================================

	private boolean isOnBoard(Coordinates coord) {
		if(coord.X > 7 | coord.Y > 7) return false;
		if(coord.X < 0 | coord.Y < 0) return false;
		return true;
	}
	
	private ArrayList<Coordinates> neighborsOf(Coordinates coord, int Color) { //Find all empty cases around one case
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
	
	private ArrayList<Coordinates> stones(int player){
		ArrayList<Coordinates> stones = new ArrayList<Coordinates>();
		
		for(int i = 0; i < Board.length; i++)
			for(int j = 0; j < Board[i].length; j++)
				if (Board[i][j] == player)
					stones.add(new Coordinates(i,j));
		return stones;	
	}
	
	
	//checks whether a piece is in a corder
	private boolean isCorner(Coordinates coord) {
		if((coord.X==0) && (coord.Y == 0)) return true;
		if((coord.X==0) && (coord.Y == 7)) return true;
		if((coord.X==7) && (coord.Y == 0)) return true;
		if((coord.X==7) && (coord.Y == 7)) return true;
		return false;
	}
	
	
	//checks whether lines in all directions are full
	private boolean areLinesFull(Coordinates coord) {
		for(int i = 0; i < Board.length; ++i) {
			// check if horizontal line if full
			if (isOnBoard(new Coordinates(coord.X,i))) 
				if (Board[coord.X][i] == GameBoard.EMPTY)return false;
			// check if vertical line is full
			if (isOnBoard(new Coordinates(i,coord.Y)))
				if(Board[i][coord.Y] == GameBoard.EMPTY) return false;
			// check in all of the diagonal directions
			if (isOnBoard(new Coordinates(coord.X + i, coord.Y + i)))
				if (Board[coord.X + i][coord.Y + i] == GameBoard.EMPTY)return false;
			if (isOnBoard(new Coordinates(coord.X-i,coord.Y+i)))
				if (Board[coord.X - i][coord.Y + i] == GameBoard.EMPTY)return false;
			if (isOnBoard(new Coordinates(coord.X + i,coord.Y - i)))
				if (Board[coord.X + i][coord.Y - i] == GameBoard.EMPTY)return false;
			if (isOnBoard(new Coordinates(coord.X - i, coord.Y - i)))
				if (Board[coord.X - i][coord.Y - i] == GameBoard.EMPTY)return false; 
		}
		return true;
	}
	
	//checks whether a piece is stable
	private boolean isStable(Coordinates coord) {
		 if (isCorner(coord) || areLinesFull(coord) ) {
				 return true;
			 } else {
				 return false;
			 }
	}
	
	//checks whether it is an X-square
	private boolean isXSquare(Coordinates coord) {
		if((coord.X==1) && (coord.Y == 1)) return true;
		if((coord.X==1) && (coord.Y == 6)) return true;
		if((coord.X==6) && (coord.Y == 1)) return true;
		if((coord.X==6) && (coord.Y == 6)) return true;
		return false;
	}
	
}
