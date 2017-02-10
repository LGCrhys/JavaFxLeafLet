package application.views.components.panel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import application.views.components.table.LocalisationDescriptionTable;
import application.views.components.table.LocalisationDescriptionTableModel;
import application.views.components.toolbar.PaginationToolbar;

public class LocalisationTablePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1375030315403517969L;
	private LocalisationDescriptionTableModel model;
	private LocalisationDescriptionTable table;
	private PaginationToolbar toolbar;
	
	public LocalisationTablePanel(){    	
		super(new BorderLayout());
		
		model = new LocalisationDescriptionTableModel();
		table = new LocalisationDescriptionTable(model);
	
		toolbar = new PaginationToolbar(table);
		
	   	add(new JScrollPane(table),BorderLayout.CENTER);
    	add(toolbar, BorderLayout.PAGE_END);
	}

}
