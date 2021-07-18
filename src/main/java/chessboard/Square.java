package chessboard;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Square extends Rectangle {
	
	private int x;
	private int y;
	private Color color;
	
	public Square (double width, double height, Color c) {
		super(width, height);
		this.color = c;
		setFill(c);
		registerEvents();
		setPickOnBounds(true);
//		highlight();
	}
	
	private void registerEvents() {
		
		EventHandler<MouseEvent> onMouseClicked = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				setFill(Color.BLACK);
			}			
		};
		
		addEventFilter(MouseEvent.MOUSE_CLICKED, onMouseClicked);
		
	}
	
	public void highlight () {
//		Circle circle = new Circle(25, Color.GRAY);
//		union(this, circle);
//		circle.radiusProperty().bind(this.widthProperty().divide(4));
//		circle.centerXProperty().bind(this.widthProperty().divide(2));
//		circle.centerYProperty().bind(this.widthProperty().divide(2));
		setFill(Color.BLACK);
	}
	
	public void dehighlight() {
		setFill(color);
	}
	
	public Color getColor() {
		return color;
	}
}