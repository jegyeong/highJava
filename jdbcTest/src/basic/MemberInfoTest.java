package basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtil;
import util.DBUtil2;
import util.DBUtil3;

/*
	회원 정보를 관리하는 프로그램 만들기 (DB테이블명 : MYMEMBER)
	
	아래의 메뉴를 모두 구현하시오.(CRUD구현)
	
	메뉴예시)
		-------------------------
		      == 작업 선택 ==
			1. 자료 입력			--> insert(Create)
			2. 자료 삭제			--> delete(Delete)
			3. 자료 수정			--> update(Update)
			4. 전체 자료 출력 		--> select(Read)
			0. 작업 끝.
		-------------------------
		
	- 자료의 삭제는 회원ID를 입력받아 삭제한다.
	- 자료를 추가할 때 회원ID의 중복 검사를 한다.
	
*/
public class MemberInfoTest {
	private Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		new MemberInfoTest().memberStart();
	}
	
	public int displayMenu(){
		System.out.println();
		System.out.println("-------------------------");
	    System.out.println("\t== 작업 선택 ==");
	    System.out.println("\t1. 자료 입력");
	    System.out.println("\t2. 자료 삭제");
	    System.out.println("\t3. 자료 수정");
	    System.out.println("\t4. 자료 수정2");
	    System.out.println("\t5. 전체 자료 출력");
	    System.out.println("\t0. 작업 끝.");
	    System.out.println("-------------------------");
	    System.out.print("   작업 선택 >> ");
	    int num = scan.nextInt();
	    return num;
	}

	public void memberStart(){
		while(true){
			int choice = displayMenu();
			
			switch(choice){
				case 1 : 		// 입력(추가)
					memberInsert();
					break;
				case 2 : 		// 삭제
					memberDelete();
					break;
				case 3 : 		// 수정
					memberUpdate();
					break;
				case 4 : 		// 수정2
					memberUpdate2();
					break;
				case 5 : 		// 전체 자료 출력
					displayMember();
					break;
				case 0 : System.out.println("작업을 종료합니다.");
					return;
				default : System.out.println("작업 선택이 잘못되었습니다.");
						System.out.println("다시 입력하세요.");
			}
			
		}
	}
	
	
	// 새로운 회원 정보를 추가하는 메서드
	public void memberInsert(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");
		
		System.out.print("회원ID >> ");
		String memId = scan.next();
		int count = getMemberCount(memId);
		if(count>0){
			System.out.println(memId + "는 이미 가입된 회원 ID입니다.");
			//System.out.println("다른 ID로 다시 입력하세요.");
			return;
		}
		
		System.out.print("회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("회원전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();	// 입력 버퍼 비우기
		System.out.print("회원주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr) "
					+ "values (?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println(memId + " 회원이 추가 되었습니다.");
			}else{
				System.out.println("추가 작업 실패~~");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
			if(conn!=null) try{conn.close();} catch(SQLException e){}
		}
		
	}
	
	// 회원 정보를 삭제하는 메서드
	public void memberDelete(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("삭제할 회원ID >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		if(count==0){
			System.out.println(memId + "는 없는 회원ID 입니다.");
			return;
		}
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "delete from mymember where mem_id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println(memId + "회원의 정보가 삭제되었습니다.");
			}else{
				System.out.println("삭제 작업 실패!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
			if(conn!=null) try{conn.close();} catch(SQLException e){}
		}
	}
	
	// 회원 정보를 수정하는 메서드
	public void memberUpdate(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("수정할 회원ID >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		
		if(count==0){
			System.out.println(memId + "은 없는 회원ID입니다. 수정작업을 종료합니다.");
			return;
		}
		
		System.out.print("새로운 회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("새로운 회원 전화번호>> ");
		String memTel = scan.next();
		
		scan.nextLine();  // 버퍼 비우기
		System.out.print("새로운 회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set mem_name=?, mem_tel=?, mem_addr=? "
					+ " where mem_id=?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, memName);
			 pstmt.setString(2, memTel);
			 pstmt.setString(3, memAddr);
			 pstmt.setString(4, memId);
			 
			 int cnt = pstmt.executeUpdate();
			 if(cnt>0){
				 System.out.println(memId + "회원의 정보가 수정되었습니다.");
			 }else{
				 System.out.println("수정 작업 실패");
			 }
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
			if(conn!=null) try{conn.close();} catch(SQLException e){}
		}
	
	}
	
	// 회원 정보를 수정하는 메서드2
	public void memberUpdate2(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("수정할 회원ID >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		
		if(count==0){
			System.out.println(memId + "은 없는 회원ID입니다. 수정작업을 종료합니다.");
			return;
		}
		
		
		int num = 0;		// 선택한 항목 번호가 저장될 변수
		String selField = "";  // 선택한 항목의 컬럼명이 저장될 변수
		String msg = "";
		do{
			System.out.println();
			System.out.println("수정할 항목을 선택하세요");
			System.out.println(" 1.회원이름     2.전화번호     3.주소");
			System.out.println("---------------------------");
			System.out.print(" 항목선택 >> ");
			num = scan.nextInt();
			
			switch(num){
				case 1 : selField = "mem_name"; msg = "새로운 회원 이름"; 
					break;
				case 2 : selField = "mem_tel"; msg = "새로운 전화번호";
					break;
				case 3 : selField = "mem_addr"; msg = "새로운 주소";
					break;
				default : System.out.println("항목선택을 잘못했습니다. 다시 선택하세요");
			}
		}while(num<1 || num>3);
		
		
		System.out.print(msg + " >> ");
		String data = "";
		if(selField.equals("mem_addr")){
			scan.nextLine(); // 버퍼 비우기
			data = scan.nextLine();
		}else{
			data = scan.next();
		}
		
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set " + selField + "=? "
					+ " where mem_id=?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, data);
			 pstmt.setString(2, memId);
			 
			 int cnt = pstmt.executeUpdate();
			 if(cnt>0){
				 System.out.println(memId + "회원의 " + selField + " 정보가 수정되었습니다.");
			 }else{
				 System.out.println("수정 작업 실패");
			 }
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
			if(conn!=null) try{conn.close();} catch(SQLException e){}
		}
	}
	
	
	// 회원 전체 자료를 출력하는 메서드
	public void displayMember(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//conn = DBUtil.getConnection();
			//conn = DBUtil2.getConnection();
			conn = DBUtil3.getConnection();
			
			String sql = "select * from mymember";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			System.out.println();
			System.out.println("--------------------------------");
			System.out.println("  ID    이름         전화번호           주 소 ");
			System.out.println("--------------------------------");
			while(rs.next()){
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				System.out.println(" " + memId + "   " + memName + "   "
							+ memTel + "   " + memAddr);
			}
			System.out.println("--------------------------------");
			System.out.println("출력 끝...");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();} catch(SQLException e){}
			if(stmt!=null) try{stmt.close();} catch(SQLException e){}
			if(conn!=null) try{conn.close();} catch(SQLException e){}
		}
		
	}
	
	
	// 회원ID를 매개값으로 받아서 해당 회원의 인원수를 반환하는 메서드
	public int getMemberCount(String memId){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select count(*) cnt from mymember where mem_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt("cnt");
			}
			
		} catch (Exception e) {
			count = 0;
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();} catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
			if(conn!=null) try{conn.close();} catch(SQLException e){}
		}
		
		return count;
				
	}
	
	
	
}












