package kr.or.ddit.prod.main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.LprodVO;
import kr.or.ddit.prod.vo.ProdVO;

public class ProdInfoMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<LprodVO> cmbLprod;

    @FXML
    private ComboBox<ProdVO> cmbProd;

    @FXML
    private TableView<ProdVO> tvProd;

    @FXML
    private TableColumn<?, ?> prodIdCol;

    @FXML
    private TableColumn<?, ?> prodNameCol;

    @FXML
    private TableColumn<?, ?> prodLguCol;

    @FXML
    private TableColumn<?, ?> prodBuyerCol;

    @FXML
    private TableColumn<?, ?> prodCostCol;

    @FXML
    private TableColumn<?, ?> prodPriceCol;

    @FXML
    private TableColumn<?, ?> prodSaleCol;

    @FXML
    private TableColumn<?, ?> prodOutlineCol;

    @FXML
    private TableColumn<?, ?> prodDetailCol;

    private IProdService service;   // Service객체 변수 선언
    private ObservableList<LprodVO> lprodCmbList;  	// 왼쪽 콤보박스의 데이터를 저장할 List 
    private ObservableList<ProdVO> prodCmbList;  	// 오른쪽 콤보박스의 데이터를 저장할 List 
    private ObservableList<ProdVO> prodTvList;  	// TableView의 데이터를 저장할 List 
    
    @FXML
    void initialize() {
    	// Service객체 생성
    	service = ProdServiceImpl.getInstance();
    	
    	// TableView의 각 컬럼 설정
    	prodIdCol.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
    	prodNameCol.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
    	prodLguCol.setCellValueFactory(new PropertyValueFactory<>("prod_lgu"));
    	prodBuyerCol.setCellValueFactory(new PropertyValueFactory<>("prod_buyer"));
    	prodCostCol.setCellValueFactory(new PropertyValueFactory<>("prod_cost"));
    	prodPriceCol.setCellValueFactory(new PropertyValueFactory<>("prod_price"));
    	prodSaleCol.setCellValueFactory(new PropertyValueFactory<>("prod_sale"));
    	prodOutlineCol.setCellValueFactory(new PropertyValueFactory<>("prod_outline"));
    	prodDetailCol.setCellValueFactory(new PropertyValueFactory<>("prod_detail"));
    	
    	// 왼쪽의 콤보박스 설정
    	
    	// 왼쪽의 콤보박스에 데이터 채워 넣기
    	List<LprodVO> lprodList = service.getAllLprod();
    	lprodCmbList = FXCollections.observableArrayList(lprodList);
    	cmbLprod.setItems(lprodCmbList);
    	cmbLprod.setValue(lprodCmbList.get(0));
    
    	// 왼쪽 콤보박스의 내용을 '상품분류명'으로 나타나도록 설정
    	cmbLprod.setCellFactory(new Callback<ListView<LprodVO>, ListCell<LprodVO>>() {
			@Override
			public ListCell<LprodVO> call(ListView<LprodVO> param) {
				return new ListCell<LprodVO>() {
					@Override
					protected void updateItem(LprodVO item, boolean empty) {
						super.updateItem(item, empty);
						if(empty) {
							setText(null);
						}else {
							setText(item.getLprod_nm());  // 상품분류명으로 지정
						}
					}
				};
			}
		});
    	
    	cmbLprod.setButtonCell(new ListCell<LprodVO>() {
    		@Override
    		protected void updateItem(LprodVO item, boolean empty) {
    			super.updateItem(item, empty);
    			if(empty) {
    				setText(null);
    			}else {
    				setText(item.getLprod_nm());
    			}
    		}
    	});
    	
    	// 왼쪽의 콤보박스에서 다른 항목을 선택했을 때 이벤트 처리
    	cmbLprod.valueProperty().addListener(new ChangeListener<LprodVO>() {
    		@Override
    		public void changed(ObservableValue<? extends LprodVO> observable,
    				LprodVO oldValue, LprodVO newValue) {
    			// 왼쪽의 콤보박스에서 선택한 항목의 lprod_gu값을 구한다.
    			String lprodGu = newValue.getLprod_gu();
    			
    			// 새롭게 선택한 lprod_gu값을 이용해서 오른쪽 콤보박스에 데이터 셋팅하기
    			List<ProdVO> prodList = service.getProdList(lprodGu);
    			
    			//  상품 분류에 맞는 상품목록이 없을 경우
    			if(prodList==null || prodList.size()==0) {
    				ProdVO temp = new ProdVO();
    				temp.setProd_name("해당 상품 없음");
    				prodCmbList = FXCollections.observableArrayList(temp);
    			}else {
    				prodCmbList = FXCollections.observableArrayList(prodList);
    			}
    			
    			cmbProd.setItems(prodCmbList);
    			cmbProd.setValue(prodCmbList.get(0));
    			
    		}
		});
    	
    	// 왼쪽 콤보박스 셋팅 끝....
    	//-------------------------------------------
    	
    	// 오른쪽 콤보박스 설정
    	
    	// 오른쪽의 콤보박스 내용을 '상품명'으로 나타나도록 설정
    	cmbProd.setCellFactory(new Callback<ListView<ProdVO>, ListCell<ProdVO>>() {
    		@Override
    		public ListCell<ProdVO> call(ListView<ProdVO> param) {
    			return new ListCell<ProdVO>() {
    				@Override
    				protected void updateItem(ProdVO item, boolean empty) {
    					super.updateItem(item, empty);
    					if(empty) {
    						setText(null);
    					}else {
    						setText(item.getProd_name());   // 상품명으로 설정
    					}
    				}
    			};
    		}
		});
    	
    	cmbProd.setButtonCell(new ListCell<ProdVO>() {
    		@Override
    		protected void updateItem(ProdVO item, boolean empty) {
    			super.updateItem(item, empty);
    			if(empty) {
					setText(null);
				}else {
					setText(item.getProd_name());   // 상품명으로 설정
				}
    		}
    	});
    	
    	// 오른쪽 콤보박스에서 데이터를 변경했을 때 이벤트 처리
    	cmbProd.valueProperty().addListener(new ChangeListener<ProdVO>() {
    		@Override
    		public void changed(ObservableValue<? extends ProdVO> observable, 
    				ProdVO oldValue, ProdVO newValue) {
    			if(newValue==null) return;
    			
    			// 현재 선택한 항목에서 prod_id값 구하기
    			String prodId = newValue.getProd_id();
    			
    			// 현재 선택한 prod_id에 맞는 상품 데이터를 구해서 TableView에 셋팅하기
    			List<ProdVO> tvList = service.getProd(prodId);
    			prodTvList = FXCollections.observableArrayList(tvList);
    			tvProd.setItems(prodTvList);
    			
    		}
		});
    	
    	
    	
    }
}








