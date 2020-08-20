package basic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BufferedIOTest02 {

	public static void main(String[] args) throws IOException {
		// 문자 기반의 Buffered 스트림 객체 연습
		// 우리가 만든 'ByteArrayIOTest02.java'파일을 읽어와 출력하기
		try {
//			InputStream is = BufferedIOTest02.class.getResourceAsStream("/basic/ByteArrayIOTest.class");
			// 이클립스에서 자바 프로그램이 실행되는 현재 위치는 '프로젝트폴더'가 기본 위치가 된다. 
			FileReader fr = new FileReader("./src/basic/ByteArrayIOTest.java");
			BufferedReader br = new BufferedReader(fr);
			
			String temp = "";
			// 문자기반의 버퍼에서는 1줄씩 읽어올 수 있는 readLine()메서드가 있다.
			for(int i=1; (temp=br.readLine()) != null; i++){
				System.out.printf("%3d : %s\n", i, temp);
			}
			
			br.close();  // 스트림 닫기
			
		} catch (IOException e) {
			System.out.println("입출력 오류...");
		}

	}

}
