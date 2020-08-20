package basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
	문제1) 5명의 별명을 Scanner로 입력하여 ArrayList에 저장하고
		  이 들중 별명의 길이가 제일 긴 별명을 출력하시오.
		 (단, 각 별명의 길이는 모두 다르게 입력한다.)
	
*/
public class ArrayListTest3 {

	public static void main(String[] args) {
		ArrayList<String> aliasList = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("서로 다른 길이의 별명 5개를 입력하세요.");
		for(int i=1; i<=5; i++){
			System.out.print(i + "번째 별명 : ");
			String alias = scan.next();
			aliasList.add(alias);
		}
		System.out.println("입력 끝...");
		
		String maxAlais = aliasList.get(0);  // 제일 긴 별명이 저장될 변수 (첫번째 별명으로 초기화)
		
		for(int i=1; i<aliasList.size(); i++){
			if(maxAlais.length() < aliasList.get(i).length()){
				maxAlais = aliasList.get(i);
			}
		}
		
		System.out.println("제일 긴 별명 : " + maxAlais);
		
		
		

	}

}








