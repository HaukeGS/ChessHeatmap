package chessboard;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pieces.Bishop;
import pieces.EmptySquare;
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
		System.out.println(piecePane.getWidth());
		System.out.println(this.getWidth());
	}

	private void initBoard() {
//		for (int i = 0; i < 8; i++) {
//			ColumnConstraints col = new ColumnConstraints();
//			RowConstraints row = new RowConstraints();
//			col.setPercentWidth(8);
//			row.setPercentHeight(8);
//			boardPane.getColumnConstraints().add(col);
//			boardPane.getRowConstraints().add(row);
//		}
//		RowConstraints rowLabel = new RowConstraints();
//		rowLabel.setPercentHeight(8);
//		boardPane.getRowConstraints().add(rowLabel);
		
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Color c;
				if ((i + j) % 2 == 0)
					c = Color.WHITE;
				else
					c = Color.BROWN;
//				boardPane.add(new Square(i, j, c), i, j);
				Rectangle square = new Rectangle();
				square.setFill(c);
				boardPane.add(square, i, j);
                square.widthProperty().bind(this.widthProperty().divide(8));
                square.heightProperty().bind(this.heightProperty().divide(8));
			}
		}
//		for (int i = 0; i < 8; i++) {
//			boardPane.add(new Label(Integer.toString(8-i)), 8, i);
//			boardPane.add(new Label(Character.toString('a' + i)), i, 8);
//		}
	}
	
	private void initPieces() {
//		piecePane.setMinHeight(this.getHeight());
//		piecePane.setMinWidth(this.getWidth());
//		piecePane.prefHeightProperty().bind(this.heightProperty());
//		piecePane.prefWidthProperty().bind(this.widthProperty());

		for (int i = 0; i < 8; i++) {
			ColumnConstraints col = new ColumnConstraints();
			RowConstraints row = new RowConstraints();
			col.setPercentWidth(12.5);
			row.setPercentHeight(12.5);
			piecePane.getColumnConstraints().add(col);
			piecePane.getRowConstraints().add(row);
		}
		ColumnConstraints colLabel = new ColumnConstraints();
//		colLabel.setPercentWidth(8);
//		boardPane.getColumnConstraints().add(colLabel);
		
		//init white pieces
		Piece Rook_1 = new Rook(Player.WHITE, this);
		piecePane.add(Rook_1, 'a', 1, 1, 1);
		piecePane.add(new Knight(Player.WHITE, this), 'b', 1, 1, 1);
		piecePane.add(new Bishop(Player.WHITE, this), 'c', 1, 1, 1);
		piecePane.add(new Queen(Player.WHITE, this), 'd', 1, 1, 1);
		piecePane.add(new King(Player.WHITE, this), 'e', 1, 1, 1);
		piecePane.add(new Bishop(Player.WHITE, this), 'f', 1, 1, 1);
		piecePane.add(new Knight(Player.WHITE, this), 'g', 1, 1, 1);
		piecePane.add(new Rook(Player.WHITE, this), 'h', 1, 1, 1);
		
//		for (int i = 3; i <= 6; i++) {
//			for (int j = 0; j < 8; j++) {
//				Rectangle empty = new Rectangle();
//				piecePane.add(empty, j, i);
//            	empty.widthProperty().bind(this.widthProperty().divide(8));
//            	empty.heightProperty().bind(this.heightProperty().divide(8));
//			}
//		}
		
		
				
		//init black pieces
		piecePane.add(new Rook(Player.BLACK, this), 'a', 8, 1, 1);
		piecePane.add(new Knight(Player.BLACK, this), 'b', 8, 1, 1);
		piecePane.add(new Bishop(Player.BLACK, this), 'c', 8, 1, 1);
		piecePane.add(new Queen(Player.BLACK, this), 'd', 8, 1, 1);
		piecePane.add(new King(Player.BLACK, this), 'e', 8, 1, 1);
		piecePane.add(new Bishop(Player.BLACK, this), 'f', 8, 1, 1);
		piecePane.add(new Knight(Player.BLACK, this), 'g', 8, 1, 1);
		piecePane.add(new Rook(Player.BLACK, this), 'h', 8, 1, 1);
		
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
			piecePane.add(new Pawn(Player.WHITE, this), (char)x, 2, 1, 1);
			piecePane.add(new Pawn(Player.BLACK, this), (char)x, 7, 1, 1);
		}
	}
	
	@Override
	public void resize(double width, double height) {
		super.resize(width, height);
		piecePane.resize(width, height);
	}
}
