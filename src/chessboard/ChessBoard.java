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
		GameLogic.init(piecePane);
	}

	private void initBoard() {
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Color c;
				if ((i + j) % 2 == 0)
					c = Color.WHITE;
				else
					c = Color.BROWN;
				boardPane.addSquare(i, j, c);
			}
		}
	}
	
	private void initPieces() {
//		ColumnConstraints colLabel = new ColumnConstraints();
//		colLabel.setPercentWidth(8);
//		boardPane.getColumnConstraints().add(colLabel);
		
		//init white pieces
		piecePane.addPiece(new Rook(Player.WHITE, 'a', 1), 'a', 1);
		piecePane.addPiece(new Knight(Player.WHITE, 'b', 1), 'b', 1);
		piecePane.addPiece(new Bishop(Player.WHITE, 'c', 1), 'c', 1);
		piecePane.addPiece(new Queen(Player.WHITE, 'd', 1), 'd', 1);
		piecePane.addPiece(new King(Player.WHITE, 'e', 1), 'e', 1);
		piecePane.addPiece(new Bishop(Player.WHITE, 'f', 1), 'f', 1);
		piecePane.addPiece(new Knight(Player.WHITE, 'g', 1), 'g', 1);
		piecePane.addPiece(new Rook(Player.WHITE, 'h', 1), 'h', 1);
		
		for (char i = 'a'; i <= 'h'; i++) {
			for (int j = 3; j <= 6; j++) {
				piecePane.addPiece(new Empty(i, j), i, j);
			}
		}
				
		//init black pieces
		piecePane.addPiece(new Rook(Player.BLACK, 'a', 8), 'a', 8);
		piecePane.addPiece(new Knight(Player.BLACK, 'b', 8), 'b', 8);
		piecePane.addPiece(new Bishop(Player.BLACK, 'c', 8), 'c', 8);
		piecePane.addPiece(new Queen(Player.BLACK, 'd', 8), 'd', 8);
		piecePane.addPiece(new King(Player.BLACK, 'e', 8), 'e', 8);
		piecePane.addPiece(new Bishop(Player.BLACK, 'f', 8), 'f', 8);
		piecePane.addPiece(new Knight(Player.BLACK, 'g', 8), 'g', 8);
		piecePane.addPiece(new Rook(Player.BLACK, 'h', 8), 'h', 8);
		
		initPawns();
			
	}
	
	private void initPawns() {
		for (int i = 0; i < 8; i++) {
			//ASCII offset
			int x = 97+i;
			piecePane.addPiece(new Pawn(Player.WHITE, (char)x, 2), (char)x, 2);
			piecePane.addPiece(new Pawn(Player.BLACK, (char)x, 7), (char)x, 7);
		}
	}
}
