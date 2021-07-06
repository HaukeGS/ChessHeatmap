package pieces;

public class Bishop extends Piece {
		
	public Bishop (Player c, char x, int y) {
		super(c, x, y);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_Bishop.png");
			setFenChar('B');
		} else {
			setImage("file:res/Chesspieces/Black_Bishop.png");	
			setFenChar('b');
		}
	}
}
