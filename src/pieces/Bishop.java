package pieces;

public class Bishop extends Piece {
		
	public Bishop (Player c) {
		super(c);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_Bishop.png");
		} else {
			setImage("file:res/Chesspieces/Black_Bishop.png");	
		}
	}
}
