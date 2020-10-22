package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ListSortTest3 {

	// 문제1) 학번(int), 이름(String), 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
	// Student 클래스를 만든다
	// 이 Student 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로
	// 받아서 초기화 처리를 한다.
	//
	// 이 Student객체는 List에 저장하여 관리한다.
	//
	// - 이 Student객체는 List에 저장하여 관리한다
	//
	// - List에 저장된 데이터들을 학번의 오름차순으로 정렬할수 있는 내부 정렬 기준을 구현하고,
	// 종점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는
	// 외부정렬 기준 클래스를 작성하여 정렬로딘 결과를 출력하시오
	//
	// (단, 등수는 List에 전체 데이터가 추가된 후에 저장되도록 한다.

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 학생 리스트 생성
		ArrayList<Student> studentList = new ArrayList<>();

		studentList.add(new Student(5, "오길동5", 100, 100, 100));// , 0, 0));
		studentList.add(new Student(1, "홍길동1", 100, 100, 100));// , 0, 0));
		studentList.add(new Student(4, "사길동4", 40, 40, 40));// , 0, 0));
		studentList.add(new Student(2, "이길동2", 20, 20, 20));// , 0, 0));
		studentList.add(new Student(6, "육길동6", 10, 10, 10));// , 0, 0));
		studentList.add(new Student(3, "삼길동3", 30, 30, 30));// , 0, 0));

		System.out.println("처음데이터 출력...");
		for (Student std : studentList) {
			System.out.println(std);
		}
		System.out.println("---------------------------------------------");

		//
		//
		//
		// 학번에 대하여 내부 정렬 사용
		Collections.sort(studentList);

		System.out.println("학번 기준 오름차순 정렬...[내부기준활용]");
		for (Student std : studentList) {
			System.out.println(std);
		}

		System.out.println("---------------------------------------------");

		//
		//
		//
		// 총점 값 설정
		for (Student student : studentList) {
			int totalscore = (student.getEscore() + student.getKscore() + student.getMscore());
			student.setTotalscore(totalscore);
		}

		// 총점에 대하여 외부 정렬 사용
		Collections.sort(studentList, new sortTotalDesc());
		System.out.println("총점 기준 내림차순 정렬...[외부기준활용]");
		for (Student std : studentList) {
			System.out.println(std);
		}
		System.out.println("---------------------------------------------");

		//
		//
		//
		// 랭크 설정 >> 총점을 기준으로 랭크 설정 및 랭크 설정후 정렬 후 출력
		int rcount = 1;
		for (int i = 0; i < studentList.size(); i++) {
			//랭크 값 설정
			int chk = studentList.get(i).getTotalscore();
			studentList.get(i).setRank(rcount);
			rcount++;
			for (int j = 0; j < studentList.size(); j++) {
				//해당 랭크가 같은경우 다음 랭크값 지정을 
				if (chk < studentList.get(j).getTotalscore()) {
					studentList.get(i).setRank(rcount);
					rcount++;
				}
			}
			rcount = 1;
		}

		System.out.println("랭크 기준 오름차순 정렬.... [메인메소드내 구현] ");
		for (Student std : studentList) {
			System.out.println(std);
		}
	}
}

class Student implements Comparable<Student> {
	private int stnum;
	private String stname;
	private int kscore;
	private int escore;
	private int mscore;
	private int totalscore;
	private int rank;

	// 각 인자 설정 int rank; 는 삭제 상태
	// Student 리스트 설정을 위한 제네레이션 설정 메소드
	// 제너레이션 필드
	public Student(int stnum, String stname, int kscore, int escore, int mscore) { // , int totalscore, int rank) {
		super();
		this.stnum = stnum;
		this.stname = stname;
		this.kscore = kscore;
		this.escore = escore;
		this.mscore = mscore;
		this.totalscore = totalscore;
		this.rank = rank;
	}

	// 내부정렬 설정
	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		return Integer.toString(this.getStnum()).compareTo(Integer.toString(o.getStnum()));
		// 또는 return Integer.compare(this.getStnum(), o.getStnum());
	}

	// 게서 세터
	public int getStnum() {
		return stnum;
	}

	public void setStnum(int stnum) {
		this.stnum = stnum;
	}

	public String getStname() {
		return stname;
	}

	public void setStname(String stname) {
		this.stname = stname;
	}

	public int getKscore() {
		return kscore;
	}

	public void setKscore(int kscore) {
		this.kscore = kscore;
	}

	public int getEscore() {
		return escore;
	}

	public void setEscore(int escore) {
		this.escore = escore;
	}

	public int getMscore() {
		return mscore;
	}

	public void setMscore(int mscore) {
		this.mscore = mscore;
	}

	public int getTotalscore() {
		return totalscore;
	}

	public void setTotalscore(int totalscore) {
		this.totalscore = totalscore;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	// 제너레이터 스트링
	@Override
	public String toString() {
		return "Student [stnum=" + stnum + ", stname=" + stname + ", kscore=" + kscore + ", escore=" + escore
				+ ", mscore=" + mscore + ", totalscore=" + totalscore + ", rank=" + rank + "]";
	}
}

// 외부 정렬을 위한 클래스
class sortTotalDesc implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		return Integer.compare(o1.getTotalscore(), o2.getTotalscore()) * -1;
	}
}
