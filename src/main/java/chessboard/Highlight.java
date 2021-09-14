package chessboard;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Highlight extends Circle {
	
	public Highlight() {
		super(40);
		setFill(Color.TRANSPARENT);
		setStroke(Color.WHITE);
		setStrokeWidth(5);
		setVisible(false);
	}
}
