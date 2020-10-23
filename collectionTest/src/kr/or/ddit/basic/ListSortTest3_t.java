package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
문제) 학번(int), 이름(String), 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
     Student클래스를 만든다.
         이 Student클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로
         받아서 초기화 처리를 한다.
     
     - 이 Student객체는 List에 저장하여 관리한다.
     
     - List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고,
             총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는
             외부 정렬 기준 클래스를 작성하여 정렬된 결과를 출력하시오.
     
     (단, 등수는 List에 전체 데이터가 추가된 후에 저장되도록 한다.)
     

*/
public class ListSortTest3_t {
	
	// 등수를 구하는 메서드
	public void setRanking(List<Student_t> stdList) {
		for(Student_t std : stdList) {  // 기준 데이터를 구하기 위한 반복문
			int rank = 1;	// 처음에는 1등으로 설정해 놓고 시작한다.
			for(Student_t std2 : stdList) {  // 비교 대상을 나타내는 반복문
				
				// 기준보다 비교대상의 값이 크면 rank값을 증가 시킨다.
				if(std.getTot() < std2.getTot()) {
					rank++;
				}
			}
			
			// 구해진 등수를 Student객체의 rank변수에 저장한다.
			std.setRank(rank);
		}
	}
	

	public static void main(String[] args) {
		ListSortTest3_t stdTest = new ListSortTest3_t();
		
		List<Student_t> stdList = new ArrayList<>();
		
		stdList.add(new Student_t(1, "홍길동", 90, 95, 80));
		stdList.add(new Student_t(3, "성춘향", 90, 75, 70));
		stdList.add(new Student_t(7, "강감찬", 95, 95, 80));
		stdList.add(new Student_t(5, "변학도", 80, 95, 90));
		stdList.add(new Student_t(2, "일지매", 100, 85, 80));
		stdList.add(new Student_t(4, "이순신", 60, 65, 70));
		stdList.add(new Student_t(6, "이몽룡", 90, 100, 90));

		// 등수 구하는 메서드를 호출해서 등수를 구한다.
		stdTest.setRanking(stdList);
		
		System.out.println("정렬전...");
		for(Student_t std : stdList) {
			System.out.println(std);
		}
		System.out.println();
		
		// 학번의 오름차순 정렬
		Collections.sort(stdList);  // 내부 정렬 기준으로 정렬한다.
		
		System.out.println("학번의 오름차순 정렬후...");
		for(Student_t std : stdList) {
			System.out.println(std);
		}
		System.out.println();
		
		// 총점의 역순으로 정렬
		Collections.sort(stdList, new SortByTotal_t());  // 외부 정렬 기준으로 정렬한다.
		
		System.out.println("총점의 내림차순 정렬후...");
		for(Student_t std : stdList) {
			System.out.println(std);
		}
		System.out.println();
		
	}

}


/*
 * 학번(int), 이름(String), 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
     Student클래스를 만든다.
         이 Student클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로
         받아서 초기화 처리를 한다.
 */
//학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현
class Student_t implements Comparable<Student_t>{
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int tot;
	private int rank;
	
	public Student_t(int num, String name, int kor, int eng, int mat) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.tot = kor + eng + mat;
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

	public int getMat() {
		return mat;
	}

	public void setMat(int mat) {
		this.mat = mat;
		this.tot = kor + eng + mat;
	}

	public int getTot() {
		return tot;
	}

	public void setTot(int tot) {
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
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat + ", tot="
				+ tot + ", rank=" + rank + "]";
	}

	// 학번의 오름차순 정렬 기준
	@Override
	public int compareTo(Student_t std) {
		return Integer.compare(this.num, std.getNum());
	}

}


/*
총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는
             외부 정렬 기준 클래스를 작성
*/
class SortByTotal_t implements Comparator<Student_t>{

	@Override
	public int compare(Student_t std1, Student_t std2) {
		if(std1.getTot() == std2.getTot()) { // 총점이 같을 때
			return std1.getName().compareTo(std2.getName());
		}else {
			return Integer.compare(std1.getTot(), std2.getTot()) * -1;
		}
	}
	
}










