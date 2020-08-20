package basic;

/*
	열거형  ==> - 서로 관련있는 상수들의 집합
		      - 클래스처럼 보이게하는 상수
		      - 비교를 할 때 '=='연산자를 사용해서 비교할 수 있다.
		      - switch문에서도 사용 가능하다.
*/
public class EnumTest {
	// 	열거형 만들기 
	
	//  기본적인 열거형
	// 형식)  enum 열거형이름 { 상수명1, 상수명2, 상수명3, ... , 상수명n }
	// 		 ==> 상수명을 따옴표로 묶지 않는다.
	//	     ==> 이 선언문 끝에 ';'를 붙이지 않는다.
	public enum City { 서울, 부산, 대구, 광주, 대전 }
	
	// 열거형 상수에 사용자가 임의의 값을 설정할 수 있다.
	// 사용자가 설정할 임의의 값은 상수명 옆에 괄호 속에 지정한다.
	public enum Season{
		봄("3월부터 5월까지"), 
		여름("6월부터 8월까지"), 
		가을("9월부터 11월까지"), 
		겨울("12월부터 2월까지") ;   // 임의의 값이 설정된 상수를 선언할 때는 마지막에 세미콜른(;)을 붙인다.
		
		// 임의의 값이 저장될 변수 선언
		private String span;
		
		// 열거형의 생성자를 만든다. ==> 지정한 임의의 값을 변수에 셋팅하는 역할을 수행한다.
		// 열거형의 생성자는 private 이어야 된다. (생략하면 기본적으로 private이 된다.)
		//private Season(String str){
		Season(String str){
			span = str;
		}
		
		// 외부에서 설정한 임의의 값을 가져갈 수 있는 메서드 만들기 (일조의 getter 메서드 만들기)
		public String getSpan(){
			return span;
		}
	}
	
	

	public static void main(String[] args) {
		/*
			열거형에서 사용하는 메서드들
			- name()메서드 ==> 열거형 상수의 이름을 문자열로 반환한다.
			- ordinal()메서드 ==> 열거형 상수가 정의된 순서값을 반환한다. (0부터 시작) 
			- valueOf("상수명") ==> 지정된 열거형에서 '상수명'과 일치하는 열거형 상수를 반환한다.
			- values() ==> 열거형 상수들을 배열에 담아 가져온다.
		*/
		
		// City형 열거형 상수들 중에서 '서울'값을 가져온다.
		City city1 = City.valueOf("서울");
		
		System.out.println("city1 : " + city1.name());
		System.out.println("city1의 ordinal : " + city1.ordinal());
		System.out.println();
		
		// City형 열거형 상수들 중에서 '대구'값을 가져온다.
		City city2 = City.대구;   // City.valueOf("대구");와 같다.
		System.out.println("city2 : " + city2.name());
		System.out.println("city2의 ordinal : " + city2.ordinal());
		System.out.println();
		
		// Season형 열거형 상수들 중에서 '봄'값 가져오기
		Season ss = Season.valueOf("봄");
		System.out.println("ss : " + ss.name());
		System.out.println("ss의 ordinal : " + ss.ordinal());
		System.out.println("ss의 span : " + ss.getSpan() );
		System.out.println();
		
		// 상수명과 임의로 설정한 값 가져오기
		for(Season time : Season.values()){
			System.out.println(time + " : " + time.getSpan());
			//System.out.println(time.name() + " : " + time.getSpan());
		}
		System.out.println();
		
		for(City city : City.values()){
			System.out.println(city.name() + " -> " + city.ordinal() );
		}
		
	
		/*
		if(MyConst.ONE == MyConst2.RED){
			System.out.println("합격....");
		}else{
			System.out.println("불합격...");
		}
		*/
	}
	
	
	

}
