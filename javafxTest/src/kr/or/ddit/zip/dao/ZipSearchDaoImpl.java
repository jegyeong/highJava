package kr.or.ddit.zip.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.util.BuildedSqlMapClient;
import kr.or.ddit.zip.vo.ZipVO;

public class ZipSearchDaoImpl implements IZipSearchDao {
	private static ZipSearchDaoImpl dao;
	private SqlMapClient smc;
	
	private ZipSearchDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static ZipSearchDaoImpl getInstance() {
		if(dao==null) dao = new ZipSearchDaoImpl();
		return dao;
	}

	@Override
	public List<ZipVO> getZipSearchDong(String dong) {
		List<ZipVO> zipList = null;
		try {
			zipList = smc.queryForList("zip.getSearchDong", dong);
		} catch (SQLException e) {
			zipList = null;
			e.printStackTrace();
		}
		return zipList;
	}

	@Override
	public List<ZipVO> getZipSearchCode(String code) {
		List<ZipVO> zipList = null;
		try {
			zipList = smc.queryForList("zip.getSearchCode", code);
		} catch (SQLException e) {
			zipList = null;
			e.printStackTrace();
		}
		return zipList;
	}

}
