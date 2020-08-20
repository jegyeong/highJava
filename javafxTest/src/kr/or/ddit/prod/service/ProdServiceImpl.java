package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.prod.vo.LprodVO;
import kr.or.ddit.prod.vo.ProdVO;

public class ProdServiceImpl implements IProdService {
	private static ProdServiceImpl service;
	private IProdDao dao;
	
	private ProdServiceImpl() {
		dao = ProdDaoImpl.getInstance();
	}
	
	public static ProdServiceImpl getInstance() {
		if(service==null) service = new ProdServiceImpl();
		
		return service;
	}
	

	@Override
	public List<LprodVO> getAllLprod() {
		return dao.getAllLprod();
	}

	@Override
	public List<ProdVO> getProdList(String prodLgu) {
		return dao.getProdList(prodLgu);
	}

	@Override
	public List<ProdVO> getProd(String prodId) {
		return dao.getProd(prodId);
	}

}
