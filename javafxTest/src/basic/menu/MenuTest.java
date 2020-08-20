package basic.menu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		//root.setPadding(new Insets(10));
		
		MenuBar menuBar = new MenuBar(); // MenuBar객체 생성
		
		// 메뉴바의 너비를 현재 창의 너비와 연동되도록 한다.
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		root.setTop(menuBar);
		
		// Menu객체 생성
		Menu fileMenu = new Menu("File");
		
		// Menu객체에 속할 부메뉴 객체(MenuItem객체) 생성
		MenuItem newMenuItem = new MenuItem("New");
		// MenuItem에 이미지 추가하기
		Image img = new Image(
				MenuTest.class.getResourceAsStream("../../images/Project.png"));
		ImageView imgView = new ImageView(img);
		newMenuItem.setGraphic(imgView);  // 메뉴아이템에 이미지 넣기
		
		
		MenuItem saveMenuItem = new MenuItem("Save");
		MenuItem exitMenuItem = new MenuItem("eXit");
		
		// MenuItem에 단축키 설정하기 (Ctrl + X)
		exitMenuItem.setAccelerator(
			new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
		
		// 메뉴를 선택했을 때 이벤트 처리 (버튼을 클릭했을 때 이벤트 처리와 같은 방법으로 기술한다.)
		exitMenuItem.setOnAction(e -> {
			Platform.exit();
		});
		
		// Menu객체에 MenuItem객체 추가하기
		fileMenu.getItems().addAll(newMenuItem, saveMenuItem, 
				new SeparatorMenuItem() ,exitMenuItem);
		// SeparatorMenuItem ==> MenuItem들 사이의 '선' 그리기

		//----------------------------------------
		Menu webMenu = new Menu("Web");
		
		CheckMenuItem htmlItem = new CheckMenuItem("HTML");
		CheckMenuItem cssItem = new CheckMenuItem("CSS");
		cssItem.setSelected(true);   // 처음에 체크 여부 설정
		CheckMenuItem scriptItem = new CheckMenuItem("JavaScript");
		
		webMenu.getItems().addAll(htmlItem, cssItem, scriptItem);
		
		//-------------------------------------------
		
		Menu dbMenu = new Menu("DataBase");
		ToggleGroup tg = new ToggleGroup();
		
		RadioMenuItem mysqlItem = new RadioMenuItem("MySql");
		mysqlItem.setToggleGroup(tg);
		
		RadioMenuItem oracleItem = new RadioMenuItem("Oracle");
		oracleItem.setToggleGroup(tg);
		
		RadioMenuItem mssqlItem = new RadioMenuItem("MS-Sql");
		mssqlItem.setToggleGroup(tg);
		
		dbMenu.getItems().addAll(mysqlItem, oracleItem, mssqlItem);
		
		// 부메뉴의 부메뉴 만들기
		Menu tutorialMenu = new Menu("Tutorial");
		tutorialMenu.getItems().addAll(
			new CheckMenuItem("Java 초급"),
			new CheckMenuItem("Java 고급"),
			new CheckMenuItem("UI설계")
		);
		
		dbMenu.getItems().addAll(new SeparatorMenuItem(), tutorialMenu);
		
		//----------------------------------------------------------
		
		// MenuBar객체에 Menu객체 추가
		menuBar.getMenus().addAll(fileMenu, webMenu, dbMenu);
		
		
		Scene scene = new Scene(root, 300, 250);
		primaryStage.setScene(scene);
		primaryStage.setTitle("메뉴 만들기");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
