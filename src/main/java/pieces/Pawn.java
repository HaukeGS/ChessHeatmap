package pieces;

import java.util.HashSet;
import java.util.Set;

import chessboard.Coord;
import util.GameLogic;

public class Pawn extends Piece {
	
public Pawn (Player c, Coord coord) {
	super(c, coord);
	if (c == Player.WHITE) {
		setImage("file:res/Chesspieces/White_Pawn.png");
		setFenChar('P');
	} else {
		setImage("file:res/Chesspieces/Black_Pawn.png");
		setFenChar('p');
	}
}

	@Override
	public Set<Coord> getAttackedCoords() {
		HashSet<Coord> result = new HashSet<Coord>();
		Coord temp = getCoord();
		if (getColor() == Player.WHITE) {
			try {
				result.add(new Coord(temp.getX()-1, temp.getY()-1));				
			} catch (IllegalArgumentException e) {
				
			}
			try {
				result.add(new Coord(temp.getX()+1, temp.getY()-1));				
			} catch (IllegalArgumentException e) {
				
			}
		} else {
			try {
				result.add(new Coord(temp.getX()-1, temp.getY()+1));				
			} catch (IllegalArgumentException e) {
				
			}
			try {
				result.add(new Coord(temp.getX()+1, temp.getY()+1));
			} catch (IllegalArgumentException e) {
				
			}									
		}
		return result;
	}

	@Override
	protected Set<Coord> getMovableCoords() {
		HashSet<Coord> result = new HashSet<Coord>();
		Coord temp = getCoord();
		if (getColor() == Player.WHITE) {
			//attacking squares
			try {
				temp = new Coord(temp.getX()-1, temp.getY()-1);
				if (GameLogic.getPiecePane().getPiece(temp).getColor() != getColor() && !(GameLogic.getPiecePane().getPiece(temp) instanceof Empty))
					result.add(temp);
			} catch (IllegalArgumentException e) {
				
			}
			temp = getCoord();
			try {
				temp = new Coord(temp.getX()+1, temp.getY()-1);
				if (GameLogic.getPiecePane().getPiece(temp).getColor() != getColor() && !(GameLogic.getPiecePane().getPiece(temp) instanceof Empty))
					result.add(temp);			
			} catch (IllegalArgumentException e) {
				
			}
			//moving forward squares
			temp = getCoord();
			try {
				temp = new Coord(temp.getX(), temp.getY()-1);
				if (GameLogic.getPiecePane().getPiece(temp) instanceof Empty) {
					result.add(temp);
					temp = new Coord(temp.getX(), temp.getY()-1);
					if (GameLogic.getPiecePane().getPiece(temp) instanceof Empty && !(hasMoved))
						result.add(temp);
				}
			} catch (IllegalArgumentException e) {
				
			}
			
		} else {
			//attacking squares
			temp = getCoord();
			try {
				temp = new Coord(temp.getX()-1, temp.getY()+1);
				if (GameLogic.getPiecePane().getPiece(temp).getColor() != getColor() && !(GameLogic.getPiecePane().getPiece(temp) instanceof Empty))
					result.add(temp);
			} catch (IllegalArgumentException e) {
				
			}
			temp = getCoord();
			try {
				temp = new Coord(temp.getX()+1, temp.getY()+1);
				if (GameLogic.getPiecePane().getPiece(temp).getColor() != getColor() && !(GameLogic.getPiecePane().getPiece(temp) instanceof Empty))
					result.add(temp);			
			} catch (IllegalArgumentException e) {
				
			}
			//moving forward squares
			temp = getCoord();
			try {
				temp = new Coord(temp.getX(), temp.getY()+1);
				if (GameLogic.getPiecePane().getPiece(temp) instanceof Empty) {
					result.add(temp);
					temp = new Coord(temp.getX(), temp.getY()+1);
					if (GameLogic.getPiecePane().getPiece(temp) instanceof Empty && !(hasMoved))
						result.add(temp);
				}
			} catch (IllegalArgumentException e) {
				
			}
			
		}
		return result;
		
	}

}
