package pieces;

import chessboard.Coord;

public class Pawn extends Piece {
	
public Pawn (Player c, Coord coord) {
	super(c, coord);
	if (c == Player.WHITE) {
		setImage("file:res/Chesspieces/White_Pawn.png");
		setFenChar('P');
	} else {
		setImage("file:res/Chesspieces/Black_Pawn.png");
		setFenChar('p');
	}
}

}
