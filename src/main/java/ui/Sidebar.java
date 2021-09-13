package ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import util.GameLogic;

public class Sidebar extends VBox {
	
	CheckBox checkBoxColored;
	Button reset;
	Rectangle evalBar;

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
		
		getChildren().addAll(checkBoxColored, reset);
	}
}
