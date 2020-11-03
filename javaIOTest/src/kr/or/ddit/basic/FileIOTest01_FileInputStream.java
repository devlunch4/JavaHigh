package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;

public class FileIOTest01_FileInputStream {

	public static void main(String[] args) {
		// FileInputStream객체를 이용한 파일 내용 읽기
		try {
			// 읽어올 파일을 매개값으로 받는 FileInputStream 객체 생성하기
			// 방법1
			// FileInputStream fin = new FileInputStream("D:/D_Other/test.txt");

			// 방법2
			File file = new File("D:/D_Other/test.txt");
			FileInputStream fin = new FileInputStream(file);

			// 읽어온 데이터가 저장될 변수
			int c;
			while ((c = fin.read()) != -1) {
				// 읽어온 데이터를 화면에 출력하기
				System.out.print((char) c);

			}
			// 스트림 닫기
			fin.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("출력오류 발생");
		}
	}

}
