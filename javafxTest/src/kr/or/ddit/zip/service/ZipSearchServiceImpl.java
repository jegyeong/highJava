package kr.or.ddit.zip.service;

import java.util.List;

import kr.or.ddit.zip.dao.IZipSearchDao;
import kr.or.ddit.zip.dao.ZipSearchDaoImpl;
import kr.or.ddit.zip.vo.ZipVO;

public class ZipSearchServiceImpl implements IZipSearchService {
	private static ZipSearchServiceImpl service;
	private IZipSearchDao dao;
	
	private ZipSearchServiceImpl() {
		dao = ZipSearchDaoImpl.getInstance();
	}
	
	public static ZipSearchServiceImpl getInstance() {
		if(service==null) service = new ZipSearchServiceImpl();
		return service;
	}
	
	
	@Override
	public List<ZipVO> getZipSearchDong(String dong) {
		return dao.getZipSearchDong(dong);
	}

	@Override
	public List<ZipVO> getZipSearchCode(String code) {
		return dao.getZipSearchCode(code);
	}

}
