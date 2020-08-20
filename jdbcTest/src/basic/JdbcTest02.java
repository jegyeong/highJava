package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
	문제 : 사용자로부터 lprod_id값을 입력받아 입력한 값보다 lprod_id가 큰 자료를 출력하시오.

*/
public class JdbcTest02 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			System.out.print("lprod_id값 입력 : ");
			//int lprodId = scan.nextInt();
			String lprodId = scan.nextLine();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"SEM", "java");
			
//			String sql = "select * from lprod where lprod_id = " + lprodId ;
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
			String sql = "select * from lprod where lprod_id = ?" ;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, lprodId);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()){
				System.out.println("lprod_ID : " + rs.getInt("lprod_id"));
				System.out.println("lprod_GU : " + rs.getString("lprod_gu"));
				System.out.println("lprod_NM : " + rs.getString("lprod_nm"));
				System.out.println("--------------------------------");
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
