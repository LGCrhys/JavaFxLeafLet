package application.views.components;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import application.enums.Hostility;
import application.models.Localisation;
import application.services.LocalisationService;

public class LocalisationTableModel implements TableModel {
	
	private List<Localisation> localisations = new ArrayList<Localisation>();
	private final String[] headers = { "Name", "Longitude", "Latitude", "Hostility"};
	
	
	public LocalisationTableModel() {
		LocalisationService locService = LocalisationService.getInstance();
		localisations = locService.getLocalisations();
	}

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
		return localisations.size();
	}

	@Override
	public Object getValueAt(int rowIdx, int columnIdx) {
		switch (columnIdx) {

		case 0:
			// Name
			return localisations.get(rowIdx).getName();

		case 1:
			// Longitude
			return localisations.get(rowIdx).getLongitude();

		case 2:
			// Latitude
			return localisations.get(rowIdx).getLatitude();

		case 3:
			// Hostility
			return localisations.get(rowIdx).getHostility();

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
			// Name
			return String.class;

		case 1:
			// Longitude
			return Double.class;

		case 2:
			// Latitude
			return Double.class;

		case 3:
			// Hostility
			return Hostility.class;

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

}
