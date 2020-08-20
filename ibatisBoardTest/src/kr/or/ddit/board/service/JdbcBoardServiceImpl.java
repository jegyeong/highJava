package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IJdbcBoardDao;
import kr.or.ddit.board.dao.JdbcBoardDaoImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;

public class JdbcBoardServiceImpl implements IJdbcBoardService {
	private IJdbcBoardDao dao;
	
	private static JdbcBoardServiceImpl service;
	
	private JdbcBoardServiceImpl() {
		dao = JdbcBoardDaoImpl.getInstance();
	}
	
	public static JdbcBoardServiceImpl getInstance(){
		if(service==null) service = new JdbcBoardServiceImpl();
		return service;
	}
	

	@Override
	public int insertBoard(JdbcBoardVO jBoardVo) {
		return dao.insertBoard(jBoardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public int updateBoard(JdbcBoardVO jBoardVo) {
		return dao.updateBoard(jBoardVo);
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		return dao.getBoard(boardNo);
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		return dao.getAllBoardList();
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String boardTitle) {
		return dao.getSearchBoardList(boardTitle);
	}

	@Override
	public int setCountIncrement(int boardNo) {
		return dao.setCountIncrement(boardNo);
	}

}
