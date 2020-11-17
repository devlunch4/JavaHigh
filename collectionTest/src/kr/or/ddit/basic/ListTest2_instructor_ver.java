package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class ListTest2_instructor_ver {

	// 문제1) 5명의 사람이 이름을 입력받아서 ArrayList에 저장한 후에 이들중에서
	// '김'씨 성을 가진 사람의 이름을 모두 출력하시고
	// 입력은 Scanner 객체를 사용한다

	// 문제2) 5명의 별명을 입력 받아서 ArrayList에 저장한후에 이들중에서 별명의 길이가 제일 긴 별명을 출력하시오
	// 단 각 별명의 길이는 모두 다르게 입력한다.

	// 문제3) 문제2에서 별명의 길이가 같은 것이 여러개 있을 경우를 처리하시오
	// (즉, 제일 긴 별명 모두 출력한다.

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("========문제1");
		ArrayList<String> nameList = new ArrayList<>();

		System.out.println("5명의 이름을 입력하세요");

		for (int i = 1; i <= 5; i++) {
			System.out.println(i + "번째 사람이름 : ");
			String name = scan.next();
			nameList.add(name);
		}

		System.out.println();

		System.out.println("김씨 성을 가진 사람들...");
		for (int i = 0; i < nameList.size(); i++) {
			// 방법1
			// 홍길도 첫글자(성) 가져오기 첫글자 0 홍 부터 ~ 1 길 이전 = 홍
			if (nameList.get(i).substring(0, 1).equals("김")) {
				System.out.println("방법1 : " + nameList.get(i));
			}

			// 방법2
			if (nameList.get(i).charAt(0) == '김') {
				System.out.println("방법2 : " + nameList.get(i));
			}

			// 방법 3
			if (nameList.get(i).indexOf("김") == 0) {
				System.out.println("방법3 : " + nameList.get(i));
			}

			// 방법 4
			if (nameList.get(i).startsWith("김") == true) {
				System.out.println("방법4 : " + nameList.get(i));
			}
		} // for문 마감
			//
			// 1번 문제 완료!!!!!!!!!!
			//

		System.out.println("========문제2");
		ArrayList<String> aliasList = new ArrayList<>();

		System.out.println("5명의 별명을 입력하세요");

		for (int i = 1; i <= 5; i++) {
			System.out.println(i + "번째 별명 : ");
			String alias = scan.next();
			aliasList.add(alias);
		}

		System.out.println();

		// 제일 긴 별명이 저장될 변수 선언 ==> List의 첫번째 데이터로 초기화 한다.

		String maxAlias = aliasList.get(0);

		// 처음에 0 번째 부터 시작이므로 i 는 1 부터 시작
		for (int i = 1; i < aliasList.size(); i++) {
			if (maxAlias.length() < aliasList.get(i).length()) {
				maxAlias = aliasList.get(i);
			}
		}

		System.out.println("긴 별명 : " + maxAlias);
		System.out.println();
		//
		// 문제2 완료!!!!!!!!!!
		//

		System.out.println("========문제3");
		// 제일 긴 별명의 글자수가 저장될 변수를 선언하고
		// List의 첫번째 데이터의 글자 수로 초기화 한다.

		int maxLength = aliasList.get(0).length();

		for (int i = 1; i < aliasList.size(); i++) {
			if (maxLength < aliasList.get(i).length()) {
				maxLength = aliasList.get(i).length();
			}
		}

		System.out.println("제일 긴 별명들...");
		for (int i = 0; i < aliasList.size(); i++) {
			if (aliasList.get(i).length() == maxLength) {
				System.out.println(aliasList.get(i));
			}

		}
		//
		// 3번 문제 완료 !!!!!!!!!!
		//

	}// main method 마감
}// class 마감
