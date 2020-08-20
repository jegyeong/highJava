package basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {

	public static void main(String[] args) {
		// 'd:/d_other/out.txt'파일에 출력하기
		
		File file = new File("d:/d_other/out.txt");
		try {
			// 바이트 기반의 출력 스트림을 이용한 예제
			FileOutputStream fout = new FileOutputStream(file);
			
			for(char ch='A'; ch<='Z'; ch++){
				fout.write(ch);  	// FileStream으로 출력하기
			}
			fout.close();  // 쓰기 완료 후 스트림 닫기
			
			System.out.println("파일에 쓰기 작업 완료!!");
		} catch (IOException e) {
			System.out.println("파일에 쓰기 오류입니다.");
		}
		
		System.out.println("--------------------------------------------");
		//------------------------------------------------
		// 위에서 저장한 파일 내용을 읽어와 출력하시오.
		try {
			FileInputStream fin = new FileInputStream(file);
			int c;
			while((c=fin.read()) != -1){
				System.out.print( (char)c );
			}
			
			fin.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
