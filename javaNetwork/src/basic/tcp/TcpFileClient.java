package basic.tcp;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpFileClient {
	private Socket socket;
	
	public void clientStart() {
		File file = new File("D:/D_Other/Koala.jpg");
		if(!file.exists()) {
			System.out.println("전송할 파일이 없습니다.");
			return;
		}
		
		try {
			socket = new Socket("localhost", 9999);
			System.out.println("파일 전송 시작...");
			
			FileInputStream fin = new FileInputStream(file);
			OutputStream os = socket.getOutputStream();
			
			byte[] tmp = new byte[1024];
			int len = 0;
			while((len=fin.read(tmp))!=-1) {
				os.write(tmp, 0, len);
			}
			
			System.out.println("파일 전송 끝...");
			
			fin.close();
			os.close();
			socket.close();
			
		} catch (Exception e) {
			
		}
		
	}
	
	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}

}
