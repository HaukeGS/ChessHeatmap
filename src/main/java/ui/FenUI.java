package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import util.GameLogic;

public class FenUI extends HBox {
	
	Label label;
	TextField textField;
	Button get;
	Button go;
	
	public FenUI() {
		super();
		label = new Label("FEN-String:");
		textField = new TextField();
		go = new Button("Go");
		get = new Button("Get");
		getChildren().addAll(label, textField, go, get);
		setSpacing(8);
		setPrefWidth(900);
		textField.setPrefWidth(700);
		
		EventHandler<ActionEvent> goButtonHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				 GameLogic.initFromFen(textField.getText());
			}
		};
		
		EventHandler<ActionEvent> getButtonHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				 textField.setText(GameLogic.generateFenString());
			}
		};
		go.setOnAction(goButtonHandler);
		get.setOnAction(getButtonHandler);
//		getChildren().filtered(p -> p.getClass() == TextField.class).get(0);
	}

}
