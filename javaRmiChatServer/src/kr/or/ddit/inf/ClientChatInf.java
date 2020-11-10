package kr.or.ddit.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientChatInf extends Remote {

	// 서버가 보내온 메시지를 화면에 출력하는 메서드
	public void printMessage(String msg) throws RemoteException;

}
