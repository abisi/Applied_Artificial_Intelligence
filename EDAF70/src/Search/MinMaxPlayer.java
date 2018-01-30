package Search;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MinMaxPlayer implements BasePlayer {

	// ==========================================================
	// Private Members
	// ==========================================================
	private int Color = 0;
	
	// ==========================================================
	// Constructor
	// ==========================================================
	public MinMaxPlayer() {
		System.out.println("Created MinMax Player");
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
