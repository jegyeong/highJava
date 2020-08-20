package kr.or.ddit.student.service;

import java.util.List;

import kr.or.ddit.student.vo.StudentVO;

public interface IStudentService {

	/**
	 * student테이블의 전체 데이터를 가져오는 메서드
	 * @return
	 */
	public List<StudentVO> getAllStudentList();

	/**
	 * 학생이름을 매개변수로 받아서 해당 이름과 같은 학생수를 반환하는 메서드
	 * @param stdName
	 * @return
	 */
	public int getStudentCount(String stdName);
	
	/**
	 * StudentVO객체를 매개변수로 받아서 해당 자료를 DB에 insert하는 메서드
	 * @param stdVo
	 * @return
	 */
	public int insertStudent(StudentVO stdVo);
	
}
