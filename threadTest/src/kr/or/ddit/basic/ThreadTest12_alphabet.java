package kr.or.ddit.basic;

public class ThreadTest12_alphabet {

	// 3개의 쓰레드가 각각 알파벳을 A~Z까지 출력하는데
	// 출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기

	// 주자 설정
	public static void main(String[] args) {
		DisplayCharacter[] players = new DisplayCharacter[] { //
				new DisplayCharacter("1번주자"), //
				new DisplayCharacter("2번주자"), //
				new DisplayCharacter("3번주자") //
		};

		// 각 주자 쓰레드로 시작
		for (DisplayCharacter player : players) {
			player.start();
		}

		// 생성된 players의 쓰레드가 모두 종료 될때까지 기다림.
		for (DisplayCharacter player : players) {
			try {
				player.join();
			} catch (Exception e) {
			}
		}

		// 모든것이 끝난 후 출력
		System.out.println("=============================================");
		System.out.println("경기 결과 (들어온 순서) : " + DisplayCharacter.rank);
	}
}

// A~Z 까지 출력하는 쓰레드
class DisplayCharacter extends Thread {
	// 빨리 출력한 순서대로 저장할 변수 선언
	public static String rank = "";
	private String name;

	// 실행되는 메인 클래스에서 생성시 인자 값 받기 (주자 설정)
	public DisplayCharacter(String name) {
		super();
		this.name = name;
	}

	// 출력
	@Override
	public void run() {
		for (char c = 'A'; c <= 'Z'; c++) {
			System.out.println(name + " 의 출력 문자 : " + c);
			try {
				// 101~500 사이의 난수를 sleep 시간으로 설정
				Thread.sleep((int) (Math.random() * (500 - 101 - 1) + 101));
			} catch (Exception e) {
			}
		}
		System.out.println(name + " 출력끝!!!");
		DisplayCharacter.rank += name + " ";
	}
}
