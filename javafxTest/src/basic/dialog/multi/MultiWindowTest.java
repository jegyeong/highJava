package basic.dialog.multi;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MultiWindowTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = 
				FXMLLoader.load(MultiWindowTest.class.getResource("mainForm.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("자식창과 데이터 주고받기");
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
