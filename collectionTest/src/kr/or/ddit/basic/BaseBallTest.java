package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class BaseBallTest {

	// 문제) Set과 list를 이용하여 숫자 야구 게임 프로그램을 작성하시오
	// 컴퓨터의 숫자는 난수를 이용하여 구한다
	// (스트라이크는 S, 볼은 B로 나타낸다.)
	//
	//
	// 예시) 1부터 9까지
	// 컴퓨터 난수 ==> 9,5,7
	// 실행예시)숫자 입력 ==> 3 5 8
	// 3 5 8===========> 1S 0B
	// 실행예시)숫자 입력 ==> 7 8 9
	// 7 8 9===========> 0S 2B
	// 실행예시)숫자 입력 ==> 9 5 7
	// 9 5 7===========> 3S 0B
	//
	// 축하합니다 당신은 4번째만에 맞췄습니다.

	// 난수 Set DONE
	// 순서 설정 파악 Set 난수 데이터를 설정 순서대로 List에 넣기
	// 입력 데이터 List 넣기
	// 맞는 지 확인후 출력

	// 기능을 메소드 별로 생성해보기

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 난수 담을 set 생성
		Set<Integer> ranSet = new HashSet<>();
		// 난수 만들기 호출
		while (ranSet.size() < 3) {
			int rnum = (int) (Math.random() * 9 + 1);
			ranSet.add(rnum);
		}
		System.out.println("생성된 난수 : " + ranSet);

		// 난수담을 list 생성
		List<Integer> ranList = new ArrayList<>(ranSet);
		Collections.shuffle(ranList);
		System.out.println("셔플 난수 리스트 : " + ranList);

		List<Integer> dranList = new ArrayList<>(ranList);
		System.out.println("셔플 된 난수 리스트 : " + dranList);

		// 입력값 담을 리스트 생성

		int count = 0;
		int strike = 0;
		int ball = 0;
		while (strike != 3) {

			List<Integer> inputList = new ArrayList<Integer>();

			System.out.println("첫번째 숫자를 입력해주세요 > ");
			int input1 = sc.nextInt();

			System.out.println("두번째 숫자를 입력해주세요 > ");
			int input2 = sc.nextInt();
			System.out.println("세번째 숫자를 입력해주세요 > ");
			int input3 = sc.nextInt();

			inputList.add(input1);
			inputList.add(input2);
			inputList.add(input3);

			System.out.println("입력된 수 리스트" + inputList);

			strike = 0;
			ball = 0;
			for (int i = 0; i < ranList.size(); i++) {
				for (int j = 0; j < inputList.size(); j++) {
					if (dranList.get(i) == inputList.get(j) && i == j) {
						strike++;
					}
					if (dranList.get(i) == inputList.get(j) && i != j) {
						ball++;
					}
				}

			}
			count++;
			System.out.println("결과 : S-" + strike + " B-" + ball);
			inputList.clear();
			strike = 0;
			ball = 0;
		}
		System.out.println(count + " 만에 성공!");

	}

}
