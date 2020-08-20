package kr.or.ddit.student.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.student.service.IStudentService;
import kr.or.ddit.student.service.StudentServiceImple;
import kr.or.ddit.student.vo.StudentVO;

public class StudentMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<StudentVO> stdTable;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> korCol;

    @FXML
    private TableColumn<?, ?> engCol;

    @FXML
    private TableColumn<?, ?> matCol;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBarChart;
    
    private IStudentService service;
    private ObservableList<StudentVO> stdData;
    

    // 추가버튼
    @FXML
    void btnAddClicked(ActionEvent event) {
    	try {
	    	// 현재 창의 컨트롤 객체를 이용해서 해당 창의 Stage객체 구하기
	    	Stage parentStage = (Stage)btnAdd.getScene().getWindow();
	    			
	    	Stage addStage = new Stage(StageStyle.DECORATED);
	    	addStage.initModality(Modality.WINDOW_MODAL);
	    	addStage.initOwner(parentStage);
	    	/*
	    	Parent addRoot = FXMLLoader.load(
	    			StudentMainController.class.getResource("../fxml/StudentAdd.fxml"));
	    	*/
	    	
	    	// FXMLoader객체 생성
	    	FXMLLoader loader = new FXMLLoader(
	    			StudentMainController.class.getResource("../fxml/StudentAdd.fxml"));
	    	
	    	// loader객체를 이용해서 fxml문서를 읽어온다.
	    	Parent addRoot = loader.load();
	    	
	    	
	    	// 추가창(StudentAdd.fxml)의 Controller객체를 구한다.
	    	// loader객체를 이용해서 자식창의 Controller객체를 구할 수 있다.
	    	StudentAddController addController = loader.getController();
	    	
	    	// 자식창의 부모창을 저장할 변수에 부모창을 저장한다.
	    	addController.setMainController(this);
	    	
	    	
	    	Scene scene = new Scene(addRoot);
	    	addStage.setScene(scene);
	    	addStage.setTitle("추가");
	    	addStage.show();
	    	
    	}catch (IOException e) {
			e.printStackTrace();
		}
    }

    // 학생별 막대그래프 버튼
    @FXML
    void btnBarChartClicked(ActionEvent event) {
    	try {
	    	Stage parentStage = (Stage) btnBarChart.getScene().getWindow();
	    	
	    	Stage barChartStage = new Stage(StageStyle.DECORATED);
	    	barChartStage.initModality(Modality.WINDOW_MODAL);
	    	barChartStage.initOwner(parentStage);
	    	
	    	Parent childRoot = FXMLLoader.load(
	    		StudentMainController.class.getResource("../fxml/StudentBarchart.fxml")
	    	);
	    	
	    	Scene scene = new Scene(childRoot);
	    	
	    	barChartStage.setScene(scene);
	    	barChartStage.setTitle("막대그래프");
	    	barChartStage.show();
    	
    	}catch (IOException e) {
			e.printStackTrace();
		}
    }

    // 테이블을 클릭했을 때
    @FXML
    void stdTableClicked(MouseEvent event) {
    	if(stdTable.getSelectionModel().isEmpty()) {
    		return;
    	}
    	// 선택한 학생 데이터 구하기
    	StudentVO stdVo = stdTable.getSelectionModel().getSelectedItem();
    	
    	Stage parentStage = (Stage)btnAdd.getScene().getWindow();
    	
    	
    	try {
			FXMLLoader loader = 
				new FXMLLoader(
					StudentMainController.class.getResource("../fxml/StudentPieChart.fxml"));
			Parent childRoot = loader.load();
			
			StudentPieChartController pieController = loader.getController();
			
			// 구해온 자식창의 Controller객체의 차트를 그려주는 메서드를 호출한다.
			pieController.showChart(stdVo);
			
			Stage pieStage = new Stage(StageStyle.DECORATED);
			pieStage.initModality(Modality.WINDOW_MODAL);
			pieStage.initOwner(parentStage);
			
			Scene scene = new Scene(childRoot);
			
			pieStage.setScene(scene);
			pieStage.setTitle(stdVo.getStd_name() + " 학생의 성적");
			pieStage.show();
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    }

    public void setTableView() {
    	stdData = FXCollections.observableArrayList(service.getAllStudentList());
    	stdTable.setItems(stdData);
    }
    
    
    @FXML
    void initialize() {
    	service = StudentServiceImple.getInstance();
    	
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("std_name"));
    	korCol.setCellValueFactory(new PropertyValueFactory<>("std_kor"));
    	engCol.setCellValueFactory(new PropertyValueFactory<>("std_eng"));
    	matCol.setCellValueFactory(new PropertyValueFactory<>("std_mat"));
    	
    	setTableView();
    }
}
