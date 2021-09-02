package ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import pieces.Empty;
import pieces.Piece;
import util.GameLogic;

public class Sidebar extends VBox {
	
	CheckBox checkBoxColored;

	public Sidebar() {
		super();
		this.checkBoxColored = new CheckBox("Colored");
		checkBoxColored.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				GameLogic.setColored(newValue);
				GameLogic.recolorSquares();
			}
			
		});
		
		getChildren().addAll(checkBoxColored);
	}
}
