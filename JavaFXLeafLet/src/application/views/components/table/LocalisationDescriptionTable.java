package application.views.components.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import application.data.Localisation;
import application.interfaces.LocalisationsView;

public class LocalisationDescriptionTable extends JTable implements LocalisationsView{

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
	public void updateLocalisations(List<Localisation> localisations) {
		((LocalisationDescriptionTableModel) this.getModel()).setLocalisations(localisations);
		revalidate();
        repaint();
	}

}
