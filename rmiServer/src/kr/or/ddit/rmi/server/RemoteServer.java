package kr.or.ddit.rmi.server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rmi.inf.RemoteInterface;
import kr.or.ddit.rmi.vo.FileInfoVO;
import kr.or.ddit.rmi.vo.TestVO;

// RMI용 클래스 만들기  ==> UnicastRemoteObject를 상속하고 RMI용 인터페이스를 구현하도록 작성한다.

public class RemoteServer extends UnicastRemoteObject implements RemoteInterface{

	// 생성자 ==> 생성자도 RemoteException을 throws해서 작성한다. 
	public RemoteServer() throws RemoteException {	}

	// RMI용 객체의 메서드에서
	// 매개변수는 클라이언트에서 서버쪽으로 전달되는 데이터가 저장되고,
	// 메서드의 반환값은 서버에서 처리한 결과를 클라이언트로 보내는 데이터가 된다.
	@Override
	public int doRemotePrint(String str) throws RemoteException {
		System.out.println("doRemotePrint() 메서드 시작");
		
		System.out.println("클라이언트에서 보낸 내용 : " + str);
		
		System.out.println("doRemotePrint() 메서드 끝...");
		System.out.println();
		
		return 200;  // 반환값
	}

	@Override
	public void doPrintList(List<String> list) throws RemoteException {
		System.out.println("doPrintList() 메서드 시작");
		
		for(String str : list) {
			System.out.println(str);
		}
		
		System.out.println("doPrintList() 메서드 끝...");
		System.out.println();
	}

	@Override
	public void doPrintVo(TestVO vo) throws RemoteException {
		System.out.println("doPrintVo() 메서드 시작");
		System.out.println("이름 : " + vo.getName());
		System.out.println("번호 : " + vo.getNum());
		System.out.println("doPrintVo() 메서드 끝...");
		System.out.println();
	}
	

	// 클라이언트가 보내온 FileInfoVO객체를 이용해서 파일을 저장한다.
	@Override
	public void setFile(FileInfoVO fileVo) throws RemoteException {
		FileOutputStream fout = null;
		String dir = "d:/d_Other/rmiData/";
		System.out.println("파일 저장 시작...");
		try {
			fout = new FileOutputStream(dir + fileVo.getFileName());
			
			fout.write(fileVo.getFileData());  // 파일 내용 저장
			fout.flush();
			fout.close();
			System.out.println("파일 저장 완료...");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public static void main(String[] args) {
		// RMI용 객체를 클라이언트에서 사용할 수 있도록 RMI환경을 설정한다.
		try {
			// 1. RMI용 인터페이스를 구현한 RMI용 클래스의 인스턴스를 생성한다.
			RemoteInterface inf = new RemoteServer();
			
			// 2. 구현한 객체를 클라이언트가 찾을 수 있도록 관리하는 Registry객체 생성
			Registry reg = LocateRegistry.createRegistry(8888); // 기본 포트 (1099)
			
			// 3. RMI용 객체를 서버에 등록한다.
			// 형식) Registry객체변수.rebind("객체의Alias", RMI객체의인스턴스);
			reg.rebind("server", inf);
			
			System.out.println("서버가 준비되었습니다.");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		

	}



}
