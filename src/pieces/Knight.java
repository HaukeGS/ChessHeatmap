package pieces;


public class Knight extends Piece {
	
public Knight (Player c, char x, int y) {
	super(c, x, y);
	if (c == Player.WHITE) {
		setImage("file:res/Chesspieces/White_Knight.png");
		setFenChar('N');
	} else {
		setImage("file:res/Chesspieces/Black_Knight.png");
		setFenChar('n');	
	}
}

}
