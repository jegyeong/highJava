package kr.or.ddit.member.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MemberMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = 
				FXMLLoader.load(MemberMain.class.getResource("../fxml/MemberMain.fxml"));
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("회원관리 프로그램");
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
