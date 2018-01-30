package Search;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GreedyPlayer implements BasePlayer {

	// ==========================================================
	// Private Members
	// ==========================================================
	private int Color = 0;
	private long TimeOut = 0;
	
	// ==========================================================
	// Constructor
	// ==========================================================
	public GreedyPlayer() {
		System.out.println("Created Greedy Player");
	}
	
	// ==========================================================
	// Base Player Methods
	// ==========================================================
		
	
	@Override
	public void initialize(int myColor, long timeOut) {
		Color = myColor;
		TimeOut = timeOut;

	}

	@Override
	public int getColor() {
		return Color;
	}

	@Override
	public Coordinates nextMove(GameBoard gb, ArrayList<Coordinates> possibleMoves) {

		// Save start Time
		long StartTime = System.currentTimeMillis();
		
		// return null if no move is available
		if (possibleMoves.isEmpty()) return null; 
		// if there is only one element, return the element
		if (possibleMoves.size() == 1) return possibleMoves.get(0);
		
		// implement the greedy move
		Coordinates greedyMove = null;
		int maxStones = 0;
		
		for(Coordinates move : possibleMoves) {
			
			GameBoard copy = gb.clone();
			copy.makeMove(Color, move);
			
			if (maxStones < copy.countStones(Color)) {
				maxStones = copy.countStones(Color);
				greedyMove = move;
			}
			
		}
		
		// Wait till the timeOut time has passed
		long millisToWait = TimeOut - (System.currentTimeMillis() - StartTime);
		if (millisToWait > 0) {
			try {
				Thread.sleep(millisToWait);
				System.out.println("return nextMove");
				return greedyMove;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("return nextMove");
		return greedyMove;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
