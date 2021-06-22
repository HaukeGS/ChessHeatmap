package pieces;

import chessboard.ChessBoard;

public class Queen extends Piece {
	
	public Queen (Player c, ChessBoard cb) {
		super(c, cb);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_Queen.png");
		} else {
			setImage("file:res/Chesspieces/Black_Queen.png");	
		}
	}

}
