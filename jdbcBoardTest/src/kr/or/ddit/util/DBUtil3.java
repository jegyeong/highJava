package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/*
	DB의 정보가 저장된 파일의 내용으로 DB접속 정보를 설정하기
	방법2) ResourceBundle객체를 이용한 방법
	
	ResourceBundle객체 ==>  확장자가 properties인 파일의 내용을 읽어와 key값과 value값을
						  	분리해서 그 정보를 갖고 있는 객체
*/
public class DBUtil3 {
	private static ResourceBundle bundle;	// ResourceBundle객체 변수 선언
	
	static{
		// ResourceBundle객체를 생성할 때 getBundle()메서드에 읽어올 파일 정보를 지정해 주는데
		// 이때 '파일명'만 지정하고 확장자는 지정하지 않는다.
		// (이유: 확장자는 항상 'properties'이기 때문에...)
		bundle = ResourceBundle.getBundle("dbinfo");  // ResourceBundle객체 생성
		
		// bundle객체의 값은  getString(key값)메서드를 이용해서 읽어온다.
		
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC드라이버 로딩 실패!!");
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(
					bundle.getString("url"), 
					bundle.getString("user"), 
					bundle.getString("pass"));
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패!!");
			return null;
		}
	}
	
	
	
}










