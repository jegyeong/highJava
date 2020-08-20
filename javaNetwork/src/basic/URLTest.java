package basic;

import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {
	// URL클래스 ==> 인터넷에 존재하는 서버들의 자원에 접근할 수 있는 주소를 다루는 클래스
	// URLConnection클래스 ==> 어플리케이션과 URL간의 통신연결을 위한 추상 클래스
	

	public static void main(String[] args) throws MalformedURLException {
		// http://www.ddit.or.kr:80/index.html?ttt=123
		
		//URL url = new URL("http://www.ddit.or.kr:80/index.html?ttt=123");
		URL url = new URL("http", "www.ddit.or.kr", 80, "/index.html?ttt=123");
		
		System.out.println("프로토콜 : " + url.getProtocol());
		System.out.println("Host : " + url.getHost());
		System.out.println("file : " + url.getFile());
		System.out.println("Query : " + url.getQuery());
		System.out.println("path : " + url.getPath());
		System.out.println("port : " + url.getPort());
		System.out.println();
		
		
		System.out.println(url.toExternalForm());
		
		

	}

}
