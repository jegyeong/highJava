package basic.chart;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class PieChartTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		PieChart pc = new PieChart();
		
		ObservableList<PieChart.Data> pieList =
			FXCollections.observableArrayList(
				new PieChart.Data("사과", 100),
				new PieChart.Data("복숭아", 40),
				new PieChart.Data("딸기", 80),
				new PieChart.Data("포도", 120),
				new PieChart.Data("귤", 60)
			);
		
		pc.setData(pieList);
		
		pc.setTitle("과일별 재고량");
		
		Scene scene = new Scene(pc);
		primaryStage.setScene(scene);
		primaryStage.setTitle("원그래프(PieChart) 연습");
		primaryStage.show();
		
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
