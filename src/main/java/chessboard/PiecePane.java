package chessboard;

import java.util.Set;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import pieces.Bishop;
import pieces.Empty;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Piece.Player;
import pieces.Queen;
import pieces.Rook;

public class PiecePane extends GridPane {
	
	private Piece[][] pieces;
	private final ChessBoard cb;
	
	//Do NOT use add to add pieces to the PiecePane. Use addPiece instead!
	public PiecePane(ChessBoard cb) {		
		pieces = new Piece[8][8];
		clearPieces();
		
		this.cb = cb;
		initConstraints();
		initPieces();
	}
	
	public void reset() {
		clearPieces();
		initPieces();
	}
	
	private void initPieces() {
		
		//init white pieces
		addPiece(new Rook(Player.WHITE, new Coord('a', 1)));
		addPiece(new Knight(Player.WHITE, new Coord('b', 1)));
		addPiece(new Bishop(Player.WHITE, new Coord('c', 1)));
		addPiece(new Queen(Player.WHITE, new Coord('d', 1)));
		addPiece(new King(Player.WHITE, new Coord('e', 1)));
		addPiece(new Bishop(Player.WHITE, new Coord('f', 1)));
		addPiece(new Knight(Player.WHITE, new Coord('g', 1)));
		addPiece(new Rook(Player.WHITE, new Coord('h', 1)));
		
		for (char i = 'a'; i <= 'h'; i++) {
			for (int j = 3; j <= 6; j++) {
				addPiece(new Empty(new Coord(i, j)));
			}
		}
				
		//init black pieces
		addPiece(new Rook(Player.BLACK, new Coord('a', 8)));
		addPiece(new Knight(Player.BLACK, new Coord('b', 8)));
		addPiece(new Bishop(Player.BLACK, new Coord('c', 8)));
		addPiece(new Queen(Player.BLACK, new Coord('d', 8)));
		addPiece(new King(Player.BLACK, new Coord('e', 8)));
		addPiece(new Bishop(Player.BLACK, new Coord('f', 8)));
		addPiece(new Knight(Player.BLACK, new Coord('g', 8)));
		addPiece(new Rook(Player.BLACK, new Coord('h', 8)));
		
		initPawns();
	}
	
	private void initPawns() {
		for (int i = 0; i < 8; i++) {
			//ASCII offset
			int x = 97+i;
			addPiece(new Pawn(Player.WHITE, new Coord((char)x, 2)));
			addPiece(new Pawn(Player.BLACK, new Coord((char)x, 7)));
		}
	}
	
	void clearPieces() {
		setGridLinesVisible(false);
		getChildren().clear();
		setGridLinesVisible(true);
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
		Piece source = pieces[sourceCoord.getX()][sourceCoord.getY()];
		Piece target = pieces[targetCoord.getX()][targetCoord.getY()];
		System.out.println(source + " to " + target);
		
//		if (!(target instanceof Empty))
		getChildren().remove(target);
		getChildren().remove(source);
		source.setCoord(targetCoord);
		addPiece(source);
		addPiece(new Empty(sourceCoord));
	}
	
	//call only if move is special
	public void removePiece(Coord coord) {
		Piece piece = pieces[coord.getX()][coord.getY()];
		pieces[coord.getX()][coord.getY()] = null;
		getChildren().remove(piece);
		addPiece(new Empty(coord));
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
	
	public void setAttackersForAllPieces() {
		clearAttackers();
		for (Piece[] pp : pieces) {
			for (Piece p : pp) {
				for (Coord c : p.getAttackedCoords()) {
					getPiece(c).addAttacker(p);
				}
			}
		}
	}

	public boolean wouldKingBeInCheck(Move move) {
		
		Piece targetPiece = getPiece(move.getTarget());
		Piece sourcePiece = getPiece(move.getSource());
		
//		Piece[][] piecesCloned = pieces.clone();
//		Piece[][] piecesTemp = pieces;
//		
//		piecesCloned[move.getTarget().getX()][move.getTarget().getY()] = sourcePiece;
//		piecesCloned[move.getSource().getX()][move.getTarget().getY()] = new Empty(move.getSource());
		
//		pieces = piecesCloned;
		
		pieces[move.getTarget().getX()][move.getTarget().getY()] = sourcePiece;
		pieces[move.getSource().getX()][move.getSource().getY()] = new Empty(move.getSource());
		
		
		setAttackersForAllPieces();
		
		boolean result = kingInCheck(move.getPiece().getColor());
		
		pieces[move.getTarget().getX()][move.getTarget().getY()] = targetPiece;
		pieces[move.getSource().getX()][move.getSource().getY()] = sourcePiece;
		
		setAttackersForAllPieces();
		
		return result;
	}
	
	private boolean kingInCheck(Player c) {
		for (Piece p : getKing(c).getAttackers()) {
			if (!p.getColor().equals(c))
				return true;
		}
		return false;
	}
	
	private Piece getKing(Player c) {
		for (Piece p[] : pieces) {
			for (Piece piece : p) {
				if (piece instanceof King && piece.getColor() == c)
					return piece;
			}
		}
		throw new IllegalStateException("No King to be found");
	}
	
	public void clearAttackers() {
		for (Piece[] pp : pieces) {
			for (Piece p : pp) {
				p.clearAttackers();
			}
			
		}
	}
}
