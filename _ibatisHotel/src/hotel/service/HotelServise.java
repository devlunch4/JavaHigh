package hotel.service;

import java.util.List;

import hotel.dao.HotelDao;
import hotel.dao.IHotelDao;
import hotel.vo.HotelRoomVO;

public class HotelServise implements IHotelServise {
	private IHotelDao dao;
	private static HotelServise service;

	private HotelServise() {
		dao = HotelDao.getInstance();
	}

	public static HotelServise getInstance() {
		if (service == null) {
			service = new HotelServise();
		}
		return service;
	}

	@Override
	public int checkIn(HotelRoomVO HVo) {
		// TODO Auto-generated method stub
		return dao.checkIn(HVo);
	}

	@Override
	public int checkOut(HotelRoomVO HVo) {
		// TODO Auto-generated method stub
		return dao.checkOut(HVo);
	}

	@Override
	public List<HotelRoomVO> allRoom() {

		return dao.allRoom();
	}

	@Override
	public int checkRoom(int room_no) {

		return dao.checkRoom(room_no);
	}

}
