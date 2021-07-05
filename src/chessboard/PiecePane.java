package chessboard;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import pieces.Piece;

public class PiecePane extends GridPane {
	
	Piece[][] pieces;
	
	//Do NOT use add to add pieces to the PiecePane. Use addPiece instead!
	public PiecePane() {		
		initConstraints();
		
		pieces = new Piece[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				pieces[i][j] = null;
			}
		}
	}

	private void initConstraints() {
		for (int i = 0; i < 8; i++) {
			ColumnConstraints col = new ColumnConstraints();
			RowConstraints row = new RowConstraints();
			col.setPercentWidth(12.5);
			row.setPercentHeight(12.5);
			getColumnConstraints().add(col);
			getRowConstraints().add(row);
		}
	}
	
	//Adds the Piece to the children and to the piece matrix
	protected void addPiece(Piece piece, char x, int y) {
		addPiece(piece, ChessUtil.asciiOffset(x), ChessUtil.inverseIndex(y));
	}
	
	protected void addPiece(Piece piece, int x, int y) {
		pieces[x][y] = piece;
		add(piece, x, y, 1 ,1);
	}
	
	protected Piece getPiece(char x, int y) {
		return getPiece(ChessUtil.asciiOffset(x), ChessUtil.inverseIndex(y));
	}
	
	protected Piece getPiece(int x, int y) {
		return pieces[x][y];
	}
	
	protected void movePiece(char sourceX, int sourceY, char targetX, int targetY) {
		movePiece(ChessUtil.asciiOffset(sourceX), ChessUtil.inverseIndex(sourceY), ChessUtil.asciiOffset(targetX), ChessUtil.inverseIndex(targetY));
	}
	
	protected void movePiece(int sourceX, int sourceY, int targetX, int targetY) {
		Piece piece = pieces[sourceX][sourceY];
		pieces[sourceX][sourceY] = null;
		getChildren().remove(piece);
		addPiece(piece, targetX, targetY);
	}
	
	protected void removePiece(char x, int y) {
		removePiece(ChessUtil.asciiOffset(x), ChessUtil.inverseIndex(y));
	}
	
	protected void removePiece(int x, int y) {
		Piece piece = pieces[x][y];
		pieces[x][y] = null;
		getChildren().remove(piece);
		
	}

}
