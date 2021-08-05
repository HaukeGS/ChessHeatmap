package pieces;

import chessboard.Coord;
import chessboard.PiecePane;
import pieces.Piece.Player;

public class Knight extends Piece {
	
public Knight (Player c, Coord coord, PiecePane pP) {
	super(c, coord, pP);
	if (c == Player.WHITE) {
		setImage("file:res/Chesspieces/White_Knight.png");
		setFenChar('N');
	} else {
		setImage("file:res/Chesspieces/Black_Knight.png");
		setFenChar('n');	
	}
}

	@Override
	public void setAttackedCoords() {
		attackedCoords.clear();
		Coord temp = getCoord();
		try {
			addAttackedCoord(new Coord(temp.getX()-1,temp.getY()-2));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			addAttackedCoord(new Coord(temp.getX()-2,temp.getY()-1));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			addAttackedCoord(new Coord(temp.getX()+1,temp.getY()-2));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			addAttackedCoord(new Coord(temp.getX()+2,temp.getY()-1));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			addAttackedCoord(new Coord(temp.getX()+2,temp.getY()+1));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			addAttackedCoord(new Coord(temp.getX()+1,temp.getY()+2));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			addAttackedCoord(new Coord(temp.getX()-1,temp.getY()+2));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			addAttackedCoord(new Coord(temp.getX()-2,temp.getY()+1));			
		} catch (IllegalArgumentException e) {
			
		}
	
	
		
	}

}
