package pieces;

import chessboard.ChessBoard;

public class Bishop extends Piece {
		
	public Bishop (Player c, ChessBoard cb) {
		super(c, cb);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_Bishop.png");
		} else {
			setImage("file:res/Chesspieces/Black_Bishop.png");	
		}
	}
}
