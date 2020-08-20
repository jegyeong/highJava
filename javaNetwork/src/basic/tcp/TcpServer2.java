package basic.tcp;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpServer2 {

	public static void main(String[] args) {
		// 서버소켓을 만들고, 클라이언트가 접속해 오면 소켓을 생성해서 
		// 데이터를 받는 쓰레드와 데이터를 보내는 쓰레드에 이 소켓을 넘겨준다.
		ServerSocket server = null;
		Socket socket = null;
		Scanner scan = new Scanner(System.in);
		System.out.print("대화명 입력 : " );
		String name = scan.nextLine();
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다...");
			
			socket = server.accept();
			
			Sender sender = new Sender(socket, name);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
