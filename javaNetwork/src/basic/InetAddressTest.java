package basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		// InetAddress클래스 ==> IP주소를 다루기 위한 클래스
		
		// Naver사이트의 IP주소 가져오기
		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		System.out.println("HostName : " + naverIp.getHostName());
		System.out.println("HostAddress : " + naverIp.getHostAddress());
		System.out.println();
		
		// 자신의 컴퓨터 Ip주소 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("HostName : " + localIp.getHostName());
		System.out.println("HostAddress : " + localIp.getHostAddress());
		System.out.println();
		
		// IP주소가 여러개인 호스트의 정보 가져오기
		//InetAddress[] naverArrays = InetAddress.getAllByName("www.naver.com");
		//InetAddress[] naverArrays = InetAddress.getAllByName("www.daum.net");
		//InetAddress[] naverArrays = InetAddress.getAllByName("www.nate.com");
		InetAddress[] naverArrays = InetAddress.getAllByName("ddit.or.kr");
		for(InetAddress nIp : naverArrays) {
			System.out.println(nIp.toString());
		}
		
		
		
		
		
		
		
	}

}
