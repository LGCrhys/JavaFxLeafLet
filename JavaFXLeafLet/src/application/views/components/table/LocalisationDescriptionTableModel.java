package application.views.components.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import application.data.Localisation;
import application.enums.Hostility;

public class LocalisationDescriptionTableModel implements TableModel { 
	
	private List<Localisation> localisations = new ArrayList<Localisation>();

	private final String[] headers = { "Hostility", "Description", "Settings"};
		
	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public String getColumnName(int columnIdx) {
		return headers[columnIdx];
	}

	@Override
	public int getRowCount() {
		if(localisations == null){
			return 0;
		}
		return localisations.size();
	}

	@Override
	public Object getValueAt(int rowIdx, int columnIdx) {
		switch (columnIdx) {

		case 0:
			// Hostility
			Hostility hostility = localisations.get(rowIdx).getHostility();
			switch(hostility){
				case Ami:
					return new ImageIcon(getClass().getClassLoader().getResource("application/resources/icons/bullet_green.png"));
				case Inconnu:
					return new ImageIcon(getClass().getClassLoader().getResource("application/resources/icons/bullet_yellow.png"));
				case Hostile:
					return new ImageIcon(getClass().getClassLoader().getResource("application/resources/icons/bullet_red.png"));
				default:
					return new ImageIcon(getClass().getClassLoader().getResource("application/resources/icons/bullet_yellow.png"));
			}

		case 1:
			// Description
			Localisation localisation = localisations.get(rowIdx);
			StringBuilder builder = new StringBuilder();
			builder.append("<html>")
			.append("<b>Name : </b>").append(localisation.getName()).append("<br>")
			.append("<b>Longitude : </b>").append(localisation.getLongitude()).append("<br>")
			.append("<b>Latitude : </b>").append(localisation.getLatitude()).append("<br>")
			.append("</html>");
			
			return builder.toString();

		case 2:
			// Settings
			return new ImageIcon(getClass().getClassLoader().getResource("application/resources/icons/menu.png"));


		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public void setValueAt(Object value, int rowIdx, int columnIdx) {
	}

	@Override
	public Class<?> getColumnClass(int columnIdx) {
		switch (columnIdx) {

		case 0:
			// Hostility
			return ImageIcon.class;

		case 1:
			// Description
			return String.class;

		case 2:
			// Settings
			return ImageIcon.class;

		default:
			throw new IllegalArgumentException();
		}
	}

	@Override
	public boolean isCellEditable(int rowIdx, int columnIdx) {
		return false;
	}
	
	@Override
	public void addTableModelListener(TableModelListener listener) {
	}

	@Override
	public void removeTableModelListener(TableModelListener listener) {
	}
	
	public void setLocalisations(List<Localisation> localisations) {
		this.localisations = localisations;
	}

}
