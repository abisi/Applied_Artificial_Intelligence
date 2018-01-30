package Search;

import java.awt.event.ActionEvent;
import java.util.*;

public class RandomPlayer implements BasePlayer {

	// ==========================================================
	// Private Members
	// ==========================================================
	private int Color = 0;
	private Random rand = new Random();
	
	// ==========================================================
	// Constructor
	// ==========================================================
	public RandomPlayer() {
		System.out.println("Created Random Player");
	}
	
	// ==========================================================
	// Base Player Methods
	// ==========================================================
		
	
	@Override
	public void initialize(int myColor) {
		Color = myColor;

	}

	@Override
	public int getColor() {
		return Color;
	}

	@Override
	public Coordinates nextMove(GameBoard gb, ArrayList<Coordinates> possibleMoves) {
		// return null if no move is available
		if (possibleMoves.isEmpty()) return null;
		
		// else return a random move out of the possible moves
		int randIndex = rand.nextInt(possibleMoves.size());
		
		return possibleMoves.get(randIndex);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
