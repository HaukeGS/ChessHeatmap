package chessboard;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import util.ChessUtil;

public class BoardPane extends GridPane {
	
	Square[][] squares;
	ChessBoard cb;
	
	public BoardPane(ChessBoard cb) {
		squares = new Square[8][8];
		this.cb = cb;
	}
	
	protected void addSquare(Coord coord, Color c) {
		Square square = new Square (100, 100, coord, c);
		squares[coord.getChessX()][coord.getChessY()] = square;
		add(square, coord.getChessX(), coord.getChessY());
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
		squares[x][y].highlight();		
	}
	
	protected void dehighlightSquare(char x, int y) {
		dehighlightSquare(ChessUtil.asciiOffset(x), ChessUtil.inverseIndex(y));
	}
	
	protected void dehighlightSquare(int x, int y) {
		squares[x][y].dehighlight();
	}

}
