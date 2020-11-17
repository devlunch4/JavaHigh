package kr.or.ddit.basic;

public class ThreadTest02 {

	// MULTI thread method
	public static void main(String[] args) {

		// SINGLE thread method
		// for (int i = 1; i <= 200; i++) {
		// System.out.println("*");
		// }
		//
		// System.out.println();
		// System.out.println();
		//
		// for (int i = 0; i <= 200; i++) {
		// System.out.println("$");
		// }

		// Thread를 작성해서 사용하는 방법

		// 방법1
		// Thread 클래시를 상속한 class의 인스턴스를 생성한 후
		// 이 인스턴스의 start()메서드를 호출해서 실행한다.

		// 클래스 호출
		MyThread1 th1 = new MyThread1();
		// 클래스 시작 호출
		th1.start();

		// 방법2
		// Runnable 인터페이스를 구현한 class의 인스턴스를 생성한 후
		// 이 인스턴스를 Thread의 인스턴스를 생성할 때 생성자의 인수값으로 넘겨준다.
		// 이 떄 생성된 Thread의 인스턴스의 start()메서드를 호출해서 실행한다.

		// 클래스 호출
		MyThread2 r2 = new MyThread2();
		// 클래스를 쓰레드로 입력
		Thread th2 = new Thread(r2);
		// 두번째 스레드 시작호출
		th2.start();

		// 방법3 ==> 익명 구현체를 이용하는 방법
		Runnable r3 = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 200; i++) {
					System.out.print("%");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						System.out.println("오류발생 r3");
					}
				}
			}
		};
		Thread th3 = new Thread(r3);
		th3.start();

		// 출력이 먼저 되더라도 쓰레드의 메소드의 작동이 아래 문구 출력후 이어질수 있다.
		System.out.println("LAST LINE MAIN METHOD");
	}
}

//////// 방법1
class MyThread1 extends Thread {

	@Override
	public void run() {
		// 이 run()메서드안에 thread가 처리할 내용을 기술한다.
		// super.run();

		for (int i = 1; i <= 200; i++) {
			System.out.print("*");
			try {
				// Thread.sleep(시간); ==> 주어진 '시간'동안 작업을 잠시 멈춘다.
				// '시간'은 밀리세컨드 단위를 사용한다.
				// 즉, 1000은 1초를 의미한다.
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println("오류발생 th1");
			}
		}
	}
}

//////// 방법2
class MyThread2 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i <= 200; i++) {
			System.out.print("$");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				System.out.println("오류발생 th2");
			}
		}

	}
}