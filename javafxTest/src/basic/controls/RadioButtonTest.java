package basic.controls;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RadioButtonTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		// 라디오 버튼들을 묶음으로 처리하는 객체 변수 선언
		ToggleGroup group = new ToggleGroup();
		
		ImageView icon = new ImageView();
		
		RadioButton rb1 = new RadioButton("Home");
		rb1.setToggleGroup(group);    // 라디오버튼을 그룹에 추가
		rb1.setUserData("Home");	// 해당 라디오 버튼을 선택했을 때 사용할 값 설정
		
		RadioButton rb2 = new RadioButton("Calendar");
		rb2.setToggleGroup(group);
		rb2.setUserData("Calendar");
		
		RadioButton rb3 = new RadioButton("Contacts");
		rb3.setToggleGroup(group);
		rb3.setUserData("Contacts");
		
		// 라디오 버튼을 묶음으로 묶어주는 ToggleGroup객체에서 상태값이 변했을 때 처리하기
		group.selectedToggleProperty().addListener(
			new ChangeListener<Toggle>() {
				@Override
				public void changed(ObservableValue<? extends Toggle> observable, 
						Toggle oldValue, Toggle newValue) {
					// oldValue ==> 변경전,  newValue ==> 변경후
					// 여기에서는 Toggle객체를 RadioButton으로 형변환해서 사용할 수 있다.
					
					if(group.getSelectedToggle()!=null) {
					
						// 선택된 RadioButton의 userData값 구하기
						//String url = group.getSelectedToggle().getUserData().toString();
						RadioButton rb = (RadioButton) newValue;
						String url = rb.getText();
						
						Image img = new Image(
							RadioButtonTest.class.getResourceAsStream(
									"../../images/" + url + ".jpg")
						);
						
						icon.setImage(img);
					}
				}
		});
		
		Button btn = new Button("확 인"); 
		btn.setOnAction(e->{
			// 라디오버튼도 isSelected()메서드로 현재 선택 여부를 알 수 있다.
			if(rb1.isSelected()==true) {
				System.out.println(rb1.getText() + " 선택"); 
			}else {
				System.out.println(rb1.getText() + " 해제");
			}
			
			// 라디오 버튼도 setSelected()메서드로 선택 여부를 변경할 수 있다.
			//rb2.setSelected(true);
			rb3.setSelected(false);
			
		});
		
		HBox hbox = new HBox(50);
		VBox vbox = new VBox(10);
		
		vbox.getChildren().addAll(rb1, rb2, rb3, btn);
		
		hbox.getChildren().addAll(vbox, icon);
		hbox.setPadding(new Insets(10));
		
		Scene scene = new Scene(hbox, 250, 150);
		primaryStage.setScene(scene);
		primaryStage.setTitle("RadioButton 연습");
		primaryStage.show();
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
