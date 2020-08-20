package basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.GetModelOptions;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.speech_to_text.v1.model.SpeechModel;
import com.ibm.watson.speech_to_text.v1.model.SpeechModels;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResult;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;
import com.ibm.watson.speech_to_text.v1.websocket.BaseRecognizeCallback;

/*
 * IBM Watson Speech to Text 서비스는 IBM의 음성 인식기능을
 * 응용 프로그램에 추가할 수 있는 API를 제공한다.
 * 이 서비스는 다양한 언어 및 오디오 형식의 음성을 빠르게 텍스트로
 * 변환한다.
 * 모든 응답내용은 UTF-8 인코딩의 JSON 형식으로 반환한다.
*/
public class SpeechToTextTest {
	
	String API_KEY = "wJ09suK3sSkYvht8W18lbEflI5iaeCdoq2iCeVvOjhMs";
	String URL = "https://api.us-south.speech-to-text.watson.cloud.ibm.com/instances/7aa2e569-c1e8-4742-a99f-afd42546b97b";
	String dir = "D:/D_Other/";
	String final_filename;

	// SpeechToText 서비스 객체 변수 선언
	SpeechToText speechToText;
	
	public void setService() {
		IamAuthenticator authenticator = new IamAuthenticator(API_KEY);
		speechToText = new SpeechToText(authenticator);
		speechToText.setServiceUrl(URL);
	}
	
	public void getModelList() {
		SpeechModels speechModels = speechToText.listModels().execute().getResult();
		System.out.println(speechModels);
	}

	public void executeService() {
		FileInputStream fis = null;
		try {
			// 읽어올 Audio파일용 FileInputStream객체 생성
			fis = new FileInputStream(SpeechToTextTest.class.getResource("speech.mp3").getPath());
			
			// 서비스의 옵션 설정
			RecognizeOptions recognizeOptions = new RecognizeOptions.Builder()
			    .audio(fis)	// 처리할 Audio파일 지정
			    .contentType("audio/mp3")	// Audio파일의 종류 지정
	//		    .audio(new FileInputStream("audio-file.flac"))
	//		    .contentType("audio/flac")

			    .model("ko-KR_BroadbandModel")	// 왓슨에서 제공하는 언어 모델 중 한국어 모델 설정
	//		    .model("en-US_BroadbandModel")  // 왓슨에서 제공하는 언어 모델 중 영어 모델 설정
			    
			    // interimResults ==> 중간 결과를 반환할지 여부 설정 
			    // (true일 경우 JSON SpeechRecognitionResults 객체의 스트림으로 반환된다.
			    .interimResults(true)		
			    
			    // 오디오에서 발견할 수 있는 키워드 목록 설정 
			    //.keywords(Arrays.asList("colorado", "tornado", "tornadoes"))
			    
			    // 키워드 검색을 위한 신뢰값 설정 ==> 신뢰도가 임계값보다 크거나 같으면
			    //		단어가 키워드와 일치하는 것으로 판단한다.
			    //		0과 1 사이로 확률을 지정한다.
			    //.keywordsThreshold((float) 0.5)
			    
			    // 반환될 대체 성적 증명서의 최대수 설정
			    // 		==> 기본적으로 하나가 반환된다.
			    .maxAlternatives(3)
			    
			    .build();
	
			 // 콜백 설정 ==> 서비스 실행 후 처리할 내용을 기술한다.
			 BaseRecognizeCallback baseRecognizeCallback =
				new BaseRecognizeCallback() {
				 // 문자 변환시 처리할 내용
			      @Override
			      public void onTranscription (SpeechRecognitionResults speechRecognitionResults) {
			    	  //System.out.println(speechRecognitionResults);
			      	for(SpeechRecognitionResult transcript:speechRecognitionResults.getResults()) {
						String text = transcript.getAlternatives().get(0).getTranscript();
						System.out.println(text);
					}
			      }
			      
			      // 연결 종료시 처리할 내용
			      @Override
			      public void onDisconnected() {
			        System.exit(0);
			      }
	
			    };
	
			  speechToText.recognizeUsingWebSocket(recognizeOptions, baseRecognizeCallback);
		  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) try { fis.close(); }catch (IOException e) {	}
		}

	}
	
	public static void main(String[] args) {
		SpeechToTextTest test = new SpeechToTextTest();
		test.setService();
//		test.getModelList();
//		test.getModel();
//		test.getKeywordResult();
		test.executeService();
	}
}
