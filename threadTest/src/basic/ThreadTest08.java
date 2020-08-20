package basic;

public class ThreadTest08 {

	public static void main(String[] args) {
		Thread th1 = new UpperThread();
		Thread th2 = new LowerThread();
		
		// 우선순위 설정  ==> start()메서드 호출 전에 해야 한다.
		th1.setPriority(9);
		th2.setPriority(2);
		
		
		// 우선 순위 출력하기
		System.out.println("th1의 우선 순위 : " + th1.getPriority());
		System.out.println("th2의 우선 순위 : " + th2.getPriority());
		
		th1.start();
		th2.start();
	}

}


// 대문자를 출력하는 쓰레드
class UpperThread extends Thread{
	@Override
	public void run() {
		for(char ch='A'; ch<='Z'; ch++){
			System.out.println(ch);
			// 시간 때우기용 반복문
			for(long i=1L; i<=1000000000L; i++) { }
		}
	}
}

// 소문자를 출력하는 쓰레드
class LowerThread extends Thread{
	@Override
	public void run() {
		for(char ch='a'; ch<='z'; ch++){
			System.out.println(ch);
			// 시간 때우기용 반복문
			for(long i=1L; i<=1000000000L; i++) { }
		}
	}
}




