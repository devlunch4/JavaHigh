package singletion;

public class SingletonTest {

	public static void main(String[] args) {
		// MySingleton test1 = new MySingleton();

		// 오류 MySingleton test1 = new MySingleton();
		// 오류의 이유 생성자가 private 이기떄문에 public으로 변경하면 오류가 사라진다
		// 외부에서 new 명령으로 생성 불가

		// 싱글톤 패턴을 사용했으므로 test2과 3는 값은 메소드를 호출한 것이다.
		MySingleton test2 = MySingleton.getInstance();
		MySingleton test3 = MySingleton.getInstance();

		System.out.println("test2 호출 : " + test2);
		System.out.println();
		System.out.println("test3 호출 : " + test3);

		// 출력을 해보면 해당 해시 값이 값으므로 같은 메소드를 호출한 것이다.
		System.out.println();
		System.out.print("test2 == test3 ? :  ");
		System.out.println(test2 == test3);
		System.out.println("test2.equals(test3); :  " + test2.equals(test3));
		System.out.println();

		System.out.println("test2 출력");
		test2.displayTest();
		
		//싱글톤 사용으로 메모리방지, 인스턴스 한개 보증, 성능향상을 예로 들수 있다.
	}

}
