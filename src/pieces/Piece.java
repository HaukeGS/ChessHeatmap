package pieces;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Piece extends Group {
	public enum Player {
		WHITE,
		BLACK
	}
	
	Image image;
	ImageView imageView;
	
	
	public Player color;
	
	public Piece(Player c)  {
		this.color = c;
		if (c == Player.WHITE) {
			image = new Image("file:res/Chesspieces/White_Knight.png");
		} else {
			image = new Image("file:res/Chesspieces/Black_Knight.png");
		}
		imageView = new ImageView(image);
		imageView.setFitHeight(100);
		imageView.setFitWidth(100);
		imageView.fitHeightProperty();
		imageView.fitWidthProperty();
		imageView.setSmooth(true);
		imageView.setPreserveRatio(true);
        //imageView.setCache(true);
		getChildren().add(imageView);
//		centerImage();
	}
	
	public void centerImage() {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        }
	}
}
