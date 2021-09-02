package chessboard;

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
import util.GameLogic;

public class ChessBoard extends StackPane {
	
	private BoardPane boardPane;
	private PiecePane piecePane;
	
	public ChessBoard() {		
		boardPane = new BoardPane(this);
		piecePane = new PiecePane(this);
		initBoard();
		initPieces();
//		initPiecesFromFen("rn2k1r1/ppp1pp1p/3p2p1/5bn1/P7/2N2B2/1PPPPP2/2BNK1RR w Gkq - 4 11");
		getChildren().addAll(boardPane, piecePane);
		GameLogic.init(piecePane, boardPane);
//		GameLogic.initFromFen(piecePane, boardPane, "rn2k1r1/ppp1pp1p/3p2p1/5bn1/P7/2N2B2/1PPPPP2/2BNK1RR w Gkq - 4 11");
	}

	private void initBoard() {
		piecePane.setGridLinesVisible(true);
		boardPane.setGridLinesVisible(true);
		
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
	
	public void initPiecesFromFen(String fen) {
		try {
			
			piecePane.clearPieces();
			int x = 0;
			int y = 0;
			for (int i = 0; fen.charAt(i) != ' '; i++) {
				if (fen.charAt(i) == '/') {
					x = 0;
					y++;
					continue;
				}
				if (fen.charAt(i) <= '8' && fen.charAt(i) >= '0') {
					int temp = Integer.parseInt(String.valueOf(fen.charAt(i)));
					for (int j = 0; j < temp; j++) {
						piecePane.addPiece(newPieceFromFen(fen.charAt(i), new Coord(x,y)));
						x++;
					}
				} else {
					piecePane.addPiece(newPieceFromFen(fen.charAt(i), new Coord(x,y)));
					x++;
				}
			}
			piecePane.setGridLinesVisible(true);
			boardPane.setGridLinesVisible(true);
		} catch (Exception e) {
			System.out.println("Invalid FEN String");
		}
		
	}
	
	private Piece newPieceFromFen(char fen, Coord c) {
		switch(fen) {
		case 'b':
			return new Bishop(Player.BLACK, c, piecePane);
		case 'k':
			return new King(Player.BLACK, c, piecePane);
		case 'n':
			return new Knight(Player.BLACK, c, piecePane);
		case 'p':
			return new Pawn(Player.BLACK, c, piecePane);
		case 'q':
			return new Queen(Player.BLACK, c, piecePane);
		case 'r':
			return new Rook(Player.BLACK, c, piecePane);
		case 'B':
			return new Bishop(Player.WHITE, c, piecePane);
		case 'K':
			return new King(Player.WHITE, c, piecePane);
		case 'N':
			return new Knight(Player.WHITE, c, piecePane);
		case 'P':
			return new Pawn(Player.WHITE, c, piecePane);
		case 'Q':
			return new Queen(Player.WHITE, c, piecePane);
		case 'R':
			return new Rook(Player.WHITE, c, piecePane);
		default:
			return new Empty(c, piecePane);
		}
	}
	
	private void initPieces() {
		
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
	
	public PiecePane getPiecePane() {
		return piecePane;
	}
}
