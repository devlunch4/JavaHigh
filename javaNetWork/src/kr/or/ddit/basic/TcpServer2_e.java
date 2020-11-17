package kr.or.ddit.basic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class TcpServer2_e {

	public static void main(String[] args) {

		try {
			// 소켓 서버를 만들고, 클라이언트가 접속해오면 소켓을 만들어서
			// 메시지를 받는 클래스와 메시지를 보내는 클래스를 새성할떄 이 소켓을 넘겨준다.

			ServerSocket server = new ServerSocket(7788);
			System.out.println("서버가 준비중입니다..(접속 대기중)");
			
			Socket socket = server.accept();
			
			Sender_e sendere = new Sender_e(socket);
	
			Receiver_e receivere = new Receiver_e(socket);
			
			sendere.start();
			receivere.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	}

}
