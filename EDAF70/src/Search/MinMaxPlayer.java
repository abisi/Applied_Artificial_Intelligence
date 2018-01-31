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
	private int MaxDepth = 4;
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
	private int evaluate(GameBoard gb, int Player) {
		return gb.countStones(Player);
	}
	
	private Move Max(GameBoard gb, int depth, int maxDepth) {
		// check for timeOut
		if (isTimeOver()) return new Move(null, evaluate(gb,Color));
		
		// check if max depth is reached
		if (depth == maxDepth) return new Move(null, evaluate(gb,Color));
		
		// if no moves are available for max
		if(!gb.isMoveAvailable(Color)) {
			// if moves are available for min
			if(gb.isMoveAvailable(Opponent)) {
				Move nextMove = Min(gb, depth + 1, maxDepth);
				return new Move(null, nextMove.Value);
			} else {
				return new Move(null, evaluate(gb,Color));
			}
		}
		
		// determine possible moves
		ArrayList<Coordinates> possibleMoves = gb.availableMoves(Color);
		Move bestMove = new Move(new Coordinates(),Integer.MIN_VALUE);
		
		// select the best move
		for (Coordinates move : possibleMoves) {
			GameBoard copy = gb.clone();
			copy.makeMove(Color,move);
			Move nextMove = Min(copy,depth+1,maxDepth);
		
			if (nextMove.Value > bestMove.Value) {
				bestMove = nextMove;
			}
			
		}
		return bestMove;
	}
	
	private Move Min(GameBoard gb, int depth, int maxDepth) {
		// check for timeOut
		if (isTimeOver()) return new Move(null, evaluate(gb,Color));
		
		// check if max depth is reached
		if (depth == maxDepth) return new Move(null, evaluate(gb,Color));
		
		// if no moves are available for min
		if(!gb.isMoveAvailable(Opponent)) {
			// if moves are available for max
			if(gb.isMoveAvailable(Color)) {
				Move nextMove = Max(gb, depth + 1, maxDepth);
				return new Move(null, nextMove.Value);
			} else {
				return new Move(null, evaluate(gb,Color));
			}
		}
		
		// determine possible moves
		ArrayList<Coordinates> possibleMoves = gb.availableMoves(Opponent);
		Move bestMove = new Move(new Coordinates(),Integer.MAX_VALUE);
		
		// select the best move
		for (Coordinates move : possibleMoves) {
			GameBoard copy = gb.clone();
			copy.makeMove(Opponent,move);
			Move nextMove = Min(copy, depth+1, maxDepth);
		
			if (nextMove.Value < bestMove.Value) {
				bestMove = nextMove;
			}
			
		}
		return bestMove;
	}
	
	private boolean isTimeOver() {
		return (System.currentTimeMillis() - StartTime < TimeOut) ? false : true;
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
		Move nextMove = Max(gb,0,MaxDepth);
		
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
		return nextMove.Coord;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
