package basic;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.text_to_speech.v1.model.Voices;
import com.ibm.watson.text_to_speech.v1.util.WaveUtils;

/*
	IBM Text To Speech 서비스는 IBM의 음성합성기능을 사용하여 
	다양한 언어, 방언 및 음성으로 텍스트를 자연스러운 발음으로 함성하는 API를 제공한다.
	
	이 서비스는 각 언어에 대한 남성 또는 여성, 때로는 둘 다 지원한다.
*/
public class TextToSpeechTest {
	String API_KEY = "iHAn8JS55_iiZoqEbuC9ewL_P_JZf99S5LUloNe2bhuU";
	String URL = "https://api.us-south.text-to-speech.watson.cloud.ibm.com/instances/63d48ede-9876-4e37-9d14-f4b5c45b8fc9";
	
	String dir = "d:/d_other/";   // 저장될 폴더 
	String filename = "voiceResult.wav";  // 저장될 파일이름
	
	// TextToSpeech서비스 객체 변수 선언
	TextToSpeech service;
	
	// 서비스 설정
	public void setService() {
		IamAuthenticator auth = new IamAuthenticator(API_KEY);
		service = new TextToSpeech(auth);
		service.setServiceUrl(URL);
	}
	
	// 왓슨에서 제공하는 음성 목록
	public void getVoiceList(){
		Voices voices = service.listVoices().execute().getResult();
		System.out.println(voices);
	}
	
	// 서비스 실행
	public void executeSevice() {
		try {
			// 옵션 설정
			SynthesizeOptions synOptions = new SynthesizeOptions.Builder()
					
					// 실행할 Text 문장
					//.text("Hello my name is Lisa. Nice to meet you!")
					.text("안녕하세요 제 이름은 영미입니다. 만나서 반가워요!!")
					
					.accept("audio/wav")  // 저장할 Audio파일 종류 설정
					//.voice("en-US_LisaV2Voice")  // Voice 설정(영문)
					.voice("ko-KR_YoungmiVoice")  // Voice 설정(한글)
					.build();
			
			// 실행한 결과를 Audio파일로 저장하기
			InputStream is = service.synthesize(synOptions).execute().getResult();
			InputStream in = WaveUtils.reWriteWaveHeader(is);
			
			String fullFilename = dir + filename;
			OutputStream out = new FileOutputStream(fullFilename);
			
			byte[] buffer = new byte[1024];
			int length;
			while((length=in.read(buffer))>0) {
				out.write(buffer, 0, length);
			}
			out.flush();
			
			out.close();
			in.close();
			is.close();
			
			System.out.println("작업 끝...");
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		TextToSpeechTest test = new TextToSpeechTest();
		test.setService();
		test.getVoiceList();
		//test.executeSevice();
		
		
	}
}
