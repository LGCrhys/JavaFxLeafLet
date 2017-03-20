package application.presenters;

import java.util.ArrayList;
import java.util.List;

import application.enums.Hostility;
import application.interfaces.LocalisationsView;
import application.interfaces.Paginable;
import application.interfaces.localisationFilterable;
import application.models.LocalisationModel;

public class LocalisationsPresenter implements Paginable, localisationFilterable {
	
	private final LocalisationModel model;
	private List<LocalisationsView> localisationViews;
	
	public LocalisationsPresenter(LocalisationModel model){
		this.model = model;
		this.localisationViews = new ArrayList<LocalisationsView>();
	}

	public List<LocalisationsView> getViews() {
		return localisationViews;
	}
	
	public void addView(Object view){
		if(view instanceof LocalisationsView){
			this.localisationViews.add((LocalisationsView) view);
			((LocalisationsView) view).updateLocalisations(model.getLocalisations());
		}
	}
	
	public void removeView(Object view){
		if(view instanceof LocalisationsView){
			this.localisationViews.remove(view);
		}
	}
	
	public void updateViews(){
		for(LocalisationsView view : this.localisationViews){
			view.updateLocalisations(this.model.getLocalisations());
		}
	}

	@Override
	public void goToPage(int page) {
		this.model.changePage(page);
		this.updateViews();		
	}

	@Override
	public void changePageSize(int size) {
		this.model.changePageSize(size);
		this.updateViews();		
	}

	@Override
	public int getNbOfRecords() {
		return this.model.getNbOfLocalisations();
	}

	@Override
	public void filterLocalisationsByAttr(String attr, Object value) {
		this.model.filterLocalisationsByAttr(attr,value);
		this.updateViews();		
	}
	
	public List<Hostility> getHostilityTypes(){
		return this.model.getHostilityTypes();
	}


}
