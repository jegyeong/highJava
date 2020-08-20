package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
	숫자 야구 게임 프로그램 작성하기
	
	- Set을 이용하여 숫자 야구 게임 프로그램을 작성한다.
	  컴퓨터의 숫자는 난수를 이용해서 구한다.
	  (스트라이크는 S, 볼은 B로 출력한다.)
	  
	예) 컴퓨터 난수 ==> 9 5 7
	
	실행 예시)
		숫자입력 : 3 5 6
		3 5 6 ==> 1S 0B
		숫자입력 : 7 8 9
		7 8 9 ==> 0S 2B
		숫자입력 : 9 7 5
		9 7 5 ==> 1S 2B
		숫자입력 : 9 5 7
		9 5 7 ==> 3S 0B
		
		4번만에 맞췄습니다.

*/
public class BaseBallTest {
	private ArrayList<Integer> numList; 	// 난수가 저장될 List
	private ArrayList<Integer> userList;	// 사용자가 입력한 값을 저장할 List
	
	int strike, ball;	// 스트라이크 개수와 볼의 개수를 저장할 변수 선언
	
	Scanner scan = new Scanner(System.in);
	
	// 1~9사이의 서로 다른 난수 3개를 만들어서 List에 저장하는 메서드
	public void getNum(){
		Set<Integer> numSet = new HashSet<>();
		
		// Set을 이용해서 3개의 난수 만들기
		while(numSet.size()<3){
			numSet.add( (int)(Math.random() * 9 + 1) );  // 1~9사이의 난수 만들기
		}
		
		// Set의 자료를 List에 넣는다.
		numList = new ArrayList<>(numSet);
		
		
		Collections.shuffle(numList);   // List의 값들을 섞어준다.
	}
	
	
	// 사용자로부터 3개의 정수를 입력 받아 List에 저장하는 메서드
	// 입력한 정수들은 서로 중복되지 않게 한다.
	public void inputNum(){
		int n1, n2, n3; 	// 입력한 값을 저장할 변수 선언
		
		do{
			System.out.print("숫자 입력 : ");
			n1 = scan.nextInt();
			n2 = scan.nextInt();
			n3 = scan.nextInt();
			if(n1==n2 || n1==n3 || n2==n3){
				System.out.println("중복되는 숫자는 입력할 수 없습니다. 다시 입력하세요.");
			}
			
		}while(n1==n2 || n1==n3 || n2==n3);
		
		userList = new ArrayList<>();
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);
		
	}
	
	
	// 스트라이크와 볼 판정 및 판정 결과를 출력하는 메서드
	public void ballCount(){
		strike = 0;
		ball = 0;  // 스트라이크와 볼의 개수 초기화
		
		for(int i=0; i<userList.size(); i++){
			for(int j=0; j<numList.size(); j++){
				if(userList.get(i) == numList.get(j)){  // 값이 같은지 비교
					if(i==j){	// 위치가 같은지 비교
						strike++;
					}else{
						ball++;
					}
				}
			}
		}
		
		// 판정 결과 출력하기
		System.out.println(userList.get(0) + ", " + userList.get(1) + 
				", " + userList.get(2) + " ==> " + strike + "S " + ball + "B");
		
	}
	
	// 게임을 진행하는 메서드
	public void startGame(){
		getNum();	// 난수 만드는 메서드 호출
		
		// 만들어진 난수값 출력 ==> 프로그램 확인용  (나중에는 주석처리한다.)
		//System.out.println(" 컴퓨터 난수 : " + numList);
		
		int cnt = 0;	// 몇번만에 맞췄는지를 저장하는 변수
		
		do{
			cnt++;
			
			inputNum();		// 사용자 입력 메서드 호출
			
			ballCount();	// 볼카운트를 처리하는 메서드 호출
			
		}while(strike!=3);   // 3스트라이크가 될때까지 반복
		
		System.out.println(cnt + "번째 만에 맞췄군요!!!");
	}
	

	public static void main(String[] args) {
//		BaseBallTest baseBall = new BaseBallTest();
//		baseBall.startGame();
		
		new BaseBallTest().startGame();

	}

}
