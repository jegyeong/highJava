package basic;

public class ArgsTest {
	
	// 가변형 인수  ==> 메서드의 매개변수의 개수가 실행될 때 마다 다를 때 사용한다.
	
	// 가변형 인수를 이용한 메서드
	// 형식)  접근제한자 반환값  메서드명(  자료형명...변수명 ){  처리할 내용들;  }
	
	// 가변형인수는 메서드 내에서는 배열로 처리된다.
	// 메서드에서 가변형 인수는 한가지만 사용할 수 있다.
	// 가변형 인수와 다른 매개변수를 같이 사용할 경우에는 가변형 인수를 제일 뒤에 배치해야 한다.
	//public int sumArg( String name, float k, int...data ){
	//public int sumArg( int k , int kk, int...data){
	public int sumArg( int...data ){
	
		int sum = 0;
		for(int i=0; i<data.length; i++){
			sum += data[i];
		}
		return sum;
	}
	
	
	
	// 배열을 이용한 메서드  ==> 매개변수로 정수값들을 받아서 이 값들의 합계를 반환하는 메서드 작성
	public int sumArr(int[] data){
		int sum = 0;
		for(int i=0; i<data.length; i++){
			sum += data[i];
		}
		return sum;
	}
	


	public static void main(String[] args) {
		ArgsTest test = new ArgsTest();
		
		int[] nums = {100,200,300,400};
		System.out.println(   test.sumArr(nums)   );
		System.out.println(   test.sumArr(new int[]{1,2,3,4,5,6,7,8,9})  );
		System.out.println();
		
		System.out.println(  test.sumArg(100,200,300,400) );
		System.out.println(  test.sumArg(1,2,3,4,5,6,7,8,9) );
		
		
	}

}
