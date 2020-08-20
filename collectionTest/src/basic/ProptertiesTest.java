package basic;

import java.util.Properties;

public class ProptertiesTest {

	public static void main(String[] args) {
		// Properties는 Map보다 축소된 기능의 객체라고 할 수 있다.
		// Map은 모든 형태의 데이터를 key와 value값으로 넣을 수 있지만
		// Properties는 key와 value값으로 String만 사용할 수 있다.
		
		// Map은 put(), get()메서드를 이용해서 데이터를 입출력하지만
		// Properties는 setProperty(), getProperty()메서드를 이용해서 입출력한다.
		
		Properties prop = new Properties();  // 객체 생성
		
		// 데이터 추가(입력)
		prop.setProperty("name", "홍길동");
		prop.setProperty("tel", "010-1234-5678");
		prop.setProperty("addr", "대전");
		
		
		// 데이터 가져오기
		String name = prop.getProperty("name");
		String tel = prop.getProperty("tel");
		String addr = prop.getProperty("addr");
		
		System.out.println("name : " + name);
		System.out.println("tel  : " + tel);
		System.out.println("addr : " + addr);
		
		
		

	}

}





