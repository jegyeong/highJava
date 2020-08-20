package basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4];   // 자료를 읽어올 때 사용할 배열
		
		// 입력용 스트림 객체 생성
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		
		// 출력용 스트림 객체 생성
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			while(input.available()>0){  // 읽어올 자료가 있는지 검사
				/*
				input.read(temp);		// 배열 temp의 크기만큼 자료를 읽어와 temp배열에 저장한다.
				output.write(temp);		// 배열 temp에 있는 모든 데이터를 출력한다.
				*/
				int length = input.read(temp); // 실제 읽어온 데이터 수를 반환한다.
				
				// 배열 temp의 데이터들 중에서 0번째부터 length개 만큼 쓰기한다.
				output.write(temp, 0, length);
				
				System.out.println("반복문 안에서 temp : " + Arrays.toString(temp));
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		
		System.out.println();
		
		outSrc = output.toByteArray();
		
		System.out.println(" inSrc => " + Arrays.toString(inSrc));
		System.out.println("  temp => " + Arrays.toString(temp));
		System.out.println("outSrc => " + Arrays.toString(outSrc));

	}

}
