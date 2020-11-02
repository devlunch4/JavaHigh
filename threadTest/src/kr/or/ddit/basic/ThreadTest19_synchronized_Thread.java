package kr.or.ddit.basic;

// wait(), notify() 메서드를 이용한 예제
//(0두 쓰레드가 번갈아 하번씩 실행하는 예제

// - wait(), notify(), notifyAll(메서드는 동기화 영역에서만 사용가능하다.)

public class ThreadTest19_synchronized_Thread {

	public static void main(String[] args) {

		WorkObject workObj = new WorkObject();

		ThreadA tha = new ThreadA(workObj);
		ThreadB thb = new ThreadB(workObj);

		// 실행을 하면 1>2>1>2>1>2 호출 순서대로 실행이 된다.
		// 상세 1의 notify > wait> 락 > 2의 notify로 wait 쓰레드를 깨우고 2는 wait로.
		// > 깨어진 1 출력후 2를 깨운후 wait 상태로 > 깨어진 2 출력후 1을 깨운후 wait >>>반복
		// 실10번 반복후 정지가 안되는 것은 이후 wait로 남은후 마지막 깨워줌이 없기 때문이다.
		tha.start();
		thb.start();

	}

}

// 공통으로 사용할 객체

class WorkObject {
	public synchronized void testMothod1() {
		System.out.println("testMethod1()메서드 실행중...");
		// 무엇이 먼저 실행될지 모르므로 먼저 notify를 실행후 락을 걸고 wait.
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

	public synchronized void testMothod2() {
		System.out.println("testMethod2()메서드 실행중...");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
}

// WorkObject의 testMethod1() 메서드만 실행하는 쓰레드
class ThreadA extends Thread {
	private WorkObject workObj;

	// 생성자
	public ThreadA(WorkObject workObj) {
		super();
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			workObj.testMothod1();
		}
		// 마지막상태의 wait를 해결하기위한 notify메소드
		synchronized (workObj) {
			System.out.println("ThreadA > Last notify()");
			workObj.notify();
		}
	}
}

////////////////////////////////////////////////////////
// WorkObject의 testMethod2() 메서드만 실행하는 쓰레드
class ThreadB extends Thread {
	private WorkObject workObj;

	// 생성자
	public ThreadB(WorkObject workObj) {
		super();
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			workObj.testMothod2();
		}
		// 마지막상태의 wait를 해결하기위한 notify메소드
		synchronized (workObj) {
			System.out.println("ThreadB > Last notify()");
			workObj.notify();
		}
	}
}