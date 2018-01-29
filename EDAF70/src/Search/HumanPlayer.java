package Search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class HumanPlayer implements BasePlayer, ActionListener
{
	
	private int Color;
	
	private Coordinates Move;
	
	public HumanPlayer() {
		System.out.println("Created human player");
	}
	
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
		System.out.println("nextMove");
		
		printArrayList(possibleMoves);
		
		// return null if no moves are possible
		if (possibleMoves.isEmpty()) return null;
		
		// determine possible moves
		Coordinates nextMove = null;
		while(nextMove == null) {
			Coordinates newMove = Move;
			
			for (Coordinates move : possibleMoves) {
				if(move.equals(newMove)) {
					nextMove = newMove;
					System.out.println("Updated move");
					break;
				}
			}
		}
		Move = null;
		System.out.println("return nextMove");
		return nextMove;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		int x = (int) button.getClientProperty("X");
		int y = (int) button.getClientProperty("Y");
		Move = new Coordinates(x,y);
		System.out.println("Move uptdated to:" + Move.toString());
	}
	
	public void printArrayList(ArrayList<Coordinates> coord) {
		for (int i = 0; i < coord.size(); i++) {
			System.out.println(coord.get(i).toString());
		}
	}
	
	

}
