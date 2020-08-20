package basic;

/*
	1~20억까지의 합계를 구하는데 걸린 시간을 체크해 보자.
	전체 합계를 구하는 작업을 싱글쓰레드가 처리할 때와
	멀티쓰레드로 분할해서 처리할 때의 시간을 비교해 보자.
*/
public class ThreadTest04 {

	public static void main(String[] args) {
		SumThread smAll = new SumThread(1L, 2_000_000_000L);

		SumThread[] sms = new SumThread[]{
			new SumThread(1L,           500000000L),	
			new SumThread(500000001L,  1000000000L),	
			new SumThread(1000000001L, 1500000000L),	
			new SumThread(1500000001L, 2000000000L)	
		};
		
		// 단독으로 처리하는 경우
		long startTime = System.currentTimeMillis();
		smAll.start();
		try {
			smAll.join();
		} catch (InterruptedException e) {
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독으로 처리할 때의 시간 : " + (endTime-startTime));
		System.out.println();
		
		//-------------------------------------------
		
		// 여러개의 쓰레드가 협력해서 처리했을 때
		startTime = System.currentTimeMillis();
		
		for(int i=0; i<sms.length; i++){
			sms[i].start();
		}
		
		for(SumThread sm : sms){
			try {
				sm.join();
			} catch (InterruptedException e) {
			}
		}
		endTime = System.currentTimeMillis();
		
		System.out.println("협력해서 처리했을 때 시간 : " + (endTime-startTime));
		
		
		
	}

}



class SumThread extends Thread{
	private long min, max;
	
	public SumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public void run() {
		long sum = 0L;
		for(long i = min; i<=max; i++){
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
	
}







