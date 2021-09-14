package chessboard;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends Rectangle {
	
	private Coord coord;
	private Color color;
	private int attackCountWhite;
	private int attackCountBlack;
	private boolean inCheck;
	
	public Square (double width, double height, Coord coord, Color c) {
		super(width, height);
		this.color = c;
		setFill(c);
		setPickOnBounds(true);
		this.coord = coord;
		this.inCheck = false;
	}
	
	public void highlightAsAttacker () {
		setFill(Color.GOLD);
	}

	public void highlightAsInCheck() {
		setFill(Color.PURPLE);
		inCheck = true;
	}

	public void highlightCheckmate() {
		setFill(Color.ANTIQUEWHITE);
		inCheck = true;
	}
	
	public void setInCheckFalse() {
		inCheck = false;
	}
	
	public void recolor(boolean colored) {
		if (colored)
			recolorColored();
		else
			recolorBW();
	}
	
	private void recolorColored() {
		double range = 0.125;
		int difference = attackCountWhite - attackCountBlack;
		double colorValue = 0.50 + Math.max(-4, Math.min(4, difference)) * range;
		color = new Color(1-colorValue,colorValue,0,1.0);
		setFill(color);		
	}
	
	private void recolorBW() {
		double range = 0.0875;
		int factor = attackCountWhite - attackCountBlack;
		double colorValue = 0.50 + Math.max(-4, Math.min(4, factor)) * range;
		color = new Color(colorValue,colorValue,colorValue,1.0);
		setFill(color);
	}
	
	public Coord getCoord() {
		return coord;
	}
	
	public void dehighlight() {
		if (inCheck)
			highlightAsInCheck();
		else
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