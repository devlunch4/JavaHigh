package singleton_teach;

public class SingletonTest_teach {

	public static void main(String[] args) {
		//MySingleton test1 = new MySingleton();  // 외부에서 new 명령으로 생성 불가
		
		MySingleton_teach test2 = MySingleton_teach.getInstance();
		MySingleton_teach test3 = MySingleton_teach.getInstance();
		
		System.out.println("test2 = " + test2.toString());
		System.out.println("test3 = " + test3);
		System.out.println();
		
		System.out.println(test2 == test3);
		System.out.println(test2.equals(test3));
		
		test2.displayTest();
		

	}

}
