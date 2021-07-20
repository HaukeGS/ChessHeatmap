package chessboard;

import util.ChessUtil;

public class Coord {
	
	private final char x;
	private final int y;
	private final int implX;
	private final int implY;

	public Coord(char x, int y) {
		this.x = x;
		this.y = y;
		this.implX = ChessUtil.asciiOffset(x);
		this.implY = ChessUtil.inverseIndex(y);
	}
	
	public Coord (int x, int y) {
		this.implX = x;
		this.implY = y;
		this.x = ChessUtil.asciiOffset(x);
		this.y = ChessUtil.inverseIndex(y);
	}
	
	public int getChessX() {
		return implX;
	}
	
	public int getChessY() {
		return implY;
	}
}
