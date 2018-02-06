package Search;

public class Move {

	// ==========================================================
	// Public Members
	// ==========================================================
	public Coordinates Coord;
	public int Value = 0;
	public int OpponentMoves;
	
	// ==========================================================
	// Constructors
	// ==========================================================
	public Move(Coordinates coord, int value, int opMoves) {
		Coord = coord;
		Value = value;
	}
	
	public Move(){
		Coord = new Coordinates();
		Value = -1;
		OpponentMoves = 0;
	}
	
}
