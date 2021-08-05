package chessboard;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import pieces.Bishop;
import pieces.Empty;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece.Player;
import pieces.Queen;
import pieces.Rook;
import util.GameLogic;

public class ChessBoard extends StackPane {
	
	private BoardPane boardPane;
	private PiecePane piecePane;
	
	public ChessBoard() {		
		boardPane = new BoardPane(this);
		piecePane = new PiecePane(this);
		piecePane.setGridLinesVisible(true);
		boardPane.setGridLinesVisible(true);
		initBoard();
		initPieces();
		getChildren().addAll(boardPane, piecePane);
		GameLogic.init(piecePane, boardPane);
	}

	private void initBoard() {
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Color c;
				if ((i + j) % 2 == 0)
					c = Color.WHITE;
				else
					c = Color.BROWN;
				boardPane.addSquare(new Coord(i, j), c);
			}
		}
	}
	
	private void initPieces() {
//		ColumnConstraints colLabel = new ColumnConstraints();
//		colLabel.setPercentWidth(8);
//		boardPane.getColumnConstraints().add(colLabel);
		
		//init white pieces
		piecePane.addPiece(new Rook(Player.WHITE, new Coord('a', 1), piecePane));
		piecePane.addPiece(new Knight(Player.WHITE, new Coord('b', 1), piecePane));
		piecePane.addPiece(new Bishop(Player.WHITE, new Coord('c', 1), piecePane));
		piecePane.addPiece(new Queen(Player.WHITE, new Coord('d', 1), piecePane));
		piecePane.addPiece(new King(Player.WHITE, new Coord('e', 1), piecePane));
		piecePane.addPiece(new Bishop(Player.WHITE, new Coord('f', 1), piecePane));
		piecePane.addPiece(new Knight(Player.WHITE, new Coord('g', 1), piecePane));
		piecePane.addPiece(new Rook(Player.WHITE, new Coord('h', 1), piecePane));
		
		for (char i = 'a'; i <= 'h'; i++) {
			for (int j = 3; j <= 6; j++) {
				piecePane.addPiece(new Empty(new Coord(i, j), piecePane));
			}
		}
				
		//init black pieces
		piecePane.addPiece(new Rook(Player.BLACK, new Coord('a', 8), piecePane));
		piecePane.addPiece(new Knight(Player.BLACK, new Coord('b', 8), piecePane));
		piecePane.addPiece(new Bishop(Player.BLACK, new Coord('c', 8), piecePane));
		piecePane.addPiece(new Queen(Player.BLACK, new Coord('d', 8), piecePane));
		piecePane.addPiece(new King(Player.BLACK, new Coord('e', 8), piecePane));
		piecePane.addPiece(new Bishop(Player.BLACK, new Coord('f', 8), piecePane));
		piecePane.addPiece(new Knight(Player.BLACK, new Coord('g', 8), piecePane));
		piecePane.addPiece(new Rook(Player.BLACK, new Coord('h', 8), piecePane));
		
		initPawns();
			
	}
	
	private void initPawns() {
		for (int i = 0; i < 8; i++) {
			//ASCII offset
			int x = 97+i;
			piecePane.addPiece(new Pawn(Player.WHITE, new Coord((char)x, 2), piecePane));
			piecePane.addPiece(new Pawn(Player.BLACK, new Coord((char)x, 7), piecePane));
		}
	}
}
