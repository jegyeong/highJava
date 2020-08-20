package basic;

import java.io.FileReader;
import java.io.IOException;

public class FileIOTest03 {

	public static void main(String[] args) {
		// 'd:/d_other/test.txt'파일 읽어와 출력하기
		
		try {
			// 문자기반의 스트림을 이용한 예제
			FileReader fr = new FileReader("d:/d_other/test.txt");
			
			int c;
			
			while((c=fr.read()) != -1){
				System.out.print( (char)c );
			}
			
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("파일이 없거나 읽기 오류입니다.");
		}
		

	}

}
