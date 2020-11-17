package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.net.Socket;

//이클래스는 소켓에서 메시지를 받아서 화면에 출력하는 연할
public class Receiver_e extends Thread {

	@SuppressWarnings("unused")
	private Socket socket;
	private DataInputStream dis;

	// 생성자
	public Receiver_e(Socket socket) {
		this.socket = socket;

		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void run() {

		while (dis != null) {
			try {
				System.out.println(dis.readUTF());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		super.run();
	}

}
