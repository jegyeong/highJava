package rmi.chat.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import rmi.chat.inf.IChat;
import rmi.chat.inf.IChatClient;

// 채팅 서버의 RMI용 객체
public class RmiChatServer extends UnicastRemoteObject implements IChat {
	
	private List<IChatClient> clientList;

	// 생성자
	public RmiChatServer() throws RemoteException {
		clientList = new ArrayList<IChatClient>();
	}

	public static void main(String[] args) {
		// 채팅 서버 객체를 생성해서 Registry에 등록한다.
		try {
			IChat server = new RmiChatServer();
			Registry reg = LocateRegistry.createRegistry(1099);
			
			reg.rebind("chatServer", server);
			System.out.println("서버가 준비되었습니다...");
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	// 접속한 클라이언트 객체를 List에 추가하는 메서드 구현
	@Override
	public void setClient(IChatClient client) throws RemoteException {
		clientList.add(client);
	}

	// List에 등록된 모든 클라이언트에게 메시지를 전달하는 메서드 구현
	@Override
	public void setMessage(String msg) throws RemoteException {
		System.out.println(msg);
		for(IChatClient client : clientList) {
			client.printMessage(msg);  // 각 클라이언트로 전달
		}
		
	}

}
