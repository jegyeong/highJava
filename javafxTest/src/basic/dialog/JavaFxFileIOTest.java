package basic.dialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.ExistingAnnotationsType;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class JavaFxFileIOTest extends Application {
	private String fileName = "NoName.txt";			// 파일명
	private File fileDir = new File("d:/D_Other");  // 문서를 읽어올 기본 폴더 설정
	
	
	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		root.setPrefSize(500, 400);
		root.setPadding(new Insets(15));
		
		MenuBar menuBar = new MenuBar();
		
		Menu fileMenu = new Menu("File");
		
		MenuItem newItem = new MenuItem("새로 만들기");
		MenuItem openItem = new MenuItem("열 기");
		MenuItem saveItem = new MenuItem("새이름으로 저장");
		MenuItem exitItem = new MenuItem("닫 기");
		
		
		TextArea taMain = new TextArea();
		
		// 새로 만들기
		newItem.setOnAction(e->{
			fileName = "NoName.txt";
			taMain.clear();
			primaryStage.setTitle(fileName);
		});
		
		// 열기
		openItem.setOnAction(e->{
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("텍스트 문서 (*.txt)", "*.txt"),
				new ExtensionFilter("모든 파일 (*.*)", "*.*")
			);
			
			// 열기할 폴더 지정
			fileChooser.setInitialDirectory(fileDir);
			
			// 열기창 열고 선택한 파일 구하기
			File selFile = fileChooser.showOpenDialog(primaryStage);
			if(selFile==null) return;
			
			fileDir = selFile.getParentFile();   // 열기한 문서의 폴더를 다음에 열기할 때 나타나도록 설정
			
			fileName = selFile.getName();  // 읽어온 파일명 저장
			
			// 파일 내용을 읽어와 TextArea에 출력하기
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(selFile));
				taMain.clear();
				
				char[] data = new char[1024];  // 읽어온 문서의 내용을 저장할 배열 선언
				int len = 0;
				while((len=br.read(data))>0) {
					// 읽어온 데이터가 저장된 char형 배열(=>data)을 문자열로 변환하기
					String strTemp = new String(Arrays.copyOf(data, len));
					
					taMain.appendText(strTemp);
				}
				
				
			} catch (IOException e2) {
				fileName = "NoName.txt";
				e2.printStackTrace();
			} finally {
				if(br!=null)try { br.close();  }catch(IOException e3) {}
			}
			
			primaryStage.setTitle(fileName);  // 문서창의 제목을 읽어온 파일명으로...

		});
		
		// 닫기
		exitItem.setOnAction(e->{
			Platform.exit();
		});
		
		
		// 저장
		saveItem.setOnAction(e->{
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("텍스트 문서 (*.txt)", "*.txt"),
				new ExtensionFilter("모든 파일 (*.*)", "*.*")
			);
			
			// 저장할 폴더 지정
			fileChooser.setInitialDirectory(fileDir);
			
			// 저장할 기본 파일명 지정
			fileChooser.setInitialFileName(fileName);
			
			// 저장 창을 보여주고 저장할 파일명 정보를 구한다.
			File selFile = fileChooser.showSaveDialog(primaryStage);
			if(selFile==null) return;
			
			fileDir = selFile.getParentFile();
			fileName = selFile.getName();
			
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter(selFile));
				
				// 줄바꿈(\n)을   (\n\r)로 변환한다.
				bw.write(taMain.getText().replace("\n", "\r\n"));				
				//bw.write(taMain.getText());
				
			} catch (IOException e2) {
				e2.printStackTrace();
			} finally {
				if(bw!=null) try { bw.close(); }catch(IOException e3) {}
			}
			
			primaryStage.setTitle(fileName);
			
			
		});
		
		
		
		fileMenu.getItems().addAll(newItem, openItem, saveItem, exitItem);
		menuBar.getMenus().add(fileMenu);
		
		root.setTop(menuBar);
		root.setCenter(taMain);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle(fileName);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}









