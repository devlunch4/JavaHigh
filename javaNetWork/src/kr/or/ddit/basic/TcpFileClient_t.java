package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import java.net.Socket;

// 문제 서버와 클라이언트가 접속이 되면 클라이언트에서
// 'd:/D-other/'폴더에 있는 '극한직업.jpg' 파일을 서버쪽에 전송한다.
public class TcpFileClient_t {
	// 클라이언트로 서버는 불필요 // private ServerSocket server;
	private Socket socket;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;

	private void clientStart() {
		File file = new File("d:/D_Other/극한직업.jpg");
		if (!file.exists()) {
			System.out.println("전송할 파일이 없습니다.");
			return;
		}
		try {
			socket = new Socket("localhost", 7878);

			System.out.println("파일전송 시작...");

			// 파일의 내용을 읽어올 스트림 객체 생성
			bis = new BufferedInputStream(new FileInputStream(file));

			// 소켓에 출력할 스트림 객체 생성
			bos = new BufferedOutputStream(socket.getOutputStream());

			byte[] temp = new byte[1024];
			int length = 0;
			while ((length = bis.read(temp)) > 0) {
				bos.write(temp, 0, length);
			}
			bos.flush();
			System.out.println("파일전송 완료!");
		} catch (Exception e) {
			System.out.println("파일전송 실패!");
		}

	}

	public static void main(String[] args) {
		new TcpFileClient_t().clientStart();
	}
}
