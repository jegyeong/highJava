package basic;

import java.util.LinkedList;

public class StackQueueTest {
	/*
		- stack : 후입선출(LIFO) ==> Stack, LinkedList
		
		- queue : 선입선출(FIFO) ==> LinkedList
	*/
	public static void main(String[] args) {
		/*
		- stack의 명령
			1. 자료 입력 : push(입력할 값)
			2. 자료 출력 : pop()  	==> 자료를 꺼내온 후 꺼내온 자료를 stack에서 삭제한다.
						peek()	==> 자료를 꺼내온다. (삭제를 하지 않는다.)
			3. 스택이 비었는지 여부 검사 : isEmpty() ==> 비었으면 true, 그렇지 않으면 false
		*/
		LinkedList<String> stack = new LinkedList<>();
		
		System.out.println("1. 비었는지 여부 : " + stack.isEmpty());
		
		stack.push("홍길동");
		stack.push("일지매");
		stack.push("이순신");
		stack.push("강감찬");
		
		System.out.println("2. 비었는지 여부 : " + stack.isEmpty());
		
		System.out.println("현재의 stack => " + stack);
		
		String data = stack.pop();
		System.out.println("꺼내온 자료 : " + data);
		System.out.println("꺼내온 자료 : " + stack.pop());
		System.out.println("현재의 stack => " + stack);
		
		System.out.println("현재 사용할 수 있는 자료 : " + stack.peek());
		System.out.println("현재의 stack => " + stack);
		
		stack.push("성춘향");
		System.out.println("현재의 stack => " + stack);
		System.out.println("꺼내온 자료 : " + stack.pop());
		System.out.println("현재의 stack => " + stack);

		System.out.println();
		System.out.println("------------------------------------");
		System.out.println();
		//---------------------------------------------------

		/*
		- queue 명령
			1. 자료 입력 : offer(입력할 자료)
			2. 자료 출력 : poll() 	==> 큐에서 자료를 꺼낸다. 꺼내온 자료는 큐에서 삭제한다.
						peek()  ==> 큐에서 자료를 꺼낸다. (삭제하지 않는다.)
		*/
		LinkedList<String> queue = new LinkedList<>();
		
		queue.offer("홍길동");
		queue.offer("일지매");
		queue.offer("이순신");
		queue.offer("강감찬");
		
		System.out.println("현재 queue => " + queue);
		
		String temp = queue.poll();
		System.out.println("꺼내온 자료 : " + temp);
		System.out.println("꺼내온 자료 : " + queue.poll());
		System.out.println("현재 queue => " + queue);
		
		System.out.println("현재 사용할 수 있는 queue 데이터 : " + queue.peek());
		System.out.println("현재 queue => " + queue);
		
		queue.offer("성춘향");
		System.out.println("현재 queue => " + queue);
		System.out.println("꺼내온 자료 : " + queue.poll());
		System.out.println("현재 queue => " + queue);
		
		
	}

}








