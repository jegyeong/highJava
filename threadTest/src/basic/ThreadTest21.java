package basic;

public class ThreadTest21 {

	public static void main(String[] args) {
		DataBox databox = new DataBox();
		
		ProducerThread pro = new ProducerThread(databox);
		ConsumerThread con = new ConsumerThread(databox);
		
		pro.start();
		con.start();

	}

}

// 데이터를 넣어주는 역할만 하는 쓰레드
class ProducerThread extends Thread{
	private DataBox databox;
	
	public ProducerThread(DataBox databox) {
		this.databox = databox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=3; i++){
			String data = "DATA-" + i;
			databox.setData(data);
		}
	}
	
}

// 데이터를 꺼내서 사용하는 역할만 하는 쓰레드
class ConsumerThread extends Thread{
	private DataBox databox;
	
	public ConsumerThread(DataBox databox) {
		this.databox = databox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=3; i++){
			String temp = databox.getData();
		}
	}
}







// 데이터를 공통으로 사용하는 클래스
class DataBox{
	private String data;
	
	// data변수에 저장된 값을 반환하는 메서드
	// 만약 data변수에 저장된 값이 없으면 값이 채워질 때까지 기다린다.
	// 그리고 값이 채워지면 그 때 값을 반환하고 데이터를 다시 비워준다.
	public synchronized String getData(){
		if(data==null){
			try {
				wait();  
			} catch (InterruptedException e) {
			}
		}
		
		String returnData = data;
		System.out.println("getData()메서드가 읽은 데이터 : " + returnData);
		data = null;
		notify();
		
		return returnData;
		
	}
	
	
	// 멤버변수 data에 값을 셋팅하는 메서드
	// 멤버변수 data에 현재 값이 있으면 값을 사용할 때까지 기다린다.
	// 값을 사용해서 멤버변수 data가 비워지면 새로운 값을 셋팅한다.
	public synchronized void setData(String data){
		if(this.data!=null){
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		
		this.data = data;
		System.out.println("setData()메서드에서 새로 셋팅한 데이터 : " + data);
		notify();
	}
	
}








