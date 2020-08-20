package basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SetTest {

	public static void main(String[] args) {
	/*
		- List와 Set의 차이점
	1. List
		- 데이터의 순서(index)가 있다.
		- 중복된 데이터를 저장할 수 있다.
	
	2. Set
		- 데이터의 순서(index)가 없다.
		- 중복된 데이터를 저장할 수 없다.
		
	*/
		HashSet hs1 = new HashSet();
		
		// 데이터 추가 ==> add()메서드 사용
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("hs1 => " + hs1);
		System.out.println();
		
		// Set은 데이터의 순서가 없고 중복을 허용하지 않는다. 그래서 이미 있는 데이터를 add하면
		// add()메서드는 false를 반환하고 데이터가 추가되지 않는다.
		boolean isAdd = hs1.add("FF");
		System.out.println("중복되지 않을 때 isAdd => " + isAdd);
		System.out.println("hs1 => " + hs1);
		System.out.println();
		
		isAdd = hs1.add("CC");
		System.out.println("중복될 때 isAdd => " + isAdd);
		System.out.println("hs1 => " + hs1);
		System.out.println();
		
		// Set의 데이터를 수정하려면 수정하는 명령이 따로 없기 때문에 원래 데이터를 삭제한 후 추가해 주어야 한다.
		// 삭제하는 메서드 : clear() -> 전체 삭제
		//				remove(삭제할데이터) --> 삭제성공(true), 실패(false) 반환
		
		// 예) FF를 EE로 변경하기
		hs1.remove("FF");
		hs1.add("EE");
		System.out.println("hs1 => " + hs1);
		System.out.println();
		
		/*
		hs1.clear();
		System.out.println("clear 후 hs1 => " + hs1);
		System.out.println();
		*/
		
		/*
		Set의 저장된 데이터를 차례로 꺼내와서 처리하는 방법
		1. 향상된 for문을 이용하는 방법
		2. Iterator를 이용하는 방법
		*/
		
		// 향상된 for문 이용하기
		System.out.println("향상된 for문으로 출력하기...");
		for(Object obj : hs1){
			System.out.println(obj);
		}
		System.out.println();
		
		// Iterator를 이용하기
		// Iterator는 컬렉션에 저장되어 있는 요소들을 읽어 오는 방법을 표준화한 것이다.
		// Iterator객체 구하기  ==> 각 컬렉션 객체에는 iterator()라는 메서드가 있는데 이 메서드가 
		//					    해당 컬렉션의 Iterator객체를 생성해주는 메서드 이다.
		
		// Set에서 Iterator객체 구하기
		Iterator it = hs1.iterator();
		
		System.out.println("Iterator로 출력하기");
		// 데이터 개수만큼 반복 처리
		// hasNext()메서드 ==> 다음 자료가 있는지 검사한다. 있으면 true, 없으면 false 반환
		while(it.hasNext()){  
			// next()메서드 ==> 포인터를 다음 자료 위치로 이동 시킨 후 그 위치의 데이터를 읽어와 반환한다.
			System.out.println(it.next());
		}
		System.out.println();
		
		System.out.println("Set의 자료 개수 : " + hs1.size());
		System.out.println();
		
		
		// 최소값 ~ 최대값 사이의 난수 만들기
		// (int)(Math.random() * (최대값-최소값+1) + 최소값)
		
		
		// Set을 이용한 로또번호 만들기
		HashSet<Integer> lottoSet = new HashSet<>();
		
		while(lottoSet.size() < 6){
			// 1 ~ 45 사이의 난수 만들기   
			int num = (int)(Math.random() * 45 + 1);
			lottoSet.add(num);
		}
		System.out.println("로또번호 : " + lottoSet);
		
		// Set 유형의 데이터를 List 유형으로 변환해서 사용할 수 있다.
		ArrayList<Integer> lottoList = new ArrayList<>(lottoSet);
		System.out.println("List 로또 : " + lottoList);
		
	}

}








