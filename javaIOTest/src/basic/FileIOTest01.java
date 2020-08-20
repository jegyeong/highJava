package basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {

	public static void main(String[] args) {
		// 'd:/d_other/test.txt' 파일의 내용을 읽어와서 출력하기
		try {
			// 바이트 기반의 스트림을 이용한 예제
			// 방법1 ==> 파일정보를 문자열로 지정하는 방법
			FileInputStream fin = new FileInputStream("d:/d_other/test.txt");
			
			// 방법2 ==> 파일 정보를 갖고 있는 File객체를 지정하는 방법
//			File file = new File("d:/d_other/test.txt");
//			FileInputStream fin = new FileInputStream(file);
			
			int c; 	// 읽어온 자료를 저장할 변수
			
			// 읽어온 값이 -1이면 파일의 끝까지 모두 읽었다는 의미이다.
			while((c = fin.read()) != -1){
				
				// 읽어온 문자 출력하기
				System.out.print( (char)c );
			}
			
			fin.close();   // 작업 완료 후 스트림 닫기
			
			
		} catch (IOException e) {
			System.out.println("파일이 없거나 읽기 오류 입니다.");
		}
		

	}

}
