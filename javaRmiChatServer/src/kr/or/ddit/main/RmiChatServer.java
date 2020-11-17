package kr.or.ddit.main;

//import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.inf.ClientChatInf;
import kr.or.ddit.inf.ServerChatInf;

//서버용 인터페이스를 구현한 클래스
public class RmiChatServer extends UnicastRemoteObject implements ServerChatInf {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<ClientChatInf> clientList = new ArrayList<>();

	// 생성자
	public RmiChatServer() throws RemoteException {
	}

	// 접속한 클라이언트 정보를 List에 추가하는 메서드
	@Override
	public void setClient(ClientChatInf client) throws RemoteException {
		clientList.add(client);
		System.out.println("등록완료");
		System.out.println("현재 접속자수 : " + clientList.size() + " 명");
	}

	// 해제한 클라이언트 정보를 List에 삭제하는 메서드
	@Override
	public void disConnect(ClientChatInf client) throws RemoteException {
		clientList.remove(client);
		System.out.println("접속해제 완료...");
		System.out.println("현재 접속자수 : " + clientList.size() + " 명");
	}

	// List에 등록된 모든 클라이언트에게 메시지 보내기
	@Override
	public void sendToAll(String msg) throws RemoteException {
		for (ClientChatInf clientChatInf : clientList) {
			clientChatInf.printMessage(msg);
		}

	}

	public static void main(String[] args) {
		try {
			ServerChatInf server = new RmiChatServer();

			// 기본 포트번호 1099
			Registry reg = LocateRegistry.createRegistry(1099);

			reg.rebind("rmiChat", server);
			System.out.println("채팅 서버 준비완료...");

		} catch (RemoteException e) {
			// TODO: handle exception
		}
	}

}
