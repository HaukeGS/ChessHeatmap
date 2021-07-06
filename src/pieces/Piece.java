package pieces;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class Piece extends Rectangle {
	public enum Player {
		WHITE,
		BLACK,
		NONE
	}
	
	protected Image image;
	protected Player color;
	
	public Piece(Player c)  {
		this.color = c;
		createEvents();
		setPickOnBounds(true);
	}

	protected void setImage(String url) {
		image = new Image(url);
		setFill(new ImagePattern(image));
	}
	
	private void createEvents() {
		
		EventHandler<MouseEvent> onMouseClicked = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				System.out.println("test");
				
			}			
		};
		
		addEventFilter(MouseEvent.MOUSE_CLICKED, onMouseClicked);
	}
	
	@Override
	public String toString() {
		return color.toString() + " " + this.getClass();
	}
}
