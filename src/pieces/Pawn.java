package pieces;

import chessboard.ChessBoard;

public class Pawn extends Piece {
	
public Pawn (Player c, ChessBoard cb) {
	super(c, cb);
	if (c == Player.WHITE) {
		setImage("file:res/Chesspieces/White_Pawn.png");
	} else {
		setImage("file:res/Chesspieces/Black_Pawn.png");
	}
}

}
