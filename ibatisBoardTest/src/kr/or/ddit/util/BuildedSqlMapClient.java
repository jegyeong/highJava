package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BuildedSqlMapClient {
	private static SqlMapClient smc;
	
	static{
		try{
			// 1. iBatis의 환경설정 파일을 읽어와서 실행 시킨다.
			// 1-1. 문자 인코딩 케릭터셋 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			// 1-2. 환경설정용 xml문서 읽어오기
			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			// 1-3. 위에서 읽어온 Reader객체를 이용하여 실제 작업에 사용할 객체를 생성한다.
			//		==> 환경설정용 xml문서의 내용을 처리한 후 작업에 사용할 객체를 생성해 준다.
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();
		}catch(IOException e){
			System.out.println("SqlMapClienct객체 생성 실패!!!");
			e.printStackTrace();
		}
	}
	
	public static SqlMapClient getSqlMapClient(){
		return smc;
	}
	
	
}
