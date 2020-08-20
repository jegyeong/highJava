package kr.or.ddit.rmi.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.rmi.inf.RemoteInterface;
import kr.or.ddit.rmi.vo.FileInfoVO;
import kr.or.ddit.rmi.vo.TestVO;

//  클라이언트쪽의 VO나 인터페이스는  서버의 VO나 인터페이스가 있는 패키지의 구조와  파일명 그리고 내용까지
//  같게 구성되어 있어야 한다.

public class RemoteClient {

	public static void main(String[] args) {
		try {
			// 등록된 서버를 찾기 위해 Registry객체를 생성한다. (서버 접속)
			Registry reg = LocateRegistry.getRegistry("localhost", 8888);
			
			// 사용할 객체는 서버에 등록된 Alias로 찾아서 객체를 불러온다.
			// 형식) Registry객체변수.lookup("객체의Alias");
			RemoteInterface inf = (RemoteInterface) reg.lookup("server");
			
			// 이 이후부터는 불러온 객체의 메서드를 호출해서 사용할 수 있다.
			int a = inf.doRemotePrint("안녕하세요 클라이언트입니다.");
			System.out.println("반환값 : " + a);
			System.out.println("----------------------------------");
			
			System.out.println("doPrintList()메서드 호출");
			List<String> nameList = new ArrayList<String>();
			nameList.add("홍길동");
			nameList.add("이순신");
			nameList.add("변학도");
			nameList.add("성춘향");
			nameList.add("강감찬");
			inf.doPrintList(nameList);
			System.out.println("----------------------------------");
			
			System.out.println("doPrintVo()메서드 호출");
			TestVO test = new TestVO();
			test.setName("일지매");
			test.setNum(2020);
			inf.doPrintVo(test);
			System.out.println("----------------------------------");
			
			
			// 파일 전송하기
			System.out.println("파일 전송 시작...");
			// 전송할 파일의 File객체 생성
			File file = new File("d:/d_Other/Koala.jpg");
			if(!file.exists()) {
				System.out.println("전송할 파일이 없습니다. 다시 확인해 보세요.");
				return;
			}
			
			FileInfoVO fVo = new FileInfoVO();
			fVo.setFileName(file.getName()); // 파일 이름 설정
			
			// 파일의 크기 구하기
			
			long fSize = file.length();
			
			// 파일 내용을 읽어와 저장할 byte형 배열 선언 ==> 배열의 크기는 파일크기와 같게 한다.
			byte[] data = new byte[(int) fSize];
			try {
				FileInputStream fin = new FileInputStream(file);
				fin.read(data);   // 파일내용을 읽어와 byte형 배열에 저장한다.
				
				// 읽어온 데이터를 FileInfoVO객체에 셋팅한다.
				fVo.setFileData(data);
				
				// RMI용 파일 전송용 메서드 호출
				inf.setFile(fVo);
				System.out.println("파일 전송 끝...");
			}catch(IOException ee) {
				ee.printStackTrace();
			}
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}

}
