package pieces;


public class Pawn extends Piece {
	
public Pawn (Player c) {
	super(c);
	if (c == Player.WHITE) {
		setImage("file:res/Chesspieces/White_Pawn.png");
	} else {
		setImage("file:res/Chesspieces/Black_Pawn.png");
	}
}

}
