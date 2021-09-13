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
		squares[coord.getX()][coord.getY()] = square;
		add(square, coord.getX(), coord.getY());
	}
	
	protected Square getSquare(Coord c) {
		return squares[c.getX()][c.getY()];
	}

	public void recolorSquares(int[][] whiteMatrix, int[][] blackMatrix, boolean colored) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				squares[i][j].setWhiteAttacks(whiteMatrix[i][j]);
				squares[i][j].setBlackAttacks(blackMatrix[i][j]);
				squares[i][j].recolor(colored);
			}
		}
	}
	
	protected void removeSquare(int x, int y) {
		Square toRemove = squares[x][y];
		getChildren().remove(toRemove);
		squares[x][y] = null;
	}
	
	public void highlightSquareAsAttacker(Coord c) {
		squares[c.getX()][c.getY()].highlightAsAttacker();		
	}
	
	public void highlightKingInCheck(Coord c) {
		squares[c.getX()][c.getY()].highlightAsInCheck();
		
	}
	
	public void dehighlightKingInCheck(Coord c) {
		squares[c.getX()][c.getY()].setInCheckFalse();;
	}
	
	public void dehighlightAttackersSquares() {
		for (Square[] ss : squares) {
			for (Square s : ss) {
				s.dehighlight();
			}
		}
	}

}
