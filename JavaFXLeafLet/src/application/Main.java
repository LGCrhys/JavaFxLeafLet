package application;
	
import application.controllers.JavaFXLeafLetController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {	
			
			JavaFXLeafLetController mainController = new JavaFXLeafLetController();
			mainController.startApplication(primaryStage);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
