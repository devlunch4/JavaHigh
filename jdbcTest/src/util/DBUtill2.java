package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// JDBC 드라이버를 로딩하고, COnnection 객체를 생성하여 반환하는 메서드로 구성된 class
// Properties객체를 이용하여 처리하기
public class DBUtill2 {
	static Properties prop;

	static {
		prop = new Properties();

		File f = new File("res/dbinfo.properties");
		FileInputStream fin = null;

		try {

			// 파일의 내용을 읽어올 스트림 객체 생성
			fin = new FileInputStream(f);

			// 입력용 스크림을 이용해서 파일 내용을 읽어와 Propreties객체에 저장한다.
			prop.load(fin);
			// 저장된 덱스트 파일에 키값과 해당 값을 분류를 해준다.
			// 파일 내용을 읽어 key값과 value값을 분류한 후 Properties객체에 추가한다.

			// Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(prop.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패~~~");
			System.out.println();
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("파일입출력 오류~~");
			System.out.println();
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			// return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
			// "PC20", "java");
			return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"),
					prop.getProperty("pass"));
			
		} catch (SQLException e) {
			System.out.println("DB 연결 실패!!!!!!!!");
			System.out.println();
			return null;
		}
	}

}
