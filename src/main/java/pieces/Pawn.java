package pieces;


public class Pawn extends Piece {
	
public Pawn (Player c, char x, int y) {
	super(c, x, y);
	if (c == Player.WHITE) {
		setImage("file:res/Chesspieces/White_Pawn.png");
		setFenChar('P');
	} else {
		setImage("file:res/Chesspieces/Black_Pawn.png");
		setFenChar('p');
	}
}

}
