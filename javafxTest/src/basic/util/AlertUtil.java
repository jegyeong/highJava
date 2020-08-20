package basic.util;

import java.util.Optional;

import com.sun.org.apache.bcel.internal.generic.DALOAD;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

public class AlertUtil {
	
	public static void infoMsg(String head, String msg) {
		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle("정보창");
		info.setHeaderText(head);
		info.setContentText(msg);
		info.showAndWait();
	}
	
	public static void errorMsg(String head, String msg) {
		Alert err = new Alert(AlertType.ERROR);
		err.setTitle("오류창");
		err.setHeaderText(head);
		err.setContentText(msg);
		err.showAndWait();
	}
	
	public static void warnMsg(String head, String msg) {
		Alert warn = new Alert(AlertType.WARNING);
		warn.setTitle("경고창");
		warn.setHeaderText(head);
		warn.setContentText(msg);
		warn.showAndWait();
	}
	
	// OK버튼을 누르면 true, 그렇지 않으면 false 반환
	public static boolean confirm(String head, String msg) {
		Alert conf = new Alert(AlertType.CONFIRMATION);
		conf.setTitle("선택창");
		conf.setHeaderText(head);
		conf.setContentText(msg);
		ButtonType result = conf.showAndWait().get();
		
		boolean select = false;
		if(result == ButtonType.OK) {
			select = true;
		}
		
		return select;
	}
	
	// 사용자로부터 값을 입력받아 그 값을 문자열로 반환하는 메서드
	// 취소버튼을 누르거나, 창을 그냥 닫으면 null값 반환
	public static String prompt(String msg) {
		String strResult = null;
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("입력창");
		dialog.setHeaderText(msg);
		dialog.setContentText("입력 : ");
		
		Optional<String> result = dialog.showAndWait();
		
		if(result.isPresent()) {
			strResult = result.get();
		}
		
		return strResult;
		
	}
	
}
