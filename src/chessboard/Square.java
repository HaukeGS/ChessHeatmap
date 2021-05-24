package chessboard;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends Group {
	
	int x;
	int y;
	private Rectangle rec;
	
	public Square (int x, int y, Color c) {
		rec = new Rectangle(100, 100);
		rec.setFill(c);
		getChildren().add(rec);
	}
}