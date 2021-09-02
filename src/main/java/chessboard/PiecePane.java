package chessboard;

import java.util.Set;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import pieces.Empty;
import pieces.Piece;
import pieces.Piece.Player;

public class PiecePane extends GridPane {
	
	private Piece[][] pieces;
	private final ChessBoard cb;
	
	//Do NOT use add to add pieces to the PiecePane. Use addPiece instead!
	public PiecePane(ChessBoard cb) {
		this.cb = cb;
		initConstraints();
		
		pieces = new Piece[8][8];
		clearPieces();
	}
	
	void clearPieces() {
		getChildren().clear();
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
			col.setMaxWidth(800);
			row.setMaxHeight(800);
			col.setHgrow(Priority.NEVER);
			row.setVgrow(Priority.NEVER);
//			col.setPercentWidth(12.5);
//			row.setPercentHeight(12.5);
			getColumnConstraints().add(col);
			getRowConstraints().add(row);
		}
	}
	
	protected void addPiece(Piece piece) {
		pieces[piece.getCoord().getX()][piece.getCoord().getY()] = piece;
		add(piece, piece.getCoord().getX(), piece.getCoord().getY(), 1 ,1);
//		piece.heightProperty().bind(cb.heightProperty().divide(8));
//		piece.widthProperty().bind(cb.widthProperty().divide(8));
	}
	
	public Piece[][] getPieces() {
		return pieces;
	}
	
	public Piece getPiece(Coord c) {
		return pieces[c.getX()][c.getY()];
	}
	
	public void movePiece(Coord sourceCoord, Coord targetCoord) {
		Piece piece = pieces[sourceCoord.getX()][sourceCoord.getY()];
		Piece target = pieces[targetCoord.getX()][targetCoord.getY()];
//		if (!(target instanceof Empty))
		getChildren().remove(target);
		getChildren().remove(piece);
		piece.setCoord(targetCoord);
		addPiece(piece);
		addPiece(new Empty(sourceCoord, this));
	}
	
	//call only if move is special
	public void removePiece(Coord coord) {
		Piece piece = pieces[coord.getX()][coord.getY()];
		pieces[coord.getX()][coord.getY()] = null;
		getChildren().remove(piece);
		addPiece(new Empty(coord, this));
	}
	
	public int[][] attackingMatrix(Player color) {
		int[][] result = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				result[i][j] = 0;
			}
		}
		for (Piece p[] : pieces) {
			for (Piece piece : p) {
				if (piece.getColor() == color) {
					Set<Coord> aC = piece.getAttackedCoords();
					for (Coord c : aC) {
						result[c.getX()][c.getY()]++;
					}
				}
			}
		}		
		return result;
	}
	
	public void clearAttackers() {
		for (Piece[] p : pieces) {
			for (Piece pp : p) {
				pp.clearAttackers();
			}
			
		}
	}
}
