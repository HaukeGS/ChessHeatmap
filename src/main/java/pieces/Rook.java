package pieces;

import java.util.HashSet;
import java.util.Set;

import chessboard.Coord;
import util.GameLogic;

public class Rook extends Piece {
	
	public Rook (Player c, Coord coord) {
		super(c, coord);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_Rook.png");
			setFenChar('R');
		} else {
			setImage("file:res/Chesspieces/Black_Rook.png");
			setFenChar('r');
		}
	}
	
	@Override
	public Set<Coord> getAttackedCoords() {
		HashSet<Coord> result = new HashSet<Coord>();
		Coord temp = getCoord();
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
		return result;
	}

	@Override
	protected Set<Coord> getMovableCoords() {
		// TODO Auto-generated method stub
		return getAttackedCoords();
	}

}
