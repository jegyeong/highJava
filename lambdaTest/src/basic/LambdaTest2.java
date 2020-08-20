package basic;

public class LambdaTest2 {

	public static void main(String[] args) {
		// 람다식을 사용하지 않은 경우
		LambdaTestInterface t1 = new LambdaTestInterface() {
			@Override
			public void test() {
				System.out.println("람다식을 사용하지 않은 경우입니다.");
			}
		};
		
		t1.test();
		
		LambdaTestInterface t2 = 
			() -> { System.out.println("안녕하세요... 람다식입니다.111111"); };
		t2.test();
		
		LambdaTestInterface t3 = 
				() ->  System.out.println("안녕하세요... 람다식입니다.222222"); 
		t3.test();
		System.out.println("--------------------------------------------");
		
		
		LambdaTestInterface2 t4 = (int s) ->{
			int result = s + 40;
			System.out.println(s + " + 40 = " + result );
		};
		t4.test(30);

		LambdaTestInterface2 t5 = k -> {
			int result = k * 20;
			System.out.println(k + " * 20 = " + result);
		};
		t5.test(30);
		
		LambdaTestInterface2 t6 = k -> System.out.println(k + " - 10 = " + (k-10));
		t6.test(30);
		System.out.println("----------------------------------------------------");
		
		
		LambdaTestInterface3 t7 = (int x, int y)->{
			int result = x + y;
			return result;
		};
		int k = t7.test(20, 50);
		System.out.println("k = " + k);
		
		LambdaTestInterface3 t8 = (x, y)->{
			int result = x - y;
			return result;
		};
		int j = t8.test(40, 5);
		System.out.println("j = " + j);
	
		LambdaTestInterface3 t9 = (x, y)->{	return x * y; };
		
		int i = t9.test(5, 14);
		System.out.println("i = " + i);
		
		LambdaTestInterface3 t10 = (x, y)-> x / y;
		int a = t10.test(20, 5);
		System.out.println("a = " + a);
		
				
	}

}
