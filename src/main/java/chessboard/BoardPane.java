package chessboard;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

public class BoardPane extends GridPane {

	Highlight[][] highlights;
	Square[][] squares;
	ChessBoard cb;

	public BoardPane(ChessBoard cb) {
		squares = new Square[8][8];
		highlights = new Highlight[8][8];
		this.cb = cb;
		initConstraints();
	}

	protected void addSquare(Coord coord, Color c) {
		Square square = new Square(100, 100, coord, c);
		squares[coord.getX()][coord.getY()] = square;
		add(square, coord.getX(), coord.getY());
	}

	protected void addHighlight(Coord coord) {
		Highlight highlight = new Highlight();
		highlights[coord.getX()][coord.getY()] = highlight;
		add(highlight, coord.getX(), coord.getY());
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
		squares[c.getX()][c.getY()].setInCheckFalse();
	}
	
	public void highlightKingInCheckmate(Coord c) {
		squares[c.getX()][c.getY()].highlightCheckmate();
	}

	public void dehighlightAttackersSquares() {
		for (Square[] ss : squares) {
			for (Square s : ss) {
				s.dehighlight();
			}
		}
	}
	
	public void highlightMovable(Coord c) {
		highlights[c.getX()][c.getY()].setVisible(true);
	}
	
	public void dehighlightMovable(Coord c) {
		highlights[c.getX()][c.getY()].setVisible(false);
	}
	
	public void dehighlightMovableAll() {
		for (Highlight[] hh : highlights) {
			for (Highlight h : hh) {
				h.setVisible(false);
			}
		}
	}

	private void initConstraints() {
		for (int i = 0; i < 8; i++) {
			ColumnConstraints col = new ColumnConstraints();
			RowConstraints row = new RowConstraints();
			col.setMaxWidth(800);
			row.setMaxHeight(800);
			col.setHgrow(Priority.NEVER);
			row.setVgrow(Priority.NEVER);
			col.setHalignment(HPos.CENTER);
			row.setValignment(VPos.CENTER);
			getColumnConstraints().add(col);
			getRowConstraints().add(row);
		}
	}

}
