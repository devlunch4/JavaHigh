package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;

// 문제) 10마리의 말들이 경주하는 경마 프로그램 작성하기

///////////////////////
// 경주마는 Horse 라는 이름의 클래스로 구성하고
// 이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버 변수로 갖ㅅ는다
// 그리고, 이 클래스에는 등수를 오름차순으로 처리하는 내부 정렬 기준이 있다. ()comparable 인터페이스 구현하기
// - 이 horse 클래스는 쓰레드로 작성한다
// 경기 구간은 1~50 구간으로 되어 있다.
///////////////////////
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

//////////////
//강사코드 시작
//////////////
public class ThreadTest13_typing {
	public static void main(String[] args) {

		// 경주마 설정
		Horse_t[] horses = new Horse_t[] { //
				new Horse_t("1번마"), //
				new Horse_t("2번마"), //
				new Horse_t("3번마"), //
				new Horse_t("4번마"), //
				new Horse_t("5번마"), //
				new Horse_t("6번마"), //
				new Horse_t("7번마"), //
				new Horse_t("8번마"), //
				new Horse_t("9번마"), //
				new Horse_t("10번마") //
		}; //

		GameState gs = new GameState(horses);

		System.out.println("경기 시작합니다~~~~~~~~~~~~~~~~~~");

		// 생성후 쓰레드 실행
		for (Horse_t h : horses) {
			h.start();
		}

		gs.start();

		// 끝나기를 기다림 join
		for (Horse_t h : horses) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}

		// 끝나기를 기다림 join

		try {
			gs.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}

		System.out.println("경기끝!!!! ");
		// 정렬
		// 방법1
		// Arrays.sort(horses);

		// 방법 2 배열을 리스트에 넣고 정렬
		ArrayList<Horse_t> horseList = new ArrayList<>();
		for (Horse_t h : horses) {
			horseList.add(h);
		}

		Collections.sort(horseList);

		// for (Horse_t h : horseList) {
		// System.out.println(h.getName() + " : " + h.getRank());
		// }

		// 출력
		for (Horse_t h : horseList) {
			System.out.println(h);
		}
	}
}

// Comparable 내부정렬을 위한 해당 클래스 "Horse_t"
class Horse_t extends Thread implements Comparable<Horse_t> {
	// 말의 등수 계산에 사용
	public static int currentRank = 0;

	private String horseName;
	private int rank;
	private int position;

	@Override
	public String toString() {
		// return "Horse_t [horseName=" + horseName + ", rank=" + rank + ", position=" +
		// position + "]";
		return "경주마" + horseName + " 의 순위는 " + rank + " 입니다.";
	}

	public Horse_t(String horseName) {
		super();
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public int compareTo(Horse_t horse) {
		// TODO Auto-generated method stub
		return Integer.compare(rank, horse.getRank());
	}

	// 말이 달리는 부분을 쓰레드로 처리
	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			position = i;
			try {
				// 말의 속도를 나타내기위한 딜레이 시간
				Thread.sleep((int) (Math.random() * 20));
			} catch (Exception e) {
				// TODO: handle exception
			}

		} // 반복문 종료 = 달리기 종료

		// 말의 등수 구하기
		// 한 마리의 말 경주가 끝나면 등수를 구해서 rank에 설정한다.
		currentRank++;
		rank = currentRank;
	}
}

// 경기중 말의 현재 위치를 출력하는 쓰레드
class GameState extends Thread {
	private Horse_t[] ho;

	public GameState(Horse_t[] ho) {
		this.ho = ho;
	}

	@Override
	public void run() {
		while (true) {
			// 모든 말이 들어왔을떄가 경기가 끝난다. 참가한 말들이 모두 완주시.
			if (Horse_t.currentRank == ho.length) {
				break;
			}

			// 빈줄 출력 각 시간에 따른 보임 밀기
			for (int i = 1; i <= 5; i++) {
				System.out.println();
			}

			// 실제 출력
			for (int i = 0; i < ho.length; i++) {
				System.out.print(ho[i].getHorseName() + " : ");
				// 해당 위치를 출력
				for (int j = 1; j < 50; j++) {
					if (ho[i].getPosition() == j) {
						System.out.print(">");
					} else {
						System.out.print("-");
					}
				}
				// 다음 말을 위한 띄어쓰기
				System.out.println();
			} // 실체 출력 반복 완료

			try {
				Thread.sleep(500);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
