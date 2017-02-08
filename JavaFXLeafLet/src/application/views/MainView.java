package application.views;

import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import application.Config;
import application.views.components.LocalisationTable;
import application.views.components.LocalisationTableModel;
import javafx.embed.swing.SwingNode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.web.WebView;
import javafx.stage.Screen;

public class MainView extends GridPane {
	
	final String html = Config.getProperty("mapurl");
    URI uri;
    private WebView map;
    private JTable LocalisatonTable;

	public MainView() {
		
		super();
		try {
			uri = new URI(html);
			
	        final SwingNode tableSwingNode = new SwingNode();
	        createTable(tableSwingNode);
	        
	        // create WebView with specified local content
	        map = new WebView();
	        map.getEngine().load(uri.toString());
	        map.setZoom(Screen.getPrimary().getDpi() / 96);
	        
	        RowConstraints growingRow = new RowConstraints();
	        growingRow.setVgrow(Priority.ALWAYS);
	        getRowConstraints().add(growingRow);
	        
	        ColumnConstraints column = new ColumnConstraints();
	        column.setPercentWidth(75);
	        getColumnConstraints().add(column);
	        
	        add(map, 0, 0);
	        add(tableSwingNode,1,0);
	        
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	             
	}
	
	private void createTable(final SwingNode tableSwingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	LocalisationTableModel model = new LocalisationTableModel();
            	LocalisatonTable = new LocalisationTable(model);
            	tableSwingNode.setContent(new JScrollPane(LocalisatonTable));
            }
        });
    }

}
