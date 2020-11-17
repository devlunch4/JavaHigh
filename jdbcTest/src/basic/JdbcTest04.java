package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//sql table 생성/////////////////////////////////////ㄴsql
//CREATE TABLE bankinfo(
//bank_no VARCHAR2(40) NOT NULL, --계좌번호
//bank_name VARCHAR2(40) NOT NULL, --은행명
//bank_user_name VARCHAR2(40) NOT NULL, --예금주명
//bank_date DATE NOT NULL, --개설날짜
//CONSTRAINT pk_bankinfo PRIMARY KEY (bank_no)
//);
//
//
//--
//
//INSERT INTO bankinfo(bank_no, bank_name, bank_user_name, bank_date)
//VALUES('123-456-78', '하나은행', '홍길동', SYSDATE);
/////////////////////////////////////////////////////////

public class JdbcTest04 {
	// 은행 계좌 번호 정보를 관리하는 에제
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		// try {
		// Class.forName("oracle.jdbc.driver.OracleDriver");
		// conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
		// "PC20", "java");
		//
		// System.out.println("계좌정보 추가하기");
		// System.out.println("계좌정보 : ");
		// String bankNo = scan.next();
		//
		// System.out.println("은행명 : ");
		// String bankName = scan.next();
		//
		// System.out.println("예금주명 : ");
		// String userName = scan.next();
		//
		// // statement 객체이용하여 추가하기
		//
		// //
		// // INSERT INTO bankinfo(bank_no, bank_name, bank_user_name, bank_date)
		// // VALUES('123-456-78', '하나은행', '홍길동', SYSDATE);
		//
		// String sql = "INSERT INTO bankinfo " + "(bank_no, bank_name, bank_user_name,
		// bank_date) " + "VALUES('"
		// + bankNo + "', '" + bankName + "', '" + userName + "', SYSDATE)";
		//
		// System.out.println(sql);
		// System.out.println();
		//
		// stmt = conn.createStatement();
		//
		// // SQL문이 SELECT문일 경우에는 executeQuery() 메서드를 사용하했는데
		// // SQL문이 SELECT문이 아닐 경우에는 executeUpate()메서드를 사용한다.
		//
		// // executeUpdate()메서드의 반환값 ==> 해당 작업에 성공한 레코드수
		//
		// int cnt = stmt.executeUpdate(sql);
		//
		// System.out.println("반환값 cnt : " + cnt);
		//
		// } catch (SQLException e) {
		// e.printStackTrace();
		// } catch (ClassNotFoundException e) {
		// e.printStackTrace();
		// } finally {
		// if (stmt != null) {
		// try {
		// stmt.close();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// }
		// if (pstmt != null) {
		// try {
		// pstmt.close();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// }
		// if (conn != null) {
		// try {
		// conn.close();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// }
		// }
		//
		// }

		// ---------------------------------------------------------------------
		// ---------------------------------------------------------------------
		// ---------------------------------------------------------------------

		// PreparedStatement 활용하기

		// ---------------------------------------------------------------------
		// ---------------------------------------------------------------------
		// ---------------------------------------------------------------------

		
		//stmt 와 pstmt 와 비교는 반복문 사용시 자동적 데이터 세팅을 할떄의 pstmt 효율성이 좋다.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC20", "java");

			System.out.println("계좌정보 추가하기");
			System.out.println("계좌정보 : ");
			String bankNo = scan.next();

			System.out.println("은행명 : ");
			String bankName = scan.next();

			System.out.println("예금주명 : ");
			String userName = scan.next();
			scan.close();
			// statement 객체이용하여 추가하기

			//
			// INSERT INTO bankinfo(bank_no, bank_name, bank_user_name, bank_date)
			// VALUES('123-456-78', '하나은행', '홍길동', SYSDATE);

			String sql = "INSERT INTO bankinfo " + "(bank_no, bank_name, bank_user_name, bank_date) "
					+ "VALUES(?,?,?, SYSDATE)";

			// PreparedStatement객체 생성하기
			// ==>객체를 생성할때 처리할 SQL문을 넣어준다.
			pstmt = conn.prepareStatement(sql);

			// SQL문의 물음표(?)자리에 들어갈 데이터를 세팅한다.
			// gudtlr) pstmt.set자료형 이름(물음표순번, 세팅할 데이터)

			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, userName);

			System.out.println(sql);
			System.out.println();

			// stmt = conn.createStatement();
			
			
			// SQL문이 SELECT문일 경우에는 executeQuery() 메서드를 사용하했는데
			// SQL문이 SELECT문이 아닐 경우에는 executeUpate()메서드를 사용한다.

			// executeUpdate()메서드의 반환값 ==> 해당 작업에 성공한 레코드수
			int cnt = pstmt.executeUpdate();

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
