package kr.or.ddit.prod.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.prod.vo.LprodVO;
import kr.or.ddit.prod.vo.ProdVO;
import kr.or.ddit.util.BuildedSqlMapClient;

public class ProdDaoImpl implements IProdDao {
	private static ProdDaoImpl dao;
	private SqlMapClient smc;
	
	// 생성자
	private ProdDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static ProdDaoImpl getInstance() {
		if(dao==null) dao = new ProdDaoImpl();
		
		return dao;
	}
	

	@Override
	public List<LprodVO> getAllLprod() {
		List<LprodVO> lprodList = null;
		try {
			lprodList = smc.queryForList("prod.getAllLprod");
		} catch (SQLException e) {
			lprodList = null;
			e.printStackTrace();
		}
		return lprodList;
	}

	@Override
	public List<ProdVO> getProdList(String prodLgu) {
		List<ProdVO> prodList = null;
		try {
			prodList = smc.queryForList("prod.getProdList", prodLgu);
		} catch (SQLException e) {
			prodList = null;
			e.printStackTrace();
		}
		return prodList;
	}

	@Override
	public List<ProdVO> getProd(String prodId) {
		List<ProdVO> prodList = null;
		try {
			prodList = smc.queryForList("prod.getProd", prodId);
		} catch (SQLException e) {
			prodList = null;
			e.printStackTrace();
		}
		return prodList;
	}

}
