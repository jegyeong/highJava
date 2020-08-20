package basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		// 멀티 쓰레드 프로그램
		
		// Thread를 사용하는 방법
		
		// 방법1
		// Thread클래스를 상속한 class의 인스턴스를 생성한 후
		// 이 인스턴스의 start()메서드를 호출한다.
		MyThread1 th1 = new MyThread1();
		th1.start();
		
		// 방법2
		// Runnable인터페이스를 구현한 class의 인스턴스를 생성한 후
		// 이 인스턴스를 Thread의 인스턴스를 생성할 때 생성자의 매개변수값으로 넘겨준다.
		// 이 때 생성된 Thread의 인스턴스의 start()메서드를 호출한다.
		MyThread2 r2 = new MyThread2();
		Thread th2 = new Thread(r2);
		th2.start();
		
		// 방법3
		// Runnable인터페이스의 익명구현체를 생성한 후 이 익명구현체를
		// Thread의 인스턴스를 생성할 때 생성자의 매개변수값으로 넘겨준다.
		// 이 때 생성된 Thread의 인스턴스의 start()메서드를 호출한다.
		Thread th3 = new Thread(
				new Runnable() {
					@Override
					public void run() {
						for(int i=1; i<=200; i++){
							System.out.print("@");
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								
							}
						}
						
					}
				}
		);
		th3.start();
		
		

	}

}

// Thread클래스를 상속한 클래스 작성하기
class MyThread1 extends Thread{
	@Override
	public void run() {
		// 이 run()메서드 영역에 쓰레드가 할 일을 코딩하면 된다.
		for(int i=1; i<=200; i++){
			System.out.print("*");
			try {
				// Thread.sleep(시간);  ==> 주어진 '시간'동안 작업을 잠시 멈춘다.
				//	'시간'은 밀리세컨드 단위를 사용한다. ( 즉, 1초는 1000을 의미한다.)
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
			
		}
	}
}


// Runnable인터페이스를 구현한 클래스 작성하기
class MyThread2 implements Runnable{
	@Override
	public void run() {
		for(int i=1; i<=200; i++){
			System.out.print("$");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			
			}
		}
	}
}








