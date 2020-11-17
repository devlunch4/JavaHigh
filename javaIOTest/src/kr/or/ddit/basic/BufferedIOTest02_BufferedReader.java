package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTest02_BufferedReader {

	public static void main(String[] args) {
		// 문자기반의 buffered 스트림 객체 사용 예제

		try {
			// 이클립스에서 자바 프로그램이 실행되는 현재위치는 해당 '프로젝트폴더'가 현재위치
			FileReader fr = new FileReader("./src/kr/or/ddit/basic/FileTest01.java");
			BufferedReader br = new BufferedReader(fr);

			// 읽어온 한 줄의 문자열이 저장될 변수 선언
			String temp = "";

			// 문자기반의 버퍼스트림의 readLine() 메서드
			// =========> 한줄씩 데이터를 읽어온다
			// =========> 읽어올 자료가 없으면 null을 반환
			for (int i = 0; (temp = br.readLine()) != null; i++) {
				System.out.printf("%4d : %s\n", i, temp);

			}
			br.close();

		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
