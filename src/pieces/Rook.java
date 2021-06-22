package pieces;

import chessboard.ChessBoard;

public class Rook extends Piece {
	
public Rook (Player c, ChessBoard cb) {
	super(c, cb);
	if (c == Player.WHITE) {
		setImage("file:res/Chesspieces/White_Rook.png");
	} else {
		setImage("file:res/Chesspieces/Black_Rook.png");	
	}
}

}
