package application.views;

import java.awt.BorderLayout;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import application.Config;
import application.models.LocalisationModel;
import application.presenters.LocalisationsPresenter;
import application.views.components.table.LocalisationDescriptionTable;
import application.views.components.table.LocalisationDescriptionTableModel;
import application.views.components.toolbar.FilterToolbar;
import application.views.components.toolbar.PaginationToolbar;
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
    private JPanel localisatonTablePanel;
    private LocalisationsPresenter localisationPresenter;
    private LocalisationModel localisationModel;

	public MainView() {
		
		super();
		localisationModel = new LocalisationModel();
		localisationPresenter = new LocalisationsPresenter(localisationModel);
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
	        column.setPercentWidth(80);
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
            	localisatonTablePanel = new JPanel(new BorderLayout());            	
            	LocalisationDescriptionTableModel model = new LocalisationDescriptionTableModel();
        		LocalisationDescriptionTable table = new LocalisationDescriptionTable(model);        	
        		FilterToolbar filterToolbar = new FilterToolbar(localisationPresenter);
        		PaginationToolbar paginationToolbar = new PaginationToolbar(localisationPresenter);
        		localisationPresenter.addView(table);
        		localisationPresenter.addView(filterToolbar);
        		localisationPresenter.addView(paginationToolbar);
        		localisatonTablePanel.add(new JScrollPane(table),BorderLayout.CENTER);
        		localisatonTablePanel.add(filterToolbar, BorderLayout.PAGE_START);
        		localisatonTablePanel.add(paginationToolbar, BorderLayout.PAGE_END);
            	tableSwingNode.setContent(localisatonTablePanel);
            }
        });
    }

}
