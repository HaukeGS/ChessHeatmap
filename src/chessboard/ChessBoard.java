package chessboard;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pieces.Bishop;
import pieces.Piece.Player;

public class ChessBoard extends GridPane {
	
	public ChessBoard() {
		setGridLinesVisible(true);
		initBoard();
		initPieces();
	}

	private void initBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Color c;
				if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
					c = Color.WHITE;
				else
					c = Color.GREY;{
				this.add(new Square(i, j, c), i, j);
				//this.add(new Bishop(Player.WHITE), i, i);
			}
		}
	}
		for (int i = 0; i < 8; i++) {
			this.add(new Label(Integer.toString(8-i)), 8, i);
			this.add(new Label(Character.toString('a' + i)), i, 8);
		}
	}
	
	private void initPieces() {
		this.add(new Bishop(Player.WHITE), 'a', 1);
	}
	
	public Square getSquare(int x, int y) {
		Square result = null;
		ObservableList<Node> childrens = getChildren();
		for (Node n : childrens) {
			if (getRowIndex(n) == x && getColumnIndex(n) == y)
				result = (Square)n;
				return result;
		}
		return result;
	}
	
	private void add(Node child, char c, int y) {
		int x = (int) c;
		x -= 97;
		this.add(child, x, y);
	}
}
