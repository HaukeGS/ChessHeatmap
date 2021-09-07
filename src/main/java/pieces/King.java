package pieces;

import java.util.HashSet;
import java.util.Set;

import chessboard.Coord;

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
	
	protected Set<Coord> getMovableCoords() {
		return getAttackedCoords();
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
