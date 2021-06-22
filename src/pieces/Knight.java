package pieces;

import chessboard.ChessBoard;

public class Knight extends Piece {
	
public Knight (Player c, ChessBoard cb) {
	super(c, cb);
	if (c == Player.WHITE) {
		setImage("file:res/Chesspieces/White_Knight.png");
	} else {
		setImage("file:res/Chesspieces/Black_Knight.png");	
	}
}

}
