package kr.or.ddit.student.service;

import java.util.List;

import kr.or.ddit.student.dao.IStudentDao;
import kr.or.ddit.student.dao.StudentDaoImpl;
import kr.or.ddit.student.vo.StudentVO;

public class StudentServiceImple implements IStudentService {
	private static StudentServiceImple service;
	
	private IStudentDao dao;
	
	private StudentServiceImple() {
		dao = StudentDaoImpl.getInstance();
	}
	
	public static StudentServiceImple getInstance() {
		if(service==null) service = new StudentServiceImple();
		return service;
	}

	@Override
	public List<StudentVO> getAllStudentList() {
		return dao.getAllStudentList();
	}

	@Override
	public int getStudentCount(String stdName) {
		return dao.getStudentCount(stdName);
	}

	@Override
	public int insertStudent(StudentVO stdVo) {
		return dao.insertStudent(stdVo);
	}

}
