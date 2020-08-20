package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
	문제) 사용자로부터 주소의 일부분을 입력 받아 Member테이블에서 사용자가 입력한 주소가 
		 포함된 회원의 ID, 이름, 우편번호, 주소를 출력하시오.
		 
	예) 만약 '대흥동'이라고 입력했다면 주소에 '대흥동'이 포함된 모든 자료를 찾아낸다.
*/
public class JdbcTest03 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "SEM", "java");
			
			System.out.print("검색할 주소 입력 : ");
			String addr = scan.nextLine();
			
			String sql = "select mem_id, mem_name, mem_zip, mem_add1, mem_add2 "
					+ " from member where mem_add1 like '%" + addr + "%'";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				System.out.println("ID : " + rs.getString("mem_id"));
				System.out.println("이    름 : " + rs.getString("mem_name"));
				System.out.println("우편번호 : " + rs.getString("mem_zip"));
				System.out.println("주    소 : " + rs.getString("mem_add1") + " " + rs.getString("mem_add2"));
				System.out.println("---------------------------------------------------------");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}

	}

}








