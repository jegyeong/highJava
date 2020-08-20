package basic;

import java.io.File;

public class FileTest01 {

	public static void main(String[] args) {
		// File객체 만들기 연습
		
		// 1. new File(String 파일또는경로)
		//		==> 디렉토리와 디렉토리  또는 디렉토리와 파일명 사이의 구분 문자는 '/'를 사용하거나 '\'를 사용할 수 있다.
		File file1 = new File("D:/D_Other/test.txt");
		//File file1 = new File("D:\\D_Other\\test.txt");
		System.out.println("파일명 : " + file1.getName());
		System.out.println("파일인가? : " + file1.isFile());
		System.out.println("디렉토리인가? : " + file1.isDirectory());
		System.out.println();
		
		
		File file2 = new File("D:/D_Other");
		System.out.println("파일명 : " + file2.getName());
		System.out.println("파일인가? : " + file2.isFile());
		System.out.println("디렉토리인가? : " + file2.isDirectory());
		System.out.println();
		
		// 2. new File(File parent, String child)
		// 		==> 'parent'디렉토리 안에 있는 'child' 파일을 말한다.
		File file3 = new File(file2, "test.txt");   // 변수 file1와 같은 파일을 나타낸다.
		System.out.println("파일명 : " + file3.getName());
		System.out.println("파일인가? : " + file3.isFile());
		System.out.println("디렉토리인가? : " + file3.isDirectory());
		System.out.println();

		// 3. new File(String parent, String child)
		File file4 = new File("D:/D_Other", "text.txt");  // 변수 file1, file3와 같은 파일을 나타낸다.
		
		
		
	}

}
