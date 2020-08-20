package basic;

import org.apache.log4j.Logger;

public class SimpleLoggerTest {
	
	// Logger클래스의 인스턴스를 구한다.
	static Logger log = Logger.getLogger(SimpleLoggerTest.class);
	

	public static void main(String[] args) {
		
		log.fatal("[FATAL] 로그 메시지 연습");
		log.error("[ERROR] 로그 메시지 연습");
		log.warn("[WARN] 로그 메시지 연습");
		log.info("[INFO] 로그 메시지 연습");
		log.debug("[DEBUG] 로그 메시지 연습");
		log.trace("[TRACE] 로그 메시지 연습");
		
	}

}
