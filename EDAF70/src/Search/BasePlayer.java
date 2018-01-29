package Search;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public interface BasePlayer extends ActionListener{

	public void initialize(int myColor);

	public int getColor();
	
	public Coordinates nextMove(GameBoard gb);	

	public void actionPerformed(ActionEvent e);
}
