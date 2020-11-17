package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		// InetAddress ==> ip주소를 다루기 위한 클래스
		// www.naver.com 의 ip정보 가져오기

		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		System.out.println("HostName : " + naverIp.getHostName());
		System.out.println("HostAddress : " + naverIp.getHostAddress());
		System.out.println();
		
		// 자신의 컴퓨터 IP 가져오기
		InetAddress localIP = InetAddress.getLocalHost();
		System.out.println("내 컴 HostName : " + localIP.getHostName());
		System.out.println("내 컴 HostAddress : " + localIP.getHostAddress());
		System.out.println();
				
		//ip 주소가 여러개인 호스트의 정보 가져오기
		
		InetAddress[] navers = InetAddress.getAllByName("www.daum.net");
		for(InetAddress nip : navers) {
			System.out.println(nip.toString());
		}
	}

}

