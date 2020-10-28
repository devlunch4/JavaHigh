package kr.or.ddit.basic;

import javax.swing.JOptionPane;

// 문제) 컴퓨터와 가위 바위보를 진행하는 프로그램을 작성하시오
//- 컴퓨터의 가위바위보는 난수를 이용해서 구하고
//-사용자는 show InputDialog()매서드를 이용해서 입력받는다
//
//-입력시간은 5초로 제한하고 카우트 다운을 진행한다
//-5초안에 입력이 었으면 게임에 진것으로 처리하고 끝낸다.

//5초안에 입력이 완료되면 승패를 구해서 출력한다.

//5초안에 입력이 완료되었을때 결과 예시
//--결과--
//컴퓨터 : 가위
//사용자 : 바위
//결과 : 당신이 이겼습니다.

public class ThreadTest07 {
	public static void main(String[] args) {
		Thread count = new Count();
		Thread xgame = new xGame();

		count.start();
		xgame.start();

	}
}

class Count extends Thread {
	@Override
	public void run() {
		for (int i = 3; i > 0; i--) {
			// 입력이 완료된 여부 검사- 입력이 완료되면 쓰레드를 종료시킨다.
			if (xGame.inputchk == true) {
				return; // run() 메서드가 종료되면 해당 쓰레드로 종료된다.
				// 하단의 10초지난 출력을 피하기 위해 return을 했다.
			}

			System.out.println(i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}

		}
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0);
	}
}

class xGame extends Thread {
	// 입력 여부 확인을 위한 변수 선언 ==> 쓰레드에서 공통으로 사용할 변수
	public static boolean inputchk;

	@Override
	public void run() {
		// 사용자 입력 및 생성

		String str = JOptionPane.showInputDialog("1.가위 2.바위 3.보");
		inputchk = true;

		if (str == null) {
			str = "esc";
		}
		System.out.println("사용자가 입력한 값 : " + str);

		String userstr = null;

		switch (str) {
		case "1":
			userstr = "가위";
			break;
		case "2":
			userstr = "바위";
			break;
		case "3":
			userstr = "보";
			break;
		case "esc":
			System.out.println("잘못된 값 입력입니다.");
			System.out.println();
			return;

		default:
			System.out.println("잘못된 값 입력입니다.");
			System.out.println();
			return;
		}

		System.out.println("사용자가 낸 값 : " + userstr);

		//
		// 컴퓨터 생성
		String comstr = null;

		int com = (int) (Math.random() * 3 + 1);
		System.out.println("생성된 컴퓨터 값 " + com);

		switch (com) {
		case 1:
			comstr = "가위";
			break;
		case 2:
			comstr = "바위";
			break;
		case 3:
			comstr = "보";
			break;

		default:
			System.out.println("잘못된 값 입력입니다.");
			break;
		}

		// 제출값 출력
		System.out.println("컴퓨터가 낸 값 : " + comstr);
		System.out.println();
		System.out.println("-------------------");
		System.out.println("컴퓨터값:사용자값");
		System.out.println("    " + comstr + " : " + userstr);
		System.out.println("-------------------");
		System.out.println();

		// 판별 결과
		if (comstr == userstr) {
			System.out.println("서로 비겼습니다.");
		} else if ((comstr == "바위" && userstr == "가위") || (comstr == "가위" && userstr == "보")
				|| (comstr == "보" && userstr == "바위")) {
			System.out.println("컴퓨터가 이겼습니다");
		} else {
			System.out.println("사용자가 이겼습니다");
		}

	}
}
