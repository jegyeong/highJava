package basic;

/*
	3개의 쓰레드가 각각 알파벳 A~Z까지를 출력하는데
	출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기
*/
public class ThreadTest11 {

	public static void main(String[] args) {
		DisplayCharacter[] disChar = new DisplayCharacter[]{
			new DisplayCharacter("홍길동"),
			new DisplayCharacter("이순신"),
			new DisplayCharacter("강감찬")
		};
		
		for(DisplayCharacter dis : disChar){
			dis.start();
		}
		
		for(int i=0; i<disChar.length; i++){
			try {
				disChar[i].join();
			} catch (InterruptedException e) {
			}
		}
		
		System.out.println();
		System.out.println("경기 결과");
		System.out.println("순위 : " + DisplayCharacter.strRank);
		

	}

}

// 알파벳 A~Z까지를 출력하는 쓰레드 클래스
class DisplayCharacter extends Thread{
	public static String strRank = "";   // 경기를 마친 쓰레드의 name값이 누적될 변수
	
	private String name;   // 경기 참여자 이름이 저장될 변수
	
	// 생성자
	public DisplayCharacter(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(char ch='A'; ch<='Z'; ch++){
			System.out.println(name + "의 출력 문자 : " + ch);
			try {
				// sleep()의 값을 201~500사이의 난수로 한다.
				Thread.sleep((int)(Math.random() * 300 + 201 ));
			} catch (InterruptedException e) {
			}
		}
		System.out.println(name + " 출력 끝...");
		strRank += name + "  ";
	}
	
}










