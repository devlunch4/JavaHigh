package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class HotelOperation {

	static HashMap<Integer, Room> hmap = new HashMap<>();
	static Scanner scan = new Scanner(System.in);

	public void makeroom() {
		// 타입별 방 생성
		for (int i = 2; i <= 4; i++) {
			String type = null;
			switch (i) {
			case 2:
				type = "싱글룸";
				break;
			case 3:
				type = "더블룸";
				break;
			case 4:
				type = "스위트룸";
				break;
			}
			for (int j = 1; j <= 9; j++) {
				int num = i * 100 + j;
				Room room = new Room(num, type, null);
				hmap.put(num, room);
			}
		}
		// 방생성 완료 호텔 개장
		System.out.println("*********************************************");
		System.out.println("		호텔문을 열었습니다. 어서오십시요. ;D");
		System.out.println("*********************************************");
	}

	//메인 메소드
	public static void main(String[] args) {
		HotelOperation hotel = new HotelOperation();
		hotel.makeroom();
		hotel.display();

	}

	//화면 표시
	private int display() {
		System.out.println("-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.println("선택>>>");

		int input = scan.nextInt();

		switch (input) {
		case 1: // 체크인
			checkin();
			break;
		case 2:// 체크아웃
			checkout();
			break;
		case 3:// 객실상태 출력
			status();
			break;
		case 4:// 업무종료
			System.out.println("업무를 종료합니다.");
			System.exit(0);
			break;

		default:
			System.out.println("잘못된 입력 입니다.");
			System.out.println();
			break;
		}
		return display();

	}

	//상태 출력
	private void status() {
		// 리스트 설정 ==> 맵정보를 리스트로 전환 생성
		List<Integer> roomList = new ArrayList<>(hmap.keySet());
		// 리스트 정렬
		Collections.sort(roomList);

		System.out.println("----------------------------------------------");
		System.out.println(" 현재 객실 상태");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호\t\t방 종류\t\t투숙객 이름");
		System.out.println("--------------------------------------");
		// 리스트 출력
		for (int roomNum : roomList) {
			Room r = hmap.get(roomNum);
			System.out.print(r.getNum() + "\t\t" + r.getType() + "\t\t");
			// 투숙객 존재 유무 판별
			String name = " - ";
			if (r.getCustomer() != null) {
				name = r.getCustomer();
			}
			System.out.println(name);
		}
		System.out.println("--------------------------------------");
		System.out.println();
	}

	//체크아웃
	private void checkout() {
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃 작업");
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃할 방 번호 입력 >>>");
		int num = scan.nextInt();
		if (!hmap.containsKey(num)) {
			System.out.println(num + " 호 객실은 존재하지 않습니다.");
			System.out.println();
			return;
		} else if (hmap.get(num).getCustomer() == null) {
			System.out.println("체크인 중이 아닙니다.");
			System.out.println();
			return;
		} else {
			System.out.println(hmap.get(num).getNum() + "호 " + hmap.get(num).getCustomer() + " 님");
			hmap.get(num).setCustomer(null);
		}
		System.out.println("체크아웃 되었습니다.");
		System.out.println();

	}

	//체크인
	private void checkin() {
		System.out.println("----------------------------------------------");
		System.out.println("체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println("* 201~209 : 싱글룸");
		System.out.println("* 301~309 : 더블룸");
		System.out.println("* 401~409 : 스위트룸");
		System.out.println("----------------------------------------------");

		System.out.println("체크인할 방 번호 입력 >>>");
		int num = scan.nextInt();

		// 입력 오류 값 확인하기
		if (!hmap.containsKey(num)) {
			System.out.println(num + " 호 객실은 존재하지 않습니다.");
			System.out.println();
			return;
		} else if (hmap.get(num).getCustomer() != null) {
			System.out.println("이미 투숙 중인 방입니다");
			System.out.println();
			return;
		} else {
			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.println("이름 입력 >>>");
			// 버퍼 초기화
			scan.nextLine();

			// 체크인 지정
			String customer = scan.nextLine();
			hmap.get(num).setCustomer(customer);
		}

		// 체크인 확인 및 출력
		System.out.println(hmap.get(num));
		System.out.println("체크인이 완료되었습니다.");
		System.out.println("----------------------------------------------");
		System.out.println();

	}
}
///
///
///

class Room {
	int num;
	String type;
	String customer;

	public Room(int num, String type, String customer) {
		super();
		this.num = num;
		this.type = type;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Room [num=" + num + ", type=" + type + ", customer=" + customer + "]";
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

}
