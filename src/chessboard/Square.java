package chessboard;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends Rectangle {
	
	int x;
	int y;
	
	public Square (double width, double height, Color c) {
		super(width, height);
		setFill(c);
//		highlight();
	}
	
	public void highlight () {
		
	}
	
	public void dehighlight() {
		
	}
}