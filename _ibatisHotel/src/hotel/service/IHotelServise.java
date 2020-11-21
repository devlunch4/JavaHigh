package hotel.service;

import java.util.List;

import hotel.vo.HotelRoomVO;

public interface IHotelServise {

	/**
	 * 체크인을 위한 방번호와 방타입 고객명을 입력 받아 DB에 UPDATE
	 * 
	 * @param guestName 방번호/룸타입/고객명
	 * @return 수정성공 : 1, 실패 : 0
	 */
	public int checkIn(HotelRoomVO HVo);

	/**
	 * 체크 아웃을 위한 방번호을 입력 받아 고객명을 빈방으로 DB UPDATE
	 * 
	 * @param roomNo 방번호
	 * @return @return 수정성공 : 1, 실패 : 0
	 */
	public int checkOut(HotelRoomVO HVo);

	/**
	 * 호텔 SELECT 문으로 해당 테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * 
	 * @return HotelRoomVO 객체를 담고있는 List 객체
	 */
	public List<HotelRoomVO> allRoom();

	/**
	 * 방번호 입력받아 해당 guest_name 값이 '-'인지
	 * 
	 * @param room_no
	 * @return 게시글번호에 맞는 자료가 있으면 리턴
	 */
	public int checkRoom(int room_no);
}
