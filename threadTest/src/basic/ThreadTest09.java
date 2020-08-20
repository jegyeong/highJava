package basic;

// 데몬 쓰레드 연습  --> 자동 저장 기능 구현하기
public class ThreadTest09 {

	public static void main(String[] args) {
		AutoSave auto = new AutoSave();
		
		auto.setDaemon(true);  // 데몬 쓰레드로 설정( start()메서드를 호출하기 전에 설정해야 한다.)
		auto.start();
		
		try {
			for(int i=1; i<=20; i++){
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println("메인 쓰레드 종료...");

	}

}

// 자동 저장하는 쓰레드 ==> 3초에 한번씩 자동 저장하는 쓰레드
class AutoSave extends Thread{
	public void save(){   // 저장기능을 수행하는 메서드
		System.out.println("작업 내용을 저장합니다...");
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(3 * 1000);
			} catch (InterruptedException e) {
				break;
			}
			
			save(); // 저장하는 메서드 호출
			
		}
	}
	
	
}

