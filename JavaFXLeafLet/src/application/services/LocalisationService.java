package application.services;

import java.util.List;

import application.data.LocalisationDomParser;
import application.models.Localisation;

public class LocalisationService {

	private static LocalisationService instance;
	
	private static List<Localisation> localisations;
	
	private LocalisationService(){
		//parse json and get all localisations
		LocalisationDomParser parser = new LocalisationDomParser("src/application/data/Locs.xml");
		localisations = parser.parse();		
	}
	
	public static synchronized LocalisationService getInstance(){
		if(instance == null){
			instance = new LocalisationService();
		}
		return instance;
	}
	
	public synchronized List<Localisation> getLocalisations(){
		return localisations;
	}	
}