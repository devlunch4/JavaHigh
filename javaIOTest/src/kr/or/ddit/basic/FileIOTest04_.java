package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest04_ {

	// 콘솔에서 사용자가 입력한 내용을 그대로 파일에 저장하기
	// 콘솔 기장 기본적인 입출력장치
	public static void main(String[] args) {

		try {
			// System.out ==> 콘솔(표준입출력장치) 출력장퓨치==> byte 기반 출력용 스트림
			// System.in ==> 콘솔 입력장치 ==> byte 기반 입력용 스트림

			// byte 기반의 스트림 문자 기반의 스트림으로 변환해주는 스트림 객체 생성
			// 콘솔로 입력한 데이터를 가져오기 위한 스트림 객체
			InputStreamReader isr = new InputStreamReader(System.in);

			// 파일로 저장하는 문자 기반 스트림 객체 생성
			FileWriter fw = new FileWriter("d:/D_other/testChar.txt");

			// System.in은 사용자가 ctrl + z 키를 입력하면 키 입력이 종료 하는것으로 인식
			//해당 doc
			//https://docs.oracle.com/javase/8/docs/api/java/io/Console.html
			System.out.println("아무내용 입력하세요. (입력의 끝은 Ctrl +Z 키)");
			int c;
			while ((c = isr.read()) != -1) {
				// 콘솔로 입력한 값을 파일로 출력
				fw.write(c);
			}
			isr.close();
			fw.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}