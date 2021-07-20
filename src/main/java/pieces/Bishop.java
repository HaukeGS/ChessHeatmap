package pieces;

import chessboard.Coord;
import pieces.Piece.Player;

public class Bishop extends Piece {
		
	public Bishop (Player c, Coord coord) {
		super(c, coord);;
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_Bishop.png");
			setFenChar('B');
		} else {
			setImage("file:res/Chesspieces/Black_Bishop.png");	
			setFenChar('b');
		}
	}
}
