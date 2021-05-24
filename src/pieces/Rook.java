package pieces;

public class Rook extends Piece {
	
public Rook (Player c) {
	super(c);
	if (c == Player.WHITE) {
		setImage("file:res/Chesspieces/White_Rook.png");
	} else {
		setImage("file:res/Chesspieces/Black_Rook.png");	
	}
}

}
