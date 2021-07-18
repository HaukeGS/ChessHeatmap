package util;

import org.alcibiade.chess.model.ChessBoardModel;
import org.alcibiade.chess.model.ChessMovePath;
import org.alcibiade.chess.model.ChessPosition;
import org.alcibiade.chess.rules.ChessRules;
import org.alcibiade.chess.rules.ChessRulesImpl;

import chessboard.PiecePane;
import pieces.Piece;
import pieces.Piece.Player;

public final class GameLogic {

		private static Stockfish stockfish;
		private static PiecePane piecePane;
		private static Player toMove;
		private static CastleRights whiteCastleRights;
		private static CastleRights blackCastleRights;
		private static int emptyMoveCount;
		private static int moveCount;
		private static Piece selected;
		private static ChessBoardModel board;
		private static ChessRules rules;
		private static ChessPosition position;
		
		private static enum CastleRights {
			BOTH,
			KINGSIDE,
			QUEENSIDE,
			NONE
		}
		
		public static void init(PiecePane pP) {
			piecePane = pP;
			stockfish = new Stockfish();
			toMove = Player.WHITE;
			whiteCastleRights = CastleRights.BOTH;
			blackCastleRights = CastleRights.BOTH;
			emptyMoveCount = 0;
			moveCount = 0;
			System.out.println(stockfish.startEngine());
		}
		
		private static void alternateToMove() {
			if (toMove == Player.WHITE)
				toMove = Player.BLACK;
			else
				toMove = Player.WHITE;
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
		
		public static boolean isMoveLegal(char sourceX, int sourceY, char targetX, int targetY) {
			try {
				System.out.println(stockfish.getLegalMoves("8/6pk/8/1R5p/3K3P/8/6r1/8 b - - 0 42"));
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			return true;
		}
		
		public static boolean isMoveLegal(Piece target) {
			if (selected == null)
				return false;
			return isMoveLegal(selected.getChessX(), selected.getChessY(), target.getChessX(), target.getChessY());
		}
		
		public static void movePiece(Piece target) {
			if (selected == null)
				throw new IllegalStateException("Crashed because you tried to move a piece without selecting one");
			piecePane.movePiece(selected.getChessX(), selected.getChessY(), target.getChessX(), target.getChessY());
			deselect();
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

		public static void init() {
			rules = new ChessRulesImpl();
			position = rules.getInitialPosition();
			for (ChessMovePath path : rules.getAvailableMoves(position)) {
			}
		}
}
