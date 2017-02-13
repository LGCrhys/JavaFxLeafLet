package application.views.components.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import application.interfaces.Paginable;

public class LocalisationDescriptionTable extends JTable implements Paginable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7111456720317499387L;
	
	public LocalisationDescriptionTable(LocalisationDescriptionTableModel model) {
		super(model);
		setTableHeader(null);
		setShowVerticalLines(false);
		setSelectionBackground(Color.LIGHT_GRAY);

        setIntercellSpacing(new Dimension(0, 0));
		
		Dimension tableSize = getPreferredSize();
        getColumn("Hostility").setPreferredWidth(Math.round((tableSize.width)* 0.15f));
        getColumn("Description").setPreferredWidth(Math.round((tableSize.width)* 0.70f));
        getColumn("Settings").setPreferredWidth(Math.round((tableSize.width)* 0.15f));
                
    	setRowHeight(60);
	}
	
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
	{
		Component c = super.prepareRenderer(renderer, row, column);
	
		if (isRowSelected(row)){
			((JComponent) c).setBorder(BorderFactory.createEmptyBorder());
		}

		return c;
	}

	 
		
	@Override
	public void goToPage(int page) {
		((LocalisationDescriptionTableModel) getModel()).changePage(page);
		revalidate();
		repaint();		
	}

	@Override
	public void changePageSize(int size) {
		((LocalisationDescriptionTableModel) getModel()).changePageSize(size);
		revalidate();
		repaint();
	}

	@Override
	public int getNbOfRecords() {
		return ((LocalisationDescriptionTableModel) getModel()).getNbOfLocalisations();
	}

}
