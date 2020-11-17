package jdbcBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtil;

public class jdbcBoard {

	// 생성자 생성
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Scanner scan = new Scanner(System.in);

	// 메인 메소드
	public static void main(String[] args) {
		System.out.println("조회수는 미구현");
		jdbcBoard board = new jdbcBoard();
		board.start();
	}

//프로그램 시작
	public void start() {

		// do-while 반복 항상 게시글 리스트 보이기
		int input;
		do {
			// 전체 출력
			displayAll();
			// 선택 메뉴 출력
			displayMenu();

			// 해당 작업 선택
			input = scan.nextInt();
			switch (input) {
			case 1: // 새글 작성
				insert();
				break;
			case 2: // 게시글 보기
				displayNum();
				break;
			case 3: // 검색
				search();
				break;
			case 0: // 종료
				System.out.println("프로그램 종료.");
				System.exit(0);
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (input != 0);
	}

	// 메뉴 출력
	public void displayMenu() {
		System.out.println("!!!----------------------");
		System.out.println("1.새글작성 2.게시글 보기 3.검색 0.작업끝");
		System.out.print(" 작업 선택 >> ");
		System.out.println();
	}

	// 전체 목록 출력
	private void displayAll() {
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println(" NO\t제목\t작성자\t조회수");
		System.out.println("----------------------------------------------");
		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();

			String sql = "select * from jdbc_board";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int no = rs.getInt("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				// String date = rs.getString("board_date");
				// String content = rs.getString("board_content");

				System.out.println(no + "\t" + title + "\t" + writer + "\t" + "date");
			} // while 종료
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

/////////////////////////////////////////////

	// 1. 새글 작성
	private void insert() {

		String title;
		String writer;
		String content;

		System.out.println();
		System.out.print("제목을 입력하세요 >> ");
		title = scan.next();

		System.out.print("작성자를 입력하세요 >> ");
		writer = scan.next();

		System.out.print("내용을 입력하세요 >> ");
		content = scan.next();

		try {
			conn = DBUtil.getConnection();

			String sql = "insert into jdbc_board(board_no,board_title, board_writer, board_date, board_content)"
					+ " values (board_seq.NEXTVAL, ?, ?, sysdate, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("새 글 작성 성공");
			} else {
				System.out.println("새 글 작성 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	// 2. 해당 게시글 보기
	private void displayNum() {
		System.out.println();
		System.out.println("보기를 원하는 게시물 번호 입력 >>");

		// 게시글 번호 반복입력 int 형식만 받음.
		while (!scan.hasNextInt()) {
			scan.nextInt();// 잘못된 입력 초기화
			System.out.println("잘못된 입력. 게시글 번호를 입력해주세요.");
		}
		int wannaNum = scan.nextInt();

		System.out.println();
		System.out.println("----------------------------------------------");

		try {
			// 해당 번호 게시글 찾기 및 출력
			conn = DBUtil.getConnection();
			String sql = "select * from jdbc_board where board_no = " + wannaNum;

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			int no = 0;
			while (rs.next()) {
				no = rs.getInt("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				String content = rs.getString("board_content");
				String date = rs.getString("board_date");

				System.out.println(no + "\t" + title + "\t" + writer + "\t" + "date");

				System.out.println("title : " + title);
				System.out.println("writer : " + writer);
				System.out.println("content : " + content);
				System.out.println("write date : " + date);
				System.out.println("조회수 : " + "미구현");
				System.out.println("----------------------------------------------");

				System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
				System.out.println();
			} // 해당 조회 쿼리문 출력 끝.

			// 출력된 쿼리문에 대한 작업 선택 값 받기
			while (!scan.hasNextInt()) {
				scan.nextInt();// 잘못된 입력 초기화
				System.out.println("잘못된 입력. 메뉴 번호를 입력해주세요.");
			}
			int indis = scan.nextInt();

			switch (indis) {
			case 1: // 수정
				update(no);
				break;
			case 2: // 삭제
				delete(no);
				break;
			case 3: // 리스트로 가기
				break;
			default:
				System.out.println("잘못된 입력");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	// 2-1회원 정보 수정
	private void update(int no) {
		System.out.println();
		System.out.print("해당 게시글 번호" + no + "를 확인합니다.");

		System.out.print("수정할 제목을 입력하세요. >>");
		String title = scan.next();

		System.out.print("수정할 내용을 입력하세요 >> ");
		String content = scan.next();

		try {
			conn = DBUtil.getConnection();
			String sql = "update jdbc_board " + "set board_title = ?" + ", board_content = ? where board_no = " + no;

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(no + " 번 게시글의 정보를 수정했습니다.");
			} else {
				System.out.println("게시글의 정보 수정 실패!!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	// 2-2 삭제
	private void delete(int no) {

		try {
			conn = DBUtil.getConnection();
			String sql = "delete from jdbc_board where board_no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(no + " 번 게시물 삭제 성공...");
			} else {
				System.out.println("게시물 삭제 실패!!!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	// 3. 검색
	private void search() {

		// 존재유무 초기값 설정
		boolean chk = false;
		System.out.println();

		do {
			System.out.print("검색할 번호를 입력하세요");
			int inputno = scan.nextInt();

			chk = getBoard(inputno);
			if (chk == false) {
				System.out.println(inputno + "은 없는 번호입니다.");
				System.out.println("검색할 게시물이 없으니 다시 입력하세요.");
			}

			try {
				conn = DBUtil.getConnection();
				String sql = "select * from jdbc_board where board_no = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, inputno);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					int no = rs.getInt("board_no");
					String title = rs.getString("board_title");
					String writer = rs.getString("board_writer");
					// String date = rs.getString("board_date");
					// String content = rs.getString("board_content");
					System.out.println(" 번호\t제목\t작성자\t\t조회수");
					System.out.println(no + "\t" + title + "\t" + writer + "\t" + "미구현");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disConnect();
			}
		} while (chk == false);
	}

	// 회원 유무 확인
	private boolean getBoard(int no) {
		boolean check = false;

		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) as cnt from jdbc_board" + " where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if (cnt > 0) {
				check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return check;
	}

	// finally
	private void disConnect() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException ee) {
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException ee) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException ee) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException ee) {
			}
	}
}// 클래스 마감
