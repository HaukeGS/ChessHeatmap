package chessboard;

import javafx.scene.layout.GridPane;
import pieces.Piece;

public class PiecePane extends GridPane {
	
	
	protected void addPiece(Piece piece, char x, int y, int colspan, int rowspan) {
		int temp = (int)x;
		temp -= 97;
		y = 8-y;
		add(piece, temp, y, colspan, rowspan);
	}

}
