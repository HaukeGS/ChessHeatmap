package pieces;

import chessboard.Coord;
import javafx.scene.paint.Color;

public class Empty extends Piece {

	public Empty(Coord coord) {
		super(Player.NONE, coord);
		setFill(Color.TRANSPARENT);
		setFenChar('1');
	}

}
