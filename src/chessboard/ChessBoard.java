package chessboard;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece.Player;
import pieces.Queen;
import pieces.Rook;

public class ChessBoard extends GridPane {
	
	public ChessBoard() {
		setGridLinesVisible(true);
		initBoard();
		initPieces();
	}

	private void initBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Color c;
				if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1))
					c = Color.WHITE;
				else
					c = Color.BROWN;{
				this.add(new Square(i, j, c), i, j);
				//this.add(new Bishop(Player.WHITE), i, i);
			}
		}
	}
		for (int i = 0; i < 8; i++) {
			this.add(new Label(Integer.toString(8-i)), 8, i);
			this.add(new Label(Character.toString('a' + i)), i, 8);
		}
	}
	
	private void initPieces() {
		//init white pieces
		this.add(new Rook(Player.WHITE), 'a', 1);
		this.add(new Knight(Player.WHITE), 'b', 1);
		this.add(new Bishop(Player.WHITE), 'c', 1);
		this.add(new Queen(Player.WHITE), 'd', 1);
		this.add(new King(Player.WHITE), 'e', 1);
		this.add(new Bishop(Player.WHITE), 'f', 1);
		this.add(new Knight(Player.WHITE), 'g', 1);
		this.add(new Rook(Player.WHITE), 'h', 1);
				
		//init black pieces
		this.add(new Rook(Player.BLACK), 'a', 8);
		this.add(new Knight(Player.BLACK), 'b', 8);
		this.add(new Bishop(Player.BLACK), 'c', 8);
		this.add(new Queen(Player.BLACK), 'd', 8);
		this.add(new King(Player.BLACK), 'e', 8);
		this.add(new Bishop(Player.BLACK), 'f', 8);
		this.add(new Knight(Player.BLACK), 'g', 8);
		this.add(new Rook(Player.BLACK), 'h', 8);
		
		initPawns();
	}
	
	private void initPawns() {
		for (int i = 0; i < 8; i++) {
			//ASCII offset
			int x = 97+i;
			this.add(new Pawn(Player.WHITE), (char)x, 2);
			this.add(new Pawn(Player.BLACK), (char)x, 7);
		}
	}
	
	public Square getSquare(int x, int y) {
		Square result = null;
		ObservableList<Node> childrens = getChildren();
		for (Node n : childrens) {
			if (getRowIndex(n) == x && getColumnIndex(n) == y)
				if (n instanceof Square) {
					result = (Square)n;
					return result;
				}
		}
		return result;
	}
	
	//add method for adding by chess coordinates rather than gridpane coordinates
	private void add(Node child, char c, int y) {
		int x = (int) c;
		//ASCII offset
		x -= 97;
		//boardwidth offset
		y = 8-y;
		this.add(child, x, y);
	}
}
