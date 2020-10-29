package kr.or.ddit.basic;

public class ThreadTest13_second {

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

	// 스레드 모두 종료 확인을 위한 참거짓 변수
	public static boolean go = true;

	public static void main(String[] args) {
		xHorse[] h = new xHorse[] { //
				new xHorse("1번말"), //
				new xHorse("2번말"), //
				new xHorse("3번말"), //
		};

		// 실행
		for (xHorse horse : h) {
			horse.start();
		}

		// 텍스트형
		// // 완주자 대기
		// for (xHorse horse : h) {
		// try {
		// horse.join();
		// } catch (Exception e) {
		// }
		// // 모든것이 끝난 후 출력
		// }

		// 표현
		xRacePrint rp = new xRacePrint(h);
		rp.start();
		for (xHorse x : h) {
			try {
				x.join();
				// 쓰래도 종료 대기 다 끝나면 상태 출력 break를 위해 변수 값 변경
				ThreadTest13_second.go = false;
			} catch (InterruptedException e) {
			}

		}
		System.out.println();
		System.out.println("!!");

		// for (xHorse x : h) {
		// System.out.println("결과" + x.name + ":" + x.rank);
		// }

	}
}

// 경마 달려달려!
class xHorse extends Thread {
	public String name;
	public static int rank = 0;
	public int state = 0;

	public xHorse(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i--) {
			// 텍스트형 위치 출력 확인
			// System.out.println(name + "의 현채위치 : " + state);
			try {
				state += 1;
				if (state == 10) {
					break;
				}
				Thread.sleep((int) (Math.random() * (500 - 1 - 1) + 1));
			} catch (Exception e) {
			}
		}

		// 완주자 표시
		rank++;
		System.out.println(name + "완주 ===" + "순위 : " + rank);
	}
}

// 상태 표시
class xRacePrint extends Thread {
	xHorse[] horses;

	// 경마들 달리는 정보 받아오기
	public xRacePrint(xHorse[] horses) {
		super();
		this.horses = horses;
	}

	@Override
	public void run() {
		while (true) {
			// 중지되기전까지 true join 진입후 해제되면 false 변하게되며 현재 클래스/메소드 종료
			if (ThreadTest13_second.go == false) {
				break;
			} else {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
				for (xHorse x : horses) {
					System.out.print(x.name + " 위치 : ");
					for (int y = 1; y < 10; y++) {
						System.out.print("-");
						if (y == x.state) {
							System.out.print(">");
						} // if
					} // for
					System.out.println();
				} // for
				System.out.println();
			} // else
		} // while
		System.out.println("fin");
	}// run()

}// print class
