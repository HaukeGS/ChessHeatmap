package ui;

import chessboard.ChessBoard;
import chessutility.GameLogic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

public class UserInterface extends BorderPane {
	
	private ChessBoard chessBoard;
	private FenUI fenui;
	private Sidebar sidebar;
	
	public UserInterface() {
		this.chessBoard = new ChessBoard();
		this.fenui = new FenUI();
		this.sidebar = new Sidebar();
		GameLogic.setSidebar(sidebar);
		GameLogic.updateBoard();
		setAlignment(chessBoard, Pos.CENTER);
		setMargin(chessBoard, new Insets(20, 20, 20, 20));
		setAlignment(fenui, Pos.CENTER);
		setMargin(fenui, new Insets(15, 15, 15, 15));
		setAlignment(sidebar, Pos.CENTER);
		setMargin(sidebar, new Insets(15, 15, 15, 15));
		setCenter(chessBoard);
		setBottom(fenui);
		setRight(sidebar);
	}

}
