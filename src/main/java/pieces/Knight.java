package pieces;

import java.util.HashSet;
import java.util.Set;

import chessboard.Coord;

public class Knight extends Piece {
	
public Knight (Player c, Coord coord) {
	super(c, coord);
	if (c == Player.WHITE) {
		setImage("file:res/Chesspieces/White_Knight.png");
		setFenChar('N');
	} else {
		setImage("file:res/Chesspieces/Black_Knight.png");
		setFenChar('n');	
	}
}

	@Override
	public Set<Coord> getAttackedCoords() {
		HashSet<Coord> result = new HashSet<Coord>();
		Coord temp = getCoord();
		try {
			result.add(new Coord(temp.getX()-1,temp.getY()-2));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			result.add(new Coord(temp.getX()-2,temp.getY()-1));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			result.add(new Coord(temp.getX()+1,temp.getY()-2));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			result.add(new Coord(temp.getX()+2,temp.getY()-1));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			result.add(new Coord(temp.getX()+2,temp.getY()+1));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			result.add(new Coord(temp.getX()+1,temp.getY()+2));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			result.add(new Coord(temp.getX()-1,temp.getY()+2));			
		} catch (IllegalArgumentException e) {
			
		}
		try {
			result.add(new Coord(temp.getX()-2,temp.getY()+1));			
		} catch (IllegalArgumentException e) {
			
		}
		return result;		
	}

	@Override
	public Set<Coord> getMovableCoords() {
		// TODO Auto-generated method stub
		return getAttackedCoords();
	}

}
