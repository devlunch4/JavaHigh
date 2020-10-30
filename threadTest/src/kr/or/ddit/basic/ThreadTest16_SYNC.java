package kr.or.ddit.basic;

public class ThreadTest16_SYNC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ShareObject sobj = new ShareObject();

		TestThread th1 = new TestThread("1번쓰레드", sobj);
		TestThread th2 = new TestThread("2번쓰레드", sobj);
		th1.start();
		th2.start();
	}

}

class TestThread extends Thread {
	private ShareObject sObj;

	public TestThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			sObj.add();

		}
	}
}

// 공통으로 사용할 클래스
class ShareObject {
	private int sum = 0;

	// 방법1 sum의 동기화시 메서드에 동기화를 설정시 public synchronized void add() {사용
	// public synchronized void add() {

	public void add() {
		// 방법2 동기화 블럭으로 설정시 synchronized (this){ 사용
		synchronized (this) {
			int n = sum;
			n += 10;
			sum = n;
			System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
		} // synchronized (this){ 사용시 마감
	}

}
