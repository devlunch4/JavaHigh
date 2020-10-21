package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ListTest2 {

	// 문제1) 5명의 사람이 이름을 입력받아서 ArrayList에 저장한 후에 이들중에서
	// '김'씨 성을 가진 사람의 이름을 모두 출력하시고
	// 입력은 Scanner 객체를 사용한다

	// 문제2) 5명의 별명을 입력 받아서 ArrayList에 저장한후에 이들중에서 별명의 길이가 제일 긴 별명을 출력하시오
	// 단 각 별명의 길이는 모두 다르게 입력한다.

	// 문제3) 문제2에서 별명의 길이가 같은 것이 여러개 있을 경우를 처리하시오
	// (즉, 제일 긴 별명 모두 출력한다.

	public static void main(String[] args) {

		// --------------------------------------------------
		System.out.println("=====문제1");
		// --------------------------------------------------
		// 문제1 김씨 이름 찾고 출력
		// 문제1 리스트
		ArrayList<String> list1 = new ArrayList<>();

		Scanner scan = new Scanner(System.in);
		String name = null;

		// 이름입력 메소드
		// for (int i = 0; i < 5; i++) {
		// System.out.println("이름을 입력해주세요");
		// name = scan.nextLine();
		// list1.add(name);
		// }

		list1.add("김1");
		list1.add("이1");
		list1.add("삼1");
		list1.add("사1");
		list1.add("김2");

		for (int i = 0; i < args.length; i++) {
			System.out.println((i + 1) + " 번째 입력 값 : " + list1.get(i));
		}
		// 입력자 전체 출력 및 확인
		System.out.println(list1);

		// 성김 매칭
		for (String str : list1) {
			if (str.indexOf("김") == 0) {
				System.out.println(str);
			}
		}

		// 모든 위치 김씨
		// for (String str : list1) {
		// if (str.contains("김") == true) {
		// System.out.println(str);
		// }
		// }
		// 모든 위치 김씨 위 향상된 포문 풀어쓰기
		// for (int i = 0; i < list1.size(); i++) {
		// if (list1.get(i).contains("김") == true) {
		// System.out.println("풀어쓰기");
		// System.out.println(list1.get(i));
		// }
		// }

		// --------------------------------------------------
		System.out.println("=====문제2");
		// --------------------------------------------------
		// 문제2 별명 입력 받고 별명 길이가 가장 긴 사람 출력
		// 문제2 리스트
		ArrayList<String> list2 = new ArrayList<>();
		String nick = null;

		// 별명 입력 메소드
		// for (int i = 0; i < 5; i++) {
		// System.out.println("별명을 입력해주세요");
		// nick = scan.nextLine();
		// list2.add(nick);
		// }

		list2.add("1");
		list2.add("12");
		list2.add("123");
		list2.add("1234");
		list2.add("12345");

		int maxIndex = 0;
		int maxLength = 0;

		for (int i = 0; i < list2.size(); i++) {
			if (list2.get(i).length() > maxLength)
				maxIndex = i;
			maxLength = list2.get(i).length();
		}
		System.out.println("긴 별명을 가진 이름의 인덱스 : " + maxIndex);
		System.out.println("긴 별명을 가진 이름 : " + list2.get(maxIndex));

		// --------------------------------------------------
		System.out.println("=====문제2!!!향상된 for문 사용");
		// --------------------------------------------------
		// 향상된 for문 길이 긴것 뽑기
		int maxIndex2 = 0;
		int maxLength2 = 0;
		for (String str : list2) {
			if (str.length() > maxLength2) {
				maxLength2 = str.length();
				maxIndex2++;
			}
		}
		// 처음에 무조건 인덱스번호가 1이 되기때문에 -1을 해준다
		System.out.println("긴 별명을 가진 이름의 인덱스 : " + (maxIndex2 - 1));
		System.out.println("긴 별명을 가진 이름 : " + list2.get(maxIndex2 - 1));

		// --------------------------------------------------
		System.out.println("=====문제3");
		// --------------------------------------------------
		// 문제3 별명 입력 받고 별명 길이가 가장 긴 사람 출력 동일할시 출력
		// 별명 동일갚 추가
		list2.add("67890");

		for (String samestr : list2) {
			if (samestr.length() == maxLength) {
				System.out.println("긴 별명 : " + samestr);
			}
		}

	}

}
