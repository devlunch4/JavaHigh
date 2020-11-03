package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02_ByteArray_Stream {

	public static void main(String[] args) {

		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null;

		// 4개짜리 배열 생성
		byte[] temp = new byte[4];

		// 입력용 스트림 객체 생성
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		// 출력용 스트림 객체 생성
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		try {
			// 읽어올 데이터가 있는지 - 데이터의 사용 유무 확인
			while (input.available() > 0) {
				////////////////////////////////////////////////
				// input.read(temp);
				//
				// // temp 배열의 데이터를 출력 스트림으로 출력
				// output.write(temp);
				//
				/////////////////////////////////////////////// 추가분 위 생략후 아래 작성 완료
				// 반환값 : 실제읽어온 데이터의 byte수를 반환한다.
				int len = input.read(temp);
				// 읽어온 데이터가 저장된 temp 배열에서 0번째 부터 읽어온 개수 만큼 출력 한다.
				// https://docs.oracle.com/javase/8/docs/api/java/io/ByteArrayOutputStream.html
				output.write(temp, 0, len);
				//
				////////////////////////////////////////////////

				System.out.println("반복문 내부의 temp 출력 : " + Arrays.toString(temp));
			}

			outSrc = output.toByteArray();
			System.out.println();
			// 초기값
			System.out.println("inSrc : " + Arrays.toString(inSrc));
			// 입력후 출력값.
			System.out.println("outSrc : " + Arrays.toString(outSrc));

			// close() 시 auto flush
			input.close();
			output.close();

		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
