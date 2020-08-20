package kr.or.ddit.student.main;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import basic.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kr.or.ddit.student.service.IStudentService;
import kr.or.ddit.student.service.StudentServiceImple;
import kr.or.ddit.student.vo.StudentVO;

public class StudentAddController {
	
	// 부모창의 Controller객체 변수를 선언하고 getter와 setter를 만든다.
	private StudentMainController mainController;
	
    public StudentMainController getMainController() {
		return mainController;
	}

	public void setMainController(StudentMainController mainController) {
		this.mainController = mainController;
	}
	//--------------------------------------------
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfKor;

    @FXML
    private TextField tfEng;

    @FXML
    private TextField tfMat;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;
    
    private IStudentService service;

    // 취소버튼
    @FXML
    void btnCancelClicked(ActionEvent event) {
    	Stage currentStage = (Stage)btnCancel.getScene().getWindow();
    	currentStage.close();
    }

    @FXML
    void btnSaveClicked(ActionEvent event) {
    	String name = tfName.getText();
    	String strKor = tfKor.getText();
    	String strEng = tfEng.getText();
    	String strMat = tfMat.getText();
    	
    	if(name.isEmpty()) {
    		AlertUtil.errorMsg("입력오류", "이름을 입력하세요.");
    		tfName.requestFocus();
    		return;
    	}
    	
    	int count = service.getStudentCount(name);
    	if(count>0) {
    		AlertUtil.errorMsg("이름중복", name + " 학생은 이미 등록되어 있습니다.");
    		tfName.clear();
    		tfName.requestFocus();
    		return;
    	}
    	
    	if(!Pattern.matches("^[0-9]{1,3}$", strKor)) {
    		AlertUtil.errorMsg("점수 입력 오류", "국어점수를 정확히 입력하세요.");
    		tfKor.requestFocus();
    		return;
    	}
    	
    	if(!Pattern.matches("^[0-9]{1,3}$", strEng)) {
    		AlertUtil.errorMsg("점수 입력 오류", "영어점수를 정확히 입력하세요.");
    		tfEng.requestFocus();
    		return;
    	}
    	
    	if(!Pattern.matches("^[0-9]{1,3}$", strMat)) {
    		AlertUtil.errorMsg("점수 입력 오류", "수학점수를 정확히 입력하세요.");
    		tfMat.requestFocus();
    		return;
    	}
    	
    	StudentVO stdVo = new StudentVO();
    	stdVo.setStd_name(name);
    	stdVo.setStd_kor(Integer.parseInt(strKor));
    	stdVo.setStd_eng(Integer.parseInt(strEng));
    	stdVo.setStd_mat(Integer.parseInt(strMat));
    	
    	int cnt = service.insertStudent(stdVo);
    	
    	if(cnt>0) {
    		AlertUtil.infoMsg("추가작업 성공", name + " 학생 성적을 추가했습니다.");
    		mainController.setTableView();  // 부모창의 TableView를 다시 셋팅하는 메서드 호출
    		
    		tfName.clear();
    		tfKor.clear();
    		tfEng.clear();
    		tfMat.clear();
    		
    	}else {
    		AlertUtil.errorMsg("추가작업 실패", name + " 학생 성적 추가 실패!!!");
    	}
    	
    	
    }

    @FXML
    void initialize() {
    	service = StudentServiceImple.getInstance();
    }
}
