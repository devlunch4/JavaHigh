package kr.or.ddit.basic;

import java.io.File;

public class FileTest01 {

	public static void main(String[] args) {
		// File 객체 만들기 연습

		// 1. new File(String 파일 또는 경로);
		// ========> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분문자는
		/////////// 역슬래시("\")를 사용하거나 슬래시("/")를 사용할수 있다.
		//
		//
		// 일반 슬래시 사용
		// File file1 = new File("D:/D_Other/test.txt");
		//

		// 역슬래시 사용시 두개씩 사용해야함
		// File file1 = new File("D:\\D_Other\\test.txt");
		File file1 = new File("D:/D_Other/test.txt");
		System.out.println("1---------");
		System.out.println("파일명 : " + file1.getName());
		System.out.println("파일주소 : " + file1.getPath());
		System.out.println("읽기 가능? : " + file1.canRead());
		System.out.println("쓰기 가능? : " + file1.canWrite());
		System.out.println("디렉토리일까? : " + file1.isDirectory());
		System.out.println("파일일까? : " + file1.isFile());
		System.out.println();

		// File file2 = new File("D:/D_Other");
		File file2 = new File("D:\\D_Other");
		System.out.println("2---------");
		System.out.println("파일명 : " + file2.getName());
		System.out.println("파일주소 : " + file2.getPath());
		System.out.println("읽기 가능? : " + file2.canRead());
		System.out.println("쓰기 가능? : " + file2.canWrite());
		System.out.println("디렉토리일까? : " + file2.isDirectory());
		System.out.println("파일일까? : " + file2.isFile());
		System.out.println();

		//
		//
		//

		// 2. new File(File parent, String child)
		///////////// ==> 'parent' 디렉토리 안에 있는 'child'파일을 나타낸다.
		File file3 = new File(file2, "test.txt");
		System.out.println("3---------");
		System.out.println("파일명 : " + file3.getName());
		System.out.println("파일주소 : " + file3.getPath());
		System.out.println("읽기 가능? : " + file3.canRead());
		System.out.println("쓰기 가능? : " + file3.canWrite());
		System.out.println("디렉토리일까? : " + file3.isDirectory());
		System.out.println("파일일까? : " + file3.isFile());
		System.out.println();

		//
		//
		//

		// 3. new File(String parent, String child)
		///////////// ==> 'parent' 디렉토리 안에 있는 'child'파일을 나타낸다.
		File file4 = new File("D:/D_Other", "test.txt");
		System.out.println("4---------");
		System.out.println("파일명 : " + file4.getName());
		System.out.println("파일주소 : " + file4.getPath());
		System.out.println("읽기 가능? : " + file4.canRead());
		System.out.println("쓰기 가능? : " + file4.canWrite());
		System.out.println("디렉토리일까? : " + file4.isDirectory());
		System.out.println("파일일까? : " + file4.isFile());
		System.out.println();

		// -------------------------------------------------------------
		System.out.println("----------------------------------------");
		System.out.println();

		// 디렉토리 만들기
		// mkdir() : File 객체의 경로 중 마지막 위치의 디렉토리를 생성한다.
		//////////// 반환값 : 만들기 성공(true), 만들기 실패(false)
		//////////// 궁간 부분의 경로가 모두 만들어져 있어야 마지막 위치의 폴더를 만들수 있다.

		// mkdirs() : 중간 부분의 경로가 없으면 중간 부분의 경로로 같이 만들어 준다.

		File file5 = new File("D:/D_Other/연습용");
		System.out.println("5---------");
		System.out.println("파일명 : " + file5.getName());
		System.out.println("파일주소 : " + file5.getPath());
		System.out.println("읽기 가능? : " + file5.canRead());
		System.out.println("쓰기 가능? : " + file5.canWrite());
		System.out.println("디렉토리일까? : " + file5.isDirectory());
		System.out.println("파일일까? : " + file5.isFile());
		System.out.println();
		System.out.println(file5.getName() + " 의 존재여부 : " + file5.exists());

		if (file5.mkdir()) {
			System.out.println(file5.getName() + " 파일 생성 완료");
		} else {
			System.out.println(file5.getName() + " 연습용 디렉토리 생성 완료");
		}

		System.out.println("------");
		File file6 = new File("D:/D_Other/test/java/src");
		if (file6.mkdir()) {
			System.out.println("디렉토리 만들기 성공");
		} else {
			System.out.println("디렉토리 만들기 실패");
		}
		System.out.println("------");
		file6 = new File("D:/D_Other/test/java/src");
		if (file6.mkdirs()) {
			System.out.println("디렉토리 만들기 성공");
		} else {
			System.out.println("디렉토리 만들기 실패");
		}
	}
}