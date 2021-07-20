package pieces;

import chessboard.Coord;
import pieces.Piece.Player;

public class Knight extends Piece {
	
public Knight (Player c, Coord coord) {
	super(c, coord);
	if (c == Player.WHITE) {
		setImage("file:res/Chesspieces/White_Knight.png");
		setFenChar('N');
	} else {
		setImage("file:res/Chesspieces/Black_Knight.png");
		setFenChar('n');	
	}
}

}
