package basic;

/*
	쓰레드의 동기화 처리 예제 => 은행의 입출금을 쓰레드로 처리하는 예제
	(synchronized를 이용한 동기화 처리)
	
*/
public class ThreadTest17 {
	private int balance; 	// 잔액이 저장될 변수
	
	// getter, setter
	public synchronized int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	// 입금하는 메서드
	public synchronized void deposit(int money){
		balance += money;
	}
	
	// 출금하는 메서드(반환값 ==> 출금성공 : true, 출금실패: false )
	// 동기화 영역에서 호출하는 메서드도 동기화처리를 해주어야 안전하다.
	
	// 메서드 자체에 동기화 처리하기 
	//public synchronized boolean withdraw(int money){
	public boolean withdraw(int money){
		
		synchronized(this){ // 동기화 블럭으로 동기화 처리하기
			if(balance >= money){
				for(int i=1; i<=100000000; i++){}  // 시간 때우기
	
				balance -= money;
				
				System.out.println("메서드 안에서 balance = " + getBalance());
				return true;
			}else{
				return false;
			}
		}
		
	}
	
	public static void main(String[] args) {
		final ThreadTest17 acount = new ThreadTest17();
		acount.setBalance(10000);  // 잔액 셋팅
		
		// 익명 구현체를 이용한 쓰레드 구현
		Runnable acountTest = new Runnable() {
			@Override
			public void run() {
				boolean result = acount.withdraw(6000);
				System.out.println("쓰레드에서 result = " + result + 
							", balance = " + acount.getBalance());
			}
		};
		//---------------------------------------------
		
		Thread th1 = new Thread(acountTest);
		Thread th2 = new Thread(acountTest);
		
		th1.start();
		th2.start();
		
		

	}

}
