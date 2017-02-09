package application.views.components.toolbar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import application.interfaces.Paginable;

public class PaginationToolbar extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -859391148827066717L;
	private static final Integer[] pageSizes= new Integer[]{10,20,40,60,80,100};
	private JComboBox<Integer> pageSize;
	private Paginable paginableComponent;
	
	public PaginationToolbar(Paginable paginableComponent){
		super();
		this.paginableComponent = paginableComponent;
		initButtons();
	}	
	
	public void initButtons() {
        JButton toolbarButton = null;
        URL buttonIcon = null;
        
        buttonIcon = getClass().getClassLoader().getResource("application/resources/icons/previous.png");
        toolbarButton = new JButton(new ImageIcon(buttonIcon));
        toolbarButton.setToolTipText("previous");
        toolbarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        this.add(toolbarButton);

        buttonIcon = getClass().getClassLoader().getResource("application/resources/icons/left.png");
        toolbarButton = new JButton(new ImageIcon(buttonIcon));
        toolbarButton.setToolTipText("left");
        toolbarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        this.add(toolbarButton);
        

        this.addSeparator();
        
        JLabel pageLabel = new JLabel("Page : 0 / 10");
        this.add(pageLabel);
        
        this.addSeparator();

        buttonIcon = getClass().getClassLoader().getResource("application/resources/icons/right.png");
        toolbarButton = new JButton(new ImageIcon(buttonIcon));
        toolbarButton.setToolTipText("right");
        toolbarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        this.add(toolbarButton);
        
        buttonIcon = getClass().getClassLoader().getResource("application/resources/icons/next.png");
        toolbarButton = new JButton(new ImageIcon(buttonIcon));
        toolbarButton.setToolTipText("next");
        toolbarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        this.add(toolbarButton);
        
        this.add(Box.createHorizontalGlue());
        pageSize = new JComboBox<Integer>(pageSizes);
        pageSize.setMaximumSize(new Dimension(50,40));
        this.add(pageSize);
        
   
    }
	
	public void setPageSize(int size){
        pageSize.setSelectedItem(size);
	}

}
