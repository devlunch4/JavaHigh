package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TcpMultiChatServer {
	// 접속한 클라이언트 정보를 저장할 Map객체 변수 선언
	// ==> key값 : 접속한 사람의 이름
	// ==> value값 : 클라이언트와 접속된 소켓
	private Map<String, Socket> clientMap;

	// 생성자
	public TcpMultiChatServer() {

		// clientMap을 동기화 처리가 되도록 생성한다.
		clientMap = Collections.synchronizedMap(new HashMap<String, Socket>());
		// 스레드 처리시 동기화 필요

	}// 생성자 끝

	public static void main(String[] args) {

		new TcpMultiChatServer().serverStart();

	}

	// 서버 시작 메서드
	private void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다. 클라이언트 연결 대기중...");

			// 계속 접속할수 있으므로 무한 반복문으로 처리한다.
			while (true) {
				// 클라이언트 접속 대기
				socket = server.accept();
				System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "]에서 접속했습니다.");

				// 서버용 쓰레드 시작
				ServerThread serverThread = new ServerThread(socket);
				serverThread.start();

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (Exception e2) {
					// 서버종료 예외시
				}
			}
		}
	}// 서버 시작 끝 메서드

	//
	//

	// clientMap에 저장된 전체 사용자에게 메시지를 전송하는 메서드
	// string msg의 내용을 받아오면 이를 클라이언트 수에 따라 출력한다.
	private void sendToAll(String msg) {
		// clientMap 데이터 개수만큼 반복
		for (String name : clientMap.keySet()) {
			try {
				// 접속되어있는 아웃풋스트림이 들어와햐한다.
				DataOutputStream dos = new DataOutputStream(
						// 클라이언트와 연결된 소켓의 outputstream
						clientMap.get(name).getOutputStream());

				dos.writeUTF(msg);

			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}// sendToAll 메서드 끝

	// 클라이언트와 연결된 소켓을 이용하여
	// 하나의 클라이언트가 보내용 메시지를 다른 클라이언트 들에게
	// 전송하는 Thread를 inner Class 형태로 만든다.
	class ServerThread extends Thread {
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;

		// 생성자
		// 접속자가 생길떄마다 소켓이 생성
		public ServerThread(Socket socket) {

			this.socket = socket;

			try {
				// 수신용
				dis = new DataInputStream(socket.getInputStream());

				// 송신용
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		// 중요 메소드!!!!!!!!
		@Override
		public void run() {
			// 클라이언트가 보낸 이름을 저장한 변수 선언
			String name = "";
			try {
				//// 클라이언트가 보낸 첫번쨰 데이터는 사용자의 이름인데
				// 이 이름이 중복되는지 여부를 feedback으로 클라이언트에게 보내주고
				// 이름이 중복되지 않으면 반복문을 탈출한다.
				// 즉 ==> 이름검사 무한 반복문
				while (true) {
					// 첫번째 데이터 읽기 (이름 데이터)
					name = dis.readUTF();
					if (clientMap.containsKey(name)) {
						// 이름이 중복 될때
						dos.writeUTF("이름중복");
					} else {
						// 중복이지 않을 때 ok 전송
						dos.writeUTF("OK");
						break;
					}
				} // while 문 끝 종료

				// 대화명을 받아서 전체 클라이언트에게 대화방 참여 메시지를 보낸다.
				sendToAll("[" + name + "]님이 대화방에 입장했습니다...");

				// 대화명과 클라이언트의 Socket객체를 Map에 저장한다
				clientMap.put(name, socket);
				System.out.println("현재 서버 접속자수 : " + clientMap.size() + " 명");

				/////////////////////////////////////// 위까지 서버 내 클라이언트의 접속 및 등록 완료

				//////////////////
				// 하나의 클라이언트가 보낸 메시지를 받아서 전체 클라이언트들에게 보낸다.
				//////////////////
				while (dis != null) {
					sendToAll(dis.readUTF());
					// 중간에 종료한다면 인셉션 발생. 그렇다면 알아서 finally로 제어가 될것이다.
				}

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				// 이 finally 영역이 실행된다는 것은 클라이언트가 접속을 종료했다는 의미.
				// 클라이언트 모두에게 전송
				sendToAll("[" + socket.getInetAddress() + ":" + socket.getPort() + "]에서 접속 종료 했습니다.");
				sendToAll("[" + name + "]님이 접속 종료 했습니다...");
				//
				//

				// 접속을 종료한 클라이언트를 Map에서 삭제한다.
				// 서버 콘솔창 출력
				System.out.println("[" + name + "]님이 접속 종료 했습니다...");
				clientMap.remove(name);
				// 서버에서 확인
				System.out.println("현재 서버 접속자수 : " + clientMap.size() + " 명");
			}

		}

	}

}
