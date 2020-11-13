package basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtill;

public class JdbcTest06 {

	// 회원을 관리하는 프로그램 작성하기
	// (db시스템의 mymember 테이블 이용)

	//////////////////////////////////////////////////////////////
	// -처리조건
	// 1.아래 메뉴의 기능을 모두 구현한다.(CRUD 구현하기)
	// 3.'자료추가'에서는 입력한 회원 ID가 중복되는지 여부를 검사해서 중복되면 다시 입력 되도록 ㅎ헌다..
	// 2.'자료 수정'이나 회원id를 입력받아 삭제한다.
	// 4.자료 수정은 회원 ID 를 제외한 전체로 자료수정한다.

	// 메뉴예시)

	// --작업성택
	// 1.자료추가 insert C
	// 2.자료삭제 delete D
	// 3.제목수정 i[date U
	// 4.전체 자료 출력 select R
	// 0.작업끝
	// -------
	// 작업번호 >>

	//////////////////////////////////////////////////////////
	// 생성자 생성
	Scanner scan = new Scanner(System.in);
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		new JdbcTest06().display();
	}

	// 디스플레이
	// 현 클래스의 문제점.
	// display 내에서 계속 display를 호충하므로 메모리에 계속 누적된다.
	private int display() {
		System.out.println("프로그램 작업선택");
		System.out.println("-----------------");
		System.out.println("1.자료 추가");
		System.out.println("2.자료 삭제");
		System.out.println("3.자료 수정");
		System.out.println("4.전체자료 출력");
		System.out.println("0.작업끝");
		System.out.println("-----------------");
		System.out.println("수행할 작업번호를 입력해주세요>>>>>>");
		int input = scan.nextInt();
		switch (input) {
		case 1:// 차료 추가
			dataAdd();
			return display();
		case 2:
			dataDelete();
			return display();
		case 3:
			dataUpdate();
			return display();
		case 4:
			printAll();
			return display();
		case 0:
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
			break;
		default:
			System.out.println("잘못된 입력입니다.원하는 작업 번호를 입력해주세요");
			System.out.println();
			return display();
		}
		return display();
	}

	// 전체 출력
	private void printAll() {
		System.out.println("전체 출력을 시작합니다.");
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println("mem_id\t mem_name\t mem_tel\t mem_addr");
		System.out.println("---------------------------------------------------------");
		try {
			// 외부 클래스 사용 접속-연결자 수행
			conn = DBUtill.getConnection();
			String sqlAll = "SELECT * FROM mymember ORDER BY mem_id";

			pstmt = conn.prepareStatement(sqlAll);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String memid = rs.getString("mem_id");
				String memname = rs.getString("mem_name");
				String memtel = rs.getString("mem_tel");
				String memaddr = rs.getString("mem_addr");

				System.out.println(memid + "\t\t" + memname + "\t\t" + memtel + "\t\t" + memaddr);

			}
		} catch (SQLException e) {

		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			// if (stmt != null)
			// try {
			// stmt.close();
			// } catch (SQLException e) {
			// }
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

	}

	// 데이터 수정
	private void dataUpdate() {
		try {
			// 외부 클래스 사용 접속-연결자 수행
			conn = DBUtill.getConnection();

			// mem_id 값의 중복 유무 확인 후 데이터 값 입력
			String memid;
			int count = 0;
			System.out.print("수정할 회원 번호 입력_ (유무확인)>>>");
			memid = scan.next();
			// 카운트가 되면 있는 것이므로 입력 등록 불가. 반복하게 설정
			String sqlchkid = "SELECT COUNT(*) cnt FROM mymember WHERE mem_id = ?";
			pstmt = conn.prepareStatement(sqlchkid);
			pstmt.setString(1, memid);

			// SELECT문 조회 데이터 저장.
			rs = pstmt.executeQuery();

			// 반복문 true false 지정자
			if (rs.next()) {
				count = rs.getInt("cnt");
			}

			if (count == 0) {
				System.out.println("입력한 mem_id " + memid + "는 존재하지 않습니다.");
				System.out.println("초기화면으로 돌아갑니다.");
				System.out.println();
			} else if (count == 1) {
				System.out.print("회원 성명 수정값 입력>>>");
				String memname = scan.next();

				System.out.print("회원 전화 수정값 입력>>>");
				String memtel = scan.next();

				System.out.print("회원 주소 수정값 입력>>>");
				String memaddr = scan.next();

				String slrinsertmem = "UPDATE mymember SET mem_name = ?, mem_tel = ?, mem_addr = ? WHERE mem_id = ?";

				pstmt = conn.prepareStatement(slrinsertmem);

				pstmt.setString(1, memname);
				pstmt.setString(2, memtel);
				pstmt.setString(3, memaddr);
				pstmt.setString(4, memid);

				// System.out.println("입력 값 확인 : " + memid + "/" + memname + "/" + memtel + "/"
				// + memaddr);

				int cnt = pstmt.executeUpdate();

				if (cnt > 0) {
					System.out.println("DB 수정 성공!!!");
					System.out.println();
				}
				// else {
				// System.out.println("DB 수정 실패~~~");
				// System.out.println();
				// }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			// if (stmt != null)
			// try {
			// stmt.close();
			// } catch (SQLException e) {
			// }
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
	}

	// 자료 삭제
	private void dataDelete() {
		try {
			// 외브 클래스 사용 접속-연결자 수행
			conn = DBUtill.getConnection();

			// mem_id 값의 중복 유무 확인 후 데이터 값 입력
			String memid;
			int count = 0;

			// 카운트가 되면 있는 것이므로 입력 등록 불가. 반복하게 설정
			System.out.print("삭제할 회원 번호 입력_>>>");
			memid = scan.next();

			String sqlchkid = "SELECT COUNT(*) cnt FROM mymember WHERE mem_id = ?";
			pstmt = conn.prepareStatement(sqlchkid);
			pstmt.setString(1, memid);
			// SELECT문 조회 데이터 저장.
			rs = pstmt.executeQuery();
			// 반복문 true false 지정자
			if (rs.next()) {
				count = rs.getInt("cnt");
			}
			if (count == 0) {
				System.out.println("입력한 mem_id " + memid + "는 없습니다.");
				System.out.println();
			} // 카운트 이프 마감
			else if (count > 0) {
				String slrinsertmem = "DELETE FROM mymember WHERE mem_id = ?";

				pstmt = conn.prepareStatement(slrinsertmem);
				pstmt.setString(1, memid);

				int cnt = pstmt.executeUpdate();

				if (cnt > 0) {
					System.out.println("해당 DB 삭제 성공!!!");
					System.out.println();
				}
				// else {
				// System.out.println("해당 DB 삭제 실패~~~");
				// System.out.println();
				// }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			// if (stmt != null)
			// try {
			// stmt.close();
			// } catch (SQLException e) {
			// }
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
	}

	// 자료 추가
	private void dataAdd() {
		try {// 외부 클래스 사용 접속-연결자 수행
			conn = DBUtill.getConnection();

			// mem_id 값의 중복 유무 확인 후 데이터 값 입력

			String memid;
			int count = 0;

			// do-while문 시작
			do {
				System.out.print("추가할 회원 번호 입력_ (중복확인)>>>");
				memid = scan.next();
				// 카운트가 되면 있는 것이므로 입력 등록 불가. 반복하게 설정
				String sqlchkid = "SELECT COUNT(*) cnt FROM mymember WHERE mem_id = ?";
				pstmt = conn.prepareStatement(sqlchkid);
				pstmt.setString(1, memid);

				// SELECT문 조회 데이터 저장.
				rs = pstmt.executeQuery();

				// 반복문 true false 지정자
				if (rs.next()) {
					count = rs.getInt("cnt");
				}
				if (count > 0) {
					System.out.println("입력한 mem_id " + memid + "는 이미 등록 된 값입니다.");
					System.out.println("다시 입력하세요.");
					System.out.println();
				} // 카운트 이프 마감
					// rs.next 이프 마감
			} while (count > 0);
			// while 조건이 아니라면 다음 단계 진행

			System.out.print("추가 되는 회원 성명 입력>>>");
			String memname = scan.next();

			System.out.print("추가 되는 회원 전화 입력>>>");
			String memtel = scan.next();

			System.out.print("추가 되는 회원 주소 입력>>>");
			String memaddr = scan.next();

			String slrinsertmem = "INSERT INTO mymember (mem_id, mem_name, mem_tel, mem_addr) VALUES (?, ?, ?, ?)";

			pstmt = conn.prepareStatement(slrinsertmem);

			pstmt.setString(1, memid);
			pstmt.setString(2, memname);
			pstmt.setString(3, memtel);
			pstmt.setString(4, memaddr);
			//
			System.out.println("입력 값 확인 : " + memid + "/" + memname + "/" + memtel + "/" + memaddr);
			//
			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("DB 추가 성공!!!");
				System.out.println();
			}
			// else {
			// System.out.println("DB 추가 실패~~~");
			// System.out.println();
			// }

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			// if (stmt != null)
			// try {
			// stmt.close();
			// } catch (SQLException e) {
			// }
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
	}
}
