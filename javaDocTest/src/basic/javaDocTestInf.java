package basic;

// javaDoc 파일 만들기 예제
//아래내용은 HTML 형식개념으로 작성한다.

/**
 * 
 * @author PC-20
 * @version 1.0
 *
 *          <p>
 *          파일명 : javaDocTestInf.java<br>
 *          설명 : javaDoc 문서 작성을 위한 연습용 Interface<br>
 *          <br>
 *          수정이력 <br>
 *          --------------------<br>
 *          수정 일자 : 2020-11-11<br>
 *          작성자 : PC-20<br>
 *          수정 내용 : 최초 생성<br>
 *          <br>
 *          </p>
 */
public interface javaDocTestInf {

	/**
	 * 메서드명 : methodTest<br>
	 * 설명 : 반환 값이 없는 메서드
	 * 
	 * @param a
	 *            첫번째 매개변수 (정수형)
	 * @param b
	 *            두번째 매개변수 (정수형)
	 */
	public void methodTest(int a, int b);

	/**
	 * 메서드명 : methodAdd<br>
	 * 설명 : 반환 값이 없는 메서드
	 * 
	 * @param x
	 *            정수형 첫번째 매개변수
	 * @param y
	 *            정수형 두번째 매개변수
	 * @return 처리된 결과가 정수형으로 반환된다.
	 */
	public int methodAdd(int x, int y);

	/**
	 * 메서드명 : methodSub<br>
	 * 설명 : 반환 값이 없는 메서드
	 * 
	 * @return 정수형으로 반환된다.
	 */
	public int methodSub();

}
