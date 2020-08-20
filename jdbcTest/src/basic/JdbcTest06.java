package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest06 {

	public static void main(String[] args) {
Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "SEM", "java");
			
			String sql = "select sum(cart_qty) hap from cart where cart_member='a001' ";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			// select문의 처리 결과가 1개의 레코드일 경우에는 while문 대신 if문을 사용해도 된다.
			if(rs.next()){
				//int sum = rs.getInt("sum(cart_qty)");  // alias가 없을 때
				int sum = rs.getInt("hap");				// hap이라는 alias가 있을 때
				System.out.println("sum = " + sum);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}

	}

}
