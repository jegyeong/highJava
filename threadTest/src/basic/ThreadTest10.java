package basic;

// 쓰레드의 상태를 출력하는 예제

public class ThreadTest10 {

	public static void main(String[] args) {
		TargetThread target = new TargetThread();  // 타겟용 쓰레드 객체 생성
		
		// 타겟용 쓰레드를 생성자의 매개값으로 주어서 쓰레드의 상태를 출력하는 쓰레드 객체를 생성한다.
		StatePrintThread prn = new StatePrintThread(target);
		
		prn.start();   // 쓰레드의 상태를 출력하는 쓰레드를 작동 시킨다.

	}

}


// TargetThread의 상태를 출력하는 쓰레드 클래스
class StatePrintThread extends Thread{
	private TargetThread target;  // 상태를 출력할 대상이 되는 쓰레드가 저장될 변수
	
	// 생성자
	public StatePrintThread(TargetThread target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		while(true){
			// target용 쓰레드의 상태값 구하기
			Thread.State state = target.getState();
			System.out.println("타겟 쓰레드의 현재 상태값 : " + state);
			
			if(state == Thread.State.NEW){  // 쓰레드의 상태가 NEW 상태인지 검사
				target.start();   // 쓰레드 작동 시작
			}
			
			if(state == Thread.State.TERMINATED){  // 쓰레드가 종료(TERMINATED)상태인지 검사
				break; // 쓰레드를 출력하는 쓰레드로 끝나도록 한다.
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			
		}
	}
	
}



// target용 쓰레드 클래스 작성
class TargetThread extends Thread{
	@Override
	public void run() {
		for(long i=1L; i<=2000000000L; i++){}  // 시간 지연용
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {	}
		
		for(long i=1L; i<=2000000000L; i++){}  // 시간 지연용
		
	}
}