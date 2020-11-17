package kr.or.ddit.basic;

// 쓰레드에서 객체를 공통으로 사용하는 예제

//원주율을 계산하는 쓰레드와
//계산된 원주율을 출력하는 쓰레드가 있다.

//원주율을 저장하는 객체가 필요하다.
//이 객체를 두 쓰레드에서 공통으로 사용해서 처리한다.

public class ThreadTest15_PI {

	public static void main(String[] args) {
		// 공통으로 사용할 객체 생성
		ShareData sd = new ShareData();

		// 쓰레드 객체를 생성하고, 공통으로 사용할 객체를 쓰레드에 주입한다.
		PrintPIThread printPi = new PrintPIThread(sd);

		CalcPIThread calcPi = new CalcPIThread();
		calcPi.setSd(sd);

		calcPi.start();
		printPi.start();

	}

}

// 원주율을 계산하는 쓰레드
class CalcPIThread extends Thread {
	private ShareData sd;

	// public CalcPIThread(ShareData sd) {
	// this.sd = sd;
	// }

	public ShareData getSd() {
		return sd;
	}

	public void setSd(ShareData sd) {
		this.sd = sd;
	}

	public void run() {
		// 원주율 공식 =( 1/1 - 1/3 + 1/5 - 1/7 .....) *4
		// 1 3 5 7 9....................
		// 0 1 2 3 4....................

		double sum = 0.0;
		for (int i = 1; i < 800000000; i += 2) {
			// 짝수
			if ((i / 2) % 2 == 0) {
				sum += (1.0 / i);
			}
			// 홀수
			if ((i / 2) % 2 == 1) {
				sum -= (1.0 / i);
			}
			// 계산된 원주율 저장
			sd.result = sum * 4;
			sd.isOK = true;
		}

	}

}

// 원주율을 출력하는 쓰레드
class PrintPIThread extends Thread {
	// 공통으로 사용할 객체의 참조값이 저장될 변수 선언
	private ShareData sd;

	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}

	public void run() {
		while (true) {
			// 계산이 완료인지 확인 if
			if (sd.isOK == true) {
				break;
			}
		}
		System.out.println();
		System.out.println("계산 완료");
		System.out.println("결과 : " + sd.result);
		System.out.println("PI : " + Math.PI);
	}

}

// 공통으로 사용할 클래스 ==> 원주율을 관리하는 클래스
class ShareData {
	// 계산된 원주율이 저장될 변수
	public double result;
	// 계산이 완료되었는지 여부를 나타내는 변수
	public boolean isOK;
	// public volatile boolean isOK;
	// volatile 속도차이를 줄이기 위해 사용
	// volatile ==> 이 키워드가 붙은 변수는 컴파일러의 최적화 대상에서 제외된다
	// 즉 갑이 변경되는 즉시 변수에 적용 시킨다.
}
