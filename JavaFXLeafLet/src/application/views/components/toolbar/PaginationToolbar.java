package application.views.components.toolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import application.data.Localisation;
import application.interfaces.LocalisationsView;
import application.interfaces.Paginable;

public class PaginationToolbar extends JToolBar  implements LocalisationsView{

	/**
	 * 
	 */
	private static final long serialVersionUID = -859391148827066717L;
	private static final Integer[] pageSizes= new Integer[]{10,20,40,60,80,100};
	private JComboBox<Integer> pageSizesCombo;
	private JLabel pageLabel;
	private int currentPage = 0;
	private int pageSize = 20;
	private int nbPages;
	private int recordsCounter = 0;
	private Paginable presenter;
	
	public PaginationToolbar(Paginable paginablePresenter){
		super();
		this.presenter = paginablePresenter;
		recordsCounter = this.presenter.getNbOfRecords();
		nbPages  = recordsCounter / pageSize ; 
		setFloatable(false);
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(200,30));
		initButtons();
	}	
	
	public void initButtons() {
        JButton toolbarButton = null;
        URL buttonIcon = null;
        
        buttonIcon = getClass().getClassLoader().getResource("application/resources/icons/previous.png");
        toolbarButton = new JButton(new ImageIcon(buttonIcon));
        toolbarButton.setToolTipText("First Page");
        toolbarButton.setPreferredSize(new Dimension(24,24));
        toolbarButton.setBackground(Color.WHITE);
        toolbarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	currentPage = 0;
            	presenter.goToPage(currentPage);
                updateLabel();
            }
        });
        this.add(toolbarButton);

        buttonIcon = getClass().getClassLoader().getResource("application/resources/icons/left.png");
        toolbarButton = new JButton(new ImageIcon(buttonIcon));
        toolbarButton.setToolTipText("Previous");
        toolbarButton.setPreferredSize(new Dimension(24,24));
        toolbarButton.setBackground(Color.WHITE);
        toolbarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(currentPage > 0){
            		currentPage--;
            	}
            	presenter.goToPage(currentPage);
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
        toolbarButton.setToolTipText("Next");
        toolbarButton.setPreferredSize(new Dimension(24,24));
        toolbarButton.setBackground(Color.WHITE);
        toolbarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(currentPage < nbPages){
            		currentPage++;
            	}
            	presenter.goToPage(currentPage);
                updateLabel();
            }
        });
        this.add(toolbarButton);
        
        buttonIcon = getClass().getClassLoader().getResource("application/resources/icons/next.png");
        toolbarButton = new JButton(new ImageIcon(buttonIcon));
        toolbarButton.setToolTipText("Last Page");
        toolbarButton.setPreferredSize(new Dimension(24,24));
        toolbarButton.setBackground(Color.WHITE);
        toolbarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	currentPage = nbPages;
            	presenter.goToPage(nbPages);
                updateLabel();
            }
        });
        this.add(toolbarButton);
        
        this.add(Box.createHorizontalGlue());
        pageSizesCombo = new JComboBox<Integer>(pageSizes);
        pageSizesCombo.setMaximumSize(new Dimension(50,40));
        pageSizesCombo.setSelectedItem(pageSize);
        toolbarButton.setBackground(Color.WHITE);
        pageSizesCombo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				pageSize = (int) e.getItem();
				nbPages = recordsCounter / pageSize;
			    if(currentPage > nbPages){
		        	currentPage = nbPages;
		        	presenter.goToPage(currentPage);
		        }
			    presenter.changePageSize(pageSize);
				updateLabel();
			}
		});
        this.add(pageSizesCombo);   
        
        presenter.changePageSize(pageSize);
        this.updateLabel();
   
    }
	
	public void updateLabel(){
		pageLabel.setText(String.format("Page : %d / %d", currentPage, nbPages));
	}

	@Override
	public void updateLocalisations(List<Localisation> localisations) {
		int nbOfRecords = this.presenter.getNbOfRecords();
		if(nbOfRecords != recordsCounter){
			currentPage = 0;
			recordsCounter = nbOfRecords;
		}
		nbPages  = recordsCounter / pageSize ;
		updateLabel();		
	}
}
