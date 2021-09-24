package ui;


import chessboard.Move;
import chessutility.GameLogic;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;

public class Sidebar extends VBox {
	
	private CheckBox checkBoxColored;
	private CheckBox xray;
	private Button reset;
	private Label evalBarLabel;
	private Slider evalBar;
//	private TableView<Move> moves;
	private ListView<Move> moves;

	public Sidebar() {
		super();
		this.setSpacing(10);
		this.checkBoxColored = new CheckBox("Colored");
		checkBoxColored.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				GameLogic.setColored(newValue);
				GameLogic.updateBoard();
			}
			
		});
		this.xray = new CheckBox("Xray");
		xray.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				GameLogic.setXray(newValue);
				GameLogic.updateBoard();
			}
			
		});
		evalBarLabel = new Label("Stockfish Evaluation");
		evalBar = new Slider(-10, 10, 0.0);
		evalBar.setDisable(true);
		evalBar.setShowTickLabels(true);
		
		
		this.reset = new Button("Reset") {
			@Override
			public void fire() {
				GameLogic.resetBoard();
			}
		};
		
		this.moves = new ListView<Move>(GameLogic.getMoveList());
		moves.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.PRIMARY) {
				Move selectedMove = moves.getSelectionModel().getSelectedItem();
				GameLogic.initFromFen(selectedMove.getFen());
			}
		});
		
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
		
		
		getChildren().addAll(checkBoxColored, xray, evalBarLabel, evalBar, reset, moves);
	}
	
	public void setEvalScore(double score) {
		if (score > 10)
			score = 10;
		else if (score < -10)
			score = -10;
		evalBar.setValue(score);
	}
}
