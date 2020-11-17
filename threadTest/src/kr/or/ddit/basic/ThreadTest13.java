package kr.or.ddit.basic;

//import java.util.List;

public class ThreadTest13 {

	// 문제) 10마리의 말들이 경주하는 경마 프로그램 작성하기
	//
	// 경주마는 Horse 라는 이름의 클래스로 구성하고
	// 이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버 변수로 갖ㅅ는다
	// 그리고, 이 클래스에는 등수를 오름차순으로 처리하는 내부 정렬 기준이 있다. ()comparable 인터페이스 구현하기
	// - 이 horse 클래스는 쓰레드로 작성한다
	// 경기 구간은 1~50 구간으로 되어 있다.
	//
	//
	// 경기중 중간 중간에 각 말들의 위치를 나타내시오
	// 예)
	// 1번말 ----->----
	// 2번말 -->-------
	// 3번말 -->-------
	// 4번말 ------>---
	// 5번말 ------->--
	//
	// 경기가 끝나면 등수 순으로 경기 결과를 출력한다.

	public static void main(String[] args) {
		Horse[] h = new Horse[] { //
				new Horse("1번말"), //
				new Horse("2번말"), //
				new Horse("3번말"), //
		};

		// 실행
		for (Horse horse : h) {
			horse.start();
		}

		// 완주자 대기
		for (Horse horse : h) {
			try {
				horse.join();
			} catch (Exception e) {
			}
			// 모든것이 끝난 후 출력
		}

	}
}

class Horse extends Thread {
	public String name;
	public static int rank = 0;
	public int state = 10;

	public Horse(String name) {
		super();
		this.name = name;
		// this.rank = rank;
		// this.state = state;
	}

	@Override
	public void run() {

		int speed = (int) (Math.random() * (500 - 1 - 1) + 1);
		for (int i = 10; i >= 0; i--) {
			System.out.println(name + "의 현채위치 : " + state);
			try {
				state -= 1;
				Thread.sleep(speed);
			} catch (Exception e) {
			}
		}

		// 완주자 표시
		Horse.rank++;
		System.out.println(name + "완주 ===" + "순위 : " + rank);

	}
}