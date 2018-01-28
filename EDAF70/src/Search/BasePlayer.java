package Search;

public class BasePlayer {

	private int Color;
	private int Opponent;
	
	public BasePlayer(int color, int opponent) {
		Color = color;
		Opponent = opponent;
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
	
	public int getColor() {
		return Color;
	}
}
