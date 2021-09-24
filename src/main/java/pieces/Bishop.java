package pieces;



import java.util.HashSet;
import java.util.Set;

import chessboard.Coord;
import chessutility.GameLogic;

public class Bishop extends Piece {
		
	public Bishop (Player c, Coord coord) {
		super(c, coord);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_Bishop.png");
			setFenChar('B');
		} else {
			setImage("file:res/Chesspieces/Black_Bishop.png");	
			setFenChar('b');
		}
		
	}
	
	@Override
	public Set<Coord> getMovableCoords() {
		HashSet<Coord> result = new HashSet<Coord>();
		Coord temp = getCoord();
		while (true) {
			try {
				temp = new Coord(temp.getX()-1, temp.getY()-1);
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty)) {
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
	public Set<Coord> getAttackedCoords() {
		HashSet<Coord> result = new HashSet<Coord>();
		Coord temp = getCoord();
		while (true) {
			try {
				temp = new Coord(temp.getX()-1, temp.getY()-1);
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty)) {					
					if(GameLogic.getPiecePane().getPiece(temp) instanceof Pawn && GameLogic.getXray())
						result.add(new Coord(temp.getX()-1, temp.getY()-1));
					if (GameLogic.getPiecePane().getPiece(temp) instanceof Queen && GameLogic.getXray()) {
						for (temp = new Coord(temp.getX()-1, temp.getY()-1); GameLogic.getPiecePane().getPiece(temp) instanceof Empty; temp = new Coord(temp.getX()-1, temp.getY()-1))
							result.add(temp);
						result.add(temp);
					}
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
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty)) {					
					if(GameLogic.getPiecePane().getPiece(temp) instanceof Pawn && GameLogic.getXray())
						result.add(new Coord(temp.getX()-1, temp.getY()+1));
					if (GameLogic.getPiecePane().getPiece(temp) instanceof Queen && GameLogic.getXray()) {
						for (temp = new Coord(temp.getX()-1, temp.getY()+1); GameLogic.getPiecePane().getPiece(temp) instanceof Empty; temp = new Coord(temp.getX()-1, temp.getY()+1))
							result.add(temp);
						result.add(temp);
					}
					break;
				}
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		temp = getCoord();
		while (true) {
			try {
				temp = new Coord(temp.getX()+1, temp.getY()-1);
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty)) {					
					if(GameLogic.getPiecePane().getPiece(temp) instanceof Pawn && GameLogic.getXray())
						result.add(new Coord(temp.getX()+1, temp.getY()-1));
					if (GameLogic.getPiecePane().getPiece(temp) instanceof Queen && GameLogic.getXray()) {
						for (temp = new Coord(temp.getX()+1, temp.getY()-1); GameLogic.getPiecePane().getPiece(temp) instanceof Empty; temp = new Coord(temp.getX()+1, temp.getY()-1))
							result.add(temp);
						result.add(temp);
					}
					break;
				}
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		temp = getCoord();
		while (true) {
			try {
				temp = new Coord(temp.getX()+1, temp.getY()+1);
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty)) {					
					if(GameLogic.getPiecePane().getPiece(temp) instanceof Pawn && GameLogic.getXray())
						result.add(new Coord(temp.getX()+1, temp.getY()+1));
					if (GameLogic.getPiecePane().getPiece(temp) instanceof Queen && GameLogic.getXray()) {
						for (temp = new Coord(temp.getX()+1, temp.getY()+1); GameLogic.getPiecePane().getPiece(temp) instanceof Empty; temp = new Coord(temp.getX()+1, temp.getY()+1))
							result.add(temp);
						result.add(temp);
					}
					break;
				}
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		return result;
	}
}
