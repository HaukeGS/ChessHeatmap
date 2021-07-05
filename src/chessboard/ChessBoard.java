package chessboard;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Piece.Player;
import pieces.Queen;
import pieces.Rook;

public class ChessBoard extends StackPane {
	
	private BoardPane boardPane;
	private PiecePane piecePane;
	
	public ChessBoard() {		
		boardPane = new BoardPane();
		piecePane = new PiecePane();
		piecePane.setGridLinesVisible(true);
		boardPane.setGridLinesVisible(true);
		initBoard();
		initPieces();
		getChildren().addAll(boardPane, piecePane);
		System.out.println(piecePane.getPiece('a', 1).toString());
	}

	private void initBoard() {
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Color c;
				if ((i + j) % 2 == 0)
					c = Color.WHITE;
				else
					c = Color.BROWN;
				boardPane.addSquare(i, j, c, this);
			}
		}
//		for (int i = 0; i < 8; i++) {
//			boardPane.add(new Label(Integer.toString(8-i)), 8, i);
//			boardPane.add(new Label(Character.toString('a' + i)), i, 8);
//		}
	}
	
	private void initPieces() {
//		ColumnConstraints colLabel = new ColumnConstraints();
//		colLabel.setPercentWidth(8);
//		boardPane.getColumnConstraints().add(colLabel);
		
		//init white pieces
		piecePane.addPiece(new Rook(Player.WHITE, this), 'a', 1);
		piecePane.addPiece(new Knight(Player.WHITE, this), 'b', 1);
		piecePane.addPiece(new Bishop(Player.WHITE, this), 'c', 1);
		piecePane.addPiece(new Queen(Player.WHITE, this), 'd', 1);
		piecePane.addPiece(new King(Player.WHITE, this), 'e', 1);
		piecePane.addPiece(new Bishop(Player.WHITE, this), 'f', 1);
		piecePane.addPiece(new Knight(Player.WHITE, this), 'g', 1);
		piecePane.addPiece(new Rook(Player.WHITE, this), 'h', 1);
				
		//init black pieces
		piecePane.addPiece(new Rook(Player.BLACK, this), 'a', 8);
		piecePane.addPiece(new Knight(Player.BLACK, this), 'b', 8);
		piecePane.addPiece(new Bishop(Player.BLACK, this), 'c', 8);
		piecePane.addPiece(new Queen(Player.BLACK, this), 'd', 8);
		piecePane.addPiece(new King(Player.BLACK, this), 'e', 8);
		piecePane.addPiece(new Bishop(Player.BLACK, this), 'f', 8);
		piecePane.addPiece(new Knight(Player.BLACK, this), 'g', 8);
		piecePane.addPiece(new Rook(Player.BLACK, this), 'h', 8);
		
		initPawns();
			
//		centerChildren();
	}
	
	private void centerChildren() {
		ObservableList<Node> children = piecePane.getChildren();
		for (Node n : children) {
			if (!(n instanceof Piece))
				throw new IllegalStateException("Piece Pane has non Piece child");
			n = (Piece)n;
						
		}
	}
	
	private void initPawns() {
		for (int i = 0; i < 8; i++) {
			//ASCII offset
			int x = 97+i;
			piecePane.addPiece(new Pawn(Player.WHITE, this), (char)x, 2);
			piecePane.addPiece(new Pawn(Player.BLACK, this), (char)x, 7);
		}
	}
	
	@Override
	public void resize(double width, double height) {
		super.resize(width, height);
		piecePane.resize(width, height);
		boardPane.resize(width, height);
	}
}
