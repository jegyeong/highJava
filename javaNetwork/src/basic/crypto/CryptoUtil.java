package basic.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil {
	// byte배열을 Hex문자열(16진수 문자열)로 변환하는 메서드
	public static String byteToHexString(byte[] data) {
		StringBuilder sb = new StringBuilder();
		
		for(byte b : data) {
			// b & 0xff  ==> 2자리 이하의 16진수 만들기
			// (b & 0xff) + 0x100 ==>   1xx와 같은 3자리 값이 된다.
			sb.append(Integer.toHexString( (b & 0xff) + 0x100).substring(1) ); // 항상 2자리의 16진수가 되도록
		}
		return sb.toString();
	}
	
	// 단방향 암호화 방식
	
	// MD5 방식 --> 32byte
	public static String md5(String msg) throws NoSuchAlgorithmException {
		// 암호화를 수행하는 객체 생성
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(msg.getBytes());  // 암호화 수행
		
		// 암호화된 결과는 digest()메서드 이용
		return CryptoUtil.byteToHexString(md.digest());
	}
	
	// SHA-256 방식 - 64byte
	public static String sha256(String msg) throws NoSuchAlgorithmException {
		// 암호화를 수행하는 객체 생성
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(msg.getBytes());  // 암호화 수행
		
		// 암호화된 결과는 digest()메서드 이용
		return CryptoUtil.byteToHexString(md.digest());
	}
		
	// SHA-512 방식 - 128byte
	public static String sha512(String msg) throws NoSuchAlgorithmException {
		// 암호화를 수행하는 객체 생성
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update(msg.getBytes());  // 암호화 수행
		
		// 암호화된 결과는 digest()메서드 이용
		return CryptoUtil.byteToHexString(md.digest());
	}
}
