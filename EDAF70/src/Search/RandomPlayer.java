package Search;

import java.awt.event.ActionEvent;
import java.util.*;

public class RandomPlayer implements BasePlayer {

	// ==========================================================
	// Private Members
	// ==========================================================
	private int Color = 0;
	private Random rand = new Random();
	private long TimeOut;
	
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
		
		// else return a random move out of the possible moves
		int randIndex = rand.nextInt(possibleMoves.size());
		Coordinates randomMove = possibleMoves.get(randIndex);
		
		// Wait till the timeOut time has passed
		long millisToWait = TimeOut - (System.currentTimeMillis() - StartTime);
		if (millisToWait > 0) {
			try {
				Thread.sleep(millisToWait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("return nextMove");
		return randomMove;	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
