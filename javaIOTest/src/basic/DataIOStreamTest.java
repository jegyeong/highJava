package basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOStreamTest {

	public static void main(String[] args) {
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/test.dat");
			
			// DataOutputStream은 출력용 데이터를 자료형에 맞게 출력해 준다.
			DataOutputStream dout = new DataOutputStream(fout);
			
			dout.writeInt(-10);			// 정수형으로 데이터 출력
			dout.writeFloat(20.1f);	// 실수형으로 데이터 출력
			dout.writeBoolean(true);	// 논리형으로 데이터 출력
			
			System.out.println("출력 완료...");
			
			dout.close();
			
			//---------------------------------------------------
			
			// 출력한 자료 읽어오기
			FileInputStream fin = new FileInputStream("d:/d_other/test.dat");
			
			DataInputStream din = new DataInputStream(fin);
			
			// DataInputStream을 이용해서 자료를 읽어올 때는 저장한 순서대로 읽어와야 한다.
			System.out.println("정수형 : " + din.readInt());
			System.out.println("실수형 : " + din.readFloat());
			System.out.println("논리형 : " + din.readBoolean());
			
			din.close();
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
