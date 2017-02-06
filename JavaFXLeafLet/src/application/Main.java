package application;
	
import java.net.URI;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			 final String html = "http://leafletjs.com/examples/custom-icons/example.html";
	        final URI uri = new URI(html);
	 
	        // create WebView with specified local content
	        final javafx.scene.web.WebView root = new javafx.scene.web.WebView();
	        root.getEngine().load(uri.toString());
	        root.setZoom(javafx.stage.Screen.getPrimary().getDpi() / 96);
	 
	        primaryStage.setTitle("Leaflet Map");
	        primaryStage.setScene(new javafx.scene.Scene(root, 620, 420));
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
