package pieces;

import chessboard.Coord;
import pieces.Piece.Player;

public class Queen extends Piece {
	
	public Queen (Player c, Coord coord) {
		super(c, coord);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_Queen.png");
			setFenChar('Q');
		} else {
			setImage("file:res/Chesspieces/Black_Queen.png");
			setFenChar('q');
		}
	}

}
