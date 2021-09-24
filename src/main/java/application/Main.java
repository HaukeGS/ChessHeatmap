package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.UserInterface;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//FXMLLoader loader = new FXMLLoader(Main.class.getResource("ChessBoard.fxml"));
			//ChessBoard root = loader.load();
//			ChessBoard root = new ChessBoard();
			UserInterface root = new UserInterface();
			Scene scene = new Scene(root,1200,1000);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Chess Heatmap");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
