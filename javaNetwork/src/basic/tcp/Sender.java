package basic.tcp;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

// 이 클래스는 소켓을 통해서 메시지를 보내는 역할을 담당하는 쓰레드이다.
public class Sender extends Thread{
	private Socket socket;
	private DataOutputStream dos;
	private String name;
	
	// 생성자
	public Sender(Socket socket, String name) {
		this.name = name;
		this.socket = socket;
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
		Scanner scan = new Scanner(System.in);
		while(dos!=null) {
			try {
				// 키보드로 한 줄 입력한 메시지 내용을 보낸다.
				dos.writeUTF(name + " : " +  scan.nextLine() );
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
