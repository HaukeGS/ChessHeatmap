package pieces;

import chessboard.Coord;
import chessboard.PiecePane;
import javafx.scene.paint.Color;

public class Empty extends Piece {

	public Empty(Coord coord, PiecePane pP) {
		super(Player.NONE, coord, pP);
		setFill(Color.TRANSPARENT);
		setFenChar('1');
	}

	@Override
	public void setAttackedCoords() {
		// TODO Auto-generated method stub
		
	}

}
