package basic;

public class DocTest {
	// javaDOC 확인
	@SuppressWarnings("null")
	public static void main(String[] args) {
		javaDocTestInf test = null;
		// 해당 test 인터페이스 호출후 인터페이스 내의 메소드 호출
		 test.methodAdd(1, 2);

	}
}
