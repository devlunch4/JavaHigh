package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import member.vo.MemberVO;
import util.DBUtil3;
import util.DBUtil3_t;

/////////////////////////////////////
//////////// JdbcTest06_t버전을 활용 함.
/////////////////////////////////////
public class MemberDaoImpl implements IMemberDao {

	@Override
	public int insertMember(MemberVO memVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수 (성공 :1 실패 :0)
		try {
			conn = DBUtil3.getConnection();

			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr) " + " values (?, ?, ?, ?) ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVO.getMem_id());
			pstmt.setString(2, memVO.getMem_name());
			pstmt.setString(3, memVO.getMem_tel());
			pstmt.setString(4, memVO.getMem_addr());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
			}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수 (성공 :1 실패 :0)

		try {
			conn = DBUtil3.getConnection();

			String sql = "delete from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
			}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수 (성공 :1 실패 :0)

		try {
			conn = DBUtil3.getConnection();

			String sql = "update mymember set mem_name = ? , mem_tel = ?, " + "mem_addr = ? where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_name());
			pstmt.setString(2, memVo.getMem_tel());
			pstmt.setString(3, memVo.getMem_addr());
			pstmt.setString(4, memVo.getMem_id());

			cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("update 작업 성공~~~");
			} else {
				System.out.println("수정 작업 실패!!!");
			}

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		Connection conn = null;
		// PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<MemberVO> memList = null; // MemberVo객체가 저장될 List객체 변수 선언

		try {

			conn = DBUtil3_t.getConnection();

			String sql = "select * from mymember";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			memList = new ArrayList<>();
			while (rs.next()) {
				MemberVO memVo = new MemberVO();

				// ResultSet객체의 데이터를 가져와 MemberVO객체에 넣는다.

				memVo.setMem_id(rs.getString("mem_id"));
				memVo.setMem_name(rs.getString("mem_name"));
				memVo.setMem_tel(rs.getString("mem_tel"));
				memVo.setMem_addr(rs.getString("mem_addr"));

				memList.add(memVo); // List에 MemberVO 객체 추가

			}

		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int cnt = 0; // 회원ID의 개수가 저장될 변수
		try {
			conn = DBUtil3.getConnection();

			String sql = "select count(*) cnt from mymember where mem_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

		return cnt;
	}

	@Override
	public int updateName(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
	//	ResultSet rs = null;
		int cnt = 0; // 회원ID의 개수가 저장될 변수
		try {
			conn = DBUtil3.getConnection();

			String sql = "update mymember set mem_name = ? where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_name());
			pstmt.setString(2, memVo.getMem_id());

			cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("이름 update 작업 성공~~~");
			} else {
				System.out.println("수정 작업 실패!!!");
			}

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return cnt;
	}

}
