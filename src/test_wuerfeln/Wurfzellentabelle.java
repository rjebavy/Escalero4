package test_wuerfeln;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Wurfzellentabelle extends Application {

	@Override
	public void start(Stage primaryStage) {
		Wurf test = new Wurf(3);
		test.initialisiereWuerfelsatz();
		
		BorderPane bp = new BorderPane();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
