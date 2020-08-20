package basic;

public class ThreadTest03 {

	public static void main(String[] args) {
		// 쓰레드의 수행 시간을 체크해 보자
		Thread th = new Thread(new MyRunner());
		
		// 1970년 1월1일0시0분0초(표준시간)부터 경과한 시간을 밀리세컨드(1/1000초)단위로 반환한다.
		long startTime = System.currentTimeMillis();
		
		th.start();
		try {
			th.join();  // 현재 쓰레드에서 th 쓰레드가 종료될 때까지 기다려라...
		} catch (InterruptedException e) {
			
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("실행시간 : " + (endTime - startTime));
		

	}

}

// 1부터 10억까지의 합계를 구하는 쓰레드
class MyRunner implements Runnable{
	@Override
	public void run() {
		long sum = 0L;
		for(long i=1L; i<= 1_000_000_000L; i++){
			sum += i;
		}
		System.out.println("합계 = " + sum);
	}
}





