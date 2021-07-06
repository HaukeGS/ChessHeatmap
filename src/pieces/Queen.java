package pieces;


public class Queen extends Piece {
	
	public Queen (Player c, char x, int y) {
		super(c, x, y);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_Queen.png");
			setFenChar('Q');
		} else {
			setImage("file:res/Chesspieces/Black_Queen.png");
			setFenChar('q');
		}
	}

}
