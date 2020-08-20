package rmi.chat.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;

// 서버용 interface
public interface IChat extends Remote{
	// 접속한 클라이언트 객체를 List에 추가하는 메서드
	public void setClient(IChatClient client) throws RemoteException;
	
	// 한 클라이언트가 보낸 메시지를 서버에 접속된 모든 클라이언트에게 메시지를 전달하는 메서드
	public void setMessage(String msg) throws RemoteException;
}
