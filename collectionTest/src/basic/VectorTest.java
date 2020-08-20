package basic;

import java.util.Vector;

public class VectorTest {

	public static void main(String[] args) {
		Vector v1 = new Vector();
		
		System.out.println("처음 크기 : " + v1.size());
		
		// 자료 추가 ==> add()메서드 이용
		v1.add("aaa");
		v1.add(111);
		v1.add(new Integer(123));
		v1.add('a');
		v1.add(true);
		v1.add(3.14);
		
		System.out.println("v1의 크기 : " + v1.size());
		
		// addElement()메서드 ==> 이 메서드도 데이터를 추가할 때 사용할 수 있는데
		//					    기본 기능이 add()메서드와 같다.
		//					  이전 프로그램과의 호환성을 위해서 존재한다.
		v1.addElement("KKK");
		
		System.out.println("v1 => " + v1);
		
		// add(index, 데이터) ==> 벡터의 index번째에 '데이터'를 끼워 넣는다.
		//					==> index는 0부터 시작한다.
		
		v1.add(1, "가나다라");
		System.out.println("v1 => " + v1);
		
		// set(index, 데이터) ==> 벡터의 index번째의 값을 '데이터'로 변경한다.(덮어쓴다.)
		//					==> 반환값 : 원래의 값
		String temp = (String)v1.set(1, "zzz");
		System.out.println("원래의 값 : " + temp);
		System.out.println("v1 => " + v1);
		
		
		// remove(index) ==> 벡터의 index번째 데이터를 삭제한다.
		//				 ==> 반환값 : 삭제된 데이터
		
		// remove(삭제할데이터) ==> 벡터에서 '삭제할데이터'를 찾아 삭제한다.
		//					 ==> '삭제할데이터'가 여러개 일 경우에는 맨 앞쪽의 데이터를 삭제한다.
		//					 ==> '삭제할데이터'가 '정수형'이거나 'char형'일 경우 
		//						  해당 데이터를 객체형으로 변환해서 사용해야 한다.
		
		
		temp = (String)v1.remove(1);
		System.out.println("삭제된 데이터 : " + temp);
		System.out.println("삭제 후 v1 => " + v1);
		
		v1.remove("KKK");
		System.out.println("삭제 후 v1 => " + v1);
		
		v1.remove(new Integer(123));
		System.out.println("삭제 후 v1 => " + v1);
		
		v1.add("aaa");
		System.out.println("추가 후 v1 => " + v1);
		
		v1.remove("aaa");
		System.out.println("삭제 후 v1 => " + v1);
		
		v1.remove(3.14);
		System.out.println("삭제 후 v1 => " + v1);
		
		v1.remove(true);
		System.out.println("삭제 후 v1 => " + v1);
		
		v1.remove(new Character('a'));
		System.out.println("삭제 후 v1 => " + v1);
		
		// get(index) ==> 벡터의 index번째 데이터를 반환한다.
		int data = (int)v1.get(0);
		System.out.println("0번째 데이터 : " + data);
		System.out.println();
		System.out.println("------------------------------");

		//-----------------------------------------------
		/*
		제네릭타입(Generic Type)이라는 것은 
		객체를 선언할 때  < >안에 그 Collection이 사용할 데이터 타입을 지정해 주는 것이다.
		이런식으로 선언하게 되면 그 데이터 타입 이외의 다른 데이터를 저장할 수 없게 된다.
		단, 제네릭으로 선언될 수 있는 데이터 타입은 클래스형 이여야 한다.
		그래서 int는 Integer, boolean은 Boolean, char는 Character등으로 대체하여 사용한다.
		
		*/
		Vector<Integer> v2 = new Vector<Integer>();  // int형만 저장할 수 있는 Vector
		Vector<String> v3 = new Vector<>();			 // String형만 저장할 수 있는 Vector
		
		v3.add("안녕하세요");
		//v3.add(123);   // 오류 : 지정한 제네릭과 다른 종류의 데이터를 추가할 수 없다.
		String temp2 = v3.get(0);
		
		
		Vector<Vector> vv = new Vector<>();
		Vector<Vector<Vector>> vvv = new Vector<>();
		
		// ----------------------------------
		
		// clear() ==> 벡터의 전체 데이터를 모두 삭제한다. (크기를 0으로 만든다.)
		v3.clear();
		System.out.println("v3의 size : " + v3.size());
		
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		
		Vector<String> v4 = new Vector<>();
		v4.add("BBB");
		v4.add("EEE");
		
		System.out.println("v3 => " + v3);
		System.out.println("v4 => " + v4);
		
		// removeAll(Collectio객체)  ==> 'Collection객체'가 가지고 있는 데이터를 모두 삭제한다.
		v3.removeAll(v4);
		System.out.println("v3 => " + v3);
		
		System.out.println("-------------------------------------");
		System.out.println();
		
		v3.clear();
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		
		// 벡터의 데이터를 순서대로 모두 가져와 사용하고 싶으면 반복문을 사용하면 된다.(주로 for문을 많이 사용한다.)
		for(int i=0; i<v3.size(); i++){
			System.out.println(i + "번째 자료 : " + v3.get(i));
		}
		
		System.out.println("-------------------------------------");
		
		// 향상된 for문
		for(String str : v3){
			System.out.println(str);
		}
		System.out.println("-------------------------------------");
		

	}

}





