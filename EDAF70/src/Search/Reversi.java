package Search;
import java.util.*;

public class Reversi {

	// ==========================================================
	// Private Members
	// ==========================================================
	private UserInterface UI;
    private static GameBoard gb;
    private BasePlayer Player1 = new HumanPlayer();
    private BasePlayer Player2 = new HumanPlayer();
    
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
		
		// Give away colors
		Player1.initialize(GameBoard.WHITE);
		Player2.initialize(GameBoard.BLACK);
		
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
			ArrayList<Coordinates> possibleMoves1 = gb.availableMoves(PlayerColor1);
			if (Player1 instanceof HumanPlayer) UI.showPossibleMoves(possibleMoves1);
			Coordinates p1move = Player1.nextMove(gb,possibleMoves1);
			if (p1move != null) gb.makeMove(PlayerColor1,p1move);
			UI.updateGameBoard(gb);
			
			// Player2
			ArrayList<Coordinates> possibleMoves2 = gb.availableMoves(PlayerColor2);
			if (Player2 instanceof HumanPlayer) UI.showPossibleMoves(possibleMoves2);
			Coordinates p2move = Player2.nextMove(gb,possibleMoves2);
			if (p2move != null) gb.makeMove(PlayerColor2,p2move);
			UI.updateGameBoard(gb);
		}
	}
	
}