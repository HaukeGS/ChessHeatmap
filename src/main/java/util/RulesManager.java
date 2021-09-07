package util;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.File;
import com.github.bhlangonijr.chesslib.Rank;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;

import chessboard.Coord;

public class RulesManager {
	
//	private ChessBoardModel board;
	
	private Board board;
	
	public RulesManager() {
		this.board = new Board();
		
	}
	
	public void importFen(String fen) {
		board.loadFromFen(fen);
	}
	
	public void getMoves() {
		System.out.println("legal moves: " + board.legalMoves());
		
	}
	
	private Square coordToSquare(Coord c) {
		Square s = Square.fromValue(c.toChessString());
//		System.out.println(s);
		return s;
	}
	
//	private Coord squareToIndex(Square s) {
////		System.out.println(s.value());
//		char x = s.value().charAt(0);
//		int y = s.value().charAt(1) - '0';
//		System.out.println(x + y);
//		return new Coord(x,y);
//	}
	
	private Coord moveToCoord(Move m) {
		Square target = m.getTo();
		Rank rank = target.getRank();
		File file = target.getFile();
		char x;
		int y;
		switch (rank) {
		case RANK_1: 
			y = 1;
			break;
		case RANK_2: 
			y = 2;
			break;
		case RANK_3: 
			y = 3;
			break;
		case RANK_4: 
			y = 4;
			break;
		case RANK_5: 
			y = 5;
			break;
		case RANK_6: 
			y = 6;
			break;
		case RANK_7: 
			y = 7;
			break;
		case RANK_8: 
			y = 8;
			break;
		default: 
			throw new RuntimeException("Couldn't convert move to Coord at move: " + m.toString());			
		}
		switch (file) {
		case FILE_A: 
			x = 'a';
			break;
		case FILE_B: 
			x = 'b';
			break;
		case FILE_C: 
			x = 'c';
			break;
		case FILE_D: 
			x = 'd';
			break;
		case FILE_E: 
			x = 'e';
			break;
		case FILE_F: 
			x = 'f';
			break;
		case FILE_G: 
			x = 'g';
			break;
		case FILE_H: 
			x = 'h';
			break;
		default: 
			throw new RuntimeException("Couldn't convert move to Coord at move: " + m.toString());			
		}
		return new Coord(x,y);
	}
	
	public boolean isLegal(Coord source, Coord target) {
		Move move = new Move(coordToSquare(source), coordToSquare(target));
//		boolean isLegal = board.isMoveLegal(move, true);
//		System.out.println(isLegal);
//		return isLegal;
		if (board.legalMoves().contains(move))
			return true;
		System.out.println("Move is not legal");
		return false;
	}
	
	public void movePiece(Coord source, Coord target) {
		if (!isLegal(source, target))
			return;
		Move move = new Move(coordToSquare(source), coordToSquare(target));
		board.doMove(move);
	}
	
	public int[][] getWhiteAttackingMatrix() {
		int[][] result;
		if (!(board.getSideToMove() == Side.WHITE)) {
			Board b = new Board();
			b.loadFromFen(GameLogic.generateFenStringFlippedSides());
			result = calculateAttackingMatrix(b);
			System.out.println("testWhite");
		} else {
			result = calculateAttackingMatrix(board);
		}
		return result;
	}
	
	public int[][] getBlackAttackingMatrix() {
		int[][] result;
		if (!(board.getSideToMove() == Side.BLACK)) {
			Board b = new Board();
			b.loadFromFen(GameLogic.generateFenStringFlippedSides());
			result = calculateAttackingMatrix(b);
			System.out.println("testBlack");
		} else {
			result = calculateAttackingMatrix(board);
		}
		return result;
	}
	
	private int[][] calculateAttackingMatrix(Board b) {
		int[][] result = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				result[i][j] = 0;
			}
		}
//		List<Move> legalMoves = b.legalMoves();
//		System.out.println(legalMoves);
//		for (Move m : legalMoves) {
//			Coord c = moveToCoord(m);
//			result[c.getX()][c.getY()]++;
//		}
//		System.out.println(result);
		
		
		return result;
	}

}
