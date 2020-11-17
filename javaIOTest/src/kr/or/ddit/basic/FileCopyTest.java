package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopyTest {

	// 'D:/D_Other/' 폴더에 있는 movieposter.jpg 파일을
	// 'D:/D_Other/연습용' 폴더에 'movieposter_복사본.jpg' 이름으로 복사하는 프로그램을 작성하시오

	public static void main(String[] args) {

		String orif = "D:/D_Other/movieposter.jpg";
		String copyf = "D:/D_Other/연습용/movieposter_복사본.jpg";
		try {
			FileInputStream inf = new FileInputStream(orif);
			FileOutputStream outf = new FileOutputStream(copyf);

			int bytedata;
			while ((bytedata = inf.read()) != -1) {
				outf.write(bytedata);
			}
			inf.close();
			outf.close();

			System.out.println("복사 완료!!");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("오류 발생!!");
		}

	}

}
