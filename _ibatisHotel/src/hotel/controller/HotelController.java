package hotel.controller;

import java.util.List;
import java.util.Scanner;

import hotel.service.HotelServise;
import hotel.service.IHotelServise;
import hotel.vo.HotelRoomVO;

public class HotelController {
	private IHotelServise hotelServise;
	private Scanner scan = new Scanner(System.in);

	public HotelController() {
		hotelServise = HotelServise.getInstance();

	}

	public static void main(String[] args) {
		new HotelController().hotelStart();
	}

	private int hotelStart() {
		while (true) {
			int choice = displayMenu();
			switch (choice) {
			case 1:
				checkIn(); // 체크인
				break;
			case 2:
				checkOut(); // 체크아웃
				break;
			case 3:
				allRoom(); // 객실확인
				break;
			case 4: // 프로그램 종료
				System.out.println();
				System.out.println("프로그램을 종료합니다.");
				return 0;
			default:
				System.out.println("잘못 선택했습니다. 다시 입력하세요.");
				System.out.println();
			}
		}
	}

	private void allRoom() {
		List<HotelRoomVO> hroomList = hotelServise.allRoom();
		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println(" 방번호      방타입      이름");
		System.out.println("--------------------------------------");
		if (hroomList == null || hroomList.size() == 0) {
			System.out.println(" 회원 정보가 하나도 없습니다.");
		} else {
			for (HotelRoomVO hRoomVO : hroomList) {
				int roomNo = hRoomVO.getRoom_no();
				String roomType = hRoomVO.getRoom_type();
				String guestName = hRoomVO.getGuest_name();
				System.out.println(roomNo + "\t" + roomType + "\t" + guestName);
			}
		}
		System.out.println("--------------------------------------");
	}

	private void checkOut() {
		System.out.println();
		System.out.println("체크아웃 할 방번호를 입력해주세요");
		int roomNo = scan.nextInt();

		HotelRoomVO VO = new HotelRoomVO();
		VO.setRoom_no(roomNo);
		int cnt = hotelServise.checkOut(VO);

	}

	private void checkIn() {
		System.out.println();
		System.out.println("체크인 할 방번호를 입력해주세요");
		int roomNo = scan.nextInt();

		int count = (int) hotelServise.checkRoom(roomNo);

		if (count >= 1) {
			System.out.println();
			System.out.println("빈방입니다.");
			System.out.println("투숙객 이름을 입력해 주세요");
			String guestName = scan.next();

			HotelRoomVO VO = new HotelRoomVO();
			VO.setGuest_name(guestName);
			VO.setRoom_no(roomNo);
			int cnt = hotelServise.checkIn(VO);

		} else if (count == 0) {
			System.out.println("투숙중이거나 존재하지 않는 방입니다.");
		}

	}

	private int displayMenu() {
		System.out.println();
		System.out.println("---------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("---------------");
		System.out.print("작업선택>>> ");
		while (!scan.hasNextInt()) {
			scan.nextInt();// 잘못된 입력 초기화
			System.out.println("잘못된 입력. 해당 작업번호를 입력해주세요.");
		}
		int num = scan.nextInt();
		return num;
	}

}
