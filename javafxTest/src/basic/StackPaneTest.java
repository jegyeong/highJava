package basic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StackPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		StackPane root = new StackPane();
		root.setPrefSize(300, 200);
		
		ImageView imgView = new ImageView();
		Image img = new Image(StackPaneTest.class.getResourceAsStream("../images/javafx.jpg"));
		imgView.setImage(img);
		imgView.setFitWidth(250);
		imgView.setFitHeight(150);
		
		TextField tfTest = new TextField();
		tfTest.setPrefSize(100, 60);
		tfTest.setStyle("-fx-background-color:red;");
		
		Button btn = new Button("확  인");
		
		root.getChildren().add(imgView);
		root.getChildren().add(tfTest);
		root.getChildren().add(btn);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("StackPane 연습");
		primaryStage.show();
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}










