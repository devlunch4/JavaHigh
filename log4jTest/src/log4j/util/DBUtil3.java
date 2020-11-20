package log4j.util;

//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class DBUtil3 {
	static Logger logger = Logger.getLogger(DBUtil3.class);
	static ResourceBundle bundle;

	static {
		bundle = ResourceBundle.getBundle("dbinfo");
		logger.info("ResourceBundle 객체 생성 -dbinfo.properties 파일 읽기 -객체명 : bundle");

		try {
			Class.forName(bundle.getString("driver"));
			logger.info("DB드라이버 로딩 성공");
			//
		} catch (ClassNotFoundException e) {
			// System.out.println("드라이버 로딩 실패~~~");
			logger.error("드라이버 로딩 실패!! " + e);
			System.out.println();
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {

			return DriverManager.getConnection(bundle.getString("url"), bundle.getString("user"),
					bundle.getString("pass"));

		} catch (SQLException e) {
			System.out.println("DB 연결 실패!!!!!!!!");
			logger.error("DB 시스템 연결 실패~~~ " + e);
			return null;
		}
	}

}
