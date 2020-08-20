package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// JDBC => Java DataBase Connectivity
// 		   Java를 이용해서 DB시스템에 접속해서 자료를 처리하는 기술
// 		   JDBC를 사용하려면 먼저 JDBC용 라이브러리(jar파일)를 프로젝트에 등록해 주어야 한다. 

/*
	DB를 처리하는 프로그램 작성 순서
	
1. 드라이버 로딩	
	- Class.forName("oracle.jdbc.driver.OracleDriver");
	
2. DB시스템에 접속하기 ==> 접속이 성공되면 Connection객체를 생성한다.
	- DriverManager.getConnection()메서드를 이용한다.

3. 실행할 쿼리문을 만들어서 DB시스템에 전달한다.
	- Statement객체 또는 PreparedStatement객체를 이용하여 SQL문을 실행한다.
	  (Statement객체와 PreparedStatement객체는 Connection객체를 이용해서 생성한다.)

4. 3번의 결과를 받아서 처리한다.
	1) 실행한 SQL문이 select문일 경우 :
		- select문의 실행 결과가 ResultSet객체에 저장된다.
	2) 실행한 SQL문이 insert, update, delete 등일 경우 :
		- 해당 SQL문이 처리한 레코드수(정수값)가 반환된다.

5. 사용했던 자원들을 반납한다.
	   
*/
public class JdbcTest01 {

	public static void main(String[] args) {
		// DB작업에 필요한 객체 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. 접속하기 => Connection객체 생성하기
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"SEM", "java");
			
			// 3. SQL문을 만들어서 실행하기
			
			// 3-1 SQL문 작성
			String sql = "select * from lprod";
			// 3-2 Statement객체 또는 PreparedStatement객체를 생성한다.
			stmt = conn.createStatement();  // Statement객체 생성
			
			// 4. SQL문을 실행하고 결과를 받아온다.
			rs = stmt.executeQuery(sql);
			
			// 5. 결과를 받아서 처리한다.
			// 	  select문의 처리 결과는 ResultSet에 저장되는데 이 ResultSet객체에 저장된 데이터는
			//    반복문과 next()메서드를 이용해서 차례로 읽어와 처리한다.
			
			// rs.next() ==> Result객체의 데이터를 가리키는 포인터를 다음 레코드 위치로 이동시키고
			//               그 곳에 데이터가 있으면 true를 반환하고 없으면 false를 반환한다.
			while(rs.next()){
				// 현재의 레코드에서 컬럼값을 가져오는 방법
				// 1) rs.get데이터타입명("컬럼명")
				// 2) rs.get데이터타입명(컬럼번호)   ==> 컬럼번호는 1번부터 시작
				// 3) rs.get데이터타입명("alias명")
				
				System.out.println("lprod_Id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_Gu : " + rs.getString(2));
				System.out.println("lprod_Nm : " + rs.getString("lprod_nm"));
				System.out.println("---------------------------------------");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 6. 사용했던 자원 반납 ==> 생성된 순서의 역순으로 반납한다.
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}

	}

}








