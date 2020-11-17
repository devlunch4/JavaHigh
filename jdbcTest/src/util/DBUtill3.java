package util;

//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.util.Properties;
import java.util.ResourceBundle;

// JDBC 드라이버를 로딩하고, COnnection 객체를 생성하여 반환하는 메서드로 구성된 class
// ResourceBundle객체 이용하기
public class DBUtill3 {
	static ResourceBundle bundle;

	static {
		bundle = ResourceBundle.getBundle("dbinfo");

	//	File f = new File("res/dbinfo.properties");
	//	FileInputStream fin = null;

		try {

			// Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
			//
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패~~~");
			System.out.println();
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			// return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
			// "PC20", "java");
			return DriverManager.getConnection(bundle.getString("url"), bundle.getString("user"),
					bundle.getString("pass"));
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패!!!!!!!!");
			System.out.println();
			return null;
		}
	}

}
