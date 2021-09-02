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
//		registerEvents();
		setPickOnBounds(true);
		this.coord = coord;
//		highlight();
	}
	
//	private void registerEvents() {
//		
//		EventHandler<MouseEvent> onMouseClicked = new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent arg0) {
//				setFill(Color.BLACK);
//			}			
//		};
//		
//		addEventFilter(MouseEvent.MOUSE_CLICKED, onMouseClicked);
//		
//	}
	
	public void highlight () {
//		Circle circle = new Circle(25, Color.GRAY);
//		union(this, circle);
//		circle.radiusProperty().bind(this.widthProperty().divide(4));
//		circle.centerXProperty().bind(this.widthProperty().divide(2));
//		circle.centerYProperty().bind(this.widthProperty().divide(2));
		setFill(Color.BLACK);
	}
	
	public void recolor(boolean colored) {
		if (colored)
			recolorColored();
		else
			recolorBW();
	}
	
	private void recolorColored() {
//		if (attackCountBlack == attackCountWhite) {
//			setFill(new Color (1.0, 1.0, 0, 1.0));
//			return;
//		} else if (attackCountBlack > attackCountWhite) {
//			
//		} else if (attackCountBlack < attackCountWhite) {
//			
//		}
		double range = 0.125;
		int difference = attackCountWhite - attackCountBlack;
		double colorValue = 0.50 + Math.max(-4, Math.min(4, difference)) * range;
		setFill(new Color(1-colorValue,colorValue,0,1.0));
		
		
	}
	
	private void recolorBW() {
		double range = 0.1;
		int factor = attackCountWhite - attackCountBlack;
		double colorValue = 0.50 + Math.max(-4, Math.min(4, factor)) * range;
		setFill(new Color(colorValue,colorValue,colorValue,1.0));
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