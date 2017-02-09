package application.views.components.toolbar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
	private Paginable paginableComponent;
	private static final Integer[] pageSizes= new Integer[]{10,20,40,60,80,100};
	private JComboBox<Integer> pageSizesCombo;
	private JLabel pageLabel;
	private int currentPage = 0;
	private int pageSize = 40;
	private int nbPages;
	private int recordsCounter = 0;
	
	public PaginationToolbar(Paginable paginableComponent){
		super();
		this.paginableComponent = paginableComponent;
		recordsCounter = this.paginableComponent.getNbOfRecords();
		nbPages  = recordsCounter / pageSize ; 
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
            	currentPage = 0;
            	paginableComponent.goToPage(currentPage);
                updateLabel();
            }
        });
        this.add(toolbarButton);

        buttonIcon = getClass().getClassLoader().getResource("application/resources/icons/left.png");
        toolbarButton = new JButton(new ImageIcon(buttonIcon));
        toolbarButton.setToolTipText("left");
        toolbarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(currentPage > 0){
            		currentPage--;
            	}
            	paginableComponent.goToPage(currentPage);
                updateLabel();
            }
        });
        this.add(toolbarButton);
        

        this.addSeparator();
        
        pageLabel = new JLabel();
        this.add(pageLabel);
        
        this.addSeparator();

        buttonIcon = getClass().getClassLoader().getResource("application/resources/icons/right.png");
        toolbarButton = new JButton(new ImageIcon(buttonIcon));
        toolbarButton.setToolTipText("right");
        toolbarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(currentPage < nbPages){
            		currentPage++;
            	}
            	paginableComponent.goToPage(currentPage);
                updateLabel();
            }
        });
        this.add(toolbarButton);
        
        buttonIcon = getClass().getClassLoader().getResource("application/resources/icons/next.png");
        toolbarButton = new JButton(new ImageIcon(buttonIcon));
        toolbarButton.setToolTipText("next");
        toolbarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	currentPage = nbPages;
            	paginableComponent.goToPage(nbPages);
                updateLabel();
            }
        });
        this.add(toolbarButton);
        
        this.add(Box.createHorizontalGlue());
        pageSizesCombo = new JComboBox<Integer>(pageSizes);
        pageSizesCombo.setMaximumSize(new Dimension(50,40));
        pageSizesCombo.setSelectedItem(pageSize);
        pageSizesCombo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				pageSize = (int) e.getItem();
				nbPages = recordsCounter / pageSize;
			    if(currentPage > nbPages){
		        	currentPage = nbPages;
		        	paginableComponent.goToPage(currentPage);
		        }
				paginableComponent.changePageSize(pageSize);
				updateLabel();
			}
		});
        this.add(pageSizesCombo);   
        
        paginableComponent.changePageSize(pageSize);
        this.updateLabel();
   
    }
	
	public void updateLabel(){
		pageLabel.setText(String.format("Page : %d / %d", currentPage, nbPages));
	}

}
