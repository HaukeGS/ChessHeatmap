package ui;

import chessboard.ChessBoard;
import util.GameLogic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FenUI extends HBox {
	
	Label label;
	TextField textField;
	Button button;
	ChessBoard cb;
	
	public FenUI(ChessBoard cb) {
		super();
		this.cb = cb;
		label = new Label("FEN-String:");
		textField = new TextField();
		button = new Button("Go");
		getChildren().addAll(label, textField, button);
		setSpacing(8);
		setPrefWidth(900);
		textField.setPrefWidth(700);
		
		EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				 GameLogic.initFromFen(textField.getText());
			}
		};
		button.setOnAction(buttonHandler);
//		getChildren().filtered(p -> p.getClass() == TextField.class).get(0);
	}

}
