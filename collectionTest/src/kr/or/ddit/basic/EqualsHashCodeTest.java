package kr.or.ddit.basic;

import java.util.HashSet;

public class EqualsHashCodeTest {

	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setId(1);
		p1.setName("홍길동1");

		Person p2 = new Person();
		p2.setId(1);
		p2.setName("홍길동1");

		// Person p2 = new Person();
		// p2.setId(2);
		// p2.setName("홍길동2");

		Person p3 = p1;

		// 내부 재 정의 이전
		System.out.println("p1 == p2 (번지가 다름) : " + (p1 == p2));
		System.out.println("p1.equals(p2) : " + p1.equals(p2));
		System.out.println("p1의 값 : " + p1);
		System.out.println("p2의 값 : " + p2);
		System.out.println("p3의 값 : " + p3);
		System.out.println("p1.getClass() == p2.getClass() : " + (p1.getClass() == p2.getClass()));
		System.out.println("p1.getClass().isInstance(p2) : " + p1.getClass().isInstance(p2));
		System.out.println("p1 == p3 (번지가 다르지만 같은 번지를 부름): " + (p1 == p3));

		// 재 정의 메소드 주석 해제해보고 해보기
		System.out.println("재정의 메소드 활성에 따라 다름 p1.equals(p2) : " + p1.equals(p2));
		//

		System.out.println("-----------------------------------------");
		// restart instructor

		
		
		//
		System.out.println("---HashSet 재정의 후 출력 >> 이전의 출력도 변경됨. '재 정의'주석 확인하기");
		HashSet<Person> testSet = new HashSet<>();

		testSet.add(p1);
		testSet.add(p2);

		// 같은 값이 입력되었다고 생각되지만
		// set의 입장에서 둘은 다르다.
		// hashSet에선 해시코드로 가져온다.
		System.out.println("testSet의 크기 : " + testSet.size());

		System.out.println("p1의 해시값 : " + p1.hashCode());
		System.out.println("p2의 해시값 : " + p2.hashCode());
		System.out.println("p3의 해시값 : " + p3.hashCode());

		// - equals() 메서드 ==> 두 객체의 내용이 같은지 검사
		// - hashCode() 메서드 ==> 두 객체의 동일성을 검사

		// -HashSet, Hashtable, HashMap과 같이 Hash로 시작하는 컬렉션 객체들은
		// 객체의 으미상의 동일성을 비교하기 위해서 hashCode() 매서드를 호출하여 비교한다.
		// 그러므로 ,객체가 같은지 여부를 결정하려면 hashCode() 메서드를 재정의 해야한다.
		//
		// - hashCode() 메서드에서 ㅏ쇼용하는 '해싱 알고리즘'은 서로 다른 객체들에 대해서
		// 같은 hashCode를 나타낼수 있다.

	}

}

class Person {
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// 해시코드에대한 재 정의
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	// 해시코드에 의한 오브젝트 재 정의
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	// //재 정의 equals 메소드 수기 입력 위의 다른 메소드는 자동생성.
	// @Override
	// public boolean equals(Object obj) {
	// if (obj == null)
	// return false;
	//
	// if (this.getClass() != obj.getClass()) // 같은 유형의 클래스인지 검사
	// return false;
	//
	// if (this == obj) // 참조값(주소값)이 같은지 검사
	// return true;
	//
	// Person myOBJ = (Person) obj;
	// if (this.name == null && myOBJ.name != null) {
	// return false;
	// }
	//
	// if (this.id == myOBJ.id && this.name == myOBJ.name) {
	// return true;
	// }
	//
	// if (this.id == myOBJ.id && this.name.equals(myOBJ.name)) {
	// return true;
	// }
	// return false;
	// }

}