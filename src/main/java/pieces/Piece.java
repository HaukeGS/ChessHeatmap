package pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import chessboard.Coord;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import util.GameLogic;

public abstract class Piece extends Rectangle implements Cloneable {
	public enum Player {
		WHITE,
		BLACK,
		NONE
	}
	
	private Rectangle rec;
	private Image image;
	private Player color;
	private char fenChar;
	protected Coord coord;
	protected boolean hasMoved;
	protected List<Piece> attackers;
	
	public Piece(Player c, Coord coord)  {
		super(100, 100);
		this.color = c;
		this.coord = coord;
		this.attackers = new ArrayList<>();
		this.hasMoved = false;
		createEvents();
	}

	protected void setImage(String url) {
		image = new Image(url);
		setFill(new ImagePattern(image));
	}
	
	protected void setFenChar(char c) {
		this.fenChar = c;
	}
	
	public char getFenChar() {
		return fenChar;
	}
	
	private void createEvents() {
		this.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.PRIMARY) { 			//left clicked piece
				Piece piece = (Piece) event.getSource();			//clicked piece
				if (util.GameLogic.getSelected() == null) { 				//if no piece is selected
					if (!(piece instanceof Empty)) {   				//and an actual piece and no empty space is clicked
						util.GameLogic.setSelected(piece);				//select the clicked piece
					} else {
						
					}
				} else if (util.GameLogic.getSelected() != null) {
					if (util.GameLogic.isMoveLegal(piece)) {
						util.GameLogic.movePiece(piece);
					} else {
						util.GameLogic.deselect();
					}
				}
			} else if (event.getButton() == MouseButton.SECONDARY) {	//right clicked piece
				if (!GameLogic.getHighlightingAttackers()) {
					GameLogic.highlightAttackers(getAttackers());					
				} else {
					GameLogic.dehighlightAttackers(getAttackers());					
				}
			}
		});
	}
	
	@Override
	public String toString() {
		return color.toString() + " " + this.getClass();
	}
	
	public boolean canMoveTo(Coord c) {
		for (Coord ca : getMovableCoords()) {
			if (ca.equals(c))
				return true;
		}
		return false;
	}
	
	public Coord getCoord() {
		return coord;
	}
	
	public void setCoord(Coord c) {
		this.coord = c;
//		setAttackedCoords();
	}
	
	public abstract Set<Coord> getAttackedCoords();
	
	protected abstract Set<Coord> getMovableCoords();
	
	public void addAttacker(Piece p) {
		attackers.add(p);
	}
	
	public Player getColor() {
		return color;
	}
	
	public void clearAttackers() {
		attackers.clear();
	}
	
	public List<Piece> getAttackers() {
		return attackers;
	}
	
	
}
