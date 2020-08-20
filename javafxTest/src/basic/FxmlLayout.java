package basic;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FxmlLayout extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		// fxml 파일을 읽어와 현재의 Stage에 적용하는 방법
		
		// fxml 파일을 읽어오는 방법1
		//VBox root = FXMLLoader.load(FxmlLayout.class.getResource("FxmlLayout.fxml"));
		//Parent root = FXMLLoader.load(FxmlLayout.class.getResource("FxmlLayout.fxml"));
		
		// fxml 파일을 읽어오는 방법2
		FXMLLoader loader = new FXMLLoader(FxmlLayout.class.getResource("FxmlLayout.fxml"));
		
		VBox root = loader.load();
		//Parent root = loader.load();
		
		
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("Fxml파일을 이용한 레이아웃 연습");
		primaryStage.show();
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
