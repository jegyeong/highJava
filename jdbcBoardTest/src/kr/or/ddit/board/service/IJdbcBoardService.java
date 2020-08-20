package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.JdbcBoardVO;

public interface IJdbcBoardService {
	/**
	 * 파라미터로 넘어온 JdbcBoardVO에 담겨진 데이터를 DB에 insert하는 메서드
	 * @param jBoardVo DB에 insert할 데이터가 저장된 JdbcBoardVO객체
	 * @return 작업성공 : 1, 작업 실패 : 0
	 */
	public int insertBoard(JdbcBoardVO jBoardVo);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글을 삭제하는 메서드
	 * @param boardNo 삭제할 게시글 번호
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * 수정할 자료가 저장된 JdbcBoarVO객체를 매개변수로 받아서 해당 자료를 update하는 메서드
	 * @param jBoardVo update할 값이 저장된 JdbcBoardVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateBoard(JdbcBoardVO jBoardVo);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글 정보 내용을 DB에서 가져와 반환하는 메서드
	 * @param boardNo 가져올 게시글 번호
	 * @return 해당 게시글번호에 맞는 자료가 저장된 JdbcBoardVO객체 (해당 게시글이 없으면 null반환)
	 */
	public JdbcBoardVO getBoard(int boardNo);
	
	/**
	 * DB의 게시판 테이블의 전체 레코드를 가져와 List에 담아서 반환하는 메서드
	 * @return JdbcBoardVO객체를 가지고 있는 List객체
	 */
	public List<JdbcBoardVO> getAllBoardList();
	
	/**
	 * 매개변수로 게시글의 제목을 받아서 해당 게시글들을 가져와 List에 담아서 반환하는 메서드
	 * @param boardTitle 검색할 게시글 제목
	 * @return 검색된 결과를 가지고 있는 List객체
	 */
	public List<JdbcBoardVO> getSearchBoardList(String boardTitle);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글의 조회수를 1 증가 시키는 메서드
	 * @param boardNo 게시글 번호
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int setCountIncrement(int boardNo);
}
