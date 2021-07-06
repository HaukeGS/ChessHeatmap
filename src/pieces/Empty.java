package pieces;

import javafx.scene.paint.Color;

public class Empty extends Piece {

	public Empty() {
		super(Player.NONE);
		setFill(Color.TRANSPARENT);
	}

}
