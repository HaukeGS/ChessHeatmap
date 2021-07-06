package chessboard;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import util.ChessUtil;

public class BoardPane extends GridPane {
	
	Square[][] squares;
	ChessBoard cb;
	
	public BoardPane(ChessBoard cb) {
		squares = new Square[8][8];
		this.cb = cb;
	}
	
	protected void addSquare(int x, int y, Color c) {
		Square square = new Square (100, 100, c);
		squares[x][y] = square;
		add(square, x, y);
        square.widthProperty().bind(cb.widthProperty().divide(8));
        square.heightProperty().bind(cb.heightProperty().divide(8));
	}
	
	protected Square getSquare(int x, int y) {
		return squares[x][y];
	}
	
	protected Square getSquare(char x, int y) {
		return getSquare(ChessUtil.asciiOffset(x), ChessUtil.inverseIndex(y));
	}
	
	protected void recolorSquare(int x, int y, Color c) {
		getSquare(x, y).setFill(c);
	}
	
	protected void recolorSquare(char x, int y, Color c) {
		recolorSquare(ChessUtil.asciiOffset(x), ChessUtil.inverseIndex(y), c);
	}
	
	protected void highlightSquare(char x, int y) {
		hightlightSquare(ChessUtil.asciiOffset(x), ChessUtil.inverseIndex(y));
	}
	
	protected void removeSquare(char x, int y) {
		removeSquare(ChessUtil.asciiOffset(x), ChessUtil.inverseIndex(y));
	}
	
	protected void removeSquare(int x, int y) {
		Square toRemove = squares[x][y];
		getChildren().remove(toRemove);
		squares[x][y] = null;
	}
	
	protected void hightlightSquare(int x, int y) {
//		Square square = squares[x][y];
//		Circle circle = new Circle(25, Color.GRAY);
//		circle.radiusProperty().bind(square.widthProperty().divide(4));
//		circle.centerXProperty().bind(square.widthProperty().divide(2));
//		circle.centerYProperty().bind(square.widthProperty().divide(2));
//		Shape.union(square, circle);
//		Color temp = square.getColor()
//		removeSquare(x,y);
//		addSquare()
		
	}

}
