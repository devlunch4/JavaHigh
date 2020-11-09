package kr.or.ddit.basic;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

//문제 서버와 클라이언트가 접속이 되면 클라이언트에서 'd:/D-other/'폴더에 있는 '극한직업.jpg' 파일을 서버쪽에 전송한다.
//서버는 클라이언트가 보내온 이미지 데이터를 'd:/D-other/연습용/'폴더에 '극한직업_전송파일.jpg'로 저장한다.

public class TcpFileServer_t {
	// 서버는 클라이언트가 보내온 이미지 데이터를 'd:/D-other/연습용/'폴더에 '극한직업_전송파일.jpg'로 저장한다.
	private ServerSocket server;
	private Socket socket;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;

	private void serverStart() {
		// 저장할 폴더 경로 설정
		File saveDir = new File("d:/D_Other/연습용");

		if (!saveDir.exists()) {
			saveDir.mkdir();
		}

		try {
			server = new ServerSocket(7878);
			System.out.println("서버 준비 완료");

			// 클라이언트 요청 대기
			socket = server.accept();

			// 클라이언트 연결 되자마자
			System.out.println("파일 다운로드 시작..");

			File saveFile = new File(saveDir, "극한직업_전송파일_t.jpg");

			// 소켓에서 데이터를 입력받을 스트림 객체 생성
			bis = new BufferedInputStream(socket.getInputStream());

			// 파일에 저장할 스트림 객체 생성
			bos = new BufferedOutputStream(new FileOutputStream(saveFile));

			byte[] temp = new byte[1024];
			int length = 0;

			// 소켓으로 읽어온 데이터를 파일로 출력한다.
			while ((length = bis.read(temp)) > 0) {
				// 바이트가 부족했을씨 모자라는 것도 전송하기위해 짤림 방지.
				bos.write(temp, 0, length);
			}
			bos.flush();
			System.out.println("파일수신 완료!");
		} catch (Exception e) {
			System.out.println("파일수신 실패!");
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
				}
			}
			//
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
				}
			}
			//
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
			//
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
				}
			}

		} // 파이널리 마지막 바로앞 괄호
	}

	public static void main(String[] args) {
		new TcpFileServer_t().serverStart();
	}
}
