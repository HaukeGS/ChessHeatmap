package pieces;

import java.util.HashSet;
import java.util.Set;

import chessboard.Coord;
import util.GameLogic;

public class Rook extends Piece {

	public Rook(Player c, Coord coord) {
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
				temp = new Coord(temp.getX(), temp.getY() - 1);
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty)) {					
					if(GameLogic.getPiecePane().getPiece(temp) instanceof Queen && GameLogic.getXray()) {
						for (temp = new Coord(temp.getX(), temp.getY()-1);GameLogic.getPiecePane().getPiece(temp) instanceof Empty; temp = new Coord(temp.getX(), temp.getY()-1)) {
							result.add(temp);
						}
						result.add(new Coord(temp.getX(), temp.getY()-1));
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
				temp = new Coord(temp.getX() - 1, temp.getY());
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty)) {					
					if(GameLogic.getPiecePane().getPiece(temp) instanceof Queen && GameLogic.getXray()) {
						for (temp = new Coord(temp.getX()-1, temp.getY());GameLogic.getPiecePane().getPiece(temp) instanceof Empty; temp = new Coord(temp.getX()-1, temp.getY())) {
							result.add(temp);
						}
						result.add(new Coord(temp.getX()-1, temp.getY()));
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
				temp = new Coord(temp.getX() + 1, temp.getY());
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty)) {					
					if(GameLogic.getPiecePane().getPiece(temp) instanceof Queen && GameLogic.getXray()) {
						for (temp = new Coord(temp.getX()+1, temp.getY());GameLogic.getPiecePane().getPiece(temp) instanceof Empty; temp = new Coord(temp.getX()+1, temp.getY())) {
							result.add(temp);
						}
						result.add(new Coord(temp.getX()+1, temp.getY()));
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
				temp = new Coord(temp.getX(), temp.getY() + 1);
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty)) {					
					if(GameLogic.getPiecePane().getPiece(temp) instanceof Queen && GameLogic.getXray()) {
						for (temp = new Coord(temp.getX(), temp.getY()+1);GameLogic.getPiecePane().getPiece(temp) instanceof Empty; temp = new Coord(temp.getX(), temp.getY()+1)) {
							result.add(temp);
						}
						result.add(new Coord(temp.getX(), temp.getY()+1));
					}
					break;	
				}
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		return result;
	}

	@Override
	public Set<Coord> getMovableCoords() {
		HashSet<Coord> result = new HashSet<Coord>();
		Coord temp = getCoord();
		while (true) {
			try {
				temp = new Coord(temp.getX(), temp.getY() - 1);
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
				temp = new Coord(temp.getX() - 1, temp.getY());
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
				temp = new Coord(temp.getX() + 1, temp.getY());
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
				temp = new Coord(temp.getX(), temp.getY() + 1);
				result.add(temp);
				if (!(GameLogic.getPiecePane().getPiece(temp) instanceof Empty))
					break;
			} catch (IllegalArgumentException e) {
				break;
			}
		}
		return result;
	}

}
