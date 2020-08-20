package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
	- 정렬과 관련된 interface는 Comparable과 Comparator 이렇게 두 가지가 있다.
	
	- 이 중에 사용자가 작성하는 객체 자체에 정렬 기준을 넣기 위해서는 Comparable 인터페이스를 구현하고,
	    정렬 기준을 외부에서 별도로 구현할 경우에는 Comparator 인터페이스를 구현하여 사용하면 된다.
	
	 - Comparable 인터페이스는 compareTo()메서드를 재정의 해서 구현해야 하고,
	   Comparator 인터페이스는 compare()메서드를 재정의 해서 구현해야 한다.
*/

// 회원 정보를 저장할 수 있는 클래스 작성  ==> 회원의 이름을 기준으로 오름차순 정렬이 될 수 있도록 구현한다.
class Member implements Comparable<Member>{
	private int num;		// 회원번호
	private String name; 	// 회원이름
	private String tel; 	// 전화번호
	
	// 생성자
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}
	
	// getter, setter
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	// 이 메서드에서 회원의 이름을 기준으로 오름차순 정렬이 되도록 재정의 한다.
	@Override
	public int compareTo(Member mem) {
		return this.getName().compareTo(mem.getName());
		/*
		// 회원번호의 오름차순일 경우
		if(this.getNum() > mem.getNum()){
			return 1;
		}else if(this.getNum()==mem.getNum()){
			return 0;
		}else{
			return -1;
		}
		*/
	}

	
}




public class ListSortTest2 {

	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "변학도", "010-2222-1111"));
		memList.add(new Member(9, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "이순신", "010-4444-1111"));
		memList.add(new Member(6, "강감찬", "010-5555-1111"));
		memList.add(new Member(2, "일지매", "010-6666-1111"));
		
		System.out.println("정렬전...");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("-----------------------------");
		
		Collections.sort(memList);
		
		System.out.println("회원이름의 오름차순 정렬후...");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("-----------------------------");
		
		// 회원 번호의 내림차순으로 정렬이 될 수 있는 외부 정렬 기준을 작성하여 
		// 리스트의 데이터를 정렬한 후 출력하시오.
		Collections.sort(memList, new SortNumDesc());
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("-----------------------------");
	}
}

// 회원 번호를 기준으로 내림차순 정렬을 수행하는 외부 정렬 기준 class 작성
class SortNumDesc implements Comparator<Member>{
	@Override
	public int compare(Member mem1, Member mem2) {
		/*
		if(mem1.getNum() < mem2.getNum()){
			return 1;	// 양수를 반환하면 두 값의 순서가 바뀐다.
		}else if(mem1.getNum() > mem2.getNum()){
			return -1;
		}else{
			return 0;
		}
		*/
		
		// 수식을 이용한 방법
		//return mem2.getNum() - mem1.getNum();
		
		// Wrapper클래스를 이용한 방법1 ==> 전역 메서드인 compare() 이용하기
		//return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
		
		// Wrapper클래스를 이용한 방법2 ==> 멤버 메서드인 compareTo() 이용하기
		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
	}
}




