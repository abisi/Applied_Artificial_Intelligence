package Search;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public interface BasePlayer extends ActionListener{

	public void initialize(int myColor, long timeOut);
	
	public int getColor();
	
	public Coordinates nextMove(GameBoard gb, ArrayList<Coordinates> possibleMoves);	

	public void actionPerformed(ActionEvent e);
	
}
