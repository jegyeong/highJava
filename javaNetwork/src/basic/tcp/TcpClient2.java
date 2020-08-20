package basic.tcp;

import java.net.Socket;
import java.util.Scanner;

public class TcpClient2 {

	public static void main(String[] args) {
		Socket socket = null;
		Scanner scan = new Scanner(System.in);
		System.out.print("대화명 입력 : ");
		String name = scan.nextLine();
		
		try {
			socket = new Socket("localhost", 7777);
			
			Sender sender = new Sender(socket, name);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
