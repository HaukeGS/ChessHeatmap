package pieces;

import java.util.HashSet;
import java.util.Set;

import chessboard.Coord;
import chessutility.GameLogic;

public class King extends Piece {
	
	public King (Player c, Coord coord) {
		super(c, coord);
		if (c == Player.WHITE) {
			setImage("file:res/Chesspieces/White_King.png");
			setFenChar('K');
		} else {
			setImage("file:res/Chesspieces/Black_King.png");	
			setFenChar('k');
		}
	}
	
	public Set<Coord> getMovableCoords() {
		Set<Coord> result = getAttackedCoords();
		if (GameLogic.canCastleKingSide(getColor())) {
			result.add(new Coord(getCoord().getX()+2, getCoord().getY()));
		}
		if (GameLogic.canCastleQueenSide(getColor())) {
			result.add(new Coord(getCoord().getX()-2, getCoord().getY()));			
		}
		return result;
	}

	@Override
	public Set<Coord> getAttackedCoords() {
		HashSet<Coord> result = new HashSet<Coord>();
		Coord temp = getCoord();
		try {
			result.add(new Coord(temp.getX(),temp.getY()+1));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			result.add(new Coord(temp.getX()+1,temp.getY()));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			result.add(new Coord(temp.getX(),temp.getY()-1));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			result.add(new Coord(temp.getX()-1,temp.getY()));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			result.add(new Coord(temp.getX()+1,temp.getY()+1));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			result.add(new Coord(temp.getX()+1,temp.getY()-1));
		} catch (IllegalArgumentException e) {
			
		}
		try {
			result.add(new Coord(temp.getX()-1,temp.getY()+1));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			result.add(new Coord(temp.getX()-1,temp.getY()-1));			
		} catch (IllegalArgumentException e) {
			
		}
		return result;
	}
}
