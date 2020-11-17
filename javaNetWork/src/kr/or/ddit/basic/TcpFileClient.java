package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
//import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpFileClient {
	public static void main(String[] args) {

		try {
			Socket socket = new Socket("localhost", 7778);

			System.out.println("클라이언트가 서버에 접속 되었습니다.");
			System.out.println();
			// 데이터 통신을 위한 소켓 스트림 얻기//////////////////
			// 들어오는 데이터 받기
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);

			// 보내는 데이터 보내기
			OutputStream out = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(out);
			/////////////////////////////////////////////

			System.out.println("송수신 스트림 생성 완료!!");
			// dos.writeUTF("d:/D_other/극한직업.jpg");

			System.out.println("서버에 파일요청 및 받아옵니다.");
			System.out.println("기다려주세요 아마 서버에서 파일을 받는중입니다.~~~");
			System.out.println();

			// 저장할 파일 경로 설정 및 수신.
			FileOutputStream fos = new FileOutputStream("d:/D_other/연습용/극한직업_복사본.jpg");

			while (true) {
				int data = dis.read();
				if (data == -1) {
					break;
				}
				fos.write(data);
			}

			fos.close();
			dos.close();
			dis.close();
			out.close();
			in.close();
			socket.close();
			System.out.println("파일 수신 완료!!!");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
