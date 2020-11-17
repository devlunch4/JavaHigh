package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest05_InputStreamReader {

	// 한글이 저장된 파일 읽어오기

	// (한글의 인코딩을지정해서 읽어오는 예제

	public static void main(String[] args) {

		try {
			// FileReader fr = new FileReader("d:/D_other/test_ansi.txt");
			// FileReader fr = new FileReader("d:/D_other/test_utf8" + ".txt");

			// 파일을 읽어올 떄나 저장할때 인코딩 방식을 지원하는 스트림
			// 입력용 : InputStreamReader
			// 출력용 : OutputStreamReader

			// byte 기반의 파일입렬용 스트림 객체 생성
			// FileInputStream fin = new FileInputStream("d:/D_other/test_utf8.txt");
			FileInputStream fin = new FileInputStream("d:/D_other/test_ansi.txt");

			// 기본 인코딩 방식으로 처리한다 ==> 인코딩 방식을 별도로 지정하지 않는 경우
			// inputStreamReader isr = new InpuStreamResder(fin);

			// 인코딩 방식을 지정해서 읽어오기

			// 인코딩 방식예시

			// -MS949 ==> 윈도우의 기본 한글 인코딩방식(ANSI와 동일)
			// -UTF-8 ==> 유니코드 UTF-8 인코딩방식
			// -US ACII ==> 영문정용 인코딩 방식

			InputStreamReader isr = new InputStreamReader(fin, "MS949");
			// InputStreamReader isr = new InputStreamReader(fin, "utf-8");

			int c;

			// while ((c = fr.read()) != -1) {
			// System.out.print((char) c);
			//
			// }
			// fr.close();
			while ((c = isr.read()) != -1) {
				System.out.print((char) c);
			}
			isr.close();
		} catch (IOException e) {

		}

	}

}
