package application.presenters;

import java.util.ArrayList;
import java.util.List;

import application.interfaces.LocalisationsView;
import application.interfaces.Paginable;
import application.interfaces.localisationFilterable;
import application.models.LocalisationModel;

public class LocalisationsPresenter implements Paginable, localisationFilterable {
	
	private final LocalisationModel model;
	private List<LocalisationsView> views;
	
	public LocalisationsPresenter(LocalisationModel model){
		this.model = model;
		this.views = new ArrayList<LocalisationsView>();
	}

	public List<LocalisationsView> getViews() {
		return views;
	}
	
	public void addView(LocalisationsView view){
		this.views.add(view);
		view.updateLocalisations(model.getLocalisations());
	}
	
	public void removeView(LocalisationsView view){
		this.views.remove(view);
	}
	
	public void updateViews(){
		for(LocalisationsView view : this.views){
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


}
