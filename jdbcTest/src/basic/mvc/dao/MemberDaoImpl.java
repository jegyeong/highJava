package basic.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.DBUtil;
import util.DBUtil3;
import basic.mvc.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao{
	
	private static MemberDaoImpl dao;
	
	private MemberDaoImpl(){}
	
	public static MemberDaoImpl getInstance(){
		if(dao==null) dao = new MemberDaoImpl();
		return dao;
	}
	

	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr) "
					+ "values (?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
			if(conn!=null) try{conn.close();} catch(SQLException e){}
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "delete from mymember where mem_id=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
			if(conn!=null) try{conn.close();} catch(SQLException e){}
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set mem_name=?, mem_tel=?, mem_addr=? "
					+ " where mem_id=?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, memVo.getMem_name());
			 pstmt.setString(2, memVo.getMem_tel());
			 pstmt.setString(3, memVo.getMem_addr());
			 pstmt.setString(4, memVo.getMem_id());
			 
			 cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
			if(conn!=null) try{conn.close();} catch(SQLException e){}
		}
		return cnt;
	}

	@Override
	public int getMemberCount(String memId) {
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

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<MemberVO> memList = new ArrayList<>();
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from mymember";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				// 각각의 레코드 값을 VO에 담는다.
				MemberVO memVo = new MemberVO();
				memVo.setMem_id( rs.getString("mem_id") );
				memVo.setMem_name( rs.getString("mem_name") );
				memVo.setMem_tel(rs.getString("mem_tel") );
				memVo.setMem_addr(rs.getString("mem_addr") );
				
				// 데이터가 담겨진 VO객체를 List에 추가한다.
				memList.add(memVo);
			}
			
			
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();} catch(SQLException e){}
			if(stmt!=null) try{stmt.close();} catch(SQLException e){}
			if(conn!=null) try{conn.close();} catch(SQLException e){}
		}
		
		return memList;
	}

	@Override
	public int updateMember(Map<String, String> paramMap) {
		// 매개변수 paramMap의 구조
		// key       value
		// field     수정할 필드명
		// data      수정할 값
		// memId     수정할 회원ID
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set " + paramMap.get("field") + "=? "
					+ " where mem_id=?";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, paramMap.get("data"));
			 pstmt.setString(2, paramMap.get("memId"));
			 
			 cnt = pstmt.executeUpdate();
			 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
			if(conn!=null) try{conn.close();} catch(SQLException e){}
		}
		return cnt;
	}

	
	@Override
	public List<MemberVO> getSearchMember(Map<String, String> searchMap) {
		// Map의 구조
		// key        value  
		// field	  검색할 컬럼명
		// search    검색할 내용
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> memList = new ArrayList<>();
		
		try {
			conn = DBUtil3.getConnection();
			// select * from mymember where mem_id like '%a%';
			String sql = "select * from mymember where " + searchMap.get("field")
					+ " like '%' || ? || '%'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchMap.get("search"));
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				MemberVO memVo = new MemberVO();
				memVo.setMem_id(rs.getString("mem_id"));
				memVo.setMem_name(rs.getString("mem_name"));
				memVo.setMem_tel(rs.getString("mem_tel"));
				memVo.setMem_addr(rs.getString("mem_addr"));
				
				memList.add(memVo);
			}
			
			
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		} finally {
			if(rs!=null) try{rs.close();} catch(SQLException e){}
			if(pstmt!=null) try{pstmt.close();} catch(SQLException e){}
			if(conn!=null) try{conn.close();} catch(SQLException e){}
		}
		
		return memList;
	}

	
}
