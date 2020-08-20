package basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient1 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// 서버에 접속하기 위해 Socket객체 생성 
		String serverIp = "localhost";   // 서버에 접속할 IP주소 지정
		// 자기 자신 컴퓨터를 나타내는 방법
		// 1) 127.0.0.1
		// 2) localhost

		System.out.println("서버에 연결합니다...");
		// Socket을 생성해서 연결을 요청한다.
		Socket socket = new Socket(serverIp, 7777);  // 서버의 IP와 포트 번호 지정
		
		// 위에서 Socket객체가 정상적으로 생성되었다는 말은 서버와 연결이 완료되었다는 말과 같다.
		// 이 부분에서는 서버와 연결이 완료된 이후의 내용을 기술한다.
		System.out.println("서버에 연결되었습니다...");
		System.out.println();
		
		System.out.println("접속한 서버 정보");
		System.out.println("서버의 IP 주소 : " + socket.getInetAddress().getHostAddress());
		System.out.println("서버의 Port 번호 : " + socket.getPort());
		System.out.println();
		
		System.out.println("연결된 클라이언트 정보");
		System.out.println("IP 주소 : " + socket.getLocalAddress());
		System.out.println("Port 번호 : " + socket.getLocalPort());
		System.out.println("=======================================");
		
		// 서버가 보낸 메시지를 받아서 출력하기
		
		// Socket의 InputStream객체를 생성한다.
		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);
		
		// 서버로부터 메시지를 받아 출력한다.
		System.out.println("서버에서 온 메시지 : " + dis.readUTF());
		
		System.out.println("연결을 종료합니다....");
		
		// 소켓과 스트림 닫기
		dis.close();
		socket.close();
		

	}

}
