package util;

import org.alcibiade.chess.rules.ChessRules;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Rank;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;

import chessboard.Coord;

public class RulesManager {
	
	private ChessRules rules;
//	private ChessBoardModel board;
	
	private Board board;
	
	public RulesManager() {
		this.board = new Board();
		
	}
	
	public void getMoves() {
		System.out.println("legal moves: " + board.legalMoves());
		
	}
	
	private Square coordToSquare(Coord c) {
		Square s = Square.fromValue(c.toChessString());
		System.out.println(s);
		return s;
	}
	
	public boolean isLegal(Coord source, Coord target) {
		Move move = new Move(coordToSquare(source), coordToSquare(target));
//		boolean isLegal = board.isMoveLegal(move, true);
//		System.out.println(isLegal);
//		return isLegal;
		if (board.legalMoves().contains(move))
			return true;
		return false;
	}
	
	public void movePiece(Coord source, Coord target) {
		if (!isLegal(source, target))
			return;
		Move move = new Move(coordToSquare(source), coordToSquare(target));
		board.doMove(move);
	}

}
