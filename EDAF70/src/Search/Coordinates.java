package Search;
/*
 * Class that represents coordinates on the gameboard.
 */
public class Coordinates {
	public int X;
	public int Y;
	
	public Coordinates(int x, int y) {
		this.X = x;
		this.Y = y;
	}
	
	public Coordinates() {
		X = 0;
		Y = 0;
	}
	
	public boolean equals(Coordinates coord) {
		return (coord == null) ? false : (X == coord.X && Y == coord.Y);
	}
	
	public String toString() {
		return "[ " + X + " , " + Y + " ]";
	}
	
}
