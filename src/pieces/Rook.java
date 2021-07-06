package pieces;


public class Rook extends Piece {
	
public Rook (Player c, char x, int y) {
	super(c, x, y);
	if (c == Player.WHITE) {
		setImage("file:res/Chesspieces/White_Rook.png");
		setFenChar('R');
	} else {
		setImage("file:res/Chesspieces/Black_Rook.png");
		setFenChar('r');
	}
}

}
