package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Member> memList = new ArrayList<>();
		memList.add(new Member(1, "홍길동1", "01011111111"));
		memList.add(new Member(3, "삼길동3", "01033333333"));
		memList.add(new Member(6, "육길동6", "01066666666"));
		memList.add(new Member(4, "사길동4", "01044444444"));
		memList.add(new Member(2, "이길동2", "01022222222"));
		memList.add(new Member(5, "오길동5", "01055555555"));

		System.out.println("처음 데이터...");
		for (Member member : memList) {
			System.out.println(member);
		}
		System.out.println("---------------------------------------------");
		// 오류 기존 외부 정렬이 해당 리스트를 미지원 Collections.sort(memList);
		// 내부 정렬의 기준이 필요하다.

		// 외부 >> 내부 정렬 사용 (정렬 기준은 외부정렬시 이름을 name 사용함)
		Collections.sort(memList);

		// 외부 >> 내부 정렬 후 출력 (정렬 기준 name 오름차순)
		System.out.println("이름 기준 오름차순 정렬후 데이터...");
		for (Member member : memList) {
			System.out.println(member);
		}
		System.out.println("---------------------------------------------");

		// 내부정렬 기준은 하나의 기준만 가능하다.
		//
		//
		// 이에 따라 외부정렬 기준을 사용하여 다른 기준을 설정한다.
		//
		//
		// 외부정렬 사용 >> 재설정된 기준 (정렬 기준 num 내림차순)
		Collections.sort(memList, new SortNumDesc());
		System.out.println("회원 번호 기준 내림차순 정렬후 데이터...");
		for (Member member : memList) {
			System.out.println(member);
		}
		System.out.println("---------------------------------------------");
	}

}

// 사용자가 작성하는 클래스 내부에 정렬기준을 지정하려면 Comparable 인터페이스를 구현해서 작성해야 한다
// (내부정렬 기준을 넣어서 클래스를 작성한다)

// Comparable 인터페이스는 compareTo()메소드가 선언되어있다.
// 그래서 이 compareTo()메서드를 재정의 해서 정렬 기준을 지정한다

// 예) Member 클래스의 회원 이름을 기준으로 오름차순이 되도록하는 내부 정렬 기준 추가하기
// (Comparable인터페이스를 구현해서 작성한다.)
class Member implements Comparable<Member> {
	private int num;
	private String name;
	private String tel;

	// 자동생성 법 alt+ shift+ S
	// 위 작업표시줄의 Source 항목 활용

	// 소스 제너레이트 필드 활용
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	// 소스 제너레이트 겟터 세터 활용
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	// 소스 제너레이트 스트링 활용
	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	@Override
	public int compareTo(Member mem) {
		// 현재는 현재 데이터인 위의 this, 비교자는 바로 현 메소드의 mem
		// 기본의 정렬 기준은 오름차순 이름에 대한 오름차순으로 정렬 된다.
		return this.getName().compareTo(mem.getName());
	}
}

// Member 클래스의 회원번호(num)의 내림차순으로 정렬할 외부정렬 기준 클래스 작성
class SortNumDesc implements Comparator<Member> {

	@Override
	public int compare(Member mem1, Member mem2) {
		// 기존의 방법 데이터값인 num을 비교 하는 방법
		// if (mem1.getNum() > mem2.getNum()) {
		// return -1; // 기존의 값이 더크므로 안바꾸어도됨.
		// } else if (mem1.getNum() == mem2.getNum()) {
		// return 0; // 두값이 같으므로 안바뀜.
		// } else {
		// return 1; // 양수이면 순서가 바뀜
		// }

		// Wrapper클래스를 이용하는 방법1
		// 기본정렬이 오름차순이므로 내림차순 변경 차 -1 해준다
		// return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;

		// Wrapper클래스를 이용하는 방법2
		// 기본정렬이 오름차순이므로 내림차순 변경 차 -1 해준다
		return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
	}

}