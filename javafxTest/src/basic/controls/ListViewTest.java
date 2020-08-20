package basic.controls;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListViewTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		Label lblMsg = new Label();
		lblMsg.setFont(new Font(20));  // Label의 글자 크기 설정
		
		// ListView에 출력할 데이터 구성 ==> ObservableList객체 이용
		ObservableList<String> data = 
			FXCollections.observableArrayList(
				"green", "gold", "blue", "black", "brown",
				"blueviolet", "pink", "yellow", "chocolate"
			);
		
		/*
		// ListView객체를 생성하고 데이터를 셋팅하기
		 
		// 방법1 ==> 객체 생성 후 setItems()메서드로 데이터를 셋팅한다.
		ListView<String> list = new ListView<String>();
		// ListView에 데이터 셋팅하기
		list.setItems(data);
		*/
		
		// 방법2 ==> 객체를 생성할 때 생성자에 데이터를 넣어서 생성한다.
		ListView<String> list = new ListView<String>(data);
		//=========================================================
		
		// ListView에서 데이터를 선택했을 때 처리하기
		list.getSelectionModel().selectedItemProperty().addListener(
			new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, 
						String oldValue, String newValue) {
					
					lblMsg.setText(newValue);  // 선택된 값을 Label에 출력
					lblMsg.setTextFill(Color.web(newValue));  // Label의 글자색 지정
					
				}
			}
		);
		
		// ListView의 원래 데이터값은 변경되지 않고, 화면에 보이는 내용을 변경하는 방법
		list.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new ListCell<String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						
						// item ==> 원래 데이터
						// empty ==> 데이터가 없는 자리는 true, 있는자리는 false
						
						//if(item!=null) {   // 방법1
						if(!empty) {  // 방법2
						
							// 보여줄 데이터가 문자열일 경우에는 setText()메서드를 사용한다.
							// 형식) setText(보여줄 문자열);
							//setText(item + " ^^^&&&***");
							
							// 보여줄 데이터가 컨트롤이나 컨테이너일 경우에는
							// setGraphic()메서드를 사용한다.
							// 형식) setGraphic(보여줄 컨트롤또는 컨테이너 객체);
							Rectangle rect = new Rectangle(100, 20);
							rect.setFill(Color.web(item));
							setGraphic(rect);
							
						}
						
					}
				};
			}
		});
		
		
		
		
		
		VBox root = new VBox(10);
		root.setPadding(new Insets(10));
		root.getChildren().addAll(list, lblMsg);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("ListView 연습");
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
