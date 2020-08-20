package basic.thread;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ThreadTimerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblTime;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnStop;
    
    private boolean isStop;    // 쓰레드가 멈출지 여부를 나타내는 변수 (true이면 멈춘다.)
    

    // 시작 버튼 이벤트 처리
    @FXML
    void btnStartClicked(ActionEvent event) {
    	isStop = false;
    	
    	Thread th = new Thread() {
    		@Override
    		public void run() {
    			// 시간 출력 형식 설정
    			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    			
    			while(!isStop) {
    				// 현재 시스템의 시간 정보를 가져와 시간 출력 형식에 맞는 문자열을 구한다.
    				String strTime = sdf.format( new Date() );
    				
    				// 일반 쓰레드에서 javaFX의 UI컨트롤의 내용을 변경하려면
    				// Platform.runLater()메서드를 이용해서 출력해야 한다.
    				// 이 메서드에는 Runnable인터페이스를 구현한 구현체가 매개값으로 들어간다.
    				
    				Platform.runLater(()->{
    					// 이 영역에 UI컨트롤을 변경하는 명령을 사용한다.
    					lblTime.setText(strTime);
    					
    				});
    				
    				
    				try {	Thread.sleep(100);	} catch (InterruptedException e) {}
    				
    			}
    		}
    	};  // 쓰레드 끝...
    	
    	th.setDaemon(true);
    	th.start();
    	
    }

    // 종료 버튼 이벤트 처리
    @FXML
    void btnStopClicked(ActionEvent event) {
    	isStop = true;
    }

    @FXML
    void initialize() {

    }
}
