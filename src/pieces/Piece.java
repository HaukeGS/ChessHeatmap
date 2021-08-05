package pieces;

import chessboard.PiecePane;
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
	private char chessX;
	private int chessY;
	
	public Piece(Player c, char chessX, int chessY)  {
		this.color = c;
		this.selected = false;
		this.chessX = chessX;
		this.chessY = chessY;
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
//						System.out.println(util.GameLogic.getSelected());						
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

	public char getChessX() {
		return chessX;
	}

	public void setChessX(char chessX) {
		this.chessX = chessX;
	}

	public int getChessY() {
		return chessY;
	}

	public void setChessY(int chessY) {
		this.chessY = chessY;
	}
	
	
}
