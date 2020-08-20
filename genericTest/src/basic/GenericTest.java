package basic;

/*
	제네릭 클래스 만들기 (1.5부터 지원)

형식) class 클래스명<제네릭타입글자>{
		제네릭타입글자  변수명 ;		// 변수 선언에 사용할 경우
		...
		
		제네릭타입글자 메서드명(매개변수들....){   // 메서드의 반환값으로 사용할 경우
			...
			return 값;
		}
		
		반환값자료형 메서드명(제네릭타입글자 변수명, ...){  // 메서드의 매개변수로 사용할 경우
			...
		}

	}
	
*/

class MyGenericClass<T>{
	private T val;		// 변수 선언에 사용
	
	public void setVal(T val){	// 메서드의 매개변수에 사용
		this.val = val;
	}
	
	public T getVal(){		// 메서드의 반환값으로 사용
		return val;
	}
	
}



class NonGenericClass{
	private Object val;
	
	public void setVal(Object val){
		this.val = val;
	}
	
	public Object getVal(){
		return val;
	}
}



public class GenericTest {

	public static void main(String[] args) {
		NonGenericClass nc1 = new NonGenericClass();
		NonGenericClass nc2 = new NonGenericClass();
		
		nc1.setVal("가나다라");
		nc2.setVal(100);
		
		String temp = (String)nc1.getVal();
		System.out.println("temp = " + temp);

		int intTemp = (int)nc2.getVal();
		System.out.println("intTemp = " + intTemp);
		
		//int test = (int)nc1.getVal();
		//System.out.println("test = " + test);
		
		//-----------------------------------------
		MyGenericClass<String> mg1 = new MyGenericClass<>(); 
		MyGenericClass<Integer> mg2 = new MyGenericClass<>(); 
		
		mg1.setVal("안녕하세요");
		//mg1.setVal(400);
		mg2.setVal(300);
		
		String gTemp = mg1.getVal();
		System.out.println("gTemp = " + gTemp);
		
		int intgTemp = mg2.getVal();
		//gTemp = mg2.getVal();
		
		System.out.println("intgTemp = " + intgTemp);
		

	}

}
