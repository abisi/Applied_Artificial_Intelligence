package Search;

public class BasePlayer {

	private int Color;
	private int Oponent;
	public int trial;
	
	public BasePlayer(int color, int oponent) {
		Color = color;
		Oponent = oponent;
	}

	public Coordinates nextMove(GameBoard gb) {
		/*
		if(!gb.isMoveAvailable(Color))
			return null;
		else
			return new Coordinates(0,0);
			*/
		return new Coordinates(0,0);
	}
	
	
}
