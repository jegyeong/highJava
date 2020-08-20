package basic;

// 인터페이스가 함수적 인터페이스라는 것을 명시적으로 표시해 주는 
// 어노테이션은 @FunctionalInterface이다.

@FunctionalInterface
public interface LambdaTestInterface {
	// 매개변수가 없고, 반환값도 없는 메서드
	public void test();
}

@FunctionalInterface
interface LambdaTestInterface2{
	// 반환값이 없고, 매개변수가 1개인 메서드
	public void test(int a);
}

@FunctionalInterface
interface LambdaTestInterface3{
	// 반환값이 있고, 매개변수도 있는 메서드
	public int test(int a, int b);
}





