package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class ThreadTest19 {

	public static void main(String[] args) {
		/* 
		Vector, Hashtable등의 예전부터 존재하던 Collection들은 내부에 동기화 처리가 되어 있다.
		
		그런데 새롭게 구성된 Collection들은 기본적으로 동기화 처리가 되어 있지 않다.
		그래서, 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면 동기화 처리를 한 후에 사용해야 한다.
		
		*/
		
		final Vector<Integer> vec = new Vector<>();
		
		// 동기화 하지 않은 경우
		final ArrayList<Integer> list = new ArrayList<>();
		
		// 동기화 하는 경우
		final List<Integer> list2 = Collections.synchronizedList(
												new ArrayList<Integer>());
		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				for(int i=0; i<1000; i++){
					//vec.add(i);
					//list.add(i);
					list2.add(i);
				}
				
			}
		};
		
		Thread[] ths = new Thread[]{
			new Thread(r), new Thread(r), new Thread(r),
			new Thread(r), new Thread(r) };
		
		for(Thread th : ths){
			th.start();
		}
		
		for(Thread th : ths){
			try {
				th.join();
			} catch (InterruptedException e) {
			}
		}
		
		//System.out.println("vec의 개수 : " + vec.size());
		//System.out.println("list의 개수 : " + list.size());
		System.out.println("list2의 개수 : " + list2.size());
		

	}

}
