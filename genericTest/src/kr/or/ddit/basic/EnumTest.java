package kr.or.ddit.basic;

public class EnumTest {

	// enum(열거형) ==> 서로 관련있는 상수들의 집합을 나타낸다
	// - ==> 클래스 처럼 보이게 하는 상수
	// - ==> 열거형은 클래스처럼 독립된 java 파일에 만들수 있고,
	// -==== 하나의 java파일에 다른 클래스와 같이 만들수 있고.
	// -==== 클래스안에 내부 클래스처럼 만들수 있다.
	//
	//
	// - 열거형의 속성 및 메서드
	// name() ==> 열거형의 상수 이름을 문자열로 반환한다.
	// ordinal() ==> 거형 상수가 정의된 순서값(일종의 index 값)을 반환한다. (0부터 시작)
	// valueOf("열거형상수명") ==> 지정된 열거형에서 '열거형상수명'과 일치하는 상수를 반환한다.
	// 열거형 이름.상수명 ==> ㅍ미ㅕ덀("상수명")와 같다.
	//

	// - 열거형 선언하기
	// 방법 1)
	// ---enum 열거형 이름 {상수명1, 상수명2, ......}
	//
	// 방법 2)
	// ---enum 열거형 이름 {
	// 상수명1(값들...),
	// 상수명2(값들...),
	// ...
	// 상수명n(값들...);
	////////// '값들'이 저장될 변수들을 선언한다. (보통 private으로 선언한다.)
	// private 자료형 이름 변수명;
	// ...

	// 열거형의 생성자를 만든다.
	// 열거형의 생성자는 '열거형 상수'에 '값들'을 세팅하는 역할을 수행한다.
	// 열거형 생성자는 묵시적으로 private 이다.

	// '변수명'은 '값들'과 개수가 같, 각각의 '값들'과 자료형이 맞아야한다.
	// private 열거형 이름 (자료형 변수명, ....){
	// 위에 선언된 변수들의 초기화 작업...
	// }

	// 구성된 '값들'을 외부에서 불러올수 있는 getter메서드를 만든다.
	// (위에 선언된 변수들의 getter를 만든다.)

	// 1번째 방법
	public enum Color {
		RED, GREEN, BLUE
	}

	public enum Num {
		ONE, TWO, THREE
	}

	// 2번째 방법
	public enum Season {
		// 상수명(값들...) 형식의 선언
		봄(3, "5월"), 여름(6, "8월"), 가을(9, "11월"), 겨울(12, "2월");

		// 값들이 저장될 변수 선언
		private int startMonth;
		private String endMonth;

		// 생성자
		// private Season(int sMonth, String eMonth) 생성자와 같다.
		Season(int sMonth, String eMonth) {
			// 초기화
			startMonth = sMonth;
			endMonth = eMonth;
		}

		// get Method 작성
		// 외부 사용을 위해 public으로
		public int getStartMonth() {
			return startMonth;
		}

		// public void setStartMonth(int startMonth) {
		// this.startMonth = startMonth;
		// }

		public String getEndMonth() {
			return endMonth;
		}

		// public void setEndMonth(String endMonth) {
		// this.endMonth = endMonth;
		// }

	}

	public static void main(String[] args) {
		// System.out.println("RED" + ConstTest.RED);
		// System.out.println("Three" + ConstTest.THREE);
		//
		// if (ConstTest.RED == ConstTest.TWO) {
		// System.out.println("~~~~~");
		// } else {
		// System.out.println("@@@@@");
		// }

		// green은 상수 green을 뜻함
		// 방법1
		Color mycol = Color.valueOf("GREEN");
		// 방법2
		Color mycol2 = Color.GREEN;
		
		Num myNum = Num.ONE;

		System.out.println("mycol : " + mycol.name());
		System.out.println("myNum : " + myNum.name());
		System.out.println("mycol2 : "+ mycol2.name());

		// ordinal은 입력된 값의 순서로 자동으로 번호확인
		System.out.println("mycol의 ordianl : " + mycol.ordinal());
		System.out.println("mynum의 ordianl : " + myNum.ordinal());

		// //서로 다른 종류의 열거형 끼리의 비교는 불가하다.
		// if(Color.BLUE == Num.THREE) {
		// System.out.println("~~~");
		// }
		System.out.println();
		System.out.println("if check");
		if (mycol == Color.RED) {
			System.out.println("같다");
		} else {
			System.out.println("다르다");
		}

		switch (mycol) {
		case RED:
			// case 문 옆에 지정한 '상수명'은 '열거형.상수명'에서 '열거형'이름을 뺴고 작성한다
			// ex) Color.RED ==> RED (mycol이 Color 형 상수이기 떄문에)
			System.out.println("RED");
			break;
		case BLUE:
			System.out.println("BLUE");
			break;
		case GREEN:
			System.out.println("GREEN");
			break;

		default:
			break;
		}

		System.out.println("-----------------------");

		// Season ss = Season.valueOf("봄");
		// 또는
		Season ss = Season.가을;
		System.out.println("name : " + ss.name());
		System.out.println("ordinal : " + ss.ordinal());
		System.out.println("startMonth : " + ss.getStartMonth());
		System.out.println("endMonth : " + ss.getEndMonth());

		// 열거형명.values() ==> 열거형에 있는 모든 상수들을 배열로 가져온다.
		for (Season time : Season.values()) {
			System.out.println(time.name() + " == " + time + " ==> " + time.getStartMonth() + " 월부터 "
					+ time.getEndMonth() + " 까지");
		}
		System.out.println();

		for (Color col : Color.values()) {
			System.out.println(col + " ==> " + col.ordinal());
		}

	}

}
/////////////////////////
///
///
///
///
///
///
///
//////////////
