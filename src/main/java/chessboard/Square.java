package chessboard;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends Rectangle {
	
	private Coord coord;
	private Color color;
	private int attackCountWhite;
	private int attackCountBlack;
	
	public Square (double width, double height, Coord coord, Color c) {
		super(width, height);
		this.color = c;
		setFill(c);
		registerEvents();
		setPickOnBounds(true);
		this.coord = coord;
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
	
	public void recolor() {
		if (attackCountBlack == 0 && attackCountWhite == 0) {
			setFill(new Color (0.5,0.5,0.5,1.0));
			return;
		} else if (attackCountWhite == 0) {
			setFill(new Color(0.15, 0.15, 0.15, 1.0));
			return;
		} else if (attackCountBlack == 0) {
			setFill(new Color(0.85, 0.85, 0.85, 1.0));
			return;
		}
		double attackWhite = (double) attackCountWhite;
		double attackBlack = (double) attackCountBlack;
		double r = 0.0;
		double g = 0.0;
		double b = 0.0;
		if (attackCountBlack > attackCountWhite) {
			r = attackWhite / attackBlack;
			g = attackWhite / attackBlack;
			b = attackWhite / attackBlack;
		} else {
			r = attackBlack / attackWhite;
			g = attackBlack / attackWhite;
			b = attackBlack / attackWhite;
		}
		setFill(new Color(r,g,b,1.0));
	}
	
	public Coord getCoord() {
		return coord;
	}
	
	public void dehighlight() {
		setFill(color);
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setBlackAttacks(int count) {
		this.attackCountBlack = count;
	}
	
	public void setWhiteAttacks(int count) {
		this.attackCountWhite = count;
	}
	
	public void printAttacks() {
		System.out.println("Attack Cound White: " + attackCountWhite);
		System.out.println("Attack Cound Black: " + attackCountBlack);
	}
	
	public void resetCounts() {
		attackCountBlack = 0;
		attackCountWhite = 0;
	}
}