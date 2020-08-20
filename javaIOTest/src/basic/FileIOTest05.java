package basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest05 {
	/*
		인코딩을 지정해서 파일 내용 읽어오기
			==> InputStreamReader 객체를 이용한다.
	*/
	public static void main(String[] args) {
		File fileAnsi = new File("d:/d_other/test_ansi.txt");
		File fileUtf8 = new File("d:/d_other/test_utf8.txt");
		
		try {
			// 기반이 되는 FileInputStream객체를 생성한 후 이 객체를 
			// InputStreamReader객체를 생성할 때 매개값으로 넣어서 생성한다.
			FileInputStream fin = new FileInputStream(fileAnsi);  // 기반이 되는 스트림 객체 생성
			//FileInputStream fin = new FileInputStream(fileUtf8);  // 기반이 되는 스트림 객체 생성
			
			// 파일의 인코딩은 운영체제와 해당 java파일의 인코딩에 영향을 받는다.
			//InputStreamReader isr = new InputStreamReader(fin);	 // 보조 스트림 객체 생성
			
			// InputStreamReader객체는 파일의 인코딩 방식을 지정할 수 있다.
			//   지정하는 인코딩 문자열
			//   - MS949  ==> 윈도우의 기본 한글 인코딩 방식(ANSI방식)
			//   - UTF-8  ==> 유니코드 utf-8 인코딩 방식
			//   - US-ASCII ==> 영문 전용 인코딩 방식
			//InputStreamReader isr = new InputStreamReader(fin, "MS949"); // 보조 스트림 객체 생성
			//InputStreamReader isr = new InputStreamReader(fin, "UTF-8"); // 보조 스트림 객체 생성
			InputStreamReader isr = new InputStreamReader(fin, "US-ASCII"); // 보조 스트림 객체 생성
			
			int c;
			while((c=isr.read()) != -1){
				System.out.print((char)c);
			}
			
			isr.close();		// 보조 스트림 객체를 닫으면 기반이 되는 스트림을 자동으로 닫힌다.
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
