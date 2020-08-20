package basic.event;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventTest extends Application {

	TextField tfMsg = new TextField();
	TextArea txtArea = new TextArea();
	
	@Override
	public void start(Stage primaryStage) {
		VBox root = new VBox(10);
		root.setPadding(new Insets(10));
		
		HBox hbox = new HBox(10);
		hbox.setPadding(new Insets(10));
		/*
		- 이벤트 처리 방법1
		- 이벤트설정할객체.setOn이벤트명() 메서드에 EventHandler인터페이스를 익명구현체 형식으로 구현해서
		    넣어준다. (이 인터페이스에는 handle()메서드가 있는데 이 메서드에 처리할 내용을 기술한다.
		*/
		Button btn1 = new Button("첫번째");
		
		btn1.setOnAction(
			/*
			new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// 이 부분에 이벤트에 대한 처리할 내용을 기술한다.
					txtArea.setText("첫번째 버튼을 클릭했습니다.");
				}
			}*/
				
			// 람다식으로 기술하기	
			event -> {
				txtArea.setText("람다식으로 처리한 첫번째 버튼 클릭 이벤트입니다.");
			}
		);
		
		//-----------------------------------------------------------
		/*
		- 이벤트 처리 방법2
		- 이벤트설정객체.addEventHandler()메서드를 사용한다. (이 메서드는 2개의 매개변수가 있다.)
		    이 메서드의 첫번째 매개변수로 '이벤트 종류'를 지정하고
		    두번째 매개변수에는 EventHandler인터페이스의 익명 구현체를 지정한다.
		*/
		Button btn2 = new Button("두번째");
		btn2.addEventHandler(
			ActionEvent.ACTION,  // 이벤트 종류 지정 
			/*
			new EventHandler<Event>() {
				public void handle(Event event) {
					txtArea.setText("두번째 이벤트 연습");
					//String ttt = txtArea.getText();
					String temp = tfMsg.getText();  // getText()메서드 : 현재 입력된 문자열을 읽어온다.
					//txtArea.setText(ttt + temp);
					txtArea.appendText("\n" + temp);   // appendText() ==> 기존의 데이터 끝에 새로운 내용을 추가한다.
				}
			}
			*/
			e->{
				txtArea.setText("람다식 처리 두번째...\n");
				txtArea.appendText("계속 출력합니다.\n");
				
				String temp = tfMsg.getText();
				txtArea.appendText(temp);
			}
		);
		
		
		//----------------------------------------------
		/*
		- 이벤트 처리 방법3-2
		  setOn이벤트명() 메서드나 addEventHandler()메서드의 매개변수로
		  EventHandler인터페이슬 구현한 객체의 인스턴스를 넣어준다.
		*/
		Button btn3 = new Button("세번째");
		btn3.setOnAction(new MyEventHandler());
		
		// 이벤트 처리방법4-2
		Button btn4 = new Button("네번째");
		btn4.addEventHandler(
				ActionEvent.ACTION, 
				new MyEventHandler2(txtArea, tfMsg));
		
		
		hbox.getChildren().addAll(btn1, btn2, btn3, btn4);
		
		root.getChildren().addAll(hbox, tfMsg, txtArea);
		
		Scene scene = new Scene(root, 500, 350);  // Scene객체 만들때 크기도 정할 수 있다.
		primaryStage.setScene(scene);
		primaryStage.setTitle("이벤트 설정 연습");
		primaryStage.show();
		

	}

	public static void main(String[] args) {
		launch(args);
	}
	
	/*
	- 이벤트 처리 방법3-1
	  inner클래스로 EventHandler인터페이스를 구현한 Class를 만든다.
	  inner클래스로 만들면 멤버 변수들을 사용할 수 있다.
	*/
	class MyEventHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			String temp = tfMsg.getText();
			txtArea.setText("세번째 방법 - inner클래스를 이용한다.\n");
			txtArea.appendText(temp);
		}
	}
	
}


/*
	- 이벤트 처리 방법4-1
	    외부의 독립된 형태의 객체를 이용하는 방법 ==> 이 객체도 EventHandler인터페이스를 구현한다.
*/
class MyEventHandler2 implements EventHandler<Event>{
	TextArea ta;
	TextField tf;
	
	// 생성자
	public MyEventHandler2(TextArea ta, TextField tf) {
		this.ta = ta;
		this.tf = tf;
	}
	
	@Override
	public void handle(Event event) {
		ta.setText("네번째 방법입니다.\n");
		String temp = tf.getText();
		ta.appendText("입력값 : " + temp);
		
	}
}




