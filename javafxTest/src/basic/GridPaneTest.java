package basic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GridPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		GridPane grid = new GridPane();
		grid.setPrefSize(300, 200);
		grid.setPadding(new Insets(10));
		grid.setHgap(10);
		grid.setVgap(20);
		
		// 컨테이너나 컨트롤에 스타일 설정하기
		// 객체.setStyle("-fx-스타일명1:값1;  -fx-스타일명2:값2;");
		grid.setStyle("-fx-background-color:yellow;");
		
		
		Label lblId = new Label("아 이 디 : ");
		Label lblPass = new Label("패스워드 : ");
		
		TextField tfId = new TextField();
		tfId.setStyle("-fx-background-color:green; -fx-text-fill:red; ");
		
		PasswordField pfPass = new PasswordField();
		
		Button btnLogIn = new Button("로 그 인");
		Button btnCancel = new Button(" 취   소 ");
		
		// GridPane에 컨트롤이나 컨테이너 배치하기 ==> add() 메서드로 추가한다.
		// 형식) grid.add(추가할컨트롤객체, 칸번호, 행번호, colspan, rowspan);
		grid.add(lblId, 1, 1);
		grid.add(lblPass, 1, 2);
		grid.add(tfId, 2, 1, 2, 1);
		grid.add(pfPass, 2, 2, 2, 1);
		grid.add(btnLogIn, 2, 3);
		grid.add(btnCancel, 3, 3);
		
		
		Scene scene = new Scene(grid);
		primaryStage.setScene(scene);
		primaryStage.setTitle("GridPane 연습");
		primaryStage.show();
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
