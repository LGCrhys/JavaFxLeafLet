package application.models;

import java.util.ArrayList;
import java.util.List;

import application.data.Localisation;
import application.enums.Hostility;
import application.services.LocalisationService;

public class LocalisationModel {
	
	private List<Localisation> localisations = new ArrayList<Localisation>();

	//Used to manage pagination of datas
	private int pageSize = 20;
	private int currentPage = 0;
	
	public LocalisationModel(){
		updateData();
	}

	public void updateData(){
		LocalisationService locService = LocalisationService.getInstance();
		localisations = locService.getLocalisations(currentPage,pageSize);
	}
	
    public void changePage(int page){
        this.currentPage = page;
        updateData();
    }
    
    public void changePageSize(int pageSize){
        this.pageSize= pageSize;
        updateData();
    }

	public void filterLocalisationsByAttr(String attr, Object value) {
		currentPage = 0;
		LocalisationService locService = LocalisationService.getInstance();
		locService.filterLocalisations(attr, value);
		updateData();
	}
		
	public int getNbOfLocalisations(){
		LocalisationService locService = LocalisationService.getInstance();
		return locService.getNbOfLocalisations();		
	}
	
	public List<Localisation> getLocalisations(){
		return localisations;
	}
	
	public Localisation getLocalisation(int idx){
		return localisations.get(idx);
	}

	public List<Hostility> getHostilityTypes() {
		LocalisationService locService = LocalisationService.getInstance();
		return locService.getHostilityTypes();	
	}

}
