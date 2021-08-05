package pieces;

import chessboard.Coord;
import chessboard.PiecePane;
import pieces.Piece.Player;

public class Rook extends Piece {
	
	public Rook (Player c, Coord coord, PiecePane pP) {
		super(c, coord, pP);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_Rook.png");
			setFenChar('R');
		} else {
			setImage("file:res/Chesspieces/Black_Rook.png");
			setFenChar('r');
		}
	}
	
	@Override
	public void setAttackedCoords() {
		attackedCoords.clear();
		Coord temp = getCoord();
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
	
	}

}
