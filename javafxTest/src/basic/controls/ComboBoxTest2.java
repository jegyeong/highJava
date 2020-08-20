package basic.controls;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ComboBoxTest2 extends Application {

	@Override
	public void start(Stage primaryStage) {
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(10));
		vbox.setAlignment(Pos.TOP_CENTER);
		
		ComboBox<MyFriend> combo = new ComboBox<MyFriend>();
		TextArea taResult = new TextArea();
		
		ObservableList<MyFriend> dataList = 
			FXCollections.observableArrayList(
				new MyFriend("a001", "홍길동", "010-1234-5678", "대전"),
				new MyFriend("b001", "일지매", "010-2222-2222", "울산"),
				new MyFriend("c001", "성춘향", "010-3333-3333", "강릉"),
				new MyFriend("d001", "이몽룡", "010-4444-4444", "인천"),
				new MyFriend("e001", "강감찬", "010-5555-5555", "천안"),
				new MyFriend("f001", "변학도", "010-6666-6666", "목포")
			);
		combo.setItems(dataList);
		
		// ComboBox의 목록이 보여지는 곳의 내용을 변경하기
		// 화면에 나타나는 셀의 내용을 변경하는 부분을 말한다.
		combo.setCellFactory(new Callback<ListView<MyFriend>, ListCell<MyFriend>>() {
			
			@Override
			public ListCell<MyFriend> call(ListView<MyFriend> param) {
				return new ListCell<MyFriend>() {
					@Override
					protected void updateItem(MyFriend item, boolean empty) {
						
						super.updateItem(item, empty);
						if(item==null || empty) {
							setText(null);
						}else {
							setText(item.getId() + "[" + item.getName() + "]");
						}
					}
				};
			}
		});
		
		// ComboBox에서 항목을 선택하면 선택된 내용이 나타나는 곳을 ButtonCell이라고 하는데
		// 이곳의 데이터도 변경되도록 해야 한다.
		combo.setButtonCell(new ListCell<MyFriend>() {
			@Override
			protected void updateItem(MyFriend item, boolean empty) {
				super.updateItem(item, empty);
				if(item==null || empty) {
					setText(null);
				}else {
					setText(item.getId() + "[" + item.getName() + "]");
				}
			}
		});
		
		
		// ComboBox의 데이터를 선택했을 때 이벤트 처리
		combo.setOnAction(e->{
			// ComboBox에서 현재 선택된 값 구하기
			// 방법1 ==> getSelectionModel()을 이용하기
			//MyFriend selData = combo.getSelectionModel().getSelectedItem();
			
			// 방법2 == getValue() 이용하기
			MyFriend selData = combo.getValue();
			
			
			taResult.setText("ID : " + selData.getId() + "\n");
			taResult.appendText("이 름 : " + selData.getName() + "\n");
			taResult.appendText("전 화 : " + selData.getTel() + "\n");
			taResult.appendText("주 소 : " + selData.getAddr() + "\n");
			
			
		});
		
		
		
		vbox.getChildren().addAll(combo, taResult);
		
		Scene scene = new Scene(vbox, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("ComboBox 연습2");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	class MyFriend{
		private String id;
		private String name;
		private String tel;
		private String addr;
		
		// 기본 생성자
		public MyFriend() {	}
		
		// 생성자
		public MyFriend(String id, String name, String tel, String addr) {
			super();
			this.id = id;
			this.name = name;
			this.tel = tel;
			this.addr = addr;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
		}
		
		
		
	}
	
	
}










