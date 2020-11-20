package log4j.basic;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jTest {
	// Logger 클래스의 인스턴스 생성하기
	static Logger logger = Logger.getLogger(Log4jTest.class);
public static void main(String[] args) {
	
	//방법 1
	//log 출력하기
//	logger.trace("이 메시지는 trace 레벨의 메시지 입니다.");
//	logger.debug("이 메시지는 debug 레벨의 메시지 입니다.");
//	logger.info("이 메시지는 info 레벨의 메시지 입니다.");
//	logger.warn("이 메시지는 warnning 레벨의 메시지 입니다.");
//	logger.error("이 메시지는 error 레벨의 메시지 입니다.");
//	logger.fatal("이 메시지는 fatal 레벨의 메시지 입니다.");

	//방법2
	logger.log(Level.FATAL,"이 메시지는 Level.Fatal 레벨의 베시지 입니다.");
	logger.log(Level.ERROR,"이 메시지는 Level.error 레벨의 베시지 입니다.");
	logger.log(Level.WARN,"이 메시지는 Level.warn 레벨의 베시지 입니다.");
	logger.log(Level.INFO,"이 메시지는 Level.info 레벨의 베시지 입니다.");
	logger.log(Level.DEBUG,"이 메시지는 Level.debug 레벨의 베시지 입니다.");
	logger.log(Level.TRACE,"이 메시지는 Level.trace 레벨의 베시지 입니다.");
	
}
}
