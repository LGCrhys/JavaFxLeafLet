package application.controllers;

import application.Config;
import application.views.JavaFXLeafLetView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JavaFXLeafLetController {
	
	final JavaFXLeafLetView view = new JavaFXLeafLetView();

	public void startApplication(Stage primaryStage){
		
		Scene s = new Scene(view, 1000, 800);	        
 
        primaryStage.setTitle(Config.getProperty("title"));
        primaryStage.setScene(s);
		
        primaryStage.show();
		
	}
}
