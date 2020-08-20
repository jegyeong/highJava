package basic.controls;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FxmlGuguDanController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfDan;

    @FXML
    private Button btnOut;

    @FXML
    private TextArea taResult;

    // '출력'버튼을 클릭했을 때 이벤트 처리
    @FXML
    void btnOutClicked(ActionEvent event) {
    	// TextField에 입력한 값 읽어오기
    	String strDan = tfDan.getText();
    	
    	if(strDan.isEmpty()) {
    		//System.out.println("출력할 단을 입력하세요.");
    		taResult.setText("출력할 단을 입력하세요.");
    		return;
    	}
    	
    	if(!Pattern.matches("[1-9][0-9]*", strDan)) {
    		taResult.setText("출력할 단은 숫자를 입력해야 합니다.");
    		return;
    	}
    	
    	
    	// 입력한 값을 숫자 형으로 변환
    	int dan = Integer.parseInt(strDan);
    	
    	//taResult.setText("");   // 내용 지우기
    	//taResult.clear();
    	tfDan.clear();
    	
    	tfDan.requestFocus();  //  포커스 주기
    	taResult.setText(dan + " 단\n\n");
    	
    	for(int i=1; i<=9; i++) {
    		int r = dan * i;
    		taResult.appendText(dan + " * " + i + " = " + r + "\n");
    	}
    	
    }

    @FXML
    void initialize() {

    }
}





