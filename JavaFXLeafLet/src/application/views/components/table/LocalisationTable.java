package application.views.components.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import application.enums.Hostility;
import application.interfaces.Paginable;

public class LocalisationTable extends JTable implements Paginable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7111456720317499387L;

	public LocalisationTable(LocalisationTableModel model) {
		super(model);
    	getTableHeader().setBackground(Color.DARK_GRAY);
    	getTableHeader().setForeground(Color.WHITE);
    	setRowHeight(35);
	}
	
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
	{
		Component c = super.prepareRenderer(renderer, row, column);

		//  Alternate row color
		Object value = getValueAt(row, column);
		
		if (!isRowSelected(row)){
			if(value.getClass() == Hostility.class){
				value = (Hostility) value;
				c.setBackground(value == Hostility.Ami ? Color.GREEN : (value == Hostility.Hostile ? Color.RED : Color.ORANGE));
			}
			else {
				c.setBackground(row % 2 == 0 ? getBackground() : Color.LIGHT_GRAY);
			}
		}

		return c;
	}

	@Override
	public void goToPage(int page) {
		((LocalisationTableModel) getModel()).changePage(page);
		revalidate();
		repaint();		
	}

	@Override
	public void changePageSize(int size) {
		((LocalisationTableModel) getModel()).changePageSize(size);
		revalidate();
		repaint();
	}

	@Override
	public int getNbOfRecords() {
		return ((LocalisationTableModel) getModel()).getNbOfLocalisations();
	}

}
