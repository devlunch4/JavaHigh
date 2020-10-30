package kr.or.ddit.basic;

import java.text.SimpleDateFormat;
import java.util.Date;

// Thread의 stop()매서드를 호출하면 쓰레드가 바로 멈춘다.
// 이 때 사용 하던 자원을 정리하지 못하고 프로그램이 종료되어
// 나중에 실행되는 프로그램에 영향향을 줄 수 있다.
// 그래서 stop()메서드는 비추천한다.
public class ThreadTest14_stop {

	public static void main(String[] args) {
		// ThreadStopTest1 th1 = new ThreadStopTest1();
		// th1.start();

		// th1 딜레이주기위한 sleep
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// // TODO: handle exception
		// }

		// 비추천된 메소드이므로 텍스트 줄이 뜬다
		// th1.stop();
		///////////////////////////
		// stop() 메소드 대신에 사용
		// th1.setStop(true);
		// 즉 get/ set을 사용 활용하여 오류가 없도록 하자

		//////// 처리시간 확인
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy년 MM월dd일 HH시mm분ss초SSS");
		Date time = new Date();
		String time1 = format1.format(time);

		System.out.println("x1 : " + time1);

		ThreadStopTest2 th2 = new ThreadStopTest2();
		th2.start();

		try {
			time1 = format1.format(time);
			System.out.println("x2 : " + time1);
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		th2.interrupt();
		time1 = format1.format(time);
		System.out.println("x3 : " + time1);
		//위쪽의 타임이 찍히지 않는다면 이는 출력량이 많아서 앞출력물이 밀린것이다.
	}

}

// 쓰레드를 멈추는 연습용 클래스

class ThreadStopTest1 extends Thread {
	private boolean stop;

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	@Override
	public void run() {
		while (!stop) {
			System.out.println("쓰레드 실행중 !!!!");
		}
		System.out.println("자원정리...");
		System.out.println("쓰레드종료...");
	}

}

// interrupt() 메서드를 이용하여 쓰레드를 멈추게 하는 방법

class ThreadStopTest2 extends Thread {

	@Override
	public void run() {

		// 방법1)
		// interrupt(), sleep()을 이용하여 중지하는 방법
		// 쓰레드가 일시정지 상타애서 interrupt() 메서드가 호출되면
		// 해당 쓰레드는 일시정지 상태에서 풀려나고 동시에 interruptException을 발생시킨다
		// try {
		// while (true) {
		// // while (true) {로 인해서 실행될일이 없어서 빨간줄.
		// System.out.println("쓰레드 실행중 !!!!");
		// // 일시정지를 발생하여 catch의 예외상황을 만듬.
		// Thread.sleep(1);
		// }
		// } catch (InterruptedException e) {
		// }

		// 방법2
		while (true) {
			System.out.println("Thread실행중");
			// // interrupt()메서드가 호출되었는지 검사한다
			// // 1) 검사방법1 쓰레드의 인스턴스 메서드인 isInterrupted()메서드를 이용한다.
			// isInterrupted() 메서드는 interrupt()메서드가 호출되면 true를 반환한다.
			// if (this.isInterrupted()) {
			// break;
			// }
			//
			/////////////////////////////////////////////////////////////////////
			// 검사방법2 ) 쓰레드의 정적메서드인 interrupted()메서드 이용하기
			// interrupted()메서드 ==> interrupted 호출 확인
			if (this.isInterrupted()) {
				break;
			}

		}

		System.out.println("자원정리...");
		System.out.println("쓰레드종료...");
	}
}
