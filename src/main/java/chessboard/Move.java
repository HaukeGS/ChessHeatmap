package chessboard;

import pieces.Empty;
import pieces.Pawn;
import pieces.Piece;

public class Move {
	
	Coord source;
	Coord target;
	Piece piece;
	boolean kingSideCastles;
	boolean QueenSideCastles;
	boolean takes;
	boolean check;
	boolean checkmate;
	String fen;
	
//	public Move (Coord s, Coord t) {
//		this.source = s;
//		this.target = t;
//		
//	}
	
	public Move (Coord s, Coord t, Piece p) {
		this.source = s;
		this.target = t;
		this.piece = p;
		this.kingSideCastles = false;
		this.QueenSideCastles = false;
		this.takes = false;
		this.check = false;
		this.checkmate = false;
	}

	public Coord getSource() {
		return source;
	}

	public void setSource(Coord source) {
		this.source = source;
	}

	public Coord getTarget() {
		return target;
	}

	public void setTarget(Coord target) {
		this.target = target;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	@Override
	public String toString() {
		return getChessNotation();
//		return piece.toString() + " from " + source.toString() + " to " + target.toString(); 
	}
	
	public String getChessNotation() {
		if(QueenSideCastles)
			return "O-O-O";
		if (kingSideCastles)
			return "O-O";
		return "" + getPieceNotation() + getTakesNotation() + source.getChessX() + source.getChessY() + getCheckNotation();
	}
	
	private String getCheckNotation() {
		if (checkmate) 
			return "#";
		if (check)
			return "+";
		return "";
	}
	
	private String getTakesNotation() {
		if (takes)
			return "x";
		return "";
	}
	
	private String getPieceNotation() {
		if (piece instanceof Pawn)
			return "";
		return String.valueOf(piece.getFenChar()).toUpperCase();
	}
	
	
	public String getFen() {
		return fen;
	}

	public void setFen(String fen) {
		this.fen = fen;
	}
	
	public void setTakes(boolean b) {
		takes = b;
	}

	public boolean isKingSideCastles() {
		return kingSideCastles;
	}

	public void setKingSideCastles(boolean kingSideCastles) {
		this.kingSideCastles = kingSideCastles;
	}

	public boolean isQueenSideCastles() {
		return QueenSideCastles;
	}

	public void setQueenSideCastles(boolean queenSideCastles) {
		QueenSideCastles = queenSideCastles;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public boolean isCheckmate() {
		return checkmate;
	}

	public void setCheckmate(boolean checkmate) {
		this.checkmate = checkmate;
	}

	public boolean isTakes() {
		return takes;
	}
	
	
}
