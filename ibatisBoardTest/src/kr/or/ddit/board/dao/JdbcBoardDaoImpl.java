package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.util.BuildedSqlMapClient;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {
	private SqlMapClient smc;
	
	private static JdbcBoardDaoImpl dao;
	
	private JdbcBoardDaoImpl() {	
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static JdbcBoardDaoImpl getInstance(){
		if(dao==null) dao = new JdbcBoardDaoImpl();
		
		return dao;
	}
	
	

	@Override
	public int insertBoard(JdbcBoardVO jBoardVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("jdbcBoard.insertBoard", jBoardVo);
			if(obj==null){
				cnt = 1;
			}
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			cnt = smc.delete("jdbcBoard.deleteBoard", boardNo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO jBoardVo) {
		int cnt = 0;
		try {
			cnt = smc.update("jdbcBoard.updateBoard", jBoardVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		JdbcBoardVO jBoardVo = null;
		try {
			jBoardVo = (JdbcBoardVO) smc.queryForObject("jdbcBoard.getBoard", boardNo);
		} catch (SQLException e) {
			jBoardVo = null;
			e.printStackTrace();
		} 
		
		return jBoardVo;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		List<JdbcBoardVO> boardList = null;
		try {
			boardList = smc.queryForList("jdbcBoard.getAllBoard");
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} 
		
		return boardList;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String boardTitle) {
		List<JdbcBoardVO> boardList = null;
		try {
			boardList = smc.queryForList("jdbcBoard.getSearchBoard", boardTitle);
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} 
		
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("jdbcBoard.setCountIncrement", boardNo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}

}
