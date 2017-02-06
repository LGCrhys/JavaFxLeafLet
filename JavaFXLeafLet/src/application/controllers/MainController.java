package application.controllers;

import application.Config;
import application.views.MainView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {
	
	final MainView view = new MainView();

	public void startApplication(Stage primaryStage){
		
		Scene s = new Scene(view, 1000, 800);	        
 
        primaryStage.setTitle(Config.getProperty("title"));
        primaryStage.setScene(s);
		
        primaryStage.show();
		
	}
}
