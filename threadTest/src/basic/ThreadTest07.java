package basic;

import javax.swing.JOptionPane;

/*
	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
	
	컴퓨터의 가위 바위 보는 난수를 이용해서 구하고,
	사용자의 입력 showInputDialog()메서드를 이용해서 입력 받는다.
	
	입력시간은 5초로 제한하고 카운트 다운을 한다.
	5초안에 입력이 없으면 게임에서 진것으로 처리한다.
	
	5초안에 입력이 있으면 승패를 구해서 다음과 같이 출력한다.
	
	결과예시)
		-- 결  과 --
		컴퓨터 : 가위
		당  신 : 바위
		결  과 : 당신이 이겼습니다.
	
*/
public class ThreadTest07 {
	public static boolean inputCheck;
	
	
	public static void main(String[] args) {
		GameTimer gt = new GameTimer();
		
		// 난수를 이용해서 컴퓨터의 가위, 바위, 보를 정한다.
		String[] data = {"가위", "바위", "보"};
		int index = (int)(Math.random() * 3);   // 0~2 사이의 난수 만들기
		String com = data[index];
		
		// 사용자의 가위, 바위 , 보 정하기
		String man = null;
		
		// 가위 바위 보를 입력받기 전에 카운트 다운을 시작한다.
		gt.start();
		do{
			man = JOptionPane.showInputDialog("가위 바위 보를 입력하세요.");
		//}while( man==null || !(man.equals("가위") || man.equals("바위") || man.equals("보")) );
		}while( man==null || !man.equals("가위") && !man.equals("바위") && !man.equals("보") );
		
		inputCheck = true;  // 입력이 완료되었다는 표식을 저장한다.
		
		// 승패 판정하기
		String result = "";
		if(man.equals(com)){
			result = "비겼습니다.";
		}else if( (man.equals("가위") && com.equals("보")) ||
				  (man.equals("바위") && com.equals("가위")) ||
				  (man.equals("보") && com.equals("바위"))  ){
			result = "당신이 이겼습니다.~~";
		}else{
			result = "당신이 졌습니다.!!!";
		}
		
		// 결과 출력하기
		System.out.println(" --- 결 과 ---");
		System.out.println(" 컴퓨터 : " + com);
		System.out.println(" 당  신 : " + man);
		System.out.println(" 결  과 : " + result);
		
		

	}

}

// 카운트 다운 쓰레드
class GameTimer extends Thread{
	@Override
	public void run() {
		for(int i=15; i>=1; i--){
			if(ThreadTest07.inputCheck==true){
				return;
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		
		System.out.println("시간이 초과되어 당신이 졌습니다.");
		System.exit(0);
		
	}
}








