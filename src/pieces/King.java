package pieces;

public class King extends Piece {
	
	public King (Player c) {
		super(c);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_King.png");
		} else {
			setImage("file:res/Chesspieces/Black_King.png");	
		}
	}
}
