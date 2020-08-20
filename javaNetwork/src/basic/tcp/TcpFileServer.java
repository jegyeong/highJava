package basic.tcp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
	private ServerSocket server;
	private Socket socket;
	
	public void serverStart() {
		// 저장할 파일 객체 생성
		File file = new File("D:/D_Other/test/Koala.jpg");
		
		try {
			server = new ServerSocket(9999);
			System.out.println("서버가 준비되었습니다.");
			
			socket = server.accept();
			
			System.out.println("파일 다운로드 시작...");
			InputStream in = socket.getInputStream();
			FileOutputStream fos = new FileOutputStream(file);
			
			byte[] tmp = new byte[1024];
			int length = 0;
			while((length=in.read(tmp))!=-1) {
				fos.write(tmp, 0, length);
			}
			
			System.out.println("파일 다운로드 완료...");
			
			fos.close();
			in.close();
			socket.close();
			server.close();
			
			
		} catch (Exception e) {	e.printStackTrace();}
	}
	
	public static void main(String[] args) {
		new TcpFileServer().serverStart();
	}

}
