package crypto.basic;

import crypto.util.AES256Util;
import crypto.util.CryptoUtil;

public class CryptoTest {

	public static void main(String[] args) throws Exception {
		CryptoUtil crypto = new CryptoUtil();
		String plainText = "안녕하세요 암호화를 공부중입니다.";
		// String plainText = "Hello, World";

		System.out.println();
		System.out.println("MD5 : " + crypto.md5(plainText));
		System.out.println("SHA-256 : " + crypto.sha256(plainText));
		System.out.println("SHA-512 : " + crypto.sha512(plainText));
		System.out.println("----------------");
		// ----------------
		// 비밀번호 찾기시 임시....
		// 첫비밀번호 암호화로 저장
		// 암호를 잃어버렸다면 임의적으로 생성후 접속후 다시 새로운암호를 등록.
		// 복원을 시키지 않는 암호화 방식
		// ----------------

		//
		//
		//

		// ----------------

		AES256Util aes256 = new AES256Util();
		String str = aes256.encrypt(plainText);

		System.out.println("~~~암호화/복호화 연습");
		System.out.println("원래의 데이터  ==> " + plainText);
		System.out.println("AES256 암호화 ==> " + str);
		System.out.println("AES256 암호화 문자열 길이 ==> " + str.length());
		System.out.println("AES256 복호화 ==> " + aes256.decrypt(str));
		System.out.println("----------------");
		System.out.println();
		String tmp = "";
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				tmp += j;
				str = aes256.encrypt(tmp);
				System.out.println(i + " - tmp ==> " + tmp);
				System.out.println("암호화 ==> " + str);
				System.out.println("암호화길이 ==> " + str.length());
				String deStr = aes256.decrypt(str);
				System.out.println("복호화 " + deStr);

			}

		}
	}

}
