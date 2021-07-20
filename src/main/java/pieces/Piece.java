package pieces;

import chessboard.Coord;
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
	
	private Image image;
	private Player color;
	private char fenChar;
	private boolean selected;
	private Coord coord;
	
	public Piece(Player c, Coord coord)  {
		this.color = c;
		this.selected = false;
		this.coord = coord;
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
		
		EventHandler<MouseEvent> onMouseClicked = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Piece piece = (Piece) event.getSource();			//clicked piece
				if (util.GameLogic.getSelected() == null) { 				//if no piece is selected
					if (!(piece instanceof Empty)) {   				//and an actual piece and no empty space is clicked
						util.GameLogic.setSelected(piece);				//select the clicked piece
						System.out.println(util.GameLogic.getSelected());						
					} else {
																	//if empty space is clicked, do nothing
					}
				} else if (util.GameLogic.getSelected() != null) {
					if (util.GameLogic.isMoveLegal(piece)) {
						util.GameLogic.movePiece(piece);
					} else {
						util.GameLogic.deselect();
					}
				}
			}			
		};
		
		addEventFilter(MouseEvent.MOUSE_CLICKED, onMouseClicked);
	}
	
	@Override
	public String toString() {
		return color.toString() + " " + this.getClass();
	}
	
	public Coord getCoord() {
		return coord;
	}
	
	public void setCoord(Coord c) {
		this.coord = c;
	}
	
	
}
