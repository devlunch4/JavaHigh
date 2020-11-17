package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import org.omg.CORBA.portable.UnknownException;

public class TcpClient1 {
	public static void main(String[] args) throws UnknownException, IOException {
		// 현재 자신 컴퓨터를 나타내는 방법
		// 1) 원래의 IP주소 : 예) 192.168.42.2
		// 2) 지정된 IP 주소 : 127.0.0.1
		// 3) 원래의 컴퓨터 이름 : 예) SEM
		// 4) 지정된 컴퓨터 이름 : localhost

		String serverIp = "localhost";

		System.out.println(serverIp + "연결중입니다...");

		Socket socket = new Socket(serverIp, 7777);

		// 이부분은 서버와 연결이 오나료된 이후의 처리 내용을 기술하면 된다.

		System.out.println("서버에 연결되었습니다.");
		System.out.println();

		// 서버에서 보내온 메시지를 받아서 화면 출력하기

		// socket의 inputstream객체 생성 ==> getInputStream()메서드 이용
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);

		// 서버가 보낸 자료를 읽어와서 출력한다.
		System.out.println("서버가 보낸 메시지 : " + dis.readUTF());
		System.out.println();

		System.out.println("연결의 종료합니다...");

		// 스트림과 소켓닫기
		dis.close();
		socket.close();
	}
}
