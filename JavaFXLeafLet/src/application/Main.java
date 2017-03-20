package application;
	
import application.presenters.MainPresenter;
import application.views.MainView;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {	
			MainView view = new MainView();
			MainPresenter mainPresenter = new MainPresenter(view);
			mainPresenter.startApplication(primaryStage);			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
