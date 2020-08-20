package rmi.chat.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;

// 클라이언트용 인터페이스
public interface IChatClient extends Remote {
	// 서버가 보낸 메시지를 화면에 출력한다.
	public void printMessage(String msg) throws RemoteException;
}
