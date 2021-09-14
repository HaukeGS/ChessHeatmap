package util;

import java.util.List;

import chessboard.BoardPane;
import chessboard.Coord;
import chessboard.Move;
import chessboard.PiecePane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pieces.Bishop;
import pieces.Empty;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Piece.Player;
import pieces.Queen;
import pieces.Rook;

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
	private static boolean colored;
	private static boolean highlightingAttackers;
	private static ObservableList<Move> whitesMoves;
	private static ObservableList<Move> blacksMoves;
	private static ObservableList<Move> moves;

	private static enum CastleRights {
		BOTH, KINGSIDE, QUEENSIDE, NONE
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
		colored = false;
		highlightingAttackers = false;
		whitesMoves = FXCollections.observableArrayList();
		blacksMoves = FXCollections.observableArrayList();
		moves = FXCollections.observableArrayList();
		updateBoard();
//			System.out.println(stockfish.startEngine());
	}

	public static void initFromFen(PiecePane pP, BoardPane bP, String fen) {
		piecePane = pP;
		boardPane = bP;
		if (fen.contains("w"))
			toMove = Player.WHITE;
		else
			toMove = Player.BLACK;
	}

	public static void initFromFen(String fen) {
		String backup = generateFenString();
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
						piecePane.addPiece(newPieceFromFen(fen.charAt(i), new Coord(x, y)));
						x++;
					}
				} else {
					piecePane.addPiece(newPieceFromFen(fen.charAt(i), new Coord(x, y)));
					x++;
				}
			}
			piecePane.setGridLinesVisible(true);
			boardPane.setGridLinesVisible(true);
			updateBoard();
		} catch (Exception e) {
			System.out.println("Invalid FEN String");
			initBackupFen(backup);
		}
	}

	private static void initBackupFen(String fen) {
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
						piecePane.addPiece(newPieceFromFen(fen.charAt(i), new Coord(x, y)));
						x++;
					}
				} else {
					piecePane.addPiece(newPieceFromFen(fen.charAt(i), new Coord(x, y)));
					x++;
				}
			}
			piecePane.setGridLinesVisible(true);
			boardPane.setGridLinesVisible(true);
		} catch (Exception e) {
			System.out.println("Invalid FEN String");
		}

	}

	private static Piece newPieceFromFen(char fen, Coord c) {
		switch (fen) {
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

	private static void alternateToMove() {
		if (toMove == Player.WHITE)
			toMove = Player.BLACK;
		else
			toMove = Player.WHITE;
//			piecePane.clearAttackers();
	}

	public static void updateBoard() {
		deselect();
		recolorSquares();
		highlightKingInCheck();
	}

	public static void recolorSquares() {
		piecePane.setAttackersForAllPieces();
		boardPane.recolorSquares(piecePane.attackingMatrix(Player.WHITE), piecePane.attackingMatrix(Player.BLACK),
				colored);
	}

	public static Piece getSelected() {
		return selected;
	}

	public static void setSelected(Piece piece) {
		if (piece.getColor() != toMove)
			return;
		selected = piece;
		highlightMovables(piece);
	}

	private static void highlightMovables(Piece piece) {
		for (Coord c : piece.getMovableCoords()) {
			if (isMoveLegal(new Move(piece.getCoord(), c, piece)))
				boardPane.highlightMovable(c);
		}

	}

	private static void dehighlightMovablesAll() {
		boardPane.dehighlightMovableAll();

	}

	public static void deselect() {
		selected = null;
		dehighlightMovablesAll();
	}

	public static void movePiece(Move move) {
		if (selected != move.getPiece())
			throw new IllegalStateException("Crashed because you tried to move a piece without selecting one");
		performSpecialMove(move);
		if (!(piecePane.getPiece(move.getTarget()) instanceof Empty))
			move.setTakes(true);
		piecePane.movePiece(move);
		updateBoard();
		updateCastleRights(move);
		alternateToMove();
		move.setFen(generateFenString());
		moves.add(move);
		if (move.getPiece().getColor() == Player.BLACK)
			blacksMoves.add(move);
		else
			whitesMoves.add(move);
	}

	public static Player getOpponent(Move move) {
		if (move.getPiece().getColor() == Player.BLACK)
			return Player.WHITE;
		return Player.BLACK;
	}

	private static void performSpecialMove(Move move) {
		Player opponent = getOpponent(move);
		if (piecePane.isKingInCheck(opponent))
			move.setCheck(true);
		if (isMoveKingSideCastles(move)) {
			piecePane.movePiece(new Move(new Coord(move.getTarget().getX() + 1, move.getTarget().getY()),
					new Coord(move.getTarget().getX() - 1, move.getTarget().getY()),
					piecePane.getPiece(new Coord(move.getTarget().getX() + 1, move.getTarget().getY()))));
			move.setKingSideCastles(true);
		} else if (isMoveQueensideCastles(move)) {
			piecePane.movePiece(new Move(new Coord(move.getTarget().getX() - 2, move.getTarget().getY()),
					new Coord(move.getTarget().getX() + 1, move.getTarget().getY()),
					piecePane.getPiece(new Coord(move.getTarget().getX() - 2, move.getTarget().getY()))));
			move.setQueenSideCastles(true);
		}
	}

	public static boolean isMoveKingSideCastles(Move move) {
		if (move.getPiece() instanceof King && (move.getSource().getX() - move.getTarget().getX()) == -2)
			return true;
		return false;
	}

	public static boolean isMoveQueensideCastles(Move move) {
		if (move.getPiece() instanceof King && (move.getSource().getX() - move.getTarget().getX()) == 2)
			return true;
		return false;
	}

	public static boolean isEnPessant(Move move) {
		if (move.getPiece() instanceof Pawn && move.getSource().getX() != move.getTarget().getX()
				&& piecePane.getPiece(move.getTarget()) instanceof Empty)
			return true;
		return false;
	}

	public static boolean isMovePromotion(Move move) {
		if ((selected instanceof Pawn) && ((move.getTarget().getY() == 0) || (move.getTarget().getY() == 7)))
			return true;
		return false;
	}

	private static void updateCastleRights(Move move) {
		if (move.getPiece() instanceof King && move.getPiece().getColor() == Player.WHITE
				&& move.getSource().equals(new Coord('e', 1))) {
			whiteCastleRights = CastleRights.NONE;
		}
		if (move.getPiece() instanceof Rook && move.getPiece().getColor() == Player.WHITE
				&& move.getSource().equals(new Coord('a', 1))) {
			if (whiteCastleRights == CastleRights.BOTH)
				whiteCastleRights = CastleRights.KINGSIDE;
			if (whiteCastleRights == CastleRights.QUEENSIDE)
				whiteCastleRights = CastleRights.NONE;
		}
		if (move.getPiece() instanceof Rook && move.getPiece().getColor() == Player.WHITE
				&& move.getSource().equals(new Coord('h', 1))) {
			if (whiteCastleRights == CastleRights.BOTH)
				whiteCastleRights = CastleRights.QUEENSIDE;
			if (whiteCastleRights == CastleRights.KINGSIDE)
				whiteCastleRights = CastleRights.NONE;
		}

		if (move.getPiece() instanceof King && move.getPiece().getColor() == Player.BLACK
				&& move.getSource().equals(new Coord('e', 8))) {
			blackCastleRights = CastleRights.NONE;
		}
		if (move.getPiece() instanceof Rook && move.getPiece().getColor() == Player.BLACK
				&& move.getSource().equals(new Coord('a', 8))) {
			if (blackCastleRights == CastleRights.BOTH)
				blackCastleRights = CastleRights.KINGSIDE;
			if (blackCastleRights == CastleRights.QUEENSIDE)
				blackCastleRights = CastleRights.NONE;
		}
		if (move.getPiece() instanceof Rook && move.getPiece().getColor() == Player.BLACK
				&& move.getSource().equals(new Coord('h', 8))) {
			if (blackCastleRights == CastleRights.BOTH)
				blackCastleRights = CastleRights.QUEENSIDE;
			if (blackCastleRights == CastleRights.KINGSIDE)
				blackCastleRights = CastleRights.NONE;
		}

	}

	private static void castlingRooks(Coord source, Coord target) {
//		piecePane.movePiece(source, target);
	}

	private static void isMoveSpecial(Move move) {
		Coord source = move.getSource();
		Coord target = move.getTarget();
		if ((selected instanceof Pawn) && (source.getX() != target.getX())
				&& (piecePane.getPiece(target) instanceof Empty)) {
			// en passant move
			System.out.println("en passant");
			if (toMove.equals(Player.WHITE)) {
				Coord toRemove = new Coord(target.getX(), target.getY() + 1);
				piecePane.removePiece(toRemove);
			} else {
				Coord toRemove = new Coord(target.getX(), target.getY() - 1);
				piecePane.removePiece(toRemove);
			}
		} else if ((selected instanceof King) && (Math.abs(target.getX() - source.getX()) >= 2)) {
			// castling move
			System.out.println("castling");
			if (target.getX() == 2) { // Queenside castle
				castlingRooks(new Coord(target.getX() - 2, target.getY()), new Coord(target.getX() + 1, target.getY()));
			} else if (target.getX() == 6) { // Kingside castle
				castlingRooks(new Coord(target.getX() + 1, target.getY()), new Coord(target.getX() - 1, target.getY()));
			} else {
				throw new RuntimeException("Something went wrong during castling. King is at" + target.toChessString()
						+ ", where he is not supposed to be.");
			}
		} else if ((selected instanceof Pawn) && ((target.getY() == 0) || (target.getY() == 7))) {
			// promotion

			System.out.println("promotion");
		}
	}

	public static String generateFenStringFlippedSides() {
		Piece[][] pieces = piecePane.getPieces();

		String result = "";

		// pieces
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

		// moveright
		if (toMove == Player.WHITE)
			result += " b";
		else
			result += " w";

		// white castle rights
		if (whiteCastleRights == CastleRights.BOTH)
			result += " KQ";
		else if (whiteCastleRights == CastleRights.KINGSIDE)
			result += " K";
		else if (whiteCastleRights == CastleRights.QUEENSIDE)
			result += " Q";
		else
			result += " ";

		// black castle rights
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

		// possible en passant
		// ToDo
		result += " -";

		// empty move count
		result += " " + emptyMoveCount;

		// move count
		result += " " + (moveCount + 1);

//			System.out.println(result);
		return result;

	}

	public static String generateFenString() {
		Piece[][] pieces = piecePane.getPieces();
		System.out.println(pieces);

		String result = "";

		// pieces
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

		// moveright
		if (toMove == Player.WHITE)
			result += " w";
		else
			result += " b";

		// white castle rights
		if (whiteCastleRights == CastleRights.BOTH)
			result += " KQ";
		else if (whiteCastleRights == CastleRights.KINGSIDE)
			result += " K";
		else if (whiteCastleRights == CastleRights.QUEENSIDE)
			result += " Q";
		else
			result += " ";

		// black castle rights
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

		// possible en passant
		// ToDo
		result += " -";

		// empty move count
		result += " " + emptyMoveCount;

		// move count
		result += " " + (moveCount + 1);

//			System.out.println(result);
		return result;
	}

	public static void setColored(boolean c) {
		colored = c;
	}

	public static void resetBoard() {
		toMove = Player.WHITE;
		whiteCastleRights = CastleRights.BOTH;
		blackCastleRights = CastleRights.BOTH;
		emptyMoveCount = 0;
		moveCount = 0;
		piecePane.reset();
		clearMoveLists();
		updateBoard();
	}

	private static void clearMoveLists() {
		whitesMoves.clear();
		blacksMoves.clear();
		moves.clear();
	}

	public static boolean isMoveLegal(Move move) {
		Piece piece = move.getPiece();
//		if (piece.getColor() != toMove) { // right color?
//			System.out.println("Wrong Color");
//			return false;
//		}
		if (!piece.canMoveTo(move.getTarget())) { // can the piece move to the target square?
			System.out.println("Not attacking Square");
			System.out.println(piece.getAttackedCoords());
			System.out.println(move.getTarget());
			return false;
		}
		if (piecePane.getPiece(move.getTarget()).getColor() == piece.getColor()) { // is the target square occupied by
																					// own piece?
			System.out.println("Occupied by own piece");
			return false;
		}
		if (piecePane.wouldKingBeInCheck(move)) {
			System.out.println("King would be in check");
			return false;
		}

		return true;
	}

	public static PiecePane getPiecePane() {
		return piecePane;
	}

	public static void highlightAttackers(List<Piece> list) {
		for (Piece p : list) {
			boardPane.highlightSquareAsAttacker(p.getCoord());
			highlightingAttackers = true;
		}
	}

	private static void highlightKingInCheck() {
		if (piecePane.isKingInCheckmate(Player.BLACK)) {
			boardPane.highlightKingInCheckmate(piecePane.getKing(Player.BLACK).getCoord());
			return;
		} else {
			boardPane.dehighlightKingInCheck(piecePane.getKing(Player.BLACK).getCoord());
		}
		if (piecePane.isKingInCheckmate(Player.WHITE)) {
			boardPane.highlightKingInCheckmate(piecePane.getKing(Player.WHITE).getCoord());
			return;
		} else {
			boardPane.dehighlightKingInCheck(piecePane.getKing(Player.WHITE).getCoord());
		}
		if (piecePane.isKingInCheck(Player.BLACK)) {
			boardPane.highlightKingInCheck(piecePane.getKing(Player.BLACK).getCoord());
		} else {
			boardPane.dehighlightKingInCheck(piecePane.getKing(Player.BLACK).getCoord());
		}
		if (piecePane.isKingInCheck(Player.WHITE)) {
			boardPane.highlightKingInCheck(piecePane.getKing(Player.WHITE).getCoord());
		} else {
			boardPane.dehighlightKingInCheck(piecePane.getKing(Player.WHITE).getCoord());
		}
	}

	public static void dehighlightAttackers() {
		boardPane.dehighlightAttackersSquares();
		highlightingAttackers = false;
	}

	public static boolean getHighlightingAttackers() {
		return highlightingAttackers;
	}

	public static boolean canCastleQueenSide(Player player) {
		if ((player == Player.BLACK)
				&& (blackCastleRights == CastleRights.BOTH || blackCastleRights == CastleRights.QUEENSIDE)) {
			if (piecePane.canBlackCastleQueenSide())
				return true;
		}
		if ((player == Player.WHITE)
				&& (whiteCastleRights == CastleRights.BOTH || whiteCastleRights == CastleRights.QUEENSIDE)) {
			if (piecePane.canWhiteCastleQueenSide())
				return true;
		}
		return false;
	}

	public static boolean canCastleKingSide(Player player) {
		if ((player == Player.BLACK)
				&& (blackCastleRights == CastleRights.BOTH || blackCastleRights == CastleRights.KINGSIDE)) {
			if (piecePane.canBlackCastleKingSide())
				return true;
		}
		if ((player == Player.WHITE)
				&& (whiteCastleRights == CastleRights.BOTH || whiteCastleRights == CastleRights.KINGSIDE)) {
			if (piecePane.canWhiteCastleKingSide())
				return true;
		}
		return false;
	}

	public static ObservableList<Move> getBlackMoveList() {
		return blacksMoves;
	}

	public static ObservableList<Move> getWhiteMoveList() {
		return whitesMoves;
	}

	public static ObservableList<Move> getMoveList() {
		return moves;
	}
}
