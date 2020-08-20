package basic.tcp;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer1 {

	public static void main(String[] args) throws IOException {
		// Tcp 소켓 통신을 하기위해 ServerSocket객체 생성
		// 포트번호는 0~1024번까지는 시스템에서 사용하는 번호이므로 이 번호 이후의 번호를 사용한다.
		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버가 접속을 준비중입니다...");
		
		
		// accept()메서드  ==> 클라이언트의 연결 요청이 올 때까지 계속 기다린다.
		//		==> 연결 요청이 오면 새로운 Socket객체를 생성해서 클라이언트의 Socket과 연결한다.
		Socket socket = server.accept();
		
		// accept()메서드 호출 이후의 내용은 서버와 클라이언트가 접속 된 후에 처리할 내용을 기술한다.
		System.out.println("클라이언트와 연결되었습니다...");
		System.out.println();
		System.out.println("접속한 클라이언트 정보");
		System.out.println("IP 주소 : " + socket.getInetAddress().getHostAddress());
		System.out.println("Port 번호 : " + socket.getPort());
		System.out.println();
		
		System.out.println("연결된 서버 정보");
		System.out.println("서버의 IP 주소 : " + socket.getLocalAddress());
		System.out.println("서버의 Port 번호 : " + socket.getLocalPort());
		System.out.println("=======================================");
		
		
		// Client에게 메시지 보내기  ==> Socket의 OutputStream을 이용해서 전송한다.
		
		// 스트림 객체 생성
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		// 메시지 보내기
		dos.writeUTF("어서오세요 서버에 접속한걸 환영합니다...");
		System.out.println("메시지를 보냈습니다...");
		
		// 소켓과 스트림 객체 닫기
		dos.close();
		socket.close();
		server.close();
		
	}

}
