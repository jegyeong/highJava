package kr.or.ddit.rmi.vo;

import java.io.Serializable;

// RMI에서 데이터 전달용으로 사용할 객체는 네트워크를 통해서
// 전달되어야 하기때문에 직렬화가 필요하다.
// 그래서 Serializable을 구현하여 작성한다.
public class TestVO implements Serializable {
	
	private static final long serialVersionUID = -708917700929957802L;
	
	private String name;
	private int num;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

}
