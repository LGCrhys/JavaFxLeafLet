package application.views.components.toolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

import application.views.components.panel.LocationFilterPanel;
import application.views.components.table.LocalisationDescriptionTable;

public class FilterToolbar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3260982569579733506L;
	private JButton filterButton;
	private JPanel filterPanel;
	
	public FilterToolbar(LocalisationDescriptionTable table){
		super();
		setFloatable(false);
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(200,30));
		initFilterPopup();
		initButtons();
	}
	
	private void initFilterPopup(){
		filterPanel = new LocationFilterPanel();
	}
	
	private void initButtons(){
		
		this.add(Box.createHorizontalGlue());
		
		URL buttonIcon = getClass().getClassLoader().getResource("application/resources/icons/filter.png");
		filterButton = new JButton(new ImageIcon(buttonIcon));
		filterButton.setToolTipText("Filter");
		filterButton.setPreferredSize(new Dimension(24,24));
		filterButton.setBackground(Color.WHITE);
        filterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	displayFilterPopup();
            }
        });
        this.add(filterButton);
		
	}
	
	public void displayFilterPopup(){
		JPopupMenu popup = new JPopupMenu();
		popup.setFocusable(false);
		popup.add(filterPanel);
		popup.show(this,10,10);
		
		
	}

}
