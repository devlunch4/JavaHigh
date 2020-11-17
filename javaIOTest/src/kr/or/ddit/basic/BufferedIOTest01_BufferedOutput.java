package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01_BufferedOutput {

	public static void main(String[] args) {
		// 입출력의 성능 향상을 위해서 Buffered 스트림을사용

		try {
			FileOutputStream fout = new FileOutputStream("d:/D_other/bufferTest.txt");

			// 버퍼의 크기가 5인 Buffered 스트림 객체 생성
			// 버퍼의 크기를 지정하지 않으면 기본크기인 8192byte로 버퍼의 크기가 정해진다.
			BufferedOutputStream bout = new BufferedOutputStream(fout, 5);

			for (char i = '1'; i <= '9'; i++) {
				bout.write(i);
			}
			System.out.println("작업끝1.");

			// 실제 출력시 for문의 9까지 입력되어야하지만.
			// 위의 아웃스트림 설정값이 5이므로 12345까지만 입력이 된다.
			// 단 flush와 close의 경우 이후의 6789까지 출력되어 입력된다.

			// 작업을 종료하기 전에 버퍼에 남아있는 데이터를 출력
			bout.flush();

			// fout.close();

			// 보조스트림을 닫으면 보조스트림에서 사용한 기반이 되는 스트림도 자동으로 닫힌다
			bout.close();
			System.out.println("작업끝2.");
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
