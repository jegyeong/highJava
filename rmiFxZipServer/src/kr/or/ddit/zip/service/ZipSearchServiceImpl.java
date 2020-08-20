package kr.or.ddit.zip.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.zip.dao.IZipSearchDao;
import kr.or.ddit.zip.dao.ZipSearchDaoImpl;
import kr.or.ddit.zip.vo.ZipVO;

public class ZipSearchServiceImpl extends UnicastRemoteObject implements IZipSearchService {
	private static ZipSearchServiceImpl service;
	private IZipSearchDao dao;
	
	private ZipSearchServiceImpl() throws RemoteException {
		dao = ZipSearchDaoImpl.getInstance();
	}
	
	public static ZipSearchServiceImpl getInstance() {
		try {
			if(service==null) service = new ZipSearchServiceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return service;
	}
	
	
	@Override
	public List<ZipVO> getZipSearchDong(String dong) throws RemoteException {
		return dao.getZipSearchDong(dong);
	}

	@Override
	public List<ZipVO> getZipSearchCode(String code) throws RemoteException {
		return dao.getZipSearchCode(code);
	}

}
