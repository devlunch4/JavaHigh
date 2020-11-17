package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Lotto {

	static Scanner scan = new Scanner(System.in);

	
	public static void main(String[] args) {
		Lotto l = new Lotto();
		l.start();
	}

	public int start() {
		// TODO Auto-generated method stub

		System.out.println("===Lotto 프로그램");
		System.out.println("1.로또구입 2.프로그램종료");
		System.out.println(">>>입력해주세요");

		int inputbuy = scan.nextInt();
		System.out.println();

		switch (inputbuy) {
		case 1: // 구입메소드

			System.out.println("Lotto 구입 시작");
			System.out.println("(1000원에 로또번호 하나입니다.)");
			System.out.println(">>>금액입력");
			int money = scan.nextInt();

			// 금액에 따른 구입수 및 번호 출력
			buy(money);

			break;
		case 2:
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
			break;
		default:
			System.out.println("!!!잘못된 입력");
			System.out.println();
			break;
		}
		return start();

		// 로또번호 출력
	}

	// 금액에 따른 구입 구분
	private static void buy(int money) {

		if (money >= 1000 && money <= 10000) {
			int getlotto = money / 1000;
			int charge = money - (getlotto * 1000);
			System.out.println(getlotto + "번 구매, 거스름돈 : " + charge);
			// 구매가능시 넘김. 로또번호 뽑기
			getNum(getlotto);
		} else {
			if (money > 10000) {
				System.out.println("입력금액 : " + money);
				System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
				System.out.println();
			}

			if (money < 1000) {
				System.out.println("입력금액 : " + money);
				System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
				System.out.println();
			}
		}
	}

	// 구입에 따른 로또 생성 수 받아 받아로또번호 출력 및 거스름돈
	private static void getNum(int getlotto) {
		HashSet<Integer> numSet = new HashSet<>();
		// 로또가 저장될
		int pcount = getlotto;
		int count = 1;
		while (pcount != 0) {
			while (numSet.size() < 6) {
				numSet.add((int) (Math.random() * (45 + 1 - 1) + 1));
			}

			// 로또번호를 넣음
			ArrayList<Integer> arrayNum = new ArrayList<>(numSet);
			Collections.sort(arrayNum);

			System.out.println(count + "번째 로또번호 : " + arrayNum);
			pcount--;
			count++;
			numSet.clear();
		}
		System.out.println();
	}

}
