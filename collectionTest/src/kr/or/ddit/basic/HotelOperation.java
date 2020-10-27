package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelOperation {

	static HashMap<Integer, Room> hmap = new HashMap<>();
	static Scanner scan = new Scanner(System.in);

	public void makeroom() {
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
		System.out.println("*********************************************");
		System.out.println("		호텔문을 열었습니다. 어서오십시요. ;D");
		System.out.println("*********************************************");
	}

	public static void main(String[] args) {
		HotelOperation hotel = new HotelOperation();
		hotel.makeroom();
		hotel.display();

	}

	private int display() {
		System.out.println("-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.println("선택>>>");

		int input = scan.nextInt();

		switch (input) {
		case 1:
			checkin();
			break;
		case 2:// 체크인
			break;
		case 3:// 체크인
			break;
		case 0:// 체크인
			break;

		default:
			System.out.println("잘못된 입력 입니다.");
			System.out.println();
			break;
		}
		return display();

	}

	private void checkin() {
		System.out.println("----------------------------------------------");
		System.out.println("체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println("* 201~209 : 싱글룸");
		System.out.println("* 301~309 : 더블룸");
		System.out.println("* 401~409 : 스위트룸");
		System.out.println("----------------------------------------------");

		System.out.println("방 번호 입력 >>>");
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
