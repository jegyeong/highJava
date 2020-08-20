package basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class FaceRecognitionTest {
	String CLIENT_ID = "HPhMS5z3Rlc1P1bvVauw";
	String ClIENT_SECRET = "60fJyesyCs";
	
	// 서비스 연결 커넥션 객체 변수 선언
	private HttpURLConnection con;
	
	// 얼굴 인신 URL설정 메서드
	public void setConnection() {
		String apiURL = "https://openapi.naver.com/v1/vision/celebrity";  // 유명인 얼굴 인식
		//String apiURL = "https://openapi.naver.com/v1/vision/face";  // 얼굴 감지
		
		try {
			URL url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			con.setUseCaches(false);
			con.setDoOutput(true);
			con.setDoInput(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 처리할 이미지 파일 전송하는 메서드
	public void setFileTransfer() {
		// multipart request
		String boundary = "---" + System.currentTimeMillis() + "---";
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
		con.setRequestProperty("X-Naver-Client-Secret", ClIENT_SECRET);
		
		OutputStream os;
		
		try {
			os = con.getOutputStream();
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));
			
			String LINE_FEED = "\r\n";
			
			// 파일 추가
			String imgFile = FaceRecognitionTest.class.getResource("actress.jpg").getPath();
			File uploadFile = new File(imgFile);
			
			String paramName = "image";  // 파라미터명은 'image'로 지정
			String fileName = uploadFile.getName();
			
			writer.append("--" + boundary).append(LINE_FEED);
			writer.append("Content-Disposition: form-data; name=\"" + paramName + 
					"\"; filename=\"" + fileName + "\"").append(LINE_FEED);
			writer.append("Content-Type: " + 
					URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
			writer.append(LINE_FEED);
			writer.flush();
			
			FileInputStream is = new FileInputStream(uploadFile);
			byte[] buffer = new byte[4096];
			int length = -1;
			while((length = is.read(buffer))>0) {
				os.write(buffer, 0, length);
			}
			os.flush();
			is.close();
			
			writer.append(LINE_FEED).flush();
			writer.append("--" + boundary + "--").append(LINE_FEED);
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 응답 수신
	public void receiveResponse() {
		BufferedReader br = null;
		int responseCode;
		
		try {
			responseCode = con.getResponseCode(); // 응답 코드 받기
			if(responseCode==200) {  // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else { // 에러 발생
				System.out.println("ERROR!! ==> responseCode=" + responseCode);
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
			
			String inputLine;
			if(br != null) {
				StringBuffer response = new StringBuffer();
				while((inputLine = br.readLine())!=null) {
					response.append(inputLine);
				}
				br.close();
				
				System.out.println(response.toString());  // 결과 출력
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {
		FaceRecognitionTest test = new FaceRecognitionTest();
		
		test.setConnection(); 
		test.setFileTransfer();
		test.receiveResponse();

	}

}
