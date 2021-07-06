package pieces;


public class Knight extends Piece {
	
public Knight (Player c) {
	super(c);
	if (c == Player.WHITE) {
		setImage("file:res/Chesspieces/White_Knight.png");
	} else {
		setImage("file:res/Chesspieces/Black_Knight.png");	
	}
}

}
