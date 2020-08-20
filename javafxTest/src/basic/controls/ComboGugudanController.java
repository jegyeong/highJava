package basic.controls;

import java.net.URL;
import java.util.ResourceBundle;

import basic.util.AlertUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.util.Callback;

public class ComboGugudanController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> cmbDan;

    @FXML
    private Button btnOut;

    @FXML
    private TextArea taResult;

    // Button을 클릭했을 때 이벤트 처리
    @FXML
    void btnOutClicked(ActionEvent event) {
    	// 콤보박스에서 선택한 값을 읽어온다.
    	Integer dan = cmbDan.getValue();
    	
    	if(dan==null) {
//    		Alert alert = new Alert(AlertType.ERROR);
//    		alert.setTitle("오류");
//    		alert.setHeaderText("작업 오류");
//    		alert.setContentText("출력할 단을 선택하세요.");
//    		alert.showAndWait();
    		AlertUtil.errorMsg("작업오류", "출력할 단을 선택하세요");
    		return;
    	}
    	
    	
    	taResult.setText(dan + " 단\n\n");
    	
    	for(int i=1; i<=9; i++) {
    		int r = dan * i;
    		taResult.appendText(dan + " * " + i + " = " + r + "\n");
    	}
    	
    	
    	
    }

    ObservableList<Integer> danList = FXCollections.observableArrayList();
    @FXML
    void initialize() {
    	//cmbDan.getItems().addAll(1,2,3,4,5,6,7,8,9);
    	
    	danList.addAll(1,2,3,4,5,6,7,8,9);
    	cmbDan.setItems(danList);
    	
    	cmbDan.setCellFactory(new Callback<ListView<Integer>, ListCell<Integer>>() {
			@Override
			public ListCell<Integer> call(ListView<Integer> param) {
				return new ListCell<Integer>() {
					@Override
					protected void updateItem(Integer item, boolean empty) {
						super.updateItem(item, empty);
						if(empty) {
							setText(null);
						}else {
							setText(item + " 단");
						}
					}
				};
			}
		});
    	
    	cmbDan.setButtonCell(new ListCell<Integer>() {
    		@Override
    		protected void updateItem(Integer item, boolean empty) {
    			super.updateItem(item, empty);
    			if(empty) {
					setText(null);
				}else {
					setText(item + " 단");
				}
    		}
    	});
    }
}
