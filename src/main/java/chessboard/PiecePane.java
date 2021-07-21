package chessboard;

import javafx.scene.layout.GridPane;
import pieces.Empty;
import pieces.Piece;
import util.ChessUtil;

public class PiecePane extends GridPane {
	
	private Piece[][] pieces;
	private final ChessBoard cb;
	
	//Do NOT use add to add pieces to the PiecePane. Use addPiece instead!
	public PiecePane(ChessBoard cb) {
		this.cb = cb;
//		initConstraints();
		
		pieces = new Piece[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				pieces[i][j] = null;
			}
		}
	}

//	private void initConstraints() {
//		for (int i = 0; i < 8; i++) {
//			ColumnConstraints col = new ColumnConstraints();
//			RowConstraints row = new RowConstraints();
//			col.setPercentWidth(12.5);
//			row.setPercentHeight(12.5);
//			getColumnConstraints().add(col);
//			getRowConstraints().add(row);
//		}
//	}
	
	protected void addPiece(Piece piece) {
		pieces[piece.getCoord().getChessX()][piece.getCoord().getChessY()] = piece;
		add(piece, piece.getCoord().getChessX(), piece.getCoord().getChessY(), 1 ,1);
		piece.heightProperty().bind(cb.heightProperty().divide(8));
		piece.widthProperty().bind(cb.widthProperty().divide(8));
	}
	
//	protected Piece getPiece(char x, int y) {
//		return getPiece(ChessUtil.asciiOffset(x), ChessUtil.inverseIndex(y));
//	}
//	
//	protected Piece getPiece(int x, int y) {
//		return pieces[x][y];
//	}
	
	public Piece[][] getPieces() {
		return pieces;
	}
	
	public Piece getPiece(Coord c) {
		return pieces[c.getChessX()][c.getChessY()];
	}
	
	public void movePiece(Coord sourceCoord, Coord targetCoord) {
		Piece piece = pieces[sourceCoord.getChessX()][sourceCoord.getChessY()];
		Piece target = pieces[targetCoord.getChessX()][targetCoord.getChessY()];
//		if (!(target instanceof Empty))
		getChildren().remove(target);
		getChildren().remove(piece);
		piece.setCoord(targetCoord);
		addPiece(piece);
		addPiece(new Empty(sourceCoord));
	}
	
	//call only if move is special
	public void removePiece(Coord coord) {
		Piece piece = pieces[coord.getChessX()][coord.getChessY()];
		pieces[coord.getChessX()][coord.getChessY()] = null;
		getChildren().remove(piece);
		addPiece(new Empty(coord));
	}

}
