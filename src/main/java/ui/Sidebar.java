package ui;


import chessboard.Move;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import util.GameLogic;

public class Sidebar extends VBox {
	
	private CheckBox checkBoxColored;
	private Button reset;
	private Rectangle evalBar;
//	private TableView<Move> moves;
	private ListView<Move> moves;

	public Sidebar() {
		super();
		this.checkBoxColored = new CheckBox("Colored");
		checkBoxColored.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				GameLogic.setColored(newValue);
				GameLogic.updateBoard();
			}
			
		});
		
		this.reset = new Button("Reset") {
			@Override
			public void fire() {
				GameLogic.resetBoard();
			}
		};
		
		this.moves = new ListView<Move>(GameLogic.getMoveList());
		
//		this.moves = new TableView<Move>();
//		TableColumn<Move, String> whitesColumn = new TableColumn<Move, String>("Whites Moves");
//		TableColumn<Move, String> blacksColumn = new TableColumn<Move, String>("Blacks Moves");
//		
//		whitesColumn.setCellValueFactory(
//				new PropertyValueFactory<Move, String>("move"));		
//		blacksColumn.setCellValueFactory(
//				new PropertyValueFactory<Move, String>("move"));
//		
//		moves.getColumns().addAll(whitesColumn, blacksColumn);
		
		
		getChildren().addAll(checkBoxColored, reset, moves);
	}
}
