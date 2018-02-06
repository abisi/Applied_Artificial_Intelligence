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
	private int MaxDepth = 5;
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
		//the quality of move has as a basis: (number of stones + flippable stones) = total stones after move
		int value = gb.countStones(Player);
		//that basis is changed according to the type of move
		Coordinates move = new Coordinates();
		
		if(gb.isStable(move)) value += 4;  // stable stones are +
		if(gb.isXSquare(move)) value -= 6; //must avoid X-squares; the disadvantage of an X-square surpasses the advantage 
										  //of putting a stable stone, hence a greater absolute change
		
		return value;
	}

	
	private Move Max(GameBoard gb, int depth, int maxDepth, int alpha, int beta) {
		// check for timeOut
		if (isTimeOver()) {
			System.out.println("Max: Time is over.");
			return new Move(null, evaluate(gb,Color), gb.availableMoves(Opponent).size());
		}
		
		// check if max depth is reached
		if (depth == maxDepth) {
			return new Move(null, evaluate(gb,Color), gb.availableMoves(Opponent).size());
		}
		
		// v_alpha: the best value to max found so far
		int v = Integer.MIN_VALUE;
		
		// if no moves are available for max
		if(!gb.isMoveAvailable(Color)) {
			// if moves are available for min
			if(gb.isMoveAvailable(Opponent)) {
				Move nextMove = Min(gb, depth + 1, maxDepth, alpha, beta);
				return new Move(null, nextMove.Value, gb.availableMoves(Opponent).size());
			} else {
				return new Move(null, evaluate(gb,Color), gb.availableMoves(Opponent).size());
			}
		}
		
		// determine possible moves
		ArrayList<Coordinates> possibleMoves = gb.availableMoves(Color);
		Move bestMove = new Move(null,Integer.MIN_VALUE, Integer.MIN_VALUE);
				
		// select the best move
		for (Coordinates move : possibleMoves) {
			GameBoard copy = gb.clone();
			copy.makeMove(Color,move);
			
			/* alpha-beta pruning:
			v = Integer.max(alpha, nextMove.Value);
			if(v >= beta) return v;
			alpha = max(alpha, v);
			}
			return v;
			*/
			
			Move nextMove = Min(copy,depth+1,maxDepth, alpha, beta);
			
			if (nextMove.Value > bestMove.Value) {
				bestMove.Coord = move;
				bestMove.Value = nextMove.Value;
			}
		}
		
		return bestMove;
	}
	
	private Move Min(GameBoard gb, int depth, int maxDepth, int alpha, int beta) {
		// check for timeOut
		if (isTimeOver()) {
			System.out.println("Min: Time is over.");
			return new Move(null, evaluate(gb,Color),gb.availableMoves(Opponent).size());
		}
		
		// check if max depth is reached
		if (depth == maxDepth) {
			return new Move(null, evaluate(gb,Color),gb.availableMoves(Opponent).size());
		}
		
		//int v = Integer.MAX_VALUE;
		
		// if no moves are available for min
		if(gb.availableMoves(Opponent).isEmpty()) {
			// if moves are available for max
			if(!gb.availableMoves(Color).isEmpty()) {
				Move nextMove = Max(gb, depth + 1, maxDepth, alpha, beta);
				return new Move(null, nextMove.Value,gb.availableMoves(Opponent).size());
			} else {
				return new Move(null, evaluate(gb,Color),gb.availableMoves(Opponent).size());
			}
		}
		
		
		// determine possible moves
		ArrayList<Coordinates> possibleMoves = gb.availableMoves(Opponent);
		Move bestMove = new Move(null,Integer.MAX_VALUE, Integer.MAX_VALUE);
		
		// select the best move
		for (Coordinates move : possibleMoves) {
			GameBoard copy = gb.clone();
			copy.makeMove(Opponent,move);
			Move nextMove = Max(copy, depth+1, maxDepth, alpha, beta);
		
			/*alpha-beta pruning:
			v = max(beta, nextMove.Value);
			if(v >= alpha) return v;
			beta = max(beta, v);
			}
			return v;
			*/
			
			if (nextMove.Value < bestMove.Value) {
				bestMove.Coord = move;
				bestMove.Value = nextMove.Value;
			}
			
		}
		return bestMove;
	}
	
	private boolean isTimeOver() {
		return ((System.currentTimeMillis() - StartTime) < TimeOut) ? false : true;
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
		Move nextMove = Max(gb,0,MaxDepth, 0, 0);
		
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
