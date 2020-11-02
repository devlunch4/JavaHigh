package kr.or.ddit.basic;

import java.io.File;

public class FileTest02 {

	public static void main(String[] args) {
		// AbsolutePath 로 파일 생성함.
		File f1 = new File("D:/D_Other/test.txt");
		System.out.println("1----------");
		System.out.println(f1.getName() + " 의 크기 " + f1.length() + " bytes");
		System.out.println("Path : " + f1.getPath());
		System.out.println("AbsolutePath : " + f1.getAbsolutePath());
		System.out.println();

		//
		File f2 = new File(".");
		System.out.println("2----------");
		System.out.println("Path : " + f2.getPath());
		System.out.println("AbsolutePath : " + f2.getAbsolutePath());
		System.out.println();

		if (f1.isFile()) {
			System.out.println(f1.getName() + " 은 파일입니다.");
		} else if (f1.isDirectory()) {
			System.out.println(f1.getName() + " 은 디렉토리(폴더)입니다.");
		} else {
			System.out.println(f1.getName() + " 은 무엇일까요???.");
		}
		System.out.println();
		//

		//

		if (f2.isFile()) {
			System.out.println(f2.getName() + " 은 파일입니다.");
		} else if (f1.isDirectory()) {
			System.out.println(f2.getName() + " 은 디렉토리(폴더)입니다.");
		} else {
			System.out.println(f2.getName() + " 은 무엇일까요???.");
		}
		System.out.println();

		// 현재 존재하지 않는 파일 지정
		File f3 = new File("D:/D_Other/sample.exe");
		System.out.println("3----------");
		System.out.println("Path : " + f3.getPath());
		System.out.println("AbsolutePath : " + f3.getAbsolutePath());
		System.out.println();

		if (f3.isFile()) {
			System.out.println(f3.getName() + " 은 파일입니다.");
		} else if (f3.isDirectory()) {
			System.out.println(f3.getName() + " 은 디렉토리(폴더)입니다.");
		} else {
			System.out.println(f3.getName() + " 은 무엇일까요???.");
		}
		System.out.println();
	}
}
