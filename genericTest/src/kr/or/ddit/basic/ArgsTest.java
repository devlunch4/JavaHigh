package kr.or.ddit.basic;

public class ArgsTest {

	// 가변형 인수 ==> 메서드의 매개변수에 주어지는 인수의 개수가 실행될 떄마다 다를 떄 사용한다
	// - -가변형 인수는 메서드 내부에서는 배열로 처리한다.
	// - -가변형 인수는 한가지 자료형만 사용할수 있다. 

	public static void main(String[] args) {
		// main(String args[]) :
		// main()메소드는 모든 자바 애플리케이션 프로그램에 꼭 들어가야 하는 문장이며,
		// 캄파일하여 실행시킬 경우 자바 런타임 인터프리터가 제일 먼저 이 main()메소드를 찾아
		// 실행을 하는 곳입니다.
		//
		// main() 메소드는 "String args[]"라는 한 개의 파라미터값을 갖도록 정의되어 있습니다.
		// args는 실행시의 클래스에 전달되는 커맨드 라인 인자로서
		// String의 배열형으로 정의함으로서 여러개의 문자열을 받아들일 수 있습니다.

		ArgsTest test = new ArgsTest();

		int[] nums = { 100, 200, 300 };

		System.out.println(test.sumDate(nums));
		// 에러 //System.out.println(test.sumDate({1,2,3,4,5}));
		System.out.println(test.sumDate(new int[] { 1, 2, 3, 4, 5 }));
		System.out.println("------------------------------------------");

		System.out.println(test.sumArgs(100, 200, 300));
		System.out.println(test.sumArgs(1, 2, 3, 4, 5));
		System.out.println(test.sumArgs2("이순신",1,2,3,4,5));

		// int k = 100;
		// test.test1(k);
		// test.test1(300);

	}

	// public void test1(int k) {
	// // TODO Auto-generated method stub
	//
	// }

	// 매개 변수들의 합을 계산해서 반환하는 매서드

	// 배열을 매개변수로 주고싶다.
	// 매개변수들의 합을 계산해서 반환하는 메서드 ==> 배열을 이용한 메서드
	public int sumDate(int[] data) {
		int sum = 0;

		// 향상된 for 문
		// for (int i : data) {
		// sum = sum + data[i];
		// }

		for (int i = 0; i < data.length; i++) {
			// sum += data[i];
			sum = sum + data[i];
		}
		return sum;
	}

	// 매개변수들의 합을 계산해서 반환하는 메서드 ==> 가변형 인수를 이용한 매서드

	public int sumArgs(int... data) {
		// 이 메소드 안에서 변수 'int...data'는 'int[] data'와 같다. 즉 배열과 같다.
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			// sum += data[i];
			sum = sum + data[i];
		}
		return sum;
	}

	// 가변형 인수와 일반 인수를 같이 사용할 경우에는 가변형 인수를 뒤쪽에 배치한다.
	// 에러 //public String subArg2(String name, int... data, float...ttt) { << 가변형은
	// 한번만 사용 가능
	// public String subArg2(int... data, int name) { << 뒤의 int name이 오지만 앞선 것의 배열
	// 값의 수의 변수로 불가
	// ( 지정 값 , 가변형 인수 )정상적인 것은 처음 정정된것 이후 다음 것을 계산하기는 쉽다
	public String sumArgs2(String name, int... data) {
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			// sum += data[i];
			sum = sum + data[i];
		}

		return name + "씨의 합계 : " + sum;
	}

}
