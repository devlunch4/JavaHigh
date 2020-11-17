package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultiChatClient {

	public static void main(String[] args) {
		new TcpMultiChatClient().clientStart();
	}

	// 클라이언트 시작 메소드

	private void clientStart() {
		Socket socket = null;
		try {

			String serverIp = "localhost";
			// 서버 접속 시작
			socket = new Socket(serverIp, 7777);
			// 서버 접속 중
			// 서버 접속 완료

			System.out.println("서버와 연결되었습니다!!!");
			System.out.println();

			// 메시지 전송용 쓰레드 생성 및 실행
			ClientSender sender = new ClientSender(socket);

			// 메시지 수신용 쓰레드 생성 및 실행
			ClientReceiver receiver = new ClientReceiver(socket);

			// 송수신 스타트!!
			sender.start();
			receiver.start();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}// 클라이언트 시작 메소드 끝 종료

	///////////////////////////////////////////////////////////

	// 클라이언트 메시지 전송용 메서드
	class ClientSender extends Thread {
		@SuppressWarnings("unused")
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;
		// 접속자 이름
		private String name;
		// 입력 스캐너
		private Scanner scan;

		//////
		// 생성자
		public ClientSender(Socket socket) {
			this.socket = socket;
			scan = new Scanner(System.in);
			try {
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());

				if (dos != null) {
					// 처음 프로그램이 시작되면 자신의 대화명(이름)을 서버로 전송하고
					// 대화명의 종복여부르 feedback으로 받아서 확인한다.

					System.out.println("대화명 입력>>>");
					name = scan.nextLine();

					while (true) {
						// 대화명 전송
						dos.writeUTF(name);

						// 대화명 중복여부를 서버로부터 받아 feedback으로 지정 응답 받음
						String feedback = dis.readUTF();

						if ("이름중복".equals(feedback)) {
							System.out.println(name + " 이란 대화명은 중복됩니다.");
							System.out.println("다른 대화명을 입력하세요");
							System.out.println("대화명 입력 >>>>>>");
							name = scan.nextLine();
						} else {
							// this.name = name;
							System.out.println(name + " 이름으로 대화방에 입장하였습니다.");
							break;
						}
					} // while 종료

				} // if dos 문 종료
			} catch (Exception e) {
				// TODO: handle exception
			}
		}// 생성자 메소드의 끝 종료

		@Override
		public void run() {

			try {
				while (dos != null) {
					// 키보드 입력 메시지를 서버로 전송
					dos.writeUTF(" [" + name + "] : " + scan.nextLine());
				} // while 종료
			} catch (Exception e) {
				// TODO: handle exception
			}

		}// run 메소드 끝

	}

	class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream dis;

		// 생성자
		public ClientReceiver(Socket socket) {

			this.socket = socket;

			try {
				dis = new DataInputStream(socket.getInputStream());

			} catch (Exception e) {
				// TODO: handle exception
			}
		}// 생성자 메소드 끝 종료

		@Override
		public void run() {
			try {
				while (dis != null) {
					// 서버로 받은 메시지를 화면에 출력
					System.out.println(dis.readUTF());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
