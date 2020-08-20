package kr.or.ddit.student.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StudentMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(
				StudentMain.class.getResource("../fxml/StudentMain.fxml")
			);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);;
			primaryStage.setTitle("성적관리 프로그램");
			primaryStage.show();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
