package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02_FileOutputStream {

	public static void main(String[] args) {

		try {
			// 출력용 파일 스트림 객체 생성
			// 방법1
			File file = new File("D:/D_Other/out.txt");
			// String filename = "D:/D_Other/out.txt";
			FileOutputStream fout = new FileOutputStream(file);
			// // 방법2
			// FileOutputStream fout = new FileOutputStream("D:/D_Other/out.txt");

			for (char ch = 'A'; ch <= 'Z'; ch++) {
				// 출력스트림을 이용해서 파일에 출력하기
				fout.write(ch);
			}
			System.out.println("작업완료~~~~~~~~~~~");

			// =========================================================
			// 저장된 파일 내용을 읽어와 출력하기
System.out.println();
System.out.println("내용 출력");
			
			@SuppressWarnings("resource")
			FileInputStream fin = new FileInputStream(file);

			int c;

			while (true) {
				c = fin.read();
				if (c == -1) {
					break;
				} else {
					System.out.print((char) c);
				}
			}

			fout.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
