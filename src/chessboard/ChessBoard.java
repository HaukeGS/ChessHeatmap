package chessboard;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import pieces.Bishop;
import pieces.Empty;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Piece.Player;
import pieces.Queen;
import pieces.Rook;
import util.Stockfish;

public class ChessBoard extends StackPane {
	
	private BoardPane boardPane;
	private PiecePane piecePane;
	private Stockfish stockfish;
	
	public ChessBoard() {		
		boardPane = new BoardPane(this);
		piecePane = new PiecePane(this);
		piecePane.setGridLinesVisible(true);
		boardPane.setGridLinesVisible(true);
		initBoard();
		initPieces();
		getChildren().addAll(boardPane, piecePane);
		boardPane.getSquare('e', 4).highlight();
//		stockfish = new Stockfish();
//		System.out.println(stockfish.startEngine());
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
		piecePane.addPiece(new Rook(Player.WHITE), 'a', 1);
		piecePane.addPiece(new Knight(Player.WHITE), 'b', 1);
		piecePane.addPiece(new Bishop(Player.WHITE), 'c', 1);
		piecePane.addPiece(new Queen(Player.WHITE), 'd', 1);
		piecePane.addPiece(new King(Player.WHITE), 'e', 1);
		piecePane.addPiece(new Bishop(Player.WHITE), 'f', 1);
		piecePane.addPiece(new Knight(Player.WHITE), 'g', 1);
		piecePane.addPiece(new Rook(Player.WHITE), 'h', 1);
		
		for (char i = 'a'; i <= 'h'; i++) {
			for (int j = 3; j <= 6; j++) {
				piecePane.addPiece(new Empty(), i, j);
			}
		}
				
		//init black pieces
		piecePane.addPiece(new Rook(Player.BLACK), 'a', 8);
		piecePane.addPiece(new Knight(Player.BLACK), 'b', 8);
		piecePane.addPiece(new Bishop(Player.BLACK), 'c', 8);
		piecePane.addPiece(new Queen(Player.BLACK), 'd', 8);
		piecePane.addPiece(new King(Player.BLACK), 'e', 8);
		piecePane.addPiece(new Bishop(Player.BLACK), 'f', 8);
		piecePane.addPiece(new Knight(Player.BLACK), 'g', 8);
		piecePane.addPiece(new Rook(Player.BLACK), 'h', 8);
		
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
			piecePane.addPiece(new Pawn(Player.WHITE), (char)x, 2);
			piecePane.addPiece(new Pawn(Player.BLACK), (char)x, 7);
		}
	}
	
	@Override
	public void resize(double width, double height) {
		super.resize(width, height);
		piecePane.resize(width, height);
		boardPane.resize(width, height);
	}
}
