package ui;

import chessboard.ChessBoard;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public class UserInterface extends BorderPane {
	
	private ChessBoard chessBoard;
	
	public UserInterface() {
		this.chessBoard = new ChessBoard();
		setAlignment(chessBoard, Pos.CENTER);
		setCenter(chessBoard);
		
	}

}
