package kr.or.ddit.basic;

public class ThreadTest11_Yield {
	public static void main(String[] args) {
		YieldThread th1 = new YieldThread("1번쓰레드");
		YieldThread th2 = new YieldThread("2번쓰레드");

		th1.start();
		th2.start();

		// th1.work = true; 상태
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
		System.out.println("==================================================================");
		th1.work = false;
		// th1.work = false; th1 양보출력중 th2 작업중

		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		th1.work = true;
		// th1.work = false; th1 작업중 th2 작업중

		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
		}
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		th1.stop = true;
		th2.stop = true;
		// 모두 정지
	}
}

// Yield()메서드 연습용 클래스
class YieldThread extends Thread {
	// 쓰레드의 종료 여부를 나타내는 값을 저장하는 변수 선언
	public boolean stop = false;
	// 작업을 처리하는 여부를 나타내는 값을 저장하는 변수 선언
	public boolean work = true;

	public YieldThread(String name) {
		// 쓰레드으 이름을 설정한다.
		super(name);

	}

	@Override
	public void run() {
		// stop이 true가 되면 반목문이 종료된다.
		while (!stop) {
			if (work) {
				// getname 메서드가 현재 name 값을 반환한다.
				System.out.println(getName() + " 작업중");
			} else {
				System.out.println(getName() + " 양보");
			}

		}
	}
}