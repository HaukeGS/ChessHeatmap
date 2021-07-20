package pieces;

import javafx.scene.paint.Color;

public class Empty extends Piece {

	public Empty(char x, int y) {
		super(Player.NONE, x, y);
		setFill(Color.TRANSPARENT);
		setFenChar('1');
	}

}
