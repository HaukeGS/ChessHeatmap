package pieces;

public class King extends Piece {
	
	public King (Player c, char x, int y) {
		super(c, x, y);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_King.png");
			setFenChar('K');
		} else {
			setImage("file:res/Chesspieces/Black_King.png");	
			setFenChar('k');
		}
	}
}
