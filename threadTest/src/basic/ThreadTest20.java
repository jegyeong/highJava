package basic;
/*
	wait(), notify()를 이용해서
	두 쓰레드가 번갈아 한번씩 실행되는 예제
*/
public class ThreadTest20 {

	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();

	}

}

// 공통으로 사용할 객체
class WorkObject{
	public synchronized void methodA(){
		System.out.println("methodA() 메서드 작업 중...");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
		}
	}
	
	public synchronized void methodB(){
		System.out.println("methodB() 메서드 작업 중...");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
		}
	}
}

// WorkObject의 methodA()만 호출하는 쓰레드
class ThreadA extends Thread{
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++){
			workObj.methodA();
		}
	}
}

//WorkObject의 methodB()만 호출하는 쓰레드
class ThreadB extends Thread{
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++){
			workObj.methodB();
		}
	}
}








