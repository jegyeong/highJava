package basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
	문제) 5명의 사람 이름을 Scanner로 입력받아 ArrayList에 저장하고,
		 이 사람들 중에 '김'씨 성의 이름을 출력하는 프로그램을 작성하시오.
*/
public class ArrayListTest2 {

	public static void main(String[] args) {
		ArrayList<String> nameList = new ArrayList<>();
		
		Scanner scan = new Scanner(System.in);
		System.out.println("5명의 이름을 입력하세요.");
		for(int i=1; i<=5; i++){
			System.out.print(i + "번째 이름 : ");
			String name = scan.next();
			nameList.add(name);
		}
		System.out.println("입력 끝...");
		
		System.out.println();
		System.out.println("김씨 성을 가진 사람들...");
		for(int i=0; i<nameList.size(); i++){
			/*
			if(nameList.get(i).charAt(0)=='김'){
				System.out.println(nameList.get(i));
			}
			*/
			/*
			if(nameList.get(i).substring(0, 1).equals("김")){
				System.out.println(nameList.get(i));
			}
			*/
			/*
			if(nameList.get(i).indexOf("김")==0){
				System.out.println(nameList.get(i));
			}
			*/
			if(nameList.get(i).startsWith("김")){
				System.out.println(nameList.get(i));
			}
		}
		
		
		

	}

}










