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
				Coord coord = new Coord(i, j);
				boardPane.addSquare(coord, c);
				boardPane.addHighlight(coord);
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
			return new Bishop(Player.BLACK, c);
		case 'k':
			return new King(Player.BLACK, c);
		case 'n':
			return new Knight(Player.BLACK, c);
		case 'p':
			return new Pawn(Player.BLACK, c);
		case 'q':
			return new Queen(Player.BLACK, c);
		case 'r':
			return new Rook(Player.BLACK, c);
		case 'B':
			return new Bishop(Player.WHITE, c);
		case 'K':
			return new King(Player.WHITE, c);
		case 'N':
			return new Knight(Player.WHITE, c);
		case 'P':
			return new Pawn(Player.WHITE, c);
		case 'Q':
			return new Queen(Player.WHITE, c);
		case 'R':
			return new Rook(Player.WHITE, c);
		default:
			return new Empty(c);
		}
	}
	
	public PiecePane getPiecePane() {
		return piecePane;
	}
}
