package hotel.vo;

public class HotelRoomVO {

	private int room_no;
	private String room_type;
	private String guest_name;
	

	//생성자 get set
	public int getRoom_no() {
		return room_no;
	}
	public String getRoom_type() {
		return room_type;
	}
	public String getGuest_name() {
		return guest_name;
	}
	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public void setGuest_name(String guest_name) {
		this.guest_name = guest_name;
	}
}
