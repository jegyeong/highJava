package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/*
	DB의 LPROD테이블에 다음과 같은 데이터를 추가한다.
	
	lprod_id : 101, lprod_gu : N101, lprod_nm : 농산물
	lprod_id : 102, lprod_gu : N102, lprod_nm : 수산물
	lprod_id : 103, lprod_gu : N103, lprod_nm : 축산물
	
*/
public class JdbcTest05 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		int[] lprodIdArr = new int[]{101, 102, 103};
		String[] lprodGuArr = new String[]{"N101", "N102", "N103"};
		String[] lprodNmArr = new String[]{"농산물", "수산물", "축산물"};
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"SEM", "java");
			/*
			// Statement 이용
			stmt = conn.createStatement();
			for(int i=0; i<lprodIdArr.length; i++){
				String sql = "insert into lprod (lprod_id, lprod_gu, lprod_nm) "
						+ " values (" + lprodIdArr[i] + ", '" + lprodGuArr[i] 
						+ "', '" + lprodNmArr[i] + "')";
				int cnt = stmt.executeUpdate(sql);
				System.out.println(i + "번째 반환값 : " + cnt);
			}
			*/
			
			// PreparedStatement 이용
			String sql = "insert into lprod (lprod_id, lprod_gu, lprod_nm) "
					+ " values (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			for(int i=0; i<lprodIdArr.length; i++){
				pstmt.setInt(1, lprodIdArr[i]);
				pstmt.setString(2, lprodGuArr[i]);
				pstmt.setString(3, lprodNmArr[i]);
				
				int cnt = pstmt.executeUpdate();
				
				System.out.println(i + "번째 반환값 : " + cnt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
			if(conn!=null) try{ conn.close(); }catch(SQLException e){}
		}

	}

}







