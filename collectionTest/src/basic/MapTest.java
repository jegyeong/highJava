package basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {
	/*
	Map ==> key값과 value값을 한 쌍으로 관리하는 객체
	 	==> key값은 중복을 허용하지 않고, 순서가 없다. (Set의 특징을 갖는다.)
	 	==> value값은 중복을 허용한다.
	
	*/
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<>();
		
		// 자료 추가 ==> put(key값, value값);
		map.put("name", "홍길동");
		map.put("tel", "010-1234-5678");
		map.put("addr", "대전시");
		
		System.out.println("map => " + map);
		System.out.println("map의 데이터 개수 => " + map.size());
		
		// 자료 수정 ==> 데이터를 저장할 때 key값이 중복되면 나중에 입력한 값이 저장된다.
		//			  즉, key값은 같게하고 value값을 다른 값으로 지정하면 된다.
		map.put("addr", "서울시");
		System.out.println("map => " + map);
		
		/*
		// 자료 삭제 ==> remove(key값);	==> 해당 key값을 갖는 데이터를 삭제한다.
		//							==> 반환값은 삭제된 데이터의 value값이 반환된다.
		String temp = map.remove("tel");
		System.out.println("temp = " + temp);
		System.out.println("map => " + map);
		*/
		
		
		// 자료 읽기 ==> get(key값);	==> 해당 key값을 갖는 데이터의 value값을 반환한다.
		System.out.println("name => " + map.get("name"));
		System.out.println();
		
		// --------------------------------------
		// map의 전체 데이터를 차례로 처리하는 방법
		
		// 방법1 ==> keySet()메서드 이용하기 (key값 전체를 구해서 처리하는 방법)
		//		    --> 맵의 key값만 읽어와 Set형으로 반환한다.
		Set<String> keySet = map.keySet();
		Iterator<String> keyIt = keySet.iterator();
		System.out.println("Iterator 이용");
		while(keyIt.hasNext()){
			String key = keyIt.next();  // 키값 가져오기
			System.out.println(key + " ==> " + map.get(key) );
		}
		System.out.println("-----------------------------------");
		
		System.out.println("향상된 for문 이용");
		for(String key : keySet){
			System.out.println(key + " --> " + map.get(key) );
		}
		System.out.println("-----------------------------------");

		
		// 방법2 ==> value값만 읽어와 처리하기
		//			values()메서드 이용하기
		for(String val : map.values()){
			System.out.println(val);
		}
		System.out.println("-----------------------------------");
		
		
		// 방법3 => Map에는  Entry라는 내부 class가 만들어져 있다.
		//		   이 Entry클래스는 key와 value라는 멤버변수로 구성되어 있다.
		//		  Map에서는 이 Entry클래스들을 Set형식으로 저장하여 관리한다.
		
		// Entry객체 전체를 가져오기  ==> entrySet()메서드를 이용한다.
		// 	  ==> 가져온 Entry들은 Set형식으로 되어 있다.
		Set<Map.Entry<String, String>> mapSet = map.entrySet();
		
		// Iterator나 향상된 for문을 이용해서 Entry객체를 처리한다.
		Iterator<Map.Entry<String, String>> entryIt = mapSet.iterator();
		
		while(entryIt.hasNext()){
			Map.Entry<String, String> entry = entryIt.next();
			
			System.out.println("key값 : " + entry.getKey());
			System.out.println("Value값 : " + entry.getValue());
			System.out.println();
		}
		System.out.println("==========================================");
		
		System.out.println();
		
		
		// key값의 존재 여부를 나타내는 메서드  ==> containsKey(key값)
		//			==> 해당 key값이 있으면 true, 없으면 false
		System.out.println("name 키값 존재여부 : " + map.containsKey("name"));
		System.out.println("age 키값 존재여부 : " + map.containsKey("age"));
		
		
		
	}

}










