package application.views.components.fx;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import application.Config;
import application.data.Localisation;
import application.interfaces.LocalisationsView;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MapContainer extends AnchorPane implements LocalisationsView{

	private WebView webView = new WebView();
	private WebEngine webEngine = webView.getEngine();

	final String html = Config.getProperty("mapurl");
	private URI uri;
	private boolean webViewReady = false;
	private List<Localisation> displayedLocalisations;
		
	public MapContainer(){
		super();
		displayedLocalisations = new ArrayList<Localisation>();
		try {
			uri = new URI(html);			
	        webEngine.load(uri.toString());	        
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	    AnchorPane.setTopAnchor(webView, 0.00);
	    AnchorPane.setLeftAnchor(webView, 0.00);
	    AnchorPane.setRightAnchor(webView, 0.00);
	    AnchorPane.setBottomAnchor(webView, 0.00);
	    getChildren().addAll(webView);
	    
	    webView.getEngine().getLoadWorker().stateProperty().addListener(
		  (ObservableValue<? extends Worker.State> observable,
		    Worker.State oldValue,
		    Worker.State newValue) -> {
		    if( newValue != Worker.State.SUCCEEDED ) {
		      return;
		    }
		    webViewReady  = true;
		    updateLocalisations(displayedLocalisations);
		  } );
	}

	@Override
	public void updateLocalisations(List<Localisation> localisations) {		
		if(webViewReady){
			Platform.runLater(new Runnable() {
		      @Override public void run() {
		    	  Gson gson = new Gson();
		    	  String json = gson.toJson(localisations);
		    	  webEngine.executeScript("angular.element($('.map')).scope().displayLocalisations("+json+")");
		      }
		    });
		}
		displayedLocalisations = localisations;
	}
	
}
