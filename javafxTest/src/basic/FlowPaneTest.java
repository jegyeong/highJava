package basic;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class FlowPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		FlowPane root = new FlowPane();
		root.setPrefSize(300, 100);
		root.setHgap(10);   // 컨트롤 간의 수평 간격
		root.setVgap(10);	// 컨트롤 간의 수직 간격
		root.setPadding(new Insets(10));
		
		Button button1 = new Button("1번");
		Button button2 = new Button("2번");
		Button button3 = new Button("3번");
		Button button4 = new Button("4번");
		Button button5 = new Button("5번");
		Button button6 = new Button("6번");
		Button button7 = new Button("7번");
		
		root.getChildren().addAll(button1, button2, button3, 
				button4, button5, button6, button7 );
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("FlowPane 연습");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
