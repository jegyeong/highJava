package basic.controls;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ComboBoxTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		
		HBox hbox = new HBox(10);
		hbox.setPadding(new Insets(10));
		hbox.setAlignment(Pos.CENTER);
		
		TextArea taMsg = new TextArea();
		
		// ComboBox객체 생성 후 데이터 셋팅하기 방법1
		ComboBox<String> combo = new ComboBox<String>();
		combo.getItems().addAll("한강", "금강", "영산강", "낙동강");
		
		//combo.setValue("한강");   // 처음에 보여줄 데이터 셋팅
		combo.setValue(combo.getItems().get(0));
		

		// 방법2
		ObservableList<String> data = FXCollections.observableArrayList(
			"사과", "딸기", "배", "포도", "감"
		);
		ComboBox<String> combo2 = new ComboBox<String>(data);
		//combo2.setValue(combo2.getItems().get(0));
		
		// 현재 설정된 데이터에 다른 데이터를 더 추가해 주기
		combo2.getItems().addAll("복숭아", "대추");
		
		
		
		Button btnOk = new Button("확 인");
		btnOk.setOnAction(e->{
			
			if(combo.getValue() == null) {
				taMsg.setText("첫번째 콤보박스에서 데이터를 선택하세요.");
				return;
			}
			
			if(combo2.getValue() == null) {
				taMsg.setText("두번째 콤보박스에서 데이터를 선택하세요.");
				return;
			}
			
			
			// ComboBox에서 현재 선택된값은 getValue()메서드로 구할수 있다.
			taMsg.setText(combo.getValue() + " 유역의 과일은 ");
			taMsg.appendText(combo2.getValue() + "가(이) 유명합니다.");
			
		});
		
		
		hbox.getChildren().addAll(combo, combo2, btnOk);
		
		root.setTop(hbox);
		root.setCenter(taMsg);
		
		Scene scene = new Scene(root, 500, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("ComboBox 연습");
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
