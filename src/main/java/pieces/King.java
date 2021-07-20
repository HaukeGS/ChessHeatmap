package pieces;

import chessboard.Coord;
import pieces.Piece.Player;

public class King extends Piece {
	
	public King (Player c, Coord coord) {
		super(c, coord);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_King.png");
			setFenChar('K');
		} else {
			setImage("file:res/Chesspieces/Black_King.png");	
			setFenChar('k');
		}
	}
}
