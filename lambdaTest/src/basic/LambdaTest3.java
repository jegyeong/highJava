package basic;

public class LambdaTest3 {

	public void method(final int temp) {
		final int localVar = 40;
		
		//temp = 400;
		
		//localVar = 200;
		
		
		// 람다식에서 지역변수를 사용하는 연습
		// 람다식 내부에서 지역변수를 사용하려면 해당 지역 변수는 final이여야 한다.
		// 자바버전 1.8이상에서는 람다식 내부에서 사용되는 지역변수가 변경되지 않으면 final을 생략해도 된다.
		LambdaTestInterface la = ()->{
			System.out.println("temp = " + temp);
			System.out.println("localVar = " + localVar);
		};
		
		la.test();
	}
	
	
	public static void main(String[] args) {
		new LambdaTest3().method(50);
	}

}
