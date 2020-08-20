package basic.controls;

import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlertTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		VBox root = new VBox(10);
		root.setPadding(new Insets(20));
		
		HBox hbTop = new HBox(10);
		hbTop.setPadding(new Insets(10));
		hbTop.setAlignment(Pos.CENTER);
		
		HBox hbBottom = new HBox(10);
		hbBottom.setPadding(new Insets(10));
		hbBottom.setAlignment(Pos.CENTER);
		
		
		Button btnInfo = new Button("Information");
		Button btnError = new Button("Error");
		Button btnWarn = new Button("Warning");
		Button btnConfirm = new Button("Confirmation");
		Button btnProm = new Button("Prompt");
		
		btnInfo.setOnAction(e->{
			// Information 창
			Alert info = new Alert(AlertType.INFORMATION);
			info.setTitle("INFORMATION");
			info.setHeaderText("INFORMATION 보기");
			info.setContentText("INFORMATION Alert창 입니다.");
			
			info.showAndWait();   // 창을 보여주고 제어를 창이 닫힐때까지 멈춘다.
			
		});
		
		btnError.setOnAction(e->{
			// Error 창
			Alert err = new Alert(AlertType.ERROR);
			err.setTitle("ERROR");
			err.setHeaderText("ERROR 보기");
			err.setContentText("ERROR Alert창 입니다.");
			
			err.showAndWait();
		});
		
		btnWarn.setOnAction(e->{
			// Warning 창
			Alert warn = new Alert(AlertType.WARNING);
			warn.setTitle("WARNING");
			warn.setHeaderText("WARNING 보기");
			warn.setContentText("WARNING Alert창 입니다.");
			
			warn.showAndWait();
		});
		
		
		btnConfirm.setOnAction(e->{
			// Confirmation 창
			Alert conf = new Alert(AlertType.CONFIRMATION);
			conf.setTitle("CONFIRMATION");
			conf.setHeaderText("CONFIRMATION 보기");
			conf.setContentText("CONFIRMATION Alert창 입니다.");
			
			// Confirmation창을 보여주고 사용자가 누른 버튼 값 읽어오기
			ButtonType confResult = conf.showAndWait().get();

			// OK버튼 또는 취소버튼 중 클릭한 버튼 구분하기
			if(confResult == ButtonType.OK) {
				System.out.println("OK버튼을 눌렀습니다.");
			}else if(confResult == ButtonType.CANCEL) {
				System.out.println("취소버튼을 눌렀습니다.");
			}
			
		});
		
		btnProm.setOnAction(e->{
			// 자바스크립트의 prompt창과 같은 기능
			
			TextInputDialog prompt = new TextInputDialog("기본값"); // 기본값은 생략 가능
			
			prompt.setTitle("prompt");
			prompt.setHeaderText("prompt 자료 입력하기");
			prompt.setContentText("입력 : ");
			
			// 창을 보여주고, 창에서 입력한 값 가져오기
			Optional<String> result = prompt.showAndWait();
			
			// 입력한값 출력하기	
			String strData = null;  // 입력한 값이 저장될 변수
			if(result.isPresent()) {  // 값이 있으면...
				strData = result.get();  // 실제 입력한 값 가져오기
			}
			
			System.out.println("입력 값 : " + strData);
			
			
			
			
		});
		
		
		
		hbTop.getChildren().addAll(btnInfo, btnError, btnWarn);
		hbBottom.getChildren().addAll(btnConfirm, btnProm);
		
		root.getChildren().addAll(hbTop, hbBottom);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Alert창 연습");
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
