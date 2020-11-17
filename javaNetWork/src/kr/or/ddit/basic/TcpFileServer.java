package kr.or.ddit.basic;

//import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//문제 서버와 클라이언트가 접속이 되면 클라이언트에서 'd:/D-other/'폴더에 있는 '극한직업.jpg' 파일을
//서버쪽에 전송한다.
//서버는 클라이언트가 보내온 이미지 데이터를 'd:/D-other/연습용/'폴더에 '극한직업_전송파일.jpg'로 저장한다.
public class TcpFileServer {

	public static void main(String[] args) {

		try {
			ServerSocket server = new ServerSocket(7778);
			System.out.println("서버 생성 완료... 클라이언트 접속 대기중");

			Socket socket = server.accept();
			System.out.println("클라이언트 접속 완료.");
			System.out.println();

			// 데이터 통신을 위한 소켓 스트림 얻기//////////////////
			// 들어오는 데이터 받기
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);

			// 보내는 데이터 보내기
			OutputStream out = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);

			System.out.println("송수신 스트림 생성 완료!!!");
			/////////////////////////////////////////////

			// 전송
			System.out.println("파일정보를 수신받아 전송합니다...는 패스!");
			System.out.println("원본파일 경로에서 송신 시작합니다.");
			System.out.println("기다려주세요!");
			System.out.println();
			// 저장경로
			FileInputStream fin = new FileInputStream("d:/D_other/극한직업.jpg");

			while (true) {
				int data = fin.read();
				if (data == -1) {
					break;
				}
				dos.write(data);
			}

			fin.close();
			dos.close();
			dis.close();
			socket.close();
			server.close();
			System.out.println("파일 송신 완료!!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
