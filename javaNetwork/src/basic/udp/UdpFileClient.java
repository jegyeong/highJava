package basic.udp;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpFileClient {

	public static void main(String[] args) {
		// D:/D_Other/Koala.jpg 파일을 서버로 전송한다.
		File file = new File("D:/D_Other/koala.jpg");
		if(!file.exists()) {
			System.out.println("전송할 파일이 없습니다. 프로그램을 종료합니다.");
			return;
		}
		
		System.out.println("파일 전송 시작...");
		System.out.println();
		
		DatagramSocket socket = null;
		try {
			// 데이터 전송 순선 : "start", 파일 이름 , 파일 용량, 파일 데이터, "end"
			InetAddress address = InetAddress.getByName("localhost");
			socket = new DatagramSocket();
			
			//--------------------------------------------------------
			String str = "start";
			
			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.getBytes().length,
					address, 8888);
			socket.send(dp);
			//---------------------------------------------------------
			String fileName = file.getName();
			dp = new DatagramPacket(fileName.getBytes(), fileName.getBytes().length,
					address, 8888);
			socket.send(dp);
			//----------------------------------------------------------
			long fileSize = file.length();
			String strSize = String.valueOf(fileSize);
			dp = new DatagramPacket(strSize.getBytes(), strSize.getBytes().length,
					address, 8888);
			socket.send(dp);
			//-----------------------------------------------------------
			
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			long totalLen = 0;
			while((len = fis.read(buffer))!=-1) {
				dp = new DatagramPacket(buffer, len, address, 8888);
				socket.send(dp);
				totalLen += len;
				
				System.out.println("현재 진행 상태 : " + totalLen + " / " + fileSize + " byte(s)");
			}
			
			//--------------------------------------------------------
			str = "end";
			
			dp = new DatagramPacket(str.getBytes(), str.getBytes().length,
					address, 8888);
			socket.send(dp);
			
			fis.close();
			socket.close();
			
			System.out.println("\n\n전송 완료...");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
