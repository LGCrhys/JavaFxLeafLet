package application.views.components;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import application.enums.Hostility;

public class LocalisationTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7111456720317499387L;

	public LocalisationTable(LocalisationTableModel model) {
		super(model);
    	getTableHeader().setBackground(Color.DARK_GRAY);
    	getTableHeader().setForeground(Color.WHITE);
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

}
