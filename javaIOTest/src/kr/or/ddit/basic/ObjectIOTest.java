package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectIOTest {

	// 객체를 파일에 저장하는 예제
	public static void main(String[] args) {
		// Member의 인스턴스 생성
		Member mem1 = new Member("홍길동1", 1, "대전1");
		Member mem2 = new Member("홍길동2", 2, "대전2");
		Member mem3 = new Member("홍길동3", 3, "대전3");
		Member mem4 = new Member("홍길동4", 4, "대전4");
		Member mem5 = new Member("홍길동5", 5, "대전5");

		try {
			// 객체 생성
			// 기반
			FileOutputStream fout = new FileOutputStream("d:/d_other/memObj.bin");
			// 기반을 받는 보조
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			// 기반 보조를 받는 보조
			ObjectOutputStream oos = new ObjectOutputStream(bout);

			// 출력(쓰기)작업

			System.out.println("객체 저장 시작>>>");
			oos.writeObject(mem1);
			oos.writeObject(mem2);
			oos.writeObject(mem3);
			oos.writeObject(mem4);
			oos.writeObject(mem5);
			System.out.println("객체 저장 완료!!!");
			System.out.println();

			oos.close();
			// -------------------------------------------

			// 저장된 객체를 읽어와 화면에 출력하기

			// 입력용 스트림 객체 생성 ((감싸고(감싸고(마지막)))
			ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream("d:/d_other/memObj.bin")));

			// 읽어온 객체를 저장할 변수
			Object obj;

			try {
				System.out.println("객체읽기 작업시작");
				System.out.println(">----------------------");
				// readObject()메서드가 데이터의 끝까지 다읽어오면 EOFException 을 발생
				while ((obj = ois.readObject()) != null) {
					// 읽어온 데이터를 원래의 객체형으로 형변환후 사용한다

					Member mem = (Member) obj;
					System.out.println("이름 : " + mem.getName());
					System.out.println("나이 : " + mem.getAge());
					System.out.println("주소 : " + mem.getAddr());
					System.out.println("-----------------------");
				}
				// 여기에 close 해도 while 에서 되지 않음 ==> EOFException 캐치문으로 이동함.
				// 파이널을 활용하여 close 처리함.
			} catch (EOFException e) {
				System.out.println("객체읽기 작업종료");
				System.out.println("<----------------------");
			} catch (ClassNotFoundException e) {// ois.readObject()) 오류 해결
				e.printStackTrace();
			} finally {
				ois.close();
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}

// 저장용 클래스 (직렬화 클래스)
class Member implements Serializable {
	// member의 노란줄 생성시 해당 클래스의 값의 비교 즉 신뢰성을 위해 이클립스에서 해당 값을 지정
	private static final long serialVersionUID = -6485635176671241944L;
	private String name;
	// transient ==> 직렬화가 되지 않을 변수에 지정.
	// ============> 직렬화가 되지 않는 멤버변수는 기본값으로 저장된다.
	// ============> 기본값(참조형변수:null, 숫차형변수 : 0, 논리형변수 : false
	// 직렬화 제외를 위해 제외할 변수 앞에 transient 추가하여 지정
	// private int age;
	transient private int age;
	// private String addr;
	transient private String addr;

	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}