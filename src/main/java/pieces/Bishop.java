package pieces;

import chessboard.Coord;
import chessboard.PiecePane;

public class Bishop extends Piece {
		
	public Bishop (Player c, Coord coord, PiecePane pP) {
		super(c, coord, pP);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_Bishop.png");
			setFenChar('B');
		} else {
			setImage("file:res/Chesspieces/Black_Bishop.png");	
			setFenChar('b');
		}
	}
	
	@Override
	public void setAttackedCoords() {
		attackedCoords.clear();
		Coord temp = getCoord();
		while (true) {
			try {
				temp = new Coord(temp.getX()-1, temp.getY()-1);
				addAttackedCoord(temp);
				if (!(piecePane.getPiece(temp) instanceof Empty)) {
					System.out.println("check");
					break;		
				}		
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		temp = getCoord();
		while (true) {
			try {
				temp = new Coord(temp.getX()-1, temp.getY()+1);
				addAttackedCoord(temp);
				if (!(piecePane.getPiece(temp) instanceof Empty))
					break;				
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		temp = getCoord();
		while (true) {
			try {
				temp = new Coord(temp.getX()+1, temp.getY()-1);
				addAttackedCoord(temp);
				if (!(piecePane.getPiece(temp) instanceof Empty))
					break;
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		temp = getCoord();
		while (true) {
			try {
				temp = new Coord(temp.getX()+1, temp.getY()+1);
				addAttackedCoord(temp);
				if (!(piecePane.getPiece(temp) instanceof Empty))
					break;				
			} catch (IllegalArgumentException e) {
				break;
			}
		}
	
	}
}
