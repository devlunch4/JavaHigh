package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.BuildedSqlMapClient;

public class MemberDaoImpl implements IMemberDao {

	private SqlMapClient smc; // iBatis용 sqlMapClient객체 변수 선언

	// 1번
	private static MemberDaoImpl dao;

	// 2번
	// DAO의 생성자에서 ibatis환경 설정과 sqlMapClient객체를 생성한다.
	private MemberDaoImpl() {
		// Reader rd = null;
		// try {
		// // 1. iBatis의 환경 설정 파일 ()sqlMapConfig.xml)을 읽어와서 실행한다.
		// // Class LprodIbatisTest 복붙 //환경설정
		// // 1-1. 문자 인코딩 캐릭터 셋 설정하기
		// Charset charset = Charset.forName("UTF-8");
		// Resources.setCharset(charset);
		//
		// // 1-2. 환경 설정 파일을 읽어온다.
		// rd = Resources.getResourceAsReader("sqlMapConfig.xml");
		//
		// // 1-3. 위에서 읽어온 reader 객체를 이용하여 실제 환경 설정을 완성한후
		// /////// SQL문을 호출해서 실행할 수 있는 객체를 생성한다.
		// smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		//
		// // 환경설정 끝.~~~~~~~~~~
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// } finally {
		// if (rd != null) {
		// try {
		// rd.close(); // 객체 닫기
		// } catch (IOException e2) {
		// // TODO: handle exception
		// }
		// }
		// }

		smc = BuildedSqlMapClient.getSqlMapClient();
	}

