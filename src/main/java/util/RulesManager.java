package util;

import org.alcibiade.chess.model.ChessBoardModel;
import org.alcibiade.chess.rules.ChessRules;
import org.alcibiade.chess.rules.ChessRulesImpl;

import chessboard.Coord;

public class RulesManager {
	
	private ChessRules rules;
	private ChessBoardModel board;
	
	public RulesManager() {
		this.rules = new ChessRulesImpl();
		this.board = new ChessBoardModel();
		board.setInitialPosition();
	}
	
	public void getMoves() {
		rules.getAvailableMoves(board);
		
	}
	
	public boolean isLegal(Coord source, Coord target) {
		return true;
	}

}
