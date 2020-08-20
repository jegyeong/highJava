package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ClientMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfIp;

    @FXML
    private TextField tfPort;

    @FXML
    private Button btnJoin;

    @FXML
    private TextArea taOutputMsg;

    @FXML
    private TextField tfMsg;

    @FXML
    private Button btnSend;
    
    @FXML
    private TextField tfName;
    
    private Socket clientSocket= null;

    @FXML
    void initialize() {
        assert tfIp != null : "fx:id=\"tfIp\" was not injected: check your FXML file 'ClientMain.fxml'.";
        assert tfPort != null : "fx:id=\"tfPort\" was not injected: check your FXML file 'ClientMain.fxml'.";
        assert btnJoin != null : "fx:id=\"btnJoin\" was not injected: check your FXML file 'ClientMain.fxml'.";
        assert taOutputMsg != null : "fx:id=\"taOutputMsg\" was not injected: check your FXML file 'ClientMain.fxml'.";
        assert tfMsg != null : "fx:id=\"tfMsg\" was not injected: check your FXML file 'ClientMain.fxml'.";
        assert btnSend != null : "fx:id=\"btnSend\" was not injected: check your FXML file 'ClientMain.fxml'.";
        
        tfPort.setPromptText("4040");
        
        btnJoin.setOnAction(e->{ socketConnect(e); } ); 
        tfMsg.setOnKeyPressed(e -> tfMsgSend(e));
        btnSend.setOnAction(e-> btnMsgSend(e) );

    }
    
    public void msgSend(){
    	try {
        	if(clientSocket==null){
        		taOutputMsg.setText("서버에 접속되지 않았습니다.\n");
        		return;
        	}
        	
        	// 입력창에서 입력 후 'Enter'키를 누르면 메시지 전송
            String sendMessage = tfName.getText() + " : " + tfMsg.getText();
            byte[] byteArray = sendMessage.getBytes("UTF-8");
            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write(byteArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tfMsg.clear();
    }
    
    public void btnMsgSend(ActionEvent event){
    	msgSend();
    }
    
    
    public void tfMsgSend(KeyEvent event){
    	if (event.getCode() == KeyCode.ENTER) {
            msgSend();
        }
    }
    
    // 서버에 접속하기
    public void socketConnect(ActionEvent event){
    	if (btnJoin.getText().equals("서버 접속")) {
    		try {
    			if(tfIp.getText().isEmpty()){
    				taOutputMsg.setText("접속할 서버의 IP주소를 입력하세요");
    				tfIp.requestFocus();
    				return;
    			}
				
    			if(tfPort.getText().isEmpty()){
    				taOutputMsg.setText("접속할 서버의 Port번호를 입력하세요");
    				tfPort.requestFocus();
    				return;
    			}
    			
    			if(tfName.getText().isEmpty()){
    				taOutputMsg.setText("이름을 입력하세요");
    				tfName.requestFocus();
    				return;
    			}
    			
    			// 방법1
    			clientSocket = new Socket(tfIp.getText(), Integer.parseInt(tfPort.getText()));
    			
    			/*
    			// 방법2
    			clientSocket = new Socket();
				//clientSocket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 4040));
    			clientSocket.connect(new InetSocketAddress(tfIp.getText(), Integer.parseInt( tfPort.getText())) );
    			*/
    			tfIp.setDisable(true);
    			tfPort.setDisable(true);
    			tfName.setDisable(true);
    			
    			taOutputMsg.setDisable(false);
    			tfMsg.setDisable(false);
    			btnSend.setDisable(false);
    			
				taOutputMsg.setText("서버에 접속하였습니다.\n\n");
				btnJoin.setText("접속 종료");
			
				try {
					// 서버 접속후 '이름'을 서버로 전송
					String sendMessage = tfName.getText();
					byte[] byteArray = sendMessage.getBytes("UTF-8");
					OutputStream outputStream = clientSocket.getOutputStream();
					outputStream.write(byteArray);
					outputStream.flush();
						 
				} catch (Exception e) {
						 e.printStackTrace();
				}
				// ClientReader객체 생성
				ClientReader clientReader = new ClientReader(clientSocket);
				clientReader.setDaemon(true);
				clientReader.start();
    		}catch (ConnectException e){
    			taOutputMsg.setText("서버가 꺼져있습니다.\n\n");
    		} catch (Exception e) {
				e.printStackTrace();
			}
		 } else if (btnJoin.getText().equals("접속 종료")) {
			 try {
				 tfIp.setDisable(false);
				 tfPort.setDisable(false);
				 tfName.setDisable(false);
    			
				 taOutputMsg.setDisable(true);
				 tfMsg.setDisable(true);
				 btnSend.setDisable(true);
				 
				 taOutputMsg.setText("\n서버에 접속해제하였습니다.\n\n");
				 btnJoin.setText("서버 접속");
				 clientSocket.close();
		     } catch (Exception e) {
		    	 if(!clientSocket.isClosed())
		    		 e.printStackTrace();
		     }
		 }
    }
    
    
    // Reader class
    class ClientReader extends Thread {
        Socket clientSocket = null;
        String readMessage = null;
 
        ClientReader(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }
 
        @Override
        public void run() {
            try {
                while (true) {
                	
                    InputStream inputStream = clientSocket.getInputStream();
                    byte[] byteArray = new byte[256];
                    int size = inputStream.read(byteArray);
                    if (size == -1){
                    	break;  // 강제종료일 경우
                    }
                    readMessage = new String(byteArray, 0, size, "UTF-8");
                    if(readMessage.equals("FFFF")) {
                        tfMsg.clear();
                        taOutputMsg.clear();
                        
                        readMessage = "서버가 종료되었습니다";    
                        Platform.runLater(() -> {
                        	tfIp.setDisable(false);
	           				tfPort.setDisable(false);
	           				tfName.setDisable(false);
	               			
	           				taOutputMsg.setDisable(true);
	           				tfMsg.setDisable(true);
	           				btnSend.setDisable(true);
	           				 
	           				btnJoin.setText("서버 접속");
                        });
                        if(!clientSocket.isClosed()){
                        	clientSocket.close();
                        }
                    }
                    taOutputMsg.appendText(readMessage + "\n");

                }
            } catch (SocketException e){
            	//System.out.println("--> " + clientSocket.isClosed());
            	if(!clientSocket.isClosed()){
            		try {	
            			clientSocket.close();  
            			taOutputMsg.appendText("서버가 종료되었습니다\n");
            		} catch (IOException e1) {	}
            	}
            } catch (Exception e) {
            		e.printStackTrace();
            }
        }
    }
}
