package kr.or.ddit.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.inf.RmiTestInterface;
import kr.or.ddit.vo.FileInfoVO;
import kr.or.ddit.vo.TestVO;

public class RemoteClient {

	public static void main(String[] args) {
		// RMI 용 객체를 서버에서 구해서 사용하는 순서
		Scanner scan = new Scanner(System.in);
		try {

			// 1. 서버에서 등록한 RMI용 객체를 찾기위해 Registry 객체를 생성한다.
			////// (서버의 IP주소와 포트번호르 지정하여 생성한다

			//
			//

			//Registry reg = LocateRegistry.getRegistry("localhost", 9988);
			Registry reg = LocateRegistry.getRegistry("192.168.42.34", 9988);

			//
			//

			// 2. 서버에서 등록한 '객체의 Alians 명'을 이용하여 객체를 불러온다.
			// 형식 >>> Registry객체변수.lookup('객체의Alians명')
			// reg.lookup("server"); 작성후 try catch 두번쨰 생성 (NotBoundException e) 활성
			RmiTestInterface inf = (RmiTestInterface) reg.lookup("server");

			// 3. 이제부터 불러온 메소드 객체를 호출해서 사용할수 있다.

			while (true) {
				System.out.println("서버로 전송할 메시지 입력>>>");
				String msg = scan.nextLine();

				if (msg.equals(".exit")) {
					System.out.println("접속이 종료 됩니다.");
					break;
				}
				int a = inf.doRemotePrint(msg);
				System.out.println("서버의 반환값 : " + a);
				System.out.println();

				List<String> list = new ArrayList<>();
				list.add("사과");
				list.add("딸기");
				list.add("바나나");
				list.add("복숭아");
				list.add("파인애플");
				inf.doPrintList(list);
				System.out.println("서버 list 전송 끝");

				TestVO testVo = new TestVO();

				testVo.setId("A001");
				testVo.setNum(123456);
				inf.doPrintVo(testVo);

				System.out.println("Vo객체 전송 끝...");
				System.out.println();
				
				// 파일 전송하기
				File file = new File("D:/temp.txt");

				if (!file.exists()) {
					System.out.println(file.getName() + "파일이 없습니다.");
					return;
				}

				FileInfoVO fileVo = new FileInfoVO();

				// 파일의 용량을 구해서 FileInfoVO의 fileData 배열의 길이를 결정한다.

				int len = (int) file.length();
				byte[] data = new byte[len];

				// 파일 입력용 스트림 객체 생성

				FileInputStream fin = new FileInputStream(file);

				// 파일 내용을 읽어와 byte형 배열에 저장한다.
				fin.read(data);

				// 구해온 정보를 FileInfoVO 객체에 세팅한다.
				fileVo.setFileName(file.getName()); // 파일명 저장
				fileVo.setFileData(data); // 파일 내용 저장.

				// 서버의 파일 전송요 메서드를 호출한다.
				inf.transFile(fileVo);
				System.out.println("파일전송 작업 끝...");
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
