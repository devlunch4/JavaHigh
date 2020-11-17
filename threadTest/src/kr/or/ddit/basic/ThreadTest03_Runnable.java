package kr.or.ddit.basic;

public class ThreadTest03_Runnable {

	// 쓰레드가 수행되는 시간을 체크해보자
	public static void main(String[] args) {
		// 한번에 쓰레드생성. 러너블 받아서
		Thread th = new Thread(new MyRunner_Runnable());

		// currentTimeMillis >> 1970년 1월1일 0시0분0초(표준시간)로 부터 경과한 시간을
		// 밀리세턴드 (1/1000초) 단위로 반환한다.
		long startTime = System.currentTimeMillis();
		th.start();

		//////// 초기 타이핑 후 추가 코드 시작
		try {
			// 현재 실행중인 쓰레드에서 대상이 되는 쓰레드(지금은 변수 th, 즉 th.start() )가 끝날때까지 기다림.
			th.join();
		} catch (InterruptedException e) {
			System.out.println("th오류 발생");
		}
		//////// 초기 타이핑 후 추가 코드 종료

		long endTime = System.currentTimeMillis();

		System.out.println("실행시간 : " + (endTime - startTime));

		// 콘솔창 출력
		//
		// 실행시간 : 0
		// 합계 : 50000005000000
		//
		// 메소드 순서상 스레드 start()후 프린트가 되어야하는데
		// 각각의 콜스택을 만들고난뒤 각자의 스택에서 실행이 된다.
		// 따라서 프린트가 먼저완료되고 이후 계산중 계산후 myruuner의 완료값이 출력된다.

	}

}

class MyRunner_Runnable implements Runnable {

	@Override
	public void run() {
		long sum = 0L;
		for (long i = 1L; i <= 10000000L; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}

}
