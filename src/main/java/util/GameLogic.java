package util;

import chessboard.BoardPane;
import chessboard.Coord;
import chessboard.PiecePane;
import pieces.Empty;
import pieces.King;
import pieces.Pawn;
import pieces.Piece;
import pieces.Piece.Player;

public final class GameLogic {

		private static Stockfish stockfish;
		private static PiecePane piecePane;
		private static BoardPane boardPane;
		private static Player toMove;
		private static CastleRights whiteCastleRights;
		private static CastleRights blackCastleRights;
		private static int emptyMoveCount;
		private static int moveCount;
		private static Piece selected;
		private static RulesManager rulesManager;
		
		private static enum CastleRights {
			BOTH,
			KINGSIDE,
			QUEENSIDE,
			NONE
		}
		
		public static void init(PiecePane pP, BoardPane bP) {
			piecePane = pP;
			boardPane = bP;
//			stockfish = new Stockfish();
			toMove = Player.WHITE;
			whiteCastleRights = CastleRights.BOTH;
			blackCastleRights = CastleRights.BOTH;
			emptyMoveCount = 0;
			moveCount = 0;
			rulesManager = new RulesManager();
			rulesManager.getMoves();
//			System.out.println(stockfish.startEngine());
		}
		
		private static void alternateToMove() {
			if (toMove == Player.WHITE)
				toMove = Player.BLACK;
			else
				toMove = Player.WHITE;
			boardPane.recolorSquares(piecePane.attackingMatrix(Player.WHITE), piecePane.attackingMatrix(Player.BLACK));
		}
		
		public static Piece getSelected() {
			return selected;
		}
		
		public static void setSelected(Piece piece) {
			selected = piece;
		}
		
		public static void deselect() {
			selected = null;
		}
		
		public static boolean isMoveLegal(Coord source, Coord target) {
			try {
				return rulesManager.isLegal(source, target);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return true;
		}
		
		public static boolean isMoveLegal(Piece target) {
			if (selected == null)
				return false;
			return isMoveLegal(selected.getCoord(), target.getCoord());
		}
		
		public static void movePiece(Piece target) {
			if (selected == null)
				throw new IllegalStateException("Crashed because you tried to move a piece without selecting one");
			isMoveSpecial(selected.getCoord(), target.getCoord());
			rulesManager.movePiece(selected.getCoord(), target.getCoord());
			piecePane.movePiece(selected.getCoord(), target.getCoord());
			deselect();
			alternateToMove();
		}
		
		private static void castlingRooks (Coord source, Coord target) {
			piecePane.movePiece(source, target);
		}
		
		private static void isMoveSpecial(Coord source, Coord target) {
			if ((selected instanceof Pawn) && (source.getX() != target.getX()) && (piecePane.getPiece(target) instanceof Empty)) {
				//en passant move
				System.out.println("en passant");
				if (toMove.equals(Player.WHITE)) {
					Coord toRemove = new Coord(target.getX(), target.getY() + 1);
					piecePane.removePiece(toRemove);				
				} else {
					Coord toRemove = new Coord(target.getX(), target.getY() - 1);
					piecePane.removePiece(toRemove);				
				}
			} else if ((selected instanceof King) && (Math.abs(target.getX() - source.getX()) >= 2)) {
				//castling move
				System.out.println("castling");
				if (target.getX() == 2) { //Queenside castle
					castlingRooks(new Coord(target.getX()-2, target.getY()), new Coord(target.getX()+1, target.getY()));
				} else if (target.getX() == 6) { //Kingside castle
					castlingRooks(new Coord(target.getX()+1, target.getY()), new Coord(target.getX()-1, target.getY()));
				} else {
					throw new RuntimeException("Something went wrong during castling. King is at" + target.toChessString() + ", where he is not supposed to be.");
				}
			} else if ((selected instanceof Pawn) && ((target.getY() == 0) || (target.getY() == 7))) {
				//promotion
				System.out.println("promotion");
			}
		}
		
		public static String generateFenStringFlippedSides() {
			Piece[][] pieces = piecePane.getPieces();
			
			String result = "";
			
			//pieces
			int emptySquareCount = 0;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					char nextChar = pieces[j][i].getFenChar();
					if (nextChar == '1') {
						emptySquareCount++;
					} else if (emptySquareCount > 0) {
						result += emptySquareCount;
						emptySquareCount = 0;
						result += nextChar;
					} else {
						result += nextChar;
					}
				}
				if (emptySquareCount > 0)
					result += emptySquareCount;
					emptySquareCount = 0;
				result += "/";
			}
			
			//moveright
			if (toMove == Player.WHITE)
				result += " b";
			else 
				result += " w";
			
			//white castle rights
			if (whiteCastleRights == CastleRights.BOTH)
				result += " KQ";
			else if (whiteCastleRights == CastleRights.KINGSIDE)
				result += " K";
			else if (whiteCastleRights == CastleRights.QUEENSIDE)
				result += " Q";
			else 
				result += " ";
			
			//black castle rights
			if (blackCastleRights == CastleRights.BOTH)
				result += "kq";
			else if (blackCastleRights == CastleRights.KINGSIDE)
				result += "k";
			else if (blackCastleRights == CastleRights.QUEENSIDE)
				result += "q";
			else if (whiteCastleRights == CastleRights.NONE)
				result += "-";
			else 
				result = "";
				
			//possible en passant
			//ToDo
			result += " -";
			
			//empty move count
			result += " " + emptyMoveCount;
			
			//move count
			result += " " + (moveCount+1);
			
//			System.out.println(result);
			return result;
			
		}
		
		public static String generateFenString() {
			Piece[][] pieces = piecePane.getPieces();
			
			String result = "";
			
			//pieces
			int emptySquareCount = 0;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					char nextChar = pieces[j][i].getFenChar();
					if (nextChar == '1') {
						emptySquareCount++;
					} else if (emptySquareCount > 0) {
						result += emptySquareCount;
						emptySquareCount = 0;
						result += nextChar;
					} else {
						result += nextChar;
					}
				}
				if (emptySquareCount > 0)
					result += emptySquareCount;
					emptySquareCount = 0;
				result += "/";
			}
			
			//moveright
			if (toMove == Player.WHITE)
				result += " w";
			else 
				result += " b";
			
			//white castle rights
			if (whiteCastleRights == CastleRights.BOTH)
				result += " KQ";
			else if (whiteCastleRights == CastleRights.KINGSIDE)
				result += " K";
			else if (whiteCastleRights == CastleRights.QUEENSIDE)
				result += " Q";
			else 
				result += " ";
			
			//black castle rights
			if (blackCastleRights == CastleRights.BOTH)
				result += "kq";
			else if (blackCastleRights == CastleRights.KINGSIDE)
				result += "k";
			else if (blackCastleRights == CastleRights.QUEENSIDE)
				result += "q";
			else if (whiteCastleRights == CastleRights.NONE)
				result += "-";
			else 
				result = "";
				
			//possible en passant
			//ToDo
			result += " -";
			
			//empty move count
			result += " " + emptyMoveCount;
			
			//move count
			result += " " + (moveCount+1);
			
//			System.out.println(result);
			return result;
		}
}
