package basic.controls;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class RadioCheckController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfName;

    @FXML
    private RadioButton rbMan;

    @FXML
    private ToggleGroup genGroup;

    @FXML
    private RadioButton rbWoman;

    @FXML
    private CheckBox chk1;

    @FXML
    private CheckBox chk2;

    @FXML
    private CheckBox chk3;

    @FXML
    private CheckBox chk4;

    @FXML
    private CheckBox chk5;

    @FXML
    private CheckBox chk6;

    @FXML
    private CheckBox chk7;

    @FXML
    private CheckBox chk8;

    @FXML
    private Button btnView;

    @FXML
    private TextArea taResult;

    @FXML
    void btnViewClicked(ActionEvent event) {
    	String name = tfName.getText();
    	if(name.isEmpty()) {
    		taResult.setText("이름을 입력하세요.");
    		return;
    	}
    	
    	String sung = "";
    	if(rbMan.isSelected()) {
    		sung = "남자";
    	}else {
    		sung = "여자";
    	}
    	
    	String hob = "";  // 선택된 취미가 저장될 변수
    	for(int i=0; i<hobbies.length; i++) {
    		if(hobbies[i].isSelected()) {
    			if(!"".equals(hob)) {
    				hob += ", ";
    			}
    			hob += hobbies[i].getText();
    		}
    	}
    	//taResult.setText(hob);
    	taResult.setText(name + "씨!\n");
    	taResult.appendText("당신은 " + sung + "이고,\n");
    	taResult.appendText("취미는 " +
    			("".equals(hob) ? "없군요!!" : hob + " 이군요"));
    	
    }

    private CheckBox[] hobbies;  // 취미의 CheckBox가 저장될 배열 변수 선언
    
    @FXML
    void initialize() {
    	hobbies = new CheckBox[] {
    		chk1, chk2, chk3, chk4, chk5, chk6, chk7, chk8	
    	};
    }
}
