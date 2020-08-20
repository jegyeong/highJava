package singleton;
/*
	singleton패턴 : 항상 한 개의 객체만 만들어지게 하는 방법
		==> 외부에서 new 명령을 사용하지 못하게 하고, 내부에서 항상 동일한 객체를 반환하도록 만들어진 클래스
*/

// singleton 클래스를 구성하는 예제
public class MySingleton {
	// 1. 자기 자신class의 참조값을 갖는 멤버변수를 private static 형식으로 선언한다.
	private static MySingleton single;
	
	// 2. 생성자는 private으로 만든다.   ==> 외부에서 new 명령을 사용하지 못한다.
	private MySingleton(){
		System.out.println("생성자 입니다.");
	}
	
	// 3. 자기자신 객체(instance)를 생성하여 반환하는 메서드를 작성한다.
	//    ==> 이 메서드는 'public static 자기자신클래스명 메서드명()' 형식으로 만든다.
	//		   이 메서드의 이름은 보통 getInstance()로 한다.
	// 		  그리고 이 메서드에서 자기 자신 객체를 생성할 때 객체가 존재하는지 여부를 검사해서
	// 		 해당 객체가 존재하지 않으면 새롭게 만들어 반환하고 기존의 객체가 존재하면 그 객체를 반환한다.
	public static MySingleton getInstance(){
		if(single==null) single = new MySingleton();
		
		return single;
	}
	
	
	public void displayTest(){
		System.out.println("이것은 싱글톤 객체의 메서드 입니다.");
	}
	
	
}









