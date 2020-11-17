package kr.or.ddit.server;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.inf.RmiTestInterface;
import kr.or.ddit.vo.FileInfoVO;
import kr.or.ddit.vo.TestVO;

// RMI 기술을 제공하는 클래스 만들기
// ==> RMI용 인터페이스를 구현하고 , UnicastRemoteObject를 상속해서 작성한다.
public class RemoteServer extends UnicastRemoteObject implements RmiTestInterface {

	// 생성자도 RemoteExpcetion을 throws 해서 작성한다.
	// ==> 기본 생성자도 반드시 작성해야한다.

	public RemoteServer() throws RemoteException {
	}

	// RMI 용 인터페이스에서 선언된 메서드들을 구현한다.

	@Override
	public int doRemotePrint(String str) throws RemoteException {
		int len = str.length();

		System.out.println("doRemotePrint() 메서드 시작...");
		System.out.println("클라이언트에서 보내용 내용 : " + str);
		System.out.println("doRemotePrint()메서드 끝...");
		System.out.println();
		return len; // 이반환값은 클라이언트로 보내지는 값이다.
	}

	@Override
	public void doPrintList(List<String> list) throws RemoteException {
		System.out.println("doPrintList()메서드 시작 ...");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + "번쨰 자료 : " + list.get(i));
		}
		System.out.println("doPrintList()메서드 끝 ...");
		System.out.println();

	}

	@Override
	public void doPrintVo(TestVO vo) throws RemoteException {
		System.out.println("doPrintVo() 메서드 시작...");
		System.out.println("ID : " + vo.getId());
		System.out.println("NUM : " + vo.getNum());
		System.out.println("doPrintVo() 메서드 끝...");
		System.out.println();

	}

	public static void main(String[] args) {
		// RMI 서비스를 제공하는 과정
		try {
			// 1. RMI 용 인터페이스를 구현한 객체의 인스턴스를 생성한다.
			//// (이떄 변수는 RMI용 인터페이스 자료형으로 선언한다.
			RmiTestInterface inf = new RemoteServer();

			// 2. 구현한 객체를 클라이언트가 찾을 수 있도록 관리하는 Registry객체를 생성한다.
			// (이 때 동신에서 사용할 포트번호를 지정하여 생성한다.)
			// (RMI의 기본 포트번호는 1099이다.)

			// 충돌 하지 않도록 숫자 를 잘 정해준다.
			Registry reg = LocateRegistry.createRegistry(9988);

			// 3.Registry객체에 서버에서 제공하는 객체
			//// (즉, RMI용 인터페이스를 구현한 객체의 인스턴스를 등록한다.)
			// 형식>> Registry객체변수,rebind("객체의 Alias명", RMI용 객체의 인스턴스);
			////// ==>이곳에서 지정한 '객체의 Alias명'은 클라이언트에서 이 객체를 구할 떄 사용한다.
			reg.rebind("server", inf);
			System.out.println("서버가 준비되었습니다.");

		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	// 클라이언트가 보내온 파일 정보를 이용하여 서버쪽에 해당 파일을 저장한다.
	@Override
	public void transFile(FileInfoVO fileVo) throws RemoteException {
		String dir = "d:/D_Other/연습용/";
		System.out.println("파일 저장 시작... ");
		System.out.println();

		FileOutputStream fout = null;
		BufferedOutputStream bout = null;

		try {
			// 클라이언트가 보내온 파일명을 이용하여 스트림 객체 생성.
			fout = new FileOutputStream(dir + fileVo.getFileName());

			// 클라이언트가 보내온 파일 데이터 (byte[])저장한다.
			fout.write(fileVo.getFileData());

			fout.flush();
			fout.close();

		} catch (IOException e) {
			// TODO: handle exception
		}
		System.out.println("파일저장끝.");
	}

}
