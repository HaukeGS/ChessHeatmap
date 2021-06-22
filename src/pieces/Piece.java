package pieces;

import chessboard.ChessBoard;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

public abstract class Piece extends Region {
	public enum Player {
		WHITE,
		BLACK
	}
	
	protected Image image;
	protected ImageView imageView;	
	protected Player color;
	
	public Piece(Player c, ChessBoard cb)  {
		this.color = c;
//		this.heightProperty().bind(cb.heightProperty().divide(8));
//		this.widthProperty().bind(cb.widthProperty().divide(8));
		setPrefHeight(USE_COMPUTED_SIZE);
		setPrefWidth(USE_COMPUTED_SIZE);
	}

	protected void setImage(String url) {
		image = new Image(url);
		imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(USE_COMPUTED_SIZE);
		imageView.setFitWidth(USE_COMPUTED_SIZE);
		imageView.fitHeightProperty().bind(this.heightProperty());
		imageView.fitWidthProperty().bind(this.widthProperty());
		imageView.setSmooth(true);
		getChildren().add(imageView);
		setCenterShape(true);
	}
}
