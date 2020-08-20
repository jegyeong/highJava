package basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultiChatClient {
	// 클라이언트가 시작되는 메서드
	public void clientStart() {
		Socket socket = null;
		try {
			String serverIp = "localhost";
			socket = new Socket(serverIp, 7777);
			// 메시지 전송용 Thread객체 생성
			ClientSender sender = new ClientSender(socket);
			
			// 메시지 수신용 Thread객체 생성
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (ConnectException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}
	}
	
	public static void main(String[] args) {
		new TcpMultiChatClient().clientStart();
	}
	
	// 메시지 전송용 Thread
	class ClientSender extends Thread{
		private Socket socket;
		private DataInputStream in;
		private DataOutputStream out;
		
		private String name;
		private Scanner scan;
		
		// 생성자
		public ClientSender(Socket socket) {
			this.socket = socket;
			scan = new Scanner(System.in);
			
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
				
				if(out!=null) {
					// 클라이언트는 처음 시작하면 대화명을 입력받아 서버로 전송하고
					// 대화명의 중복 여부를 feedback받아 확인한다.
					System.out.print("대화명 입력 : ");
					String name = scan.next();
					
					while(true) {
						out.writeUTF(name);  // 대화명 전송
						String feedback = in.readUTF();  // 대화명 중복 여부를 받는다.
						if("이름중복".equals(feedback)) {  // 대화명이 중복될 때
							System.out.println(name + "은 대화명이 중복됩니다. 다른 대화명을 입력하세요.");
							System.out.print("대화명 입력 : ");
							name = scan.next();
						}else { // 중복되지 않을 때
							scan.nextLine();  // 입력 버퍼 비우기
							this.name = name;
							System.out.println("[" + name + "] 대화명으로 대화방에 입장했습니다.");
							break;
						}
					}
				}
				
			} catch (IOException e) {
			}
			
		} // 생성자 끝..
		
		@Override
		public void run() {
			try {
				while(out!=null) {
					// 키보드로 입력한 데이터를 서버로 전송
					out.writeUTF("[" + name + "] : " + scan.nextLine());
				}
			} catch (IOException e) {
			}
		}
		
	}
	
	// 메시지 수신용 Thread
	class ClientReceiver extends Thread{
		private Socket socket;
		private DataInputStream in;
		
		// 생성자
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				// 서버로부터 메시지를 받을 InputStream객체 생성
				in = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
			}
		}
		
		@Override
		public void run() {
			while(in!=null) {
				try {
					// 서버로부터 받은 메시지를 화면에 출력한다.
					System.out.println(in.readUTF());
				} catch (IOException e) {
				}
			}
		}
		
	}
	
	

}








