/**
 * 
 */
package application.views.components.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author clegall
 */
public class LocationFilterPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -922046669404964878L;
	
	public LocationFilterPanel(){
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
	    
	    JPanel checkboxPanel = new JPanel();
	    checkboxPanel.setLayout(new GridLayout(1, 3));
	    checkboxPanel.add(new JCheckBox("Hostile",true));
	    checkboxPanel.add(new JCheckBox("Inconnu", true));
	    checkboxPanel.add(new JCheckBox("Ami", true));
	    
	    add(checkboxPanel);
	    
	   
	}

}
