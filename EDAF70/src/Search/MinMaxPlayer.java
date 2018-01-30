package Search;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MinMaxPlayer implements BasePlayer {

	// ==========================================================
	// Private Members
	// ==========================================================
	private int Color = 0;
	private int Opponent = 0;
	private long TimeOut;
	
	// ==========================================================
	// Constructor
	// ==========================================================
	public MinMaxPlayer() {
		System.out.println("Created MinMax Player");
	}
	
	// ==========================================================
	// Private Methods
	// ==========================================================
	private int evaluateMe(GameBoard gb) {
		return gb.countStones(Color);
	}
	
	private int evaluateOp(GameBoard gb) {
		return gb.countStones(Opponent);
	}
	
	private Move MaxMin(GameBoard gb, int depth, int maxDepth, int a, int b) {
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
