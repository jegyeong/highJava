package basic;

import java.util.ArrayList;

public class ArrayListTest {

	public static void main(String[] args) {
		// ArrayList는 기본적인 사용법이 Vector와 같다.
		ArrayList list1 = new ArrayList();
		
		System.out.println("처음 크기 : " + list1.size());
		// add()메서드를 이용하여 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111);
		list1.add('k');
		list1.add(false);
		list1.add(12.345);
		
		System.out.println("list1의 크기 : " + list1.size());
		System.out.println("list1 => " + list1);
		
		// get()메서드로 데이터를 꺼내온다.
		System.out.println("1번째 자료 : " + list1.get(1));
		
		// 데이터 끼워넣기도 똑같다.
		list1.add(0, "zzz");
		System.out.println("list1 => " + list1);
		
		// 데이터 수정하기 ==> set()메서드
		String temp = (String)list1.set(0, "yyy");
		System.out.println("temp => " + temp);
		System.out.println("list1 => " + list1);
		
		// 삭제도 같다.
		list1.remove(0);
		System.out.println("삭제 후 list1 => " + list1);
		
		list1.remove("bbb");
		System.out.println("삭제 후 list1 => " + list1);
		
		System.out.println("------------------------------------");
		
		// 제네릭을 지정하여 선언할 수 있다.
		ArrayList<String> list2 = new ArrayList<>();
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");
		
		for(int i=0; i<list2.size(); i++){
			System.out.println(i + " : " + list2.get(i));
		}
		System.out.println("------------------------------------");
		
		for(String s : list2){
			System.out.println(s);
		}
		System.out.println("------------------------------------");

		// contains(비교객체); ==> 리스트에 '비교객체'가 있으면 true, 없으면 false 반환
		System.out.println("DDDD 데이터 존재 여부 : " + list2.contains("DDDD"));
		System.out.println("ZZZZ 데이터 존재 여부 : " + list2.contains("ZZZZ"));
		System.out.println("------------------------------------");
		
		// indexOf(비교객체); ==> 리스트에 '비교객체'가 있으면 '비교객체'가 있는 index값을
		//						반환하고, 없으면 -1을 반환한다.
		System.out.println("DDDD의 index값 : " + list2.indexOf("DDDD"));
		System.out.println("ZZZZ의 index값 : " + list2.indexOf("ZZZZ"));
		System.out.println("------------------------------------");
		
		// toArray()  ==> 리스트 안의 데이터들을 배열로 변환하여 반환한다.
		//			  ==> 기본적으로 Object형 배열로 변환된다.
		
		// toArray(new 제네릭타입[0])  ==> 제네릭 타입의 배열로 변환된다.
		
		Object[] strArr = list2.toArray();
		//String[] strArr = list2.toArray();  // 오류
		
		System.out.println("배열의 개수 : " + strArr.length);
		for(int i=0; i<strArr.length; i++){
			//System.out.println(i + "번째 : " + strArr[i]);
			String tempData = (String)strArr[i];
			System.out.println(i + "번째 : " + tempData);
		}
		System.out.println("------------------------------------");
		
		String[] strArr2 = list2.toArray(new String[0]);
		for(String str : strArr2){
			System.out.println(str);
		}
		
		
		
		
		
		
		

	}

}
