package basic.crypto;

public class CryptoTest {

	public static void main(String[] args) throws Exception {
		String str = "Hello, world!";
		
		System.out.println("MD5 : " + CryptoUtil.md5(str));
		System.out.println("SHA-256 : " + CryptoUtil.sha256(str));
		System.out.println("SHA-512 : " + CryptoUtil.sha512(str));
		
		System.out.println("------------------------------");
		AES256Util aes256 = new AES256Util();
		String temp = "Hello, World!!";
		String strTest = aes256.encrypt(temp);
		System.out.println("원 본 값 : " + temp);
		System.out.println("암호화 값 : " + strTest);
		System.out.println("복호화 값 : " + aes256.decrypt(strTest));
		
		
		

	}

}
