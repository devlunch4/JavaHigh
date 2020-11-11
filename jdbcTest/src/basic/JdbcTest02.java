package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제1 ) 사용자로부터 Lprod_id 값을 입력받아 입력한 값보다 Lprod_id값이 큰 자료들을 출력하시오

public class JdbcTest02 {

	public static void main(String[] args) {
		// DB 작업에 필요한 객체변수 선언

		// Connection은 java.sql 것을 사용
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩.
			// 대소문자 구분
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. DB연결 ==> Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC20", "java");

			// 3-1. 실행할 SQL문 생성
			String sql = "select * from lprod";

			// 3-2. Statement객체 생성 ==> Connection 객체를 이용한다.
			stmt = conn.createStatement();

			// 4. SQL문을 DB서버로 전송해서 실행하고 결과를 얻어온다.
			// ==== (지금은 실행할 SQL문이 SELECT 문이기 떄문에 결과가 RersultSet객체에 저장되어 반환된다.
			rs = stmt.executeQuery(sql);

			// 5, 결과 처리하기 ==> 한 레코드씩 화면에 출력하기
			// =====> ResultSet에 저장된 데이터를 차례로 꺼내오려면 반복문과 next()ㅔㅁ서드를 이용한다.

	

			//////////////////////////////////////////////////////////////////
			//
			//
			// 해당 입력 값을 받아서 while 문 안에서 if 문으로 돌린다.

			Scanner scan = new Scanner(System.in);
			System.out.println("lprod는 1~9까지 있습니다.>>");
			System.out.println("입력 숫자 이후의 lprod의 정보가 출력됩니다.>>");
			System.out.println("숫자를 입력하세요>>");
			int input = scan.nextInt();
			//
			//
			//
			//////////////////////////////////////////////////////////////////
			
			
			System.out.println("== 처리 결과 출력 ==");
			// 포인터는 첫 행 위에부터 존재/시작
			// rs.next() ==> ResultSet 객체의 데이터를 가리키는 포인터를 다음 레코드 자리로 이동시키고
			// ============= 그곳에 데이터가 있으면 true 없으면 false를 반환한다
			while (rs.next()) {
				// 포인터가 가리키는 곳의 데이터를 가져오는 방법
				// 형식1) rs.get 자료형 이름("컬럼명")
				// 형식2) rs.get 자료형 이름(컬럼번호) ==> 컬럼번호는 1부터 시작
				// 형식3) re.get 자료형이름("컬럼의 alias명")
				if (input < rs.getInt("lprod_id")) {
					System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
					System.out.println("Lprod_gu : " + rs.getString(2));
					System.out.println("Lprod_nm : " + rs.getString("lprod_nm"));
					System.out.println("---------------------------------");
				}
			}
			System.out.println("전체자료 출력 끝...");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// rs
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// stmt
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// conn
		}
	}

}