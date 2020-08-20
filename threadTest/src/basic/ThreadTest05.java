package basic;

import javax.swing.JOptionPane;

public class ThreadTest05 {

	public static void main(String[] args) {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		System.out.println("입력값 : " + str);
		
		// 카운트 다운을 나타내는 부분
		for(int i=10; i>=1; i--){
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		
		

	}

}
