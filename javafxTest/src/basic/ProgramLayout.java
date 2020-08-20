package basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProgramLayout extends Application {

	
	@Override
	public void start(Stage primaryStage) {
	/*	
		컨테이너 객체 : 다른 컨테이너나 컨트롤객체를 포함하는 객체로 주로 전체적인 화면 레이아웃을 정할는 용으로 사용된다.
					(예 : VBox, HBox, BorderPane, AnchorPane, StackPane 등...)
		컨트롤 객체 : 사용자가 직접 보거나 사용할 수 있는 객체
					(예 : Button, Label, CheckBox, RadioButton, ListView, TableView등)
	*/
		VBox root = new VBox();  // 컨트롤이나 컨테이너를 세로로 배치하는 레이아웃
		root.setPrefWidth(650);  // VBox의 너비
		root.setPrefHeight(150); // VBox의 높이
		
		root.setSpacing(10);   // 컨트롤 간의 간격
		root.setAlignment(Pos.CENTER);   // 컨트롤들을 가운데 정렬
		
		// 안쪽 여백 설정
		// Insets객체는  상,우,하,좌 순으로 값을 설정한다. 
		root.setPadding(new Insets(10, 10, 10, 10));
		
		Label lblMsg = new Label();   // Label 컨트롤객체 생성
		lblMsg.setText("안녕하세요 JavaFx입니다.");  // Label에 문자열 출력
		lblMsg.setFont(new Font(50));		//  Font객체를 이용하여 글자 크기 설정
		
		Button btnClose = new Button(" 종  료 ");
		
		// 버튼에 대한 클릭 이벤트 처리
		btnClose.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// 이벤트가 발생했을 때 처리할 내용을 이곳에 기술한다.
				//primaryStage.close();  // Stage객체의 close()메서드 호출
				
				// 프로그램 종료
				Platform.exit();
				
				//System.exit(0);  // 이 명령은 되도록 사용하지 않는다.
				
			}
		});
		
		
		// HBox 객체 생성
		HBox hbox = new HBox();   // HBox객체 ==> 컨트롤들을 가로로 배치하는 컨테이너 객체
		hbox.setSpacing(10);	// 컨트롤 사이의 간격
		hbox.setPadding(new Insets(10));  // 안쪽 여백
		hbox.setAlignment(Pos.CENTER);
		
		TextField tfTemp = new TextField();  // 한줄의 문자열을 입력할 수 있는 TextField객체 생성
		tfTemp.setPrefWidth(300);	// TextField객체의 너비
		
		Button btnOk = new Button();
		btnOk.setText(" 확  인 ");
		
		// HBox에 추가하기
		// add()메서드는 컨트롤들을 한개씩 추가할 때 사용한다.
		//hbox.getChildren().add(tfTemp);
		//hbox.getChildren().add(btnOk);
		
		// addAll()메서드는 여러개의 컨트롤들을 한꺼번에 추가한다.
		hbox.getChildren().addAll(tfTemp, btnOk);
		
		
		
		// 만들어진 컨트롤 객체들을 컨테이너 객체에 추가한다.
		// 방법1 ==> ObservableList객체 이용하기
		ObservableList<Node> list = root.getChildren();  // Vbox에서 ObservableList객체 구하기
		list.add(lblMsg);
		list.add(hbox);
		list.add(btnClose);
		
		
		
		
		
		// VBox를 루트 컨테이너로 하는 Scene객체 생성
		Scene scene = new Scene(root);
		
		// 만들어진 Scene객체를 Stage객체에 추가한다.
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("프로그램 레이아웃 연습");  // 창 제목
		
		primaryStage.show();	// 창(Stage) 보이기
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}





