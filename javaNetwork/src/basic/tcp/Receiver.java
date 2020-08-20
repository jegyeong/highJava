package basic.tcp;

import java.io.DataInputStream;
import java.net.Socket;

// 이 클래스는 소켓에서 메시지를 받아서 
// 그 메시지를 화면에 출력하는 일을 담당하는 쓰레드이다.
public class Receiver extends Thread {
	private Socket socket;
	DataInputStream dis;
	
	// 생성자  ==> 연결된 Socket객체를 매개값으로 받아서 처리한다.
	public Receiver(Socket socket) {
		this.socket = socket;
		try {
			// Socket을 이용해서 스트림 객체 생성
			dis = new DataInputStream(socket.getInputStream()); 
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		while(dis!=null) {
			try {
				// 받아온 메시지 출력하기
				System.out.println(dis.readUTF());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}
