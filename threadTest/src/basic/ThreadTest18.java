package basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
	쓰레드의 동기화 처리 예제 => 은행의 입출금을 쓰레드로 처리하는 예제
	(lock를 이용한 동기화 처리)
*/

public class ThreadTest18 {
	private int balance; 	// 잔액이 저장될 변수
	private final Lock lock = new ReentrantLock();	// Lock객체 생성(되도록이면 private final로 만든다.)
	
	// getter, setter
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	// 입금하는 메서드
	public void deposit(int money){
		// lock()메서드로 락을 설정한 곳에서는 반드시 unlock()메서드로 락을 해제해 주어야 한다.
		lock.lock();  // 락 설정
		balance += money;
		lock.unlock();  // 락 해제  
	}
	
	// 출금하는 메서드(반환값 ==> 출금성공 : true, 출금실패: false )
	// 동기화 영역에서 호출하는 메서드도 동기화처리를 해주어야 안전하다.
	
	// 메서드 자체에 동기화 처리하기 
	public boolean withdraw(int money){
		// try ~ catch블럭이 사용되는 부분에서 unlock()메서드 호출을 finally 영역에서 하도록 한다.
		
		lock.lock(); // 락 설정
		
		boolean chk = false;
		try{
			if(balance >= money){
				for(int i=1; i<=100000000; i++){}  // 시간 때우기
	
				balance -= money;
				
				System.out.println("메서드 안에서 balance = " + getBalance());
				chk = true;
			}else{
				chk = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();  // 락 해제
		}
		
		return chk;
	
	}
	
	
	
	public static void main(String[] args) {
		final ThreadTest18 acount = new ThreadTest18();
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
