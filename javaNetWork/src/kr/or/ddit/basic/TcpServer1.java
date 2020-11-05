package kr.or.ddit.basic;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer1 {

	public static void main(String[] args) throws IOException {
		// 서로 데이터를 어떻게 받을지 고려/생각/타입 해야한다.
		// TCP 소켓 통신을 위해 ServerSockt객체를 생성한다
		ServerSocket server = new ServerSocket(7777);

		System.out.println("접속을 기다립니다...");

		// accept메소드 ==> 클라이언트에서 연결 요청이 올떄까지 계속 기다린다.
		// =============> 연결 요청이 오면 내찯ㅅ 객체를 생성해서 클라이언트의 socket과 연결한다.
		Socket socket = server.accept();

		// accept()메서드 이후의 소스는 연결이 완료된 후에만 실행된다.
		// 이후엔 연결이 된것을 가정한 상황이라 가정하고 코딩을 시작한다.

		System.out.println("클라이언트와 연결이 완료되었습니다.");
		System.out.println();

		System.out.println("접속한 클라이언트 정보");
		System.out.println("IP주소 : " + socket.getInetAddress().getHostAddress());
		System.out.println("Port번호 : " + socket.getPort());
		System.out.println();

		System.out.println("연결된 서버 정보(현재는 내컴퓨터가 서버)");
		System.out.println("IP 주소 : " + socket.getLocalAddress());
		System.out.println("Port 주소 : " + socket.getLocalPort());
		System.out.println();

		// 클라이언트에게 메시지 보내기 ==> 소켓의 outputstream객체를 이용해서 전송한다.
		// 소켓의 outputstream객체를 구한다.
		OutputStream out = socket.getOutputStream();
		DataOutputStream dout = new DataOutputStream(out);

		// 메시지 전송 ==> 출력 명령으로 전송한다.
		dout.writeUTF("환영합니다, 어서오세요~~~");
		System.out.println("메시지를 보냈습니다.");

		// 소켓과 연결 스트림 닫기
		dout.close();
		socket.close();
		server.close();
	}

}
