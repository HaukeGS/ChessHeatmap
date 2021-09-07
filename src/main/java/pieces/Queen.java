package pieces;

import java.util.HashSet;
import java.util.Set;

import chessboard.Coord;
import util.GameLogic;

public class Queen extends Piece {
	
	public Queen (Player c, Coord coord) {
		super(c, coord);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_Queen.png");
			setFenChar('Q');
		} else {
			setImage("file:res/Chesspieces/Black_Queen.png");
			setFenChar('q');
		}
	}
	
	@Override
	public Set<Coord> getAttackedCoords() {
		HashSet<Coord> result = new HashSet<Coord>();
		Coord temp = coord;
		while (true) {
			try {
				temp = new Coord(temp.getX(), temp.getY()-1);
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty))
					break;				
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		temp = getCoord();
		while (true) {
			try {
				temp = new Coord(temp.getX()-1, temp.getY());
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty))
					break;				
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		temp = getCoord();
		while (true) {
			try {
				temp = new Coord(temp.getX()+1, temp.getY());
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty))
					break;				
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		temp = getCoord();
		while (true) {
			try {
				temp = new Coord(temp.getX(), temp.getY()+1);
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty))
					break;				
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		temp = getCoord();
		while (true) {
			try {
				temp = new Coord(temp.getX()-1, temp.getY()-1);
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty))
					break;				
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		temp = getCoord();
		while (true) {
			try {
				temp = new Coord(temp.getX()-1, temp.getY()+1);
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty))
					break;				
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		temp = getCoord();
		while (true) {
			try {
				temp = new Coord(temp.getX()+1, temp.getY()-1);
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty))
					break;				
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		temp = getCoord();
		while (true) {
			try {
				temp = new Coord(temp.getX()+1, temp.getY()+1);
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty))
					break;				
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		return result;
	}

	@Override
	protected Set<Coord> getMovableCoords() {
		return getAttackedCoords();
	}

}
