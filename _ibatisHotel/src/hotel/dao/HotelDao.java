package hotel.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import hotel.vo.HotelRoomVO;
import util.BuildedSqlMapClient;

public class HotelDao implements IHotelDao {

	private SqlMapClient smc; // iBatis용 sqlMapClient객체 변수 선언

	// 1번
	private static HotelDao dao;

	// 2번
	// DAO의 생성자에서 ibatis환경 설정과 sqlMapClient객체를 생성한다.
	private HotelDao() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}

	// 3번
	public static HotelDao getInstance() {
		if (dao == null) {
			dao = new HotelDao();
		}
		return dao;
	}

	@Override
	public int checkIn(HotelRoomVO HVo) {

		int cnt = 0; // 반환값이 저장될 변수 (작업성공 : 1, 실패 : 0)

		try {
			cnt = smc.update("ihotel.updateCheckIn", HVo);

			System.out.println("체크인 UPDATE 작업 성공~~xml");
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public int checkOut(String roomNo) {
		int cnt = 0; // 반환값이 저장될 변수 (작업성공 : 1, 실패 : 0)

		try {
			cnt = smc.update("ihotel.updateCheckOut", roomNo);

			System.out.println("체크인 UPDATE 작업 성공~~xml");
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public List<HotelRoomVO> allRoom() {
		List<HotelRoomVO> hotelRooms = null;
		try {
			hotelRooms = smc.queryForList("ihotel.allRoom");
		} catch (SQLException e) {
			hotelRooms = null;
			e.printStackTrace();
		}
		return hotelRooms;
	}

	@Override
	public int getRoom(int room_no) {
		int hotelRoomVo;

		try {
			hotelRoomVo =  (int) smc.queryForObject("ihotel.checkRoom", room_no);

		} catch (SQLException e) {
			hotelRoomVo = 0;
			e.printStackTrace();
		}

		return hotelRoomVo;
	}

}
