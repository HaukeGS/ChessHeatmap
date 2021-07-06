package pieces;


public class Queen extends Piece {
	
	public Queen (Player c) {
		super(c);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_Queen.png");
		} else {
			setImage("file:res/Chesspieces/Black_Queen.png");	
		}
	}

}
