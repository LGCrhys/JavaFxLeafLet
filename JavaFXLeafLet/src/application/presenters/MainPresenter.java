package application.presenters;

import application.Config;
import application.views.MainView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPresenter {
	
	final MainView view;
	
	public MainPresenter(MainView view){
		this.view = view;
	}

	public void startApplication(Stage primaryStage){
		Scene s = new Scene(view, 1400, 800);
		primaryStage.setTitle(Config.getProperty("title"));
		primaryStage.setScene(s);
		primaryStage.show();
		
	}
}
