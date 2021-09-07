package chessboard;

import pieces.Empty;
import pieces.Piece;

public class Move {
	
	Coord source;
	Coord target;
	Piece piece;
	
//	public Move (Coord s, Coord t) {
//		this.source = s;
//		this.target = t;
//		
//	}
	
	public Move (Coord s, Coord t, Piece p) {
		this.source = s;
		this.target = t;
		this.piece = p;
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
		return piece.toString() + " from " + source.toString() + " to " + target.toString(); 
	}
	
	
}
