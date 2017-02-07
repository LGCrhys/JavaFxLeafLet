package application.views;

import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.SwingUtilities;

import application.Config;
import javafx.embed.swing.SwingNode;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Screen;

public class MainView extends BorderPane {
	
	final String html = Config.getProperty("mapurl");
    URI uri;

	public MainView() {
		
		super();
		try {
			uri = new URI(html);
			
	        final SwingNode swingNode = new SwingNode();
	        createSwingContent(swingNode);
	        
	        // create WebView with specified local content
	        final WebView map = new WebView();
	        map.getEngine().load(uri.toString());
	        map.setZoom(Screen.getPrimary().getDpi() / 96);  

            setTop(swingNode);
	        setCenter(map); 
	        
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	             
	}
	
	private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

}
