package basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
	'd:/d_Other/Koala.jpg'파일을
	'd:/d_Other/연습용'폴더에 
	복사하는 프로그램을 작성하시오.
*/
public class FileCopyTest {

	public static void main(String[] args) {
		File sourceFile = new File("D:/D_Other/Koala.jpg");
		if(!sourceFile.exists()){
			System.out.println(sourceFile.getPath() + "는 없는 파일입니다. 작업 종료...");
			return;
		}
		
		File targetFile = new File("D:/D_Other/연습용/" + sourceFile.getName());
		
		try {
			// 복사할 입력용 파일 스트림 객체 생성
			FileInputStream fin = new FileInputStream(sourceFile);
			BufferedInputStream bin = new BufferedInputStream(fin);
			
			
			// 복사될 출력용 파일 스트림 객체 생성
			FileOutputStream fout = new FileOutputStream(targetFile);
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			
			int c;
			System.out.println("파일 복사 시작...");
			
//			while((c=fin.read()) != -1){
//				fout.write(c);
//			}
			
			while((c=bin.read()) != -1){
				bout.write(c);
			}
			bout.flush();
			
			System.out.println("파일 복사 완료...");
//			fin.close();
//			fout.close();
			bin.close();
			bout.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		

	}

}











