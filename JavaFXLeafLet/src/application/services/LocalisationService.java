package application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import application.data.Localisation;
import application.data.LocalisationDomParser;
import application.enums.Hostility;

public class LocalisationService {

	private static LocalisationService instance;	
	private static List<Localisation> localisations;
	private static List<Localisation> allLocalisations;
	
	private LocalisationService(){
		//parse json and get all localisations
		LocalisationDomParser parser = new LocalisationDomParser("src/application/data/Locs.xml");
		allLocalisations = parser.parse();
		localisations = new ArrayList<Localisation>(allLocalisations);
	}
	
	public static LocalisationService getInstance(){
		if(instance == null){
			instance = new LocalisationService();
		}
		return instance;
	}
	
	public List<Localisation> getLocalisations(int offset, int recordsNb){
		int start = offset * recordsNb;
		int end = recordsNb * (offset + 1);
		return localisations.subList(start, end>localisations.size()?localisations.size():end);
	}
	
	public void filterLocalisations(String filteredAttribute, Object filteredValue){
		//Apply filter:
		if(filteredAttribute.equals("hostility")){
			//TODO manage 
			localisations = allLocalisations.stream().filter(loc -> ((List<Localisation>) filteredValue).contains(loc.getHostility())).collect(Collectors.toList());
		}
	}

	public int getNbOfLocalisations() {
		return localisations.size();
	}

	public List<Hostility> getHostilityTypes() {
		Set<Hostility> collect = localisations.stream().map(sc -> sc.getHostility()).collect(Collectors.toSet());
		return new ArrayList<Hostility>(collect);
	}	
}