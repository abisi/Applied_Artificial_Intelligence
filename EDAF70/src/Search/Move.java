package Search;

public class Move {

	// ==========================================================
	// Public Members
	// ==========================================================
	public Coordinates Coord;
	public int Value;
	public boolean Cut;
	
	// ==========================================================
	// Constructors
	// ==========================================================
	public Move(Coordinates coord, int value, boolean cut) {
		Coord = coord;
		Value = value;
		Cut = cut;
	}
	
	public Move(){
		Coord = new Coordinates();
		Value = 0;
		Cut = false;
	}
	
}
