package basic;

/*
	Thread의 stop()메서드를 호출하면 쓰레드가 바로 멈춘다.
	이 때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어 나중에 실행되는
	프로그램에 영향을 줄 수 있다. 그래서 stop()메서드는 비추천되어 있다.
*/
public class ThreadTest14 {

	public static void main(String[] args) {
		/*
		ThreadStopEx1 th1 = new ThreadStopEx1();
		th1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		//th1.stop();	// stop()메서드는 사용하지 않는다.
		th1.setStop(true);  
		*/
		
		// interrupt()메서드를 이용한 쓰레드 멈추기
		ThreadStopEx2 th2 = new ThreadStopEx2();
		th2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		th2.interrupt();
		

	}

}



class ThreadStopEx1 extends Thread{
	private boolean stop;
	
	public void setStop(boolean stop){
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while(!stop){
			System.out.println("쓰레드 실행 중....");
		}
		
		System.out.println("자원 정리...");
		System.out.println("실행 종료...");
		
	}
}

// interrupt()메서드를 이용하여 쓰레드를 멈추게 하는 방법
class ThreadStopEx2 extends Thread{
	@Override
	public void run() {
		/*
		// 방법1
		while(true){
			System.out.println("실행 중 ...");
			try {
				Thread.sleep(1);  // sleep()에의해 일지정지 상태에서 interrupt()메서드가 실행되면
								  // InterruptedException이 발행한다.
			} catch (InterruptedException e) {
				break;
			}
		}
		*/
		
		while(true){
			System.out.println("실행 중 ...");
			
			// interrupt()메서드가 호출되었는지 검사하기
			
			/*
			// 검사 방법1
			// ==> 인스턴스 객체용 메서드 isInterrupted()를 이용하여 검사한다.
			if(this.isInterrupted()){
				break;
			}
			*/
			
			// 검사 방법2
			// ==> Thread의 정적 메서드 interrupted()를 이용하여 검사한다.
			if(Thread.interrupted()){
				break;
			}
		}
		
		System.out.println("자원 정리...");
		System.out.println("실행 종료...");
		
	}
}





