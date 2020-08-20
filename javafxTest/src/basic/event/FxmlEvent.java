package basic.event;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxmlEvent extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		
		Parent root = FXMLLoader.load(FxmlEvent.class.getResource("fxmlEvent.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Fxml문서의 이벤트 연습");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
