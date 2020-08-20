package kr.or.ddit.zip.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ZipSearch extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = 
				FXMLLoader.load(ZipSearch.class.getResource("../fxml/ZipSearch.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("우편번호 검색");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
