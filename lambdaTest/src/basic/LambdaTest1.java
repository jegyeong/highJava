package basic;
/*
	람다식 ==> 익명함수를 생성하기 위한 식
		 ==> 자바에서는 '매개변수를 가진 코드 블럭'으로  '런타임시' '익명구현객체'로 생성된다.
		 ==> 람다식으로 변환할 수 있는 인터페이스는 구현해야 할 메서드가 1개인 인터페이스만 가능하다.
		     (이런 인터페이스를 '함수적 인터페이스'라고 한다.)  
	
	형식)
		인터페이스명  변수명 = 람다식;
		
	람다식형식)
		(자료형이름 변수명, ...) -> { 실행문들; ... }
		
	람다식 작성 규칙
	1. '자료형이름'은 생략할 수 있다.
		(int a) -> { System.out.println( a ); }
		(a) -> { System.out.println( a ); }
		
	2. 매개변수가 1개일 경우에는 괄호 '( )'를 생략할 수 있다.
		a -> { System.out.println( a ); }
		
	3. 실행문이 1개이면 중괄호 '{ }'를 생략할 수 있다.
		a -> System.out.println( a );
	
	4. 매개변수가 1개가 아닐 경우에는 괄호 '( )'를 생략할 수 없다.
		( ) -> System.out.println( "안녕" );
	
	5. 반환값이 있을 경우에는 return명령을 사용한다.
		(a, b) -> { return a + b; }
	
	6. 실행문에 return문만 있는 경우 return과 중괄호 '{ }'를 생략할 수 있다.
		(a, b) ->  return a + b			// 중괄호만 생략한 경우
		(a, b) ->  a + b				// 중괄호와 return명령을 생략한 경우
	
*/
public class LambdaTest1 {

	public static void main(String[] args) {
		// Runnable인터페이스는 '함수적 인터페이스'이다.
		
		// 람다식을 사용하지 않을 경우
		Thread th1 = new Thread(
			new Runnable() {
				@Override
				public void run() {
					for(int i=1; i<=10; i++) {
						System.out.println("일반적인 방법 " + i);
					}
				}
			}
		);
		
		th1.start();
		//----------------------------------------
		
		// 람다식을 사용하는 경우
		Thread th2 = new Thread(
			() -> {
				for(int i=1; i<=10; i++) {
					System.out.println("람다식 - " + i);
				}
			}
		);
		th2.start();
		
		
	}

}









