package pieces;

import chessboard.ChessBoard;

public class King extends Piece {
	
	public King (Player c, ChessBoard cb) {
		super(c, cb);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_King.png");
		} else {
			setImage("file:res/Chesspieces/Black_King.png");	
		}
	}
}
