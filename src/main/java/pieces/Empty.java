package pieces;

import java.util.HashSet;
import java.util.Set;

import chessboard.Coord;
import javafx.scene.paint.Color;

public class Empty extends Piece {

	public Empty(Coord coord) {
		super(Player.NONE, coord);
		setFill(Color.TRANSPARENT);
		setFenChar('1');
	}

	@Override
	public Set<Coord> getAttackedCoords() {
		return new HashSet<Coord>();
		
	}

	@Override
	protected Set<Coord> getMovableCoords() {
		// TODO Auto-generated method stub
		return getAttackedCoords();
	}

}
