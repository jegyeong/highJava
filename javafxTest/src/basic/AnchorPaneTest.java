package basic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnchorPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		AnchorPane root = new AnchorPane();
		root.setPrefSize(300, 200);
		
		Label lblId = new Label("아 이 디 : ");
		lblId.setLayoutX(62);		// x좌표 설정
		lblId.setLayoutY(22);		// y좌표 설정
		
		Label lblPass = new Label("패스워드 : ");
		lblPass.setLayoutX(62);
		lblPass.setLayoutY(60);
		
		TextField tfId = new TextField();
		tfId.setLayoutX(130);
		tfId.setLayoutY(22);
		
		PasswordField pfPass = new PasswordField();
		pfPass.setLayoutX(130);
		pfPass.setLayoutY(60);
		
		
		Button btnLogin = new Button("로 그 인");
		btnLogin.setLayoutX(100);
		btnLogin.setLayoutY(100);
		
		Button btnCancel = new Button("취  소");
		btnCancel.setLayoutX(180);
		btnCancel.setLayoutY(100);
		
		root.getChildren().addAll(lblId, lblPass, tfId, pfPass, btnLogin, btnCancel);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("AnchorPane 연습");
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}












