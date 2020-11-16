package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	// JDBC 드라이버를 로딩하고, COnnection 객체를 생성하여 반환하는 메서드로 구성된 class
	// SQL ORACLE 에서만 사용 가능하다.

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패~~~");
			System.out.println();
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC20", "java");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패!!!!!!!!");
			System.out.println();
			return null;
		}
	}

}
