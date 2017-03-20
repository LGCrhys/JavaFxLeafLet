/**
 * 
 */
package application.views.components.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.enums.Hostility;
import application.presenters.LocalisationsPresenter;

/**
 * @author clegall
 */
public class LocationFilterPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -922046669404964878L;
	private LocalisationsPresenter presenter;
	private List<Hostility> displayedHostilities;
	
	public LocationFilterPanel(LocalisationsPresenter localisationFilterablePresenter){
		this.presenter = localisationFilterablePresenter;
		this.displayedHostilities = new ArrayList<Hostility>();
		this.initUI();
	}
	
	private void initUI(){	
		
		setLayout(new GridLayout(3, 1));
	
	    JLabel title = new JLabel("Localisation Filters : ");
	    title.setBackground(Color.LIGHT_GRAY);
	    title.setOpaque(true);
	    Font font = title.getFont();
	    Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
	    title.setFont(boldFont);
	    add(title);
	
	    add(new JLabel("Hostility : ")); 
	    
	    JPanel checkboxPanel = new JPanel(new GridLayout(1, 3));
	    JCheckBox checkbox;
	    for(Hostility hostility : presenter.getHostilityTypes()){
	    	displayedHostilities.add(hostility);
	    	checkbox = new JCheckBox(hostility.toString(),true);
	    	checkbox.addActionListener(new ActionListener() {
	    	    @Override
	    	    public void actionPerformed(ActionEvent event) {
	    	    	JCheckBox cb = (JCheckBox) event.getSource();
	    	        if (cb.isSelected()) {
	    	        	displayedHostilities.add(Hostility.fromString(cb.getText()));
	    	        } else {
	    	        	displayedHostilities.remove(Hostility.fromString(cb.getText()));
	    	        }
	    	        filterByHostility();
	    	    }
	    	});
	    	checkboxPanel.add(checkbox);
	    }	    
	    add(checkboxPanel);   
	}
	
	private void filterByHostility(){
		presenter.filterLocalisationsByAttr("hostility", displayedHostilities);
	}

}
