package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SeltTest {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		// List 와 Set 의 차이점

		// 1. List
		// - 데이터의 순서(index)가 있다
		// - 중복되는 데이터를 저장할수 있다.
		//
		// 2. Set
		// - 데이터의 순서(index)가 없다.
		// - 중복되는 데이터를 저장할수 없다
		//

		HashSet hs1 = new HashSet<>();

		// Set에 데이터를 추가할 떄 add()메서드를 사용한다.
		// add()메서드의 반환값 : 추가성공(true), 추가실패(false)
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(1);
		hs1.add("CC");
		hs1.add(3);
		hs1.add("BB");
		hs1.add(2);
		// hs1.add("EE");

		System.out.println("Set data : " + hs1);
		System.out.println("데이터의 개수 : " + hs1.size());
		System.out.println();

		// set에 중복되는 데이터를 추가하면 false를 반환하고 데이터는 추가되지 않는다.
		boolean isAdd = hs1.add("FF");
		System.out.println("추가 값이 중복되지 않을때 : " + isAdd);
		System.out.println("Set data : " + hs1);
		System.out.println();

		isAdd = hs1.add("FF");
		System.out.println("추가 값이 중복 될 때 : " + isAdd);
		System.out.println("Set data : " + hs1);
		System.out.println();

		// Set의 데이터를 수정하려면 수정하는 명렬이 따로 없기 떄문에
		// 수정하려는 데이터를 삭제한 후에 새로운 데이터를 추가하는 방법을 사용한다

		// 삭제하는 메서드 : remove(삭제할 데이터)
		// 반환값 : 삭제성공(true), 삭제실패(false)
		// 전체 자료 삭제 메서드 : clear()

		// "FF"데이터를 "EE"로 변경하기 수정이라기보다 삭제후 추가 개념.
		hs1.remove("FF");
		System.out.println("삭제 후 Set 데이터 : " + hs1);
		hs1.add("EE");
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();

		// hs1.clear();
		// System.out.println("전체 삭제 후 Set 데이터 : " + hs1);
		// System.out.println();

		// Set 데이터는 순서(Index)가 없기 떄문에 List처럼 index로 데이터를 하나씩 불러올수 없다.
		// 그래서 데이터를 하나씩 얻기 위해서는 Iterator형의 객체로 변환해야 한다.

		// -Set 형의 데이터들을 Iterator형 객체로 변환해 주는 메서드 ==> iterator()

		Iterator it = hs1.iterator();

		// Iterator의 hasNext()메서드
		// - ==> Iterator의 포인터가 가리키는 곳의 다음번째 자리에 데이터가 있으면 true, 없으면 false를 반환한다.

		// iterator의 next()메서드
		// - ==> Iterator의 포인터를 다음번째 위치로 이동한 후 그 곳의 데이터를 반환한다.

		while (it.hasNext()) {
			System.out.println(it.next());
		}

		// ------------------------------------------------------
		// 향상된 for 문을 사용하면 Iterator를 사용하지 않고 처리가능
		System.out.println("향상된 for문 사용--------");
		for (Object object : hs1) {
			System.out.println(object);
		}
		System.out.println();
		// ------------------------------------------------------

		// 우리반 학생들중 번호를 이용하여 추첨하는 프로그램 작성하기
		// 번호1번부터 25번까지 있고, 추첨할 인원은 3명이다
		// (int) (Math.random() * (최대값 - 최소값 + 1 ) + 최소값);
		// 당첨자를 출력하시오

		HashSet<Integer> testSet = new HashSet<>();

		// Set 이기 때문에 중복되는 수가 입력되지 않는다
		while (testSet.size() < 3) {
			testSet.add((int) (Math.random() * (25 - 1 + 1) + 1));
		}
		System.out.println("당첨자 번호 : " + testSet);

		// 최소값~ 최대값 사이의 정수형 난수 만들기
		// (int) (Math.random() * (최대값 - 최소값 + 1 ) + 최소값);

		// Set 유형의 자료를 List형으로 변환하기 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		ArrayList<Integer> testList = new ArrayList<>(testSet);
		System.out.println("List 데이터 출력...");
		for (int i = 0; i < testList.size(); i++) {
			System.out.println(testList.get(i));
		}
		for (Integer integer : testList) {
			System.out.println("향상된 for문 사용 : " + integer);
		}
	}
}
