package chessboard;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Square extends Group {
	
	int x;
	int y;
	private Rectangle rec;
	private Circle circle;
	
	public Square (int x, int y, Color c) {
		rec = new Rectangle(100, 100);
		rec.setFill(c);
		circle = new Circle(10);
		circle.setFill(Color.GREY);
		getChildren().add(rec);
//		highlight();
	}
	
	public void highlight () {
		getChildren().add(circle);
	}
	
	public void dehighlight() {
		getChildren().remove(circle);
	}
}