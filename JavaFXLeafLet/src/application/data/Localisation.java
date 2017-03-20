package application.data;

import application.enums.Hostility;

public class Localisation {

	private int id;
	private String name;
	private Double longitude;
	private Double latitude;
	private Hostility hostility;


	public Localisation(int id){
		this.id = id;
		this.name = "";
		this.longitude = 0.0;
		this.latitude = 0.0;
		this.hostility = Hostility.Inconnu;
	}
	
	public Localisation(int id, String name, Double longitude, Double latitude, Hostility hostility){
		this.id = id;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.hostility = hostility;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public void setHostility(Hostility hostility) {
		this.hostility = hostility;
	}
	
}
