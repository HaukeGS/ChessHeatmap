package pieces;

import chessboard.Coord;
import pieces.Piece.Player;

public class Rook extends Piece {
	
public Rook (Player c, Coord coord) {
	super(c, coord);
	if (c == Player.WHITE) {
		setImage("file:res/Chesspieces/White_Rook.png");
		setFenChar('R');
	} else {
		setImage("file:res/Chesspieces/Black_Rook.png");
		setFenChar('r');
	}
}

}
