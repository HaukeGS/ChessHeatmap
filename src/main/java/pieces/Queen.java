package pieces;

import chessboard.Coord;
import chessboard.PiecePane;
import pieces.Piece.Player;

public class Queen extends Piece {
	
	public Queen (Player c, Coord coord, PiecePane pP) {
		super(c, coord, pP);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_Queen.png");
			setFenChar('Q');
		} else {
			setImage("file:res/Chesspieces/Black_Queen.png");
			setFenChar('q');
		}
	}
	
	@Override
	public void setAttackedCoords() {
		attackedCoords.clear();
		Coord temp = coord;
		while (true) {
			try {
				temp = new Coord(temp.getX(), temp.getY()-1);
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
				temp = new Coord(temp.getX()-1, temp.getY());
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
				temp = new Coord(temp.getX()+1, temp.getY());
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
				temp = new Coord(temp.getX(), temp.getY()+1);
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
				temp = new Coord(temp.getX()-1, temp.getY()-1);
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
