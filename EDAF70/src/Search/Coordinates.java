package Search;

public class Coordinates {

	// ==========================================================
	// Public Members
	// ==========================================================
	public int X;
	public int Y;
	
	public String Message = "";
	
	// ==========================================================
	// Constructors
	// ==========================================================
	public Coordinates(int x, int y) {
		this.X = x;
		this.Y = y;
	}
	
	public Coordinates() {
		X = 0;
		Y = 0;
	}
	
	// ==========================================================
	// Public Methods
	// ==========================================================
	
	public boolean equals(Coordinates coord) {
		return (coord == null) ? false : (X == coord.X && Y == coord.Y);
	}
	
	public String toString() {
		return "[ " + X + " , " + Y + " ]";
	}
	
}
