package basic;

import java.util.LinkedList;

/*
	스택의 기능을 이용하여 웹브라우저의 '앞으로가기'와 '뒤로가기'를 구현하시오.
*/
public class StackQueueTest2 {
	private LinkedList<String> stBack;	 	// 이전 방문 기록
	private LinkedList<String> stForward;	// 다음 방문 기록
	
	// 생성자
	public StackQueueTest2() {
		stBack = new LinkedList<>();
		stForward = new LinkedList<>();
	}
	
	// 주소를 매개변수로 받아서 해당 싸이트를 방문하는 메서드
	public void goUrl(String url){
		System.out.println(url + " 싸이트에 접속함...");
		stBack.push(url);
	}
	
	// 뒤로 가기
	public void goBack(){
		if(!stBack.isEmpty()){	// 이전 방문 기록이 있는지 검사
			String url = stBack.pop();
			System.out.println(url + " 싸이트에 접속함...");
			stForward.push(url);
		}
	}
	
	// 앞으로 가기
	public void goForward(){
		if(!stForward.isEmpty()){ // 다음 방문 기록이 있는지 검사
			String url = stForward.pop();
			System.out.println(url + " 싸이트에 접속함...");
			stBack.push(url);
		}
	}
	
	// 방문 내역을 출력하는 메서드
	public void history(){
		System.out.println("------------------------");
		System.out.println("이전 방문 기록들 : " + stBack);
		System.out.println("현재 싸이트 : " + stBack.peek());
		System.out.println("다음 방문 기록들 : " + stForward);
		System.out.println("------------------------");
	}
	
	
	public static void main(String[] args) {
		StackQueueTest2 test = new StackQueueTest2();
		
		// 싸이트 방문
		test.goUrl("구글");
		test.goUrl("네이버");
		test.goUrl("다음");
		
		// 방문 내역 확인
		test.history();
		System.out.println();
		
		// 뒤로 가기 실행
		test.goBack();
		test.goBack();
		test.history();
		
		// 앞으로 가기 실행
		test.goForward();
		test.history();
		
	}

}








