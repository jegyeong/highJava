package basic.pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class PaginationTest extends Application {
	private int rowsPerPage = 10; 		// 한 화면에 보여줄 데이터 개수 지정
	private int totalCount;				// 전체 레코드 수
	private int pageCount;				// 페이지 수
	
	private TableView<MemberVO> table;	// TableView객체 변수 선언
	private Pagination pagination;		// Pagination객체 변수 선언
	
	private List<MemberVO> data;		
	
	private IMemberService service;		// Service객체 변수 선언

	@Override
	public void start(Stage primaryStage) {
		service = MemberServiceImpl.getInstance();
		
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		
		table = new TableView<MemberVO>();
		
		TableColumn<MemberVO, String> idCol = new TableColumn<MemberVO, String>("회원ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		idCol.setPrefWidth(150);
		
		TableColumn<MemberVO, String> nameCol = new TableColumn<MemberVO, String>("회원이름");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		nameCol.setPrefWidth(170);
		
		TableColumn<MemberVO, String> telCol = new TableColumn<MemberVO, String>("전화번호");
		telCol.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
		telCol.setPrefWidth(200);
		
		TableColumn<MemberVO, String> addrCol = new TableColumn<MemberVO, String>("주 소");
		addrCol.setCellValueFactory(new PropertyValueFactory<>("mem_addr"));
		addrCol.setPrefWidth(250);
		
		table.getColumns().addAll(idCol, nameCol, telCol, addrCol);
		
		// 전체 레코드수를 구한다.   ==> 102
		totalCount = service.getAllMemberCount();
		
		// 전체 페이지수를 구한다.		==> 102 / 10
		//pageCount = (totalCount % rowsPerPage==0 ) ? totalCount / rowsPerPage : (totalCount / rowsPerPage) + 1;
		pageCount = (int) Math.ceil((double)totalCount/rowsPerPage );
		
		
		pagination = new Pagination();
		// Pagination객체에 전체 페이지 수와 처음에 보여줄 index값을 설정한다.
		// index값은 1페이지일 경우 0으로 지정한다.
		pagination.setPageCount(pageCount);	// 전체 페이지 수 지정
		pagination.setCurrentPageIndex(0);	// 처음 선택될 페이지의 index값 지정
		
		// 첫페이지의 데이터를 가져온다.
		changeTableView(0);
		
		// Pagination의 페이지 번호를 변경했을 때 이벤트 처리
		pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, 
					Number oldValue, Number newValue) {
				
				changeTableView(newValue.intValue());
				
			}
		});
		
		
		
		
		root.setCenter(table);
		root.setBottom(pagination);
		
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pagination 연습");
		primaryStage.show();
		
	}
	
	//  12
	//  20
	
	// Pagination의 현재 선택된 index값을 매개값으로 받아서 해당 페이지에 맞는
	// 데이터를 가져와 TableView에 넣어주는 메서드
	// index는   0 => 1페이지,  1 => 2페이지, ....
	public void changeTableView(int index) {
		int start = index * rowsPerPage;		// 시작 번호 계산
		int end = Math.min( start + rowsPerPage,  totalCount); // 끝번호 계산
		
		Map<String, Integer> pageMap = new HashMap<String, Integer>();
		pageMap.put("start", start);
		pageMap.put("end", end);
		
		data = service.getPagingMemberList(pageMap);  // 시작번호부터 끝번호까지의 자료 가져오기
		
		// TableView에 가져온 데이터 셋팅하기
		table.setItems(FXCollections.observableArrayList(data));
		
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}
}
