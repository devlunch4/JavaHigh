package kr.or.ddit.basic;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest03_CMD {
	public static void main(String[] args) {
		FileTest03_CMD test = new FileTest03_CMD();

		File viewFile = new File("d:/d_other"); // 보고싶은 디렉토리 설정

		test.displayFileList(viewFile);

	}

	// 디렉토리(폴더)를 매개값을 받아서 해당 디렉토리(폴더)에 있는
	// 모든 파일과 디렉토리(폴터) 목록을 출력하는 메서드
	public void displayFileList(File dir) {
		if (!dir.isDirectory()) {
			System.out.println("디렉토리가(폴더)만 가능합니다.");
			// 이후 미진행
			return;
		}

		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리 내용");
		System.out.println();

		// 디렉토리 리스트를 만들기 위해 사용
		// 해당 디렉토리 안에 있는 모든 파일과 디렉토리 목록을 구해온다.
		File[] files = dir.listFiles();
		// String[] filestrs = dir.list();

		// 시간 표현 년 월 일 오전오후 시분
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd a hh:mm");

		// 가져온 파일과 디렉토리 목록 개수만큼 반복처리하기
		for (int i = 0; i < files.length; i++) {

			String fileName = files[i].getName();
			String attr = ""; // 파일의 속성을(읽기,쓰기,히든,디렉토리를 구분)
			String size = ""; // 파일의 크기를 표현 int 타입이나 string 으로 구분

			if (files[i].isDirectory()) {
				attr = "<DIR>";
			} else {
				size = files[i].length() + "";
				attr = files[i].canRead() ? "R" : "";
				attr += files[i].canWrite() ? "W" : "";
				attr += files[i].isHidden() ? "H" : "";
			}
			System.out.printf("%s %5s %12s %s\n", df.format(new Date(files[i].lastModified())), attr, size, fileName);
		}
	}
}
