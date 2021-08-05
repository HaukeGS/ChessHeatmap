package chessboard;

import util.ChessUtil;

public class Coord {
	
	private final char x;
	private final int y;
	private final int implX;
	private final int implY;

	public Coord(char x, int y) {
		if (x < 'a' || x > 'h' || y < 1 || y > 8)
			throw new IllegalArgumentException("You tried to create a Coordinate not on the Chessboard.");
		this.x = x;
		this.y = y;
		this.implX = ChessUtil.asciiOffset(x);
		this.implY = ChessUtil.inverseIndex(y);
	}
	
	public Coord (int x, int y) {
		if (x <0 || x > 7 || y < 0 || y > 7)
			throw new IllegalArgumentException("You tried to create a Coordinate not on the Chessboard.");		
		this.implX = x;
		this.implY = y;
		this.x = ChessUtil.asciiOffset(x);
		this.y = ChessUtil.inverseIndex(y);
	}
	
	public char getChessX() {
		return x;
	}
	
	public int getChessY() {
		return y;
	}
	
	public int getX() {
		return implX;
	}
	
	public int getY() {
		return implY;
	}
	
	@Override
	public String toString() {
		return toChessString();
	}
	
	public String toChessString() {
		return Character.toUpperCase(x) + Integer.toString(y);
	}
}
