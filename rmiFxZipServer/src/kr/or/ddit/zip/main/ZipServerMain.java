package kr.or.ddit.zip.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import kr.or.ddit.zip.service.IZipSearchService;
import kr.or.ddit.zip.service.ZipSearchServiceImpl;

public class ZipServerMain {

	public static void main(String[] args) {
		try {
			IZipSearchService service = ZipSearchServiceImpl.getInstance();
			
			Registry reg = LocateRegistry.createRegistry(9999);
			reg.rebind("zipService", service);
			
			System.out.println("서버가 준비되었습니다.");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		

	}

}
