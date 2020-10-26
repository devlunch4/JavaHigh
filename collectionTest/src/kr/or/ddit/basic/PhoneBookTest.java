package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class PhoneBookTest {
	//
	// 문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고
	// Map을 이용해서 전화번호 정보를 관리하는 프로그램을 작성하시오.
	// 이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체 출력하는 기능이 있다.
	//
	// (Map의 구조는 key값으로 '이름'을 사용하고, value값으로는 'Phone클래스의 인스턴스'로 한다.)
	//
	// - 삭제, 검색기능은 '이름'을 입력받아 처리한다.
	//
	// -------------------------------------------------
	//
	// 실행예시)
	// ---------------------
	// 다음 메뉴를 선택하세요.
	// 1. 전화번호 등록
	// 2. 전화번호 수정
	// 3. 전화번호 삭제
	// 4. 전화번호 검색
	// 5. 전화번호 전체 출력
	// 0. 프로그램 종료
	// ---------------------
	// 번호입력 >> 1
	//
	// 새롭게 등록할 전화번호 정보를 입력하세요.
	// 이름 >> 홍길동
	// 전화번호 >> 010-1234-5678
	// 주소 >> 대전시 중구 대흥동
	//
	// '홍길동' 전화번호 등록 완료!!
	//
	// ---------------------
	// 다음 메뉴를 선택하세요.
	// 1. 전화번호 등록
	// 2. 전화번호 수정
	// 3. 전화번호 삭제
	// 4. 전화번호 검색
	// 5. 전화번호 전체 출력
	// 0. 프로그램 종료
	// ---------------------
	// 번호입력 >> 1
	//
	// 새롭게 등록할 전화번호 정보를 입력하세요.
	// 이름 >> 홍길동
	//
	// '홍길동'은 이미 등록된 사람입니다.
	//
	// ---------------------
	// 다음 메뉴를 선택하세요.
	// 1. 전화번호 등록
	// 2. 전화번호 수정
	// 3. 전화번호 삭제
	// 4. 전화번호 검색
	// 5. 전화번호 전체 출력
	// 0. 프로그램 종료
	// ---------------------
	// 번호입력 >> 5
	//
	// --------------------------------------------
	// 번호 이름 전화번호 주소
	// --------------------------------------------
	// 1 홍길동 010-1234-5678 대전시 중구 대흥동
	// ~~~~
	// ~~~~
	// --------------------------------------------
	// 출력 완료...
	//
	// ---------------------
	// 다음 메뉴를 선택하세요.
	// 1. 전화번호 등록
	// 2. 전화번호 수정
	// 3. 전화번호 삭제
	// 4. 전화번호 검색
	// 5. 전화번호 전체 출력
	// 0. 프로그램 종료
	// ---------------------
	// 번호입력 >> 0
	//
	// 프로그램을 종료합니다..

	static HashMap<String, Phone> map = new HashMap<>();
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		start();
	}

	// 시작 구동
	public static int start() {
		System.out.println();
		System.out.println("--------------------");
		System.out.println("다음 메뉴를 선택하세요.");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("0. 프로그램 종료");
		System.out.println("--------------------");
		System.out.println("번호입력 >>>");
		int input = scan.nextInt();

		switch (input) {
		case 1: // 등록
			insert();
			break;
		case 2: // 수정
			update();
			break;
		case 3: // 삭제
			delete();
			break;
		case 4:// 검색
			serach();
			break;
		case 5:// 출력
			printAll();
			break;
		case 0:// 종료
			System.out.println("프로그램 종료됩니다.");
			System.exit(0);
			break;
		default:// 기본값
			break;
		}
		return start();
	}

	// 모두 출력
	private static void printAll() {
		System.out.println();
		System.out.println("------------------------------");
		System.out.println("번호\t이름\t주소\t전화번호");
		System.out.println("------------------------------");

		if (map.size() == 0) {
			System.out.println("등록된 정보가 없습니다.");
			System.out.println("------------------------------");
		} else {
			int count = 0;
			Iterator<String> keyIt = map.keySet().iterator();
			while (keyIt.hasNext()) {
				count++;
				String key = keyIt.next(); // key값 구하기
				Phone p = map.get(key); // key값에 해당하는 value값(Phone객체) 구하기
				System.out.println(count + "\t" + p.getName() + "\t" + p.getAddr() + "\t" + p.getTel());
			
			}
System.out.println("------------------------------");
		}
	}

	private static void serach() {
		System.out.println();
		System.out.println("검색할 전화번호의  이름정보를 입력");
		System.out.print("이름>>>");
		String name = scan.next();

		// 존재 확인
		if (!map.containsKey(name)) {
			System.out.println(name + " 전화번호 정보는 없습니다");
			return;
		}

		// 입력 정보 가져오기
		Phone p = map.get(name);

		System.out.println(name + " 의 전화번호 정보");
		System.out.println("====================");
		System.out.println("이름 : " + p.getName());
		System.out.println("주소 : " + p.getAddr());
		System.out.println("전화번호 : " + p.getTel());
		System.out.println("====================");
		System.out.println("검색정보 출력 완료");
	}

	// 삭제
	private static void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호의 이름 정보를 입력");
		System.out.print("이름>>>");
		String name = scan.next();

		// 존재 확인
		if (!map.containsKey(name)) {
			System.out.println(name + "전화번호 정보는 없습니다.");
			return;
		}

		map.remove(name);

		System.out.println("삭제완료!!!");
	}

	// 수정
	private static void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요...");
		System.out.print("이 름 >> ");
		String name = scan.next();

		// 존재 확인
		if (!map.containsKey(name)) {
			System.out.println(name + "의 정보가 없습니다.");
			return;
		}

		System.out.print("수정되는 주소>>>");
		String addr = scan.next();

		System.out.print("수정되는 전화번호 >>>");
		String tel = scan.next();

		map.put(name, new Phone(name, addr, tel));
		System.out.println("수정완료!! 수정 값 : " + map);

	}

	// 등록
	private static void insert() {
		System.out.println();
		System.out.println("--------------------");
		System.out.println("이름 입력>>>");
		
		
		String name = scan.next();
		
		if (map.containsKey(name)) {
			System.out.println(name + "의 정보가 이미 있습니다!!!!!!");
			return;
		}
		
		System.out.println("--------------------");
		System.out.println("주소 입력>>>");
		String addr = scan.next();
		System.out.println("--------------------");
		System.out.println("전화 입력>>>");
		String tel = scan.next();

		map.put(name, new Phone(name, addr, tel));
		
		System.out.println("=입력 값=====");
		System.out.println("이름 : " + map.get(name).getName());
		System.out.println("주소 : "+ map.get(name).getAddr());
		System.out.println("전화 : " + map.get(name).getTel());
		System.out.println("=입력완료!!==");
	}
}

// 전화번호가 저장되는 클래스
class Phone {
	String name;
	String addr;
	String tel;

	public Phone(String name, String addr, String tel) {
		super();
		this.name = name;
		this.addr = addr;
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
