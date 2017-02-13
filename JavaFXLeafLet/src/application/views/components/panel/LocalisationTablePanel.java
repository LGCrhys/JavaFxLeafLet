package application.views.components.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import application.views.components.table.LocalisationDescriptionTable;
import application.views.components.table.LocalisationDescriptionTableModel;
import application.views.components.toolbar.FilterToolbar;
import application.views.components.toolbar.PaginationToolbar;

public class LocalisationTablePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1375030315403517969L;
	private LocalisationDescriptionTableModel model;
	private LocalisationDescriptionTable table;
	private PaginationToolbar paginationToolbar;
	private FilterToolbar filterToolbar;
	
	public LocalisationTablePanel(){    	
		super(new BorderLayout());
		
		model = new LocalisationDescriptionTableModel();
		table = new LocalisationDescriptionTable(model);
	
		filterToolbar = new FilterToolbar(table);
		paginationToolbar = new PaginationToolbar(table);
		
	   	add(new JScrollPane(table),BorderLayout.CENTER);
    	add(filterToolbar, BorderLayout.PAGE_START);
    	add(paginationToolbar, BorderLayout.PAGE_END);
	}

}
