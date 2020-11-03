package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest01_ByteInOut {

	public static void main(String[] args) {
		// byte 배열의 데이터를 입력스트림으로 읽어서 출력 스트림으로 출력하는 예제

		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null;

		// 입력용 스트림 객체 생성
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);

		// 출력용 스트림 객체 생성
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		// 데이터 읽기후 저장 될 변수를 생성
		int data;

		// read는 1바이트씩 읽기
		// 코딩을 줄이기. 데이터를 읽어오며 읽을 것이 없으면 -1을 반환
		// read()메서드 ==> 더이상 읽어올 자료가 없으면 -1을 반환한다.
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!! 스킬
		while ((data = input.read()) != -1) {
			// 읽어온 데이터를 사용하여 처리하는 내용을 기술
			// 현재의 경우 출력 - 출력용 스트림 사용하여 읽은 데이터를 출력한다.
			output.write(data);
			// while 문 반복
		}

		// 출력된 스트림 값을 배열로 변환
		outSrc = output.toByteArray();

		// close() ==> try catch로 묶어주어야함.
		try {
			input.close();
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 가져온 데이터 출력
		// 초기값
		System.out.println("inSrc : " + Arrays.toString(inSrc));

		// 입력후 출력값.
		System.out.println("outSrc : " + Arrays.toString(outSrc));
		System.out.println();
	}

}