	// 3번
	public static MemberDaoImpl getInstance() {
		if (dao == null)
			dao = new MemberDaoImpl();
		return dao;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		// Connection conn = null;
		// PreparedStatement pstmt = null;

		int cnt = 0; // 반환값이 저장될 변수 (작업성공 : 1, 실패 : 0)

		try {
			// conn = DBUtil3.getConnection();
			//
			// String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr) " +
			// " values (?, ?, ?, ?) ";
			//
			// pstmt = conn.prepareStatement(sql);
			// pstmt.setString(1, memVo.getMem_id());
			// pstmt.setString(2, memVo.getMem_name());
			// pstmt.setString(3, memVo.getMem_tel());
			// pstmt.setString(4, memVo.getMem_addr());
			//
			// cnt = pstmt.executeUpdate();

			Object obj = smc.insert("mymember.insertMember", memVo);
			if (obj == null) {
				cnt = 1;
				System.out.println("INSERT 성공~~xml");
			} else {
				System.out.println("INSERT 실패~~xml");
			}
			System.out.println("-------------------------------------");

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		// finally {
		// if (pstmt != null)
		// try {
		// pstmt.close();
		// } catch (SQLException e) {
		// }
		// if (conn != null)
		// try {
		// conn.close();
		// } catch (SQLException e) {
		// }
		// }

		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		// Connection conn = null;
		// PreparedStatement pstmt = null;
		int cnt2 = 0;
		try {
			// conn = DBUtil3.getConnection();
			//
			// String sql = "delete from mymember where mem_id = ? ";
			// pstmt = conn.prepareStatement(sql);
			// pstmt.setString(1, memId);
			//
			// cnt = pstmt.executeUpdate();

			//
			//

			// 1) sqlMapClient객체변수.delete("namespace값.id속성값", 파라미터 클래스);
			//////// 반환값 : 작업에 성공한 레코드 수
			cnt2 = smc.delete("mymember.deleteMember", memId);
			if (cnt2 > 0) {
				System.out.println("DELETE 성공~~xml");
			} else {
				System.out.println("DELETE 실패~~xml");
			}
			System.out.println("-------------------------------------");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// finally {
		// if (pstmt != null)
		// try {
		// pstmt.close();
		// } catch (SQLException e) {
		// }
		// if (conn != null)
		// try {
		// conn.close();
		// } catch (SQLException e) {
		// }
		// }

		return cnt2;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		// Connection conn = null;
		// PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			// conn = DBUtil3.getConnection();
			//
			// String sql = "update mymember set mem_name = ? , mem_tel = ?, " + "mem_addr =
			// ? where mem_id = ? ";
			// pstmt = conn.prepareStatement(sql);
			// pstmt.setString(1, memVo.getMem_name());
			// pstmt.setString(2, memVo.getMem_tel());
			// pstmt.setString(3, memVo.getMem_addr());
			// pstmt.setString(4, memVo.getMem_id());
			//
			// cnt = pstmt.executeUpdate();

			// 2) sqlMapClient객체변수.update("namespace값.id속성값", 파라미터클래스)
			// ///// ==> 반환값 : 작업에 성공한 레코드 수
			cnt = smc.update("mymember.updateMember", memVo);

			if (cnt > 0) {
				System.out.println("UPDATE 작업 성공~~xml");
			} else {
				System.out.println("UPDATE 작업 실패~~xml");
			}
			System.out.println("-------------------------------------");

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}

		// finally {
		// if (pstmt != null)
		// try {
		// pstmt.close();
		// } catch (SQLException e) {
		// }
		// if (conn != null)
		// try {
		// conn.close();
		// } catch (SQLException e) {
		// }
		// }

		return cnt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MemberVO> getAllMemberList() {
		// Connection conn = null;
		// Statement stmt = null;
		// ResultSet rs = null;
		//
		List<MemberVO> memList = null; // MemberVO객체가 저장될 List객체 변수 선언

		try {
			// conn = DBUtil3.getConnection();
			//
			// String sql = "select * from mymember";
			//
			// stmt = conn.createStatement();
			//
			// rs = stmt.executeQuery(sql);
			//
			// memList = new ArrayList<>(); // List객체 생성
			//
			// while (rs.next()) {
			// MemberVO memVo = new MemberVO(); // MemberVO객체 생성
			//
			// // ResultSet객체의 데이터를 가져와 MemberVO객체에 넣는다.
			// memVo.setMem_id(rs.getString("mem_id"));
			// memVo.setMem_name(rs.getString("mem_name"));
			// memVo.setMem_tel(rs.getString("mem_tel"));
			// memVo.setMem_addr(rs.getString("mem_addr"));
			//
			// memList.add(memVo); // List에 MemberVO객체 추가
			// }

			System.out.println();
			System.out.println("xml 활용 모두 출력하기");
			System.out.println();
			// 여러개 출력시 list - 단일 출력시 Object
			memList = smc.queryForList("mymember.getAllMember");

			for (MemberVO memVO : memList) {
				System.out.println("id : " + memVO.getMem_id());
				System.out.println("nm : " + memVO.getMem_name());
				System.out.println("tel : " + memVO.getMem_tel());
				System.out.println("addr : " + memVO.getMem_addr());
				System.out.println("--------------------------");
			}
			System.out.println("출력끝...");

		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		}

		// finally {
		// if (rs != null)
		// try {
		// rs.close();
		// } catch (SQLException e) {
		// }
		// if (stmt != null)
		// try {
		// stmt.close();
		// } catch (SQLException e) {
		// }
		// if (conn != null)
		// try {
		// conn.close();
		// } catch (SQLException e) {
		// }
		// }

		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		// Connection conn = null;
		// PreparedStatement pstmt = null;
		// ResultSet rs = null;

		int count = 0; // 회원ID의 개수가 저장될 변수
		try {
			// conn = DBUtil3.getConnection();
			//
			// String sql = "select count(*) cnt from mymember where mem_id = ?";
			//
			// pstmt = conn.prepareStatement(sql);
			// pstmt.setString(1, memId);
			//
			// rs = pstmt.executeQuery();
			//
			// if (rs.next()) {
			// count = rs.getInt("cnt");
			// }

			// 인자값 변환 casting
			count = (int) smc.queryForObject("mymember.getMemberCount", memId);

		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		}
		// finally {
		// if (rs != null)
		// try {
		// rs.close();
		// } catch (SQLException e) {
		// }
		// if (pstmt != null)
		// try {
		// pstmt.close();
		// } catch (SQLException e) {
		// }
		// if (conn != null)
		// try {
		// conn.close();
		// } catch (SQLException e) {
		// }
		// }

		return count;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// key값 ==> 회원ID(memId), 변경할컬럼명(field), 변경할데이터(data)
		// Connection conn = null;
		// PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			// conn = DBUtil3.getConnection();
			//
			// String sql = "update mymember set " + paramMap.get("field") + " = ? where
			// mem_id=? ";
			// pstmt = conn.prepareStatement(sql);
			// pstmt.setString(1, paramMap.get("data"));
			// pstmt.setString(2, paramMap.get("memId"));
			//
			// cnt = pstmt.executeUpdate();

			cnt = smc.update("mymember.updateMember2", paramMap);

		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		// finally {
		// if (pstmt != null)
		// try {
		// pstmt.close();
		// } catch (SQLException e) {
		// }
		// if (conn != null)
		// try {
		// conn.close();
		// } catch (SQLException e) {
		// }
		// }

		return cnt;
	}

}
