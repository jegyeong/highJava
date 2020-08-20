package basic.crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES256Util {
	private String iv;
	private Key keySpec;
	
	// 16자리의 키값을 설정한다.
	private String key = "1a2s3d4f5g6h7j8k";  
	
	// 생성자
	public AES256Util() throws UnsupportedEncodingException {
		this.iv = key.substring(0, 16);
		
		byte[] keyBytes = new byte[16];
		byte[] b = key.getBytes("UTF-8");
		int len = b.length;
		if(len>keyBytes.length) {
			len = keyBytes.length;
		}
		
		System.arraycopy(b, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		this.keySpec = keySpec;
	}
	
	
	// 암호화 하는 메서드
	public String encrypt(String str) throws NoSuchAlgorithmException,
		NoSuchPaddingException, InvalidKeyException, 
		InvalidAlgorithmParameterException, 
		IllegalBlockSizeException, BadPaddingException, 
		UnsupportedEncodingException {
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		
		String enStr = Base64.getEncoder().encodeToString(encrypted);
		return enStr;
		
	}
	
	public String decrypt(String str) throws NoSuchAlgorithmException, 
		NoSuchPaddingException, InvalidKeyException, 
		InvalidAlgorithmParameterException, UnsupportedEncodingException,
		IllegalBlockSizeException, BadPaddingException {
		
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
		
		byte[] byteStr = Base64.getDecoder().decode(str);
		
		return new String(c.doFinal(byteStr), "UTF-8");
	}
	
}






