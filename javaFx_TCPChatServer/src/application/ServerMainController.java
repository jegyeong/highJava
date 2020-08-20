package application;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ServerMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnServerStart;

    @FXML
    private Label lblCount;

    @FXML
    private TextArea taOutputMsg;
    
    // 접속한 소켓을 저장할 list --> (접속자리스트)
    private List<Socket> socketList = Collections.synchronizedList(new ArrayList<Socket>()) ;
    
    ServerSocket mainServerSocket= null;

    @FXML
    void initialize() {
        assert btnServerStart != null : "fx:id=\"btnServerStart\" was not injected: check your FXML file 'ServierMain.fxml'.";
        assert lblCount != null : "fx:id=\"lblCount\" was not injected: check your FXML file 'ServierMain.fxml'.";
        assert taOutputMsg != null : "fx:id=\"taOutputMsg\" was not injected: check your FXML file 'ServierMain.fxml'.";
        
        btnServerStart.setOnAction(e-> { serverStart(e); });
    }
    
    public void serverStart(ActionEvent event){
    	if (btnServerStart.getText().equals("Server Start")) {
				try {
					mainServerSocket = null;
					taOutputMsg.setText("ip : " + InetAddress.getLocalHost() + "\n");
					
					// 방법1
					//mainServerSocket = new ServerSocket(port번호);
					
					// 방법2
					mainServerSocket = new ServerSocket();
					mainServerSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 4040));
					
					taOutputMsg.appendText("서버가 열렸습니다.\n\n");
					btnServerStart.setText("Server Stop");
					lblCount.setText(socketList.size() + " 명");
				
					ConnectThread connectThread = new ConnectThread(mainServerSocket);
					connectThread.setDaemon(true);
					connectThread.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
    	} else if (btnServerStart.getText().equals("Server Stop")) {
    		try {
    			taOutputMsg.appendText("\n서버가 닫혔습니다.\n\n");
                btnServerStart.setText("Server Start");                    
                
                // to send 'SERVER CLOSE' message to Client
                String sendMessage = "FFFF";
                byte[] byteArray = sendMessage.getBytes("UTF-8");
                for (int i = 0; i < socketList.size(); i++) {
                    OutputStream outputStream = socketList.get(i).getOutputStream();
                    outputStream.write(byteArray);
                }
                socketList.clear();
                mainServerSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    //------------------------------------------------------------------------------
    // Connect class
    class ConnectThread extends Thread {
    	ServerSocket mainServerSocket = null;
 
        ConnectThread(ServerSocket mainServerSocket) {
            this.mainServerSocket = mainServerSocket;
        }
 
        @Override
        public void run() {
            try {
                while (true) {
                    Socket socket = mainServerSocket.accept();
                    
                    //outputField.setText("사용자 접속!");
 
                    socketList.add(socket);
                    Platform.runLater(() -> {
                        lblCount.setText(socketList.size() + " 명");
                    });
 					
                    // 접속한 사용자가 처음 보낸 메시지 받기 ( 사용자 이름 받기)
                    InputStream inputStream = socket.getInputStream();
                    byte[] byteArray = new byte[256];
                    int size = inputStream.read(byteArray);
 
                    String readMessage = new String(byteArray, 0, size, "UTF-8");
                    Platform.runLater(() -> {
                        taOutputMsg.appendText(readMessage + "님 접속\n");
                    });
                    
                    //--
                    
                    String sendMessage = readMessage +"님이 접속하셨습니다.";
                    byteArray = sendMessage.getBytes("UTF-8");
                    for (int i = 0; i < socketList.size(); i++) {
                    	if (socket != socketList.get(i)){
	                        OutputStream outputStream = socketList.get(i).getOutputStream();
	                        outputStream.write(byteArray);
	                        outputStream.flush();
                    	}
                    }
                    //--
                    
                    ServerReader serverReader = new ServerReader(socket);
                    serverReader.start();
                }
            } catch (Exception e) {
            	//System.out.println("==> " + mainServerSocket.isClosed());
            	if(!mainServerSocket.isClosed())
            		e.printStackTrace();
            }
        }
    }
    //-------------------------------------------------------------------------------
    // Reader class
    class ServerReader extends Thread {
        Socket socket = null;
 
        ServerReader(Socket socket) {
            this.socket = socket;
        }
 
        @Override
        public void run() {
            try {
                while (true) {
                    InputStream inputStream = socket.getInputStream();
                    byte[] byteArray = new byte[256];
                    int size = inputStream.read(byteArray);
                    //System.out.println("<<<<<============>>>> size = " + size);
                    if (size == -1) { // 클라이언트이 '접속종료'버튼으로 접속을 끊었을 때.
                        for (int i = 0; i < socketList.size(); i++) {
                            if (socket == socketList.get(i)) {
                            	socketList.remove(i);
                            	break;
                            } 
                        }
                        Platform.runLater(() -> {
                        	lblCount.setText(socketList.size() + " 명- while");
                        });
                        break;
                    }
 
                    String readMessage = new String(byteArray, 0, size, "UTF-8");
                    //System.out.println("li.size() = " + socketList.size());
                    byteArray = readMessage.getBytes("UTF-8");
                    //outputField.setText(readMessage);
                    
                    //--------------------------
                    for (int i = 0; i < socketList.size(); i++) {
							OutputStream outputStream = socketList.get(i).getOutputStream();
							outputStream.write(byteArray);
							//System.out.println("byteArray(Server) => " + byteArray);
							outputStream.flush();
                    }
                    //---------------------------
                    
                }
            } catch (Exception e) {
            	// 클라이언트 프로그램이 종료되었을 때.
                for (int i = 0; i < socketList.size(); i++) {
                    if (socket == socketList.get(i)) {
                    	socketList.remove(i);
                    	break;
                    } 
                }
                Platform.runLater(() -> {
                	lblCount.setText(socketList.size() + " 명");
                });
            }
        }
    }
    //-----------------------------------------------------------------------------
    
}
