package chessboard;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class BoardPane extends GridPane {
	
	Square[][] squares;
	
	public BoardPane() {
		squares = new Square[8][8];
	}
	
	protected void addSquare(int x, int y, Color c, ChessBoard cb) {
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

}
