package kr.or.ddit.member.main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import basic.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfTel;

    @FXML
    private TextField tfAddr;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

    @FXML
    private TableView<MemberVO> table;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> telCol;

    @FXML
    private TableColumn<?, ?> addrCol;

    private String strWork = ""; 
    
    
    // 추가버튼
    @FXML
    void dataAdd(ActionEvent event) {
    	// 입력 상자들을 편집 가능 상태로 만들기
    	tfId.setEditable(true);
    	tfName.setEditable(true);
    	tfTel.setEditable(true);
    	tfAddr.setEditable(true);
    	
    	// 확인, 취소버튼 활성화
    	btnOk.setDisable(false);
    	btnCancel.setDisable(false);
    	
    	// 추가, 수정, 삭제버튼 비활성화
    	btnAdd.setDisable(true);
    	btnEdit.setDisable(true);
    	btnDel.setDisable(true);
    	// TableView도 비활성화
    	table.setDisable(true);
    	
    	// 입력 상자의 내용을 모두 삭제
    	tfId.clear();
    	tfName.clear();
    	tfTel.clear();
    	tfAddr.clear();
    	
    	tfId.requestFocus(); 	// 포커스 주기
    	
    	strWork = "추가";
    }

    // 수정버튼
    @FXML
    void dataEdit(ActionEvent event) {
    	if(table.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("작업오류", "수정할 데이터를 선택한 후 사용하세요.");
    		return;
    	}
    	
    	// 입력 상자들을 편집 가능 상태로 만들기
    	tfName.setEditable(true);
    	tfTel.setEditable(true);
    	tfAddr.setEditable(true);
    	
    	// 확인, 취소버튼 활성화
    	btnOk.setDisable(false);
    	btnCancel.setDisable(false);
    	
    	// 추가, 수정, 삭제버튼 비활성화
    	btnAdd.setDisable(true);
    	btnEdit.setDisable(true);
    	btnDel.setDisable(true);
    	// TableView도 비활성화
    	table.setDisable(true);
    	
    	tfName.requestFocus(); 	// 포커스 주기
    	strWork = "수정";
    }

    // 삭제버튼
    @FXML
    void dataDel(ActionEvent event) {
    	if(table.getSelectionModel().isEmpty()) {
    		AlertUtil.errorMsg("작업오류", "삭제할 데이터를 선택한 후 사용하세요.");
    		return;
    	}
    	// 현재 선택한 데이터 구하기
    	MemberVO mem = table.getSelectionModel().getSelectedItem();
    	String memId = mem.getMem_id();  // 회원ID값 구하기
    	
    	
    	if(AlertUtil.confirm("삭제여부확인", memId + " 정보를 정말로 삭제하시겠습니까?")) {
    	
	    	int cnt = service.deleteMember(memId);
	    	
	    	if(cnt>0) {
	    		AlertUtil.infoMsg("작업성공", memId + " 회원 정보를 삭제했습니다.");
	    		setMemberData();  // 삭제 후의 회원정보들을 다시 가져온다.
	    	}else {
	    		AlertUtil.infoMsg("작업실패", "삭제 작업 실패!!");
	    	}
    	}
    }

    // 확인 버튼
    @FXML
    void dataRun(ActionEvent event) {
    	String memId = tfId.getText();
    	String memName = tfName.getText();
    	String memTel = tfTel.getText();
    	String memAddr = tfAddr.getText();
    	if(memId.isEmpty()) {
    		AlertUtil.errorMsg("입력오류", "회원 ID를 입력하세요.");
    		tfId.requestFocus();
    		return;
    	}
    		
    	if(memName.isEmpty()) {
    		AlertUtil.errorMsg("입력오류", "회원 이름을 입력하세요.");
    		tfName.requestFocus();
    		return;
    	}
    	
    	if(memTel.isEmpty()) {
    		AlertUtil.errorMsg("입력오류", "회원 전화번호를 입력하세요.");
    		tfTel.requestFocus();
    		return;
    	}
    	
    	if(memAddr.isEmpty()) {
    		AlertUtil.errorMsg("입력오류", "회원 주소를 입력하세요.");
    		tfAddr.requestFocus();
    		return;
    	}
    	
    	// VO객체에 자료 저장
    	MemberVO mem = new MemberVO();
    	mem.setMem_id(memId);
    	mem.setMem_name(memName);
    	mem.setMem_tel(memTel);
    	mem.setMem_addr(memAddr);
    	
    	
    	if("추가".equals(strWork)) {  // 추가 작업 상태인지 검사
    		int count = service.getMemberCount(memId);
    		if(count>0) {
    			AlertUtil.warnMsg("ID중복오류", memId + "는 이미 있는 회원입니다.");
    			//tfId.clear();
    			tfId.requestFocus();
    			return;
    		}
    		
    		int cnt = service.insertMember(mem);
    		if(cnt>0) {
    			AlertUtil.infoMsg("작업성공", memId + "회원을 추가했습니다.");
    		}else {
    			AlertUtil.infoMsg("작업실패", "추가 작업 실패!!!");
    		}
    		
    	}else if("수정".equals(strWork)) {  // 수정작업
    		int cnt = service.updateMember(mem);
    		
    		if(cnt>0) {
    			AlertUtil.infoMsg("작업성공", memId + "회원  정보를 수정했습니다.");
    		}else {
    			AlertUtil.infoMsg("작업실패", "수정 작업 실패!!!");
    		}
    	}
    	
    	setMemberData();
    	workCancel();
    	
    }

    // 취소버튼
    @FXML
    void dataCancel(ActionEvent event) {
    	AlertUtil.infoMsg("작업취소", "작업이 취소되었습니다.");
    	
    	workCancel();
    }
    
    private void workCancel() {
    	// 입력 상자들을 편집이 불가능한 상태로 만들기
    	tfId.setEditable(false);
    	tfName.setEditable(false);
    	tfTel.setEditable(false);
    	tfAddr.setEditable(false);
    	
    	// 확인, 취소버튼 비활성화
    	btnOk.setDisable(true);
    	btnCancel.setDisable(true);
    	
    	// 추가, 수정, 삭제버튼 활성화
    	btnAdd.setDisable(false);
    	btnEdit.setDisable(false);
    	btnDel.setDisable(false);
    	// TableView도 활성화
    	table.setDisable(false);
    	
    	// 입력 상자의 내용을 모두 삭제
    	tfId.clear();
    	tfName.clear();
    	tfTel.clear();
    	tfAddr.clear();
    	
    	// 데이터를 이전 상태로 만들기
    	if(!table.getSelectionModel().isEmpty()) {
    		// 선택한 곳의 데이터 구하기
        	MemberVO mem = table.getSelectionModel().getSelectedItem();
        	
        	tfId.setText(mem.getMem_id());
        	tfName.setText(mem.getMem_name());
        	tfTel.setText(mem.getMem_tel());
        	tfAddr.setText(mem.getMem_addr());
    	}
    	strWork = "";
    }
    

    // TableView를 클릭했을 때 이벤트 처리
    @FXML
    void tableClick(MouseEvent event) {
    	if(table.getSelectionModel().isEmpty()) {
    		return;
    	}
    	
    	// 선택한 곳의 데이터 구하기
    	MemberVO mem = table.getSelectionModel().getSelectedItem();
    	
    	tfId.setText(mem.getMem_id());
    	tfName.setText(mem.getMem_name());
    	tfTel.setText(mem.getMem_tel());
    	tfAddr.setText(mem.getMem_addr());
    	
    }

    private IMemberService service;   // Service객체가 저장될 변수
    private ObservableList<MemberVO> data; // TableView에 셋팅될 ObservableList객체 변수 
    
    // DB의 전체 데이터를 가져와 TableView에 출력하는 메서드
    private void setMemberData() {
    	List<MemberVO> memList = service.getAllMember();
    	data = FXCollections.observableArrayList(memList);
    	table.setItems(data);
    	
    	// 입력 상자의 내용을 모두 삭제
    	tfId.clear();
    	tfName.clear();
    	tfTel.clear();
    	tfAddr.clear();

    }
    
    @FXML
    void initialize() {
    	// TableView의 각 컬럼과 VO객체의 맴버변수를 매핑 시킨다.
    	idCol.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
    	telCol.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
    	addrCol.setCellValueFactory(new PropertyValueFactory<>("mem_addr"));
    	
    	// Service객체 생성
    	service = MemberServiceImpl.getInstance();
    	
    	setMemberData(); 
    	
    }
}
