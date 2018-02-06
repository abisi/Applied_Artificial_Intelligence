package Search;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MinMaxPlayer implements BasePlayer {

	// ==========================================================
	// Private Members
	// ==========================================================
	private int Color = 0;
	private int Opponent = 0;
	private long TimeOut = 0;
	private long StartTime = 0;
	
	// ==========================================================
	// Constructor
	// ==========================================================
	public MinMaxPlayer() {
		System.out.println("Created MinMax Player");
	}
	
	// ==========================================================
	// Private Methods
	// ==========================================================
	
	private int Max(GameBoard gb, int depth, int maxDepth, int a, int b)
		throws OutOfTimeException {
		// check for timeOut
		if (isTimeOver())
			throw new OutOfTimeException();
		// check if game is over
		if (gb.isGameOver())
			return gb.finalEvaluation(Color);
		
		// check if max depth is reached
		if (depth >= maxDepth)
			return gb.evaluate(Color);
		
		// determine possible moves
		ArrayList<Coordinates> possibleMoves = gb.availableMoves(Color);

		// check if the player has possible moves
		if (possibleMoves.isEmpty())
			return Min(gb,depth +1, maxDepth,a,b);
		
		// initialize alpha
		int alpha = a;
		
		// select the best move
		for (Coordinates move : possibleMoves) {
			GameBoard copy = gb.clone();
			copy.makeMove(Color,move);
			
			alpha = Integer.max(alpha, Min(copy,depth + 1, maxDepth, a, b));
		}
		
		return alpha;
	}
	
	private int Min(GameBoard gb, int depth, int maxDepth, int a, int b) 
		throws OutOfTimeException {
		// check for timeOut
		if (isTimeOver())
			throw new OutOfTimeException();
		// check if game is over
		if (gb.isGameOver())
			return gb.finalEvaluation(Color);
		
		// check if max depth is reached
		if (depth >= maxDepth)
			return gb.evaluate(Color);
		
		// determine possible moves
		ArrayList<Coordinates> possibleMoves = gb.availableMoves(Opponent);

		// check if the player has possible moves
		if (possibleMoves.isEmpty())
			return Min(gb,depth +1, maxDepth,a,b);
		
		// initialize beta
		int beta = b;
		
		// select the best move
		for (Coordinates move : possibleMoves) {
			GameBoard copy = gb.clone();
			copy.makeMove(Opponent,move);
			
			beta = Integer.min(beta, Max(copy,depth + 1, maxDepth, a, b));
		}
		
		return beta;
	}
	
	private boolean isTimeOver() {
		return ((System.currentTimeMillis() - StartTime) < TimeOut) ? false : true;
	}
	
	private class OutOfTimeException extends Exception {
		private static final long serialVersionUID = -4005731770362803760L;
	}
	
	// ==========================================================
	// Base Player Methods
	// ==========================================================
	
	@Override
	public void initialize(int myColor, long timeOut) {
		Color = myColor;
		TimeOut = timeOut;
		Opponent = (Color == GameBoard.WHITE) ? GameBoard.BLACK : GameBoard.WHITE;

	}

	@Override
	public int getColor() {
		return Color;
	}

	@Override
	public Coordinates nextMove(GameBoard gb, ArrayList<Coordinates> possibleMoves) {
		StartTime = System.currentTimeMillis();
		
		// if no moves are available, return null
		if(possibleMoves.isEmpty())
			return null;
		
		// if only one move is possible, return it
		if(possibleMoves.size()==1)
			return possibleMoves.get(0);
		
		// find the best move by going as deep with the recursion depth as possible in the given time
		Coordinates bestMove = null;
		try {
			for (int maxDepth = 1 ;; maxDepth++) {
				Coordinates tempBestMove = null;
				int bestValue = Integer.MIN_VALUE;
				
				for (Coordinates move : possibleMoves) {
					GameBoard clone = gb.clone();
					clone.makeMove(Color, move);
					int value = Min(clone,0,maxDepth,Integer.MAX_VALUE,Integer.MIN_VALUE);
					if(value >= bestValue) {
						bestValue = value;
						tempBestMove = move;
					}
				}
				
				bestMove = tempBestMove;
			}
		} catch(OutOfTimeException e){
			
		}
		
		return bestMove;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
