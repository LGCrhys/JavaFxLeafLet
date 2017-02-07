package application.models;

import application.enums.Hostility;

public class Localisation {

	private final String name;
	private final Double longitude;
	private final Double latitude;
	private Hostility hostility;

	public Localisation(String name, Double longitude, Double latitude, Hostility hostility){
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.hostility = hostility;
	}

	public String getName() {
		return name;
	}
	public Double getLongitude() {
		return longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public Hostility getHostility() {
		return hostility;
	}	
	public void setHostility(Hostility hostility) {
		this.hostility = hostility;
	}
	
}
