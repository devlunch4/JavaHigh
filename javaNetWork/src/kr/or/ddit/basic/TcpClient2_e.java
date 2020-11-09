package kr.or.ddit.basic;

import java.net.Socket;

public class TcpClient2_e {

	public static void main(String[] args) {
		// 서버에 접속하는 소켓을 생성하고, 서버와 접속이 성공하면
		// 메시지를 보내는 클래스와 메시지를 받는 클래스를 생성할떄 이소켓을 넣어준다.

		try {
			Socket socket = new Socket("localhost", 7788);

			System.out.println("클라이언트가 서버에 연결되었습니다.");

			Sender_e sendere = new Sender_e(socket);
			Receiver_e receivere = new Receiver_e(socket);

			sendere.start();
			receivere.start();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
