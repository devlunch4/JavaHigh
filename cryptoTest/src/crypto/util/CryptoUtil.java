package crypto.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil {

	// 문자열을 MD5 방식으로 암효화 한다 (자리수 :32byte)
	public String md5(String msg) throws NoSuchAlgorithmException {

		// 암호화 방식을 지정한 객체 생성
		MessageDigest md = MessageDigest.getInstance("MD5");

		// 암호화 하기
		md.update(msg.getBytes());

		// 암호화된 데이터를 가져와 16진수로 변환해서 반환
		return byteToHexString(md.digest());
	}

	// 문자열 SHA-256 방식으로 암호화 한다(자리수: 64byte)
	public String sha256(String msg) throws NoSuchAlgorithmException {
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
		sha256.update(msg.getBytes());
		return byteToHexString(sha256.digest());
	}

	// 문자열 SHA-512 방식으로 암호화 한다(자리수: 128byte)
	public String sha512(String msg) throws NoSuchAlgorithmException {
		MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
		sha512.update(msg.getBytes());
		return byteToHexString(sha512.digest());
	}

	// ------------------------------------!!!!!!!!!!!!!!!!!
	// byte 배열의 데이터를 16진수 값으로 변환하는 메서드
	public String byteToHexString(byte[] data) {
		StringBuilder sb = new StringBuilder();
		for (byte b : data) {
			// (b & 0xff) + 0x100) ==> 16진수 2자리 만들기
			// 0xa &0xff ==> 0x0a ==. 0xa + 0x100 ==> 0x10a ==> "10a" ==> "0a"
			// & 비트 연산자
			sb.append(Integer.toHexString((b & 0xff) + 0x100).substring(1));
			// toHexString((b & 0xff) + 0x100)) == toString((b & 0xff) + 0x100),16)
			// substring(1) >>>> "1234567" >> 인덱스가 1 이므로 "34567" 출력
		}

		return sb.toString();

	}
}
