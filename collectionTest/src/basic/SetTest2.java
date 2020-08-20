package basic;

import java.util.SortedSet;
import java.util.TreeSet;

public class SetTest2 {

	public static void main(String[] args) {
		// TreeSet ==> 데이터들을 자동으로 정렬해서 저장한다.
		TreeSet<String> ts = new TreeSet<>();
		
		for(char ch='Z'; ch>='A'; ch--){
			String temp = String.valueOf(ch);
			//String temp = ch + "";
			ts.add(temp);
		}
		System.out.println("TreeSet 자료 => " + ts);

		// TreeSet에 저장된 자료 중 특정한 범위의 자료를 찾아서 반환해 주는 메서드들이 있다.
		//  ==> 반환되는 자료는 SortedSet형으로 반환된다.
		
		// headSet(기준값) ==> '기준값'보다 작은 자료들을 반환한다. ('기준값'은 포함되지 않는다.)
		// headSet(기준값, 포함여부) ==> 포함여부가 true이면 '기준값'까지 포함해서 반환한다.
		SortedSet<String> ss1 = ts.headSet("K");
		System.out.println("K 이전 자료 : " + ss1);
		System.out.println("K 이전 자료 : " + ts.headSet("K", true));
		System.out.println();
		
		// tailSet(기준값) ==> '기준값'보다 큰 자료들을 반환한다. ('기준값'이 포함된다.)
		// tailSet(기준값, 포함여부) ==> 포함 여부가 false이변 '기준값'이 포함되지 않는다.
		SortedSet<String> ss2 = ts.tailSet("K");
		System.out.println("K 이후 자료 : " + ss2);
		System.out.println("K 이후 자료 : " + ts.tailSet("K", false));
		System.out.println();
		
		// subSet(시작값, 종료값) ==> 시작값부터 종료값 이전까지의 자료를 반환한다.(시작값 포함, 종료값 미포함)
		// subSet(시작값, 포함여부, 종료값, 포함여부)
		System.out.println("K부터 N까지의 자료 : " + ts.subSet("K", "N"));
		
		System.out.println("K부터 N까지의 자료 : " + ts.subSet("K", true, "N", true));
		System.out.println("K부터 N까지의 자료 : " + ts.subSet("K", false, "N", false));
		System.out.println("K부터 N까지의 자료 : " + ts.subSet("K", false, "N", true));
		
		
		
	}

}







