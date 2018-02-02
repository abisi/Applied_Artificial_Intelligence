package Search;
import java.util.*;

public class Reversi {

	// ==========================================================
	// Private Members
	// ==========================================================
	// Userface and game board
	private UserInterface UI;
    private static GameBoard gb;
    
    // Define the two player types
    private BasePlayer Player1 = new HumanPlayer();
    private BasePlayer Player2 = new MinMaxPlayer();
    
    // time Limit in milliseconds
    private long TimeOut = 4000;
    
    // ==========================================================
 	// Main Method
 	// ========================================================== 
	public static void main(String[] args) {
		
		// creates the Reversi class
		Reversi rvs = new Reversi();
        
        // start a new game
        rvs.newGame();
		
	}
	
    // ==========================================================
 	// Constructor
 	// ========================================================== 
	Reversi() {
		UI = new UserInterface(Player1,Player2);
		gb = new GameBoard();
	}
	
    // ==========================================================
 	// Public Methods
 	// ========================================================== 
	public void newGame() {
		
		// Ask the player 1 about the color
		int col = (Player1 instanceof HumanPlayer) ? UI.chooseColor() : GameBoard.WHITE;
		int PlayerColor1 = (col == 0) ? GameBoard.WHITE : GameBoard.BLACK;
		int PlayerColor2 = (PlayerColor1 == GameBoard.WHITE) ? GameBoard.BLACK : GameBoard.WHITE;
		
		// Define the time limit
		TimeOut = Long.valueOf(1000 * UI.chooseTimeLimit());
		
		// Give away colors
		Player1.initialize(PlayerColor1, TimeOut);
		Player2.initialize(PlayerColor2, TimeOut);
		
		// create starting position
		gb.makeMove(Player1.getColor(),new Coordinates(4,3));
		gb.makeMove(Player1.getColor(),new Coordinates(3,4));
		gb.makeMove(Player2.getColor(),new Coordinates(3,3));
		gb.makeMove(Player2.getColor(),new Coordinates(4,4));
		
		// update GameBoard
		UI.updateGameBoard(gb);
		
		// start playing
		play();
		
	}

	// ==========================================================
 	// Private Methods
 	// ========================================================== 
	
	private void play() {
		
		// define player
		int PlayerColor1 = Player1.getColor();
		int PlayerColor2 = Player2.getColor();
		
		// play as long as the GameBoard is not full
		while(!gb.isFull()) {
			
			// Player1
			UI.updateGameBoard(gb);
			ArrayList<Coordinates> possibleMoves1 = gb.availableMoves(PlayerColor1);
			UI.showPossibleMoves(possibleMoves1);
			Coordinates p1move = Player1.nextMove(gb,possibleMoves1);
			if (p1move != null) gb.makeMove(PlayerColor1,p1move);
			UI.updateGameBoard(gb);
			
			// Player2
			UI.updateGameBoard(gb);
			ArrayList<Coordinates> possibleMoves2 = gb.availableMoves(PlayerColor2);
			UI.showPossibleMoves(possibleMoves2);
			Coordinates p2move = Player2.nextMove(gb,possibleMoves2);
			if (p2move != null) gb.makeMove(PlayerColor2,p2move);
			UI.updateGameBoard(gb);
			
			// stop the game if both players cannot play anymore
			if(possibleMoves1.isEmpty() && possibleMoves2.isEmpty()) break;
		}
		
		// Game is over
		int Player1Stones = gb.countStones(PlayerColor1);
		int Player2Stones = gb.countStones(PlayerColor2);
		
		// show it to the player and exit
		UI.endGame(Player1Stones,Player2Stones);
		
	}
	
}