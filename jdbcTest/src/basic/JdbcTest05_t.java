package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// LPROD테이블에 새로운 데이터 추가하기

//추가할 데이터 중 LPROD_GU와 LPROD_NM은 직접 입력 받아서 처리하는데
//입력 받은 LPROD_GU 가 입력되어있다면 다시 받아서 처리한다.
public class JdbcTest05_t {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC20", "java");

			String gu = "";
			while (true) {
				System.out.println("lprod_gu 추가하기");
				gu = scan.next();

				String sqlfind = "SELECT * FROM lprod WHERE lprod_gu = ?";
				pstmt = conn.prepareStatement(sqlfind);
				pstmt.setString(1, gu);
				
				rs = pstmt.executeQuery();

				if (rs.next()) {
					System.out.println("lprod_gu에 입력한 값 : " + gu + " 이 존재합니다 . 다른 값을 입력해주세요");
					System.out.println();
				} else {
					System.out.println("중복값이 없습니다 다음 입력해주세요");
					System.out.println();
					break;
				}
			}

			System.out.println("lprod_nm : ");
			String nm = scan.next();

			// statement 객체이용하여 추가하기

			//
			// INSERT INTO bankinfo(bank_no, bank_name, bank_user_name, bank_date)
			// VALUES('123-456-78', '하나은행', '홍길동', SYSDATE);

			String sql = "INSERT INTO lprod (lprod_id, lprod_gu, lprod_nm)  " + "values (((SELECT MAX(lprod_id) FROM lprod) + 1), '" + gu + "', '" + nm + "' )";

			stmt = conn.createStatement();
			System.out.println(sql);
			System.out.println();

			stmt = conn.createStatement();

			// SQL문이 SELECT문일 경우에는 executeQuery() 메서드를 사용하했는데
			// SQL문이 SELECT문이 아닐 경우에는 executeUpate()메서드를 사용한다.

			// executeUpdate()메서드의 반환값 ==> 해당 작업에 성공한 레코드수

			int cnt = stmt.executeUpdate(sql);

			System.out.println("반환값 cnt : " + cnt);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}