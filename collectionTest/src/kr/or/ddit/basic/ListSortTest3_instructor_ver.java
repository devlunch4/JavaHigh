package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListSortTest3_instructor_ver {

	// 등수를 구하는 메서드
	public void setRanking(List<Studenti> stdList) {
		for (Studenti std : stdList) {
			{ // 기준 데이터를 구하기 위한 반복문
				int rank = 1; // 처음에는 1등으로 설정해 놓고 시작한다.
				for (Studenti std2 : stdList) { // 비교 대상을 나타내는 반복문
					// 기준보다 비교대상의 값이 크면 rank값을 증가 시킨다.
					if (std.getTot() < std2.getTot()) {
						rank++;
					}
				}
				std.setRank(rank);
			}
		}
	}

	public static void main(String[] args) {
		ListSortTest3_instructor_ver stdTest = new ListSortTest3_instructor_ver();

		List<Studenti> stdList = new ArrayList<>();

		stdList.add(new Studenti(5, "오길동5", 100, 10, 100));
		stdList.add(new Studenti(1, "홍길동1", 100, 100, 100));
		stdList.add(new Studenti(4, "사길동4", 40, 40, 40));
		stdList.add(new Studenti(2, "이길동2", 20, 20, 20));
		stdList.add(new Studenti(6, "육길동6", 10, 100, 10));
		stdList.add(new Studenti(3, "삼길동3", 30, 30, 30));

		System.out.println("정렬전...");
		for (Studenti std : stdList) {
			System.out.println(std);
		}
		System.out.println();

		// 등수 정렬 호출
		stdTest.setRanking(stdList);

		System.out.println("등수 정렬후...");
		for (Studenti std : stdList) {
			System.out.println(std);
		}
		System.out.println();
		
		
		// 학번의 오름차순 정렬
		Collections.sort(stdList); // 내부정렬 기준으로 정렬

		System.out.println("학번오름차순 정렬 후...");
		for (Studenti std : stdList) {
			System.out.println(std);
		}
		System.out.println();
		//
		
		//총점의 역순으로 정렬
		Collections.sort(stdList, new SortByTotal()); // 외부정을으로 기준 정렬
		System.out.println("총점 내림차순 정렬 후...");
		for (Studenti std : stdList) {
			System.out.println(std);
		}
		System.out.println();

	}

}

// 내부정렬 Comparable 제너럴은 해당 클래스로 설정
class Studenti implements Comparable<Studenti> {
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int tot;
	private int rank;

	public Studenti(int num, String name, int kor, int eng, int mat) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
	}

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

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
		this.tot = kor + eng + mat;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
		this.tot = kor + eng + mat;
	}

	public int getmat() {
		return mat;
	}

	public void setmat(int mat) {
		this.mat = mat;
		this.tot = kor + eng + mat;
	}

	public int getTot() {
		this.tot = kor + eng + mat;
		return tot;
		
	}

	public void setTot(int tot) {
		this.tot = kor + eng + mat;
		this.tot = tot;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Studenti [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat + ", tot="
				+ tot + ", rank=" + rank + "]";
	}

	// 학번의 오름차순 정렬 기준 [내부정렬]
	@Override
	public int compareTo(Studenti std) {
		return Integer.compare(this.num, std.num);
	}

}

class SortByTotal implements Comparator<Studenti> {

	@Override
	public int compare(Studenti std1, Studenti std2) {
		if (std1.getTot() == std2.getTot()) {// 총점이 같을때
			return std1.getName().compareTo(std2.getName());
		} else {
			return Integer.compare(std1.getTot(), std2.getTot()) * -1;
		}
	}

}
