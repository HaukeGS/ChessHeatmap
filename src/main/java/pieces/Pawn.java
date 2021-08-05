package pieces;

import chessboard.Coord;
import chessboard.PiecePane;
import pieces.Piece.Player;

public class Pawn extends Piece {
	
public Pawn (Player c, Coord coord, PiecePane pP) {
	super(c, coord, pP);
	if (c == Player.WHITE) {
		setImage("file:res/Chesspieces/White_Pawn.png");
		setFenChar('P');
	} else {
		setImage("file:res/Chesspieces/Black_Pawn.png");
		setFenChar('p');
	}
}

	@Override
	public void setAttackedCoords() {
		attackedCoords.clear();
		Coord temp = getCoord();
		if (getColor() == Player.WHITE) {
			try {
				addAttackedCoord(new Coord(temp.getX()-1, temp.getY()-1));				
			} catch (IllegalArgumentException e) {
				
			}
			try {
				addAttackedCoord(new Coord(temp.getX()+1, temp.getY()-1));				
			} catch (IllegalArgumentException e) {
				
			}
		} else {
			try {
				addAttackedCoord(new Coord(temp.getX()-1, temp.getY()+1));				
			} catch (IllegalArgumentException e) {
				
			}
			try {
				addAttackedCoord(new Coord(temp.getX()+1, temp.getY()+1));
			} catch (IllegalArgumentException e) {
				
			}									
		}
		
	}

}
