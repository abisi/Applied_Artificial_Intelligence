package Search;

 

public class GameBoard {

	public static int EMPTY = 0;
	public static int BLACK = 1;
	public static int WHITE = 2;
	
	int[][] Board[8][8];
	

	public GameBoard() {
		Board = new int[8][8];
	}
	
	public int getSize() {
		return Board.length();
	}
	
	public boolean isFull() {
		for(int i = 0; i < Board.length(); i++) {
			for(int j = 0; j > Board[i].length(); j++) {
				if (Board[i][j] == 0) return false;
			}
		}
		return true;
	}
	
	public ArrayList<Coordinates> emptyNeighbours(Coordinates coord ) {
		ArrayList<Coordinates> result = new ArrayList<Coordinates>();
		
		if (Board[coord.X-1][coord.Y] == 0 && Board[coord.X-1][coord.Y] != null) 
			result.add(new Coordinates(coord.X-1,coord.Y));
		if (Board[coord.X+1][coord.Y] == 0 && Board[coord.X+1][coord.Y] != null) 
			result.add(new Coordinates(coord.X+1,coord.Y));
		if (Board[coord.X][coord.Y-1] == 0 && Board[coord.X][coord.Y-1] != null) 		
			result.add(new Coordinates(coord.X,coord.Y-1));
		if (Board[coord.X][coord.Y+1] == 0 && Board[coord.X][coord.Y+1] != null) 	
			result.add(new Coordinates(coord.X,coord.Y+1));	
		if (Board[coord.X-1][coord.Y-1] == 0 && Board[coord.X-1][coord.Y-1] != null) 
			result.add(new Coordinates(coord.X-1,coord.Y-1));
		if (Board[coord.X+1][coord.Y+1] == 0 && Board[coord.X+1][coord.Y+1] != null) 	
			result.add(new Coordinates(coord.X+1,coord.Y+1));
		if (Board[coord.X+1][coord.Y-1] == 0 && Board[coord.X+1][coord.Y-1] != null) 
			result.add(new Coordinates(coord.X+1,coord.Y-1));
		if (Board[coord.X-1][coord.Y+1] == 0 && Board[coord.X-1][coord.Y+1] != null) 
			result.add(new Coordinates(coord.X-1,coord.Y+1));
		}
	
	public ArrayList<Coordinates> opponentStones(int player){
		int opponent = (player == 1) ? 2 : 1; 
		ArrayList<Coordinates> oppStones = new ArrayList<Coordinates>();
		
		for(int i = 0; i < Board.length(); i++) {
			for(int j = 0; j < Board[i].length(); j++) {
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
		return availableMoves(player).length == 0 ? false : true;
	}
	
	
}
