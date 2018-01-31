package Search;

public class Move {

	// ==========================================================
	// Public Members
	// ==========================================================
	public Coordinates Coord;
	public int Value = 0;
	
	// ==========================================================
	// Constructors
	// ==========================================================
	public Move(Coordinates coord, int value) {
		Coord = coord;
		Value = value;
	}
	
	public Move(){
		Coord = new Coordinates();
		Value = -1;
	}
	
}
