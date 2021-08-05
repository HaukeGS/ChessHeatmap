package pieces;

import chessboard.Coord;
import chessboard.PiecePane;
import pieces.Piece.Player;

public class King extends Piece {
	
	public King (Player c, Coord coord, PiecePane pP) {
		super(c, coord, pP);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_King.png");
			setFenChar('K');
		} else {
			setImage("file:res/Chesspieces/Black_King.png");	
			setFenChar('k');
		}
	}

	@Override
	public void setAttackedCoords() {
		attackedCoords.clear();
		Coord temp = getCoord();
		try {
			addAttackedCoord(new Coord(temp.getX(),temp.getY()+1));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			addAttackedCoord(new Coord(temp.getX()+1,temp.getY()));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			addAttackedCoord(new Coord(temp.getX(),temp.getY()-1));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			addAttackedCoord(new Coord(temp.getX()-1,temp.getY()));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			addAttackedCoord(new Coord(temp.getX()+1,temp.getY()+1));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			addAttackedCoord(new Coord(temp.getX()+1,temp.getY()-1));
		} catch (IllegalArgumentException e) {
			
		}
		try {
			addAttackedCoord(new Coord(temp.getX()-1,temp.getY()+1));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			addAttackedCoord(new Coord(temp.getX()-1,temp.getY()-1));			
		} catch (IllegalArgumentException e) {
			
		}
		
	}
}
