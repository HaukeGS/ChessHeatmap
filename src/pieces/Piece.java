package pieces;

import chessboard.ChessBoard;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public abstract class Piece extends Rectangle {
	public enum Player {
		WHITE,
		BLACK
	}
	
	protected Image image;
	protected Player color;
	
	public Piece(Player c, ChessBoard cb)  {
		this.color = c;
		this.heightProperty().bind(cb.heightProperty().divide(8));
		this.widthProperty().bind(cb.widthProperty().divide(8));
	}

	protected void setImage(String url) {
		image = new Image(url);
		setFill(new ImagePattern(image));
	}
}
