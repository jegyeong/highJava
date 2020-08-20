package kr.or.ddit.student.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.student.vo.StudentVO;
import kr.or.ddit.util.BuildedSqlMapClient;

public class StudentDaoImpl implements IStudentDao {
	private static StudentDaoImpl dao;
	private SqlMapClient smc;
	
	private StudentDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static StudentDaoImpl getInstance() {
		if(dao==null) dao = new StudentDaoImpl();
		return dao;
	}

	@Override
	public List<StudentVO> getAllStudentList() {
		List<StudentVO> stdList = null;
		try {
			stdList = smc.queryForList("student.getAllStudentList");
		} catch (SQLException e) {
			stdList = null;
			e.printStackTrace();
		}
		return stdList;
	}

	@Override
	public int getStudentCount(String stdName) {
		int count = 0;
		try {
			count = (int)smc.queryForObject("student.getStudentCount", stdName);
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int insertStudent(StudentVO stdVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("student.insertStudent", stdVo);
			if(obj==null) cnt = 1;
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

}
