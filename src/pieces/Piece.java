package pieces;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Piece extends Group {
	public enum Player {
		WHITE,
		BLACK
	}
	
	protected Image image;
	protected ImageView imageView;	
	protected Player color;
	
	public Piece(Player c)  {
		this.color = c;
	}

	protected void setImage(String url) {
		image = new Image(url);
		imageView = new ImageView(image);
		imageView.setFitHeight(100);
		imageView.setFitWidth(100);
		imageView.fitHeightProperty();
		imageView.fitWidthProperty();
		imageView.setSmooth(true);
		imageView.setPreserveRatio(true);
		getChildren().add(imageView);
	}
}
