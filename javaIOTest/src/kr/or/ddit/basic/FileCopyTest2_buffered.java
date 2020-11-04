package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopyTest2_buffered {

	// 'D:/D_Other/' 폴더에 있는 movieposter.jpg 파일을
	// 'D:/D_Other/연습용' 폴더에 'movieposter_복사본.jpg' 이름으로 복사하는 프로그램을 작성하시오

	public static void main(String[] args) {

		String orif = "D:/D_Other/movieposter.jpg";
		String copyf = "D:/D_Other/연습용/movieposter_복사본.jpg";
		try {
			FileInputStream inf = new FileInputStream(orif);

			BufferedInputStream bin = new BufferedInputStream(inf);

			FileOutputStream outf = new FileOutputStream(copyf);

			BufferedOutputStream bout = new BufferedOutputStream(outf);

			/*
			 * int bytedata; while ((bytedata = inf.read()) != -1) { outf.write(bytedata); }
			 * inf.close(); outf.close();
			 * 
			 * System.out.println("복사 완료!!");
			 * 
			 * } catch (Exception e) { // TODO: handle exception
			 * System.out.println("오류 발생!!"); }
			 */
			int bytedata;
			while ((bytedata = bin.read()) != -1) {
				bout.write(bytedata);
			}
			bout.flush();
			bout.close();
			bin.close();
			
			System.out.println("복사 완료!!");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("오류 발생!!");
		}

	}

}
