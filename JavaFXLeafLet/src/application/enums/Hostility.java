package application.enums;

public enum Hostility {
	Ami,Inconnu,Hostile;
	
	public static Hostility fromString(String hostiliyStr){
		Hostility res;
		
		switch(hostiliyStr.toLowerCase()){
			case "ami":
				res = Ami;
				break;
			case "inconnu":
				res = Inconnu;
				break;
			case "hostile":
				res = Hostile;
				break;
			default:
				res = Inconnu;
				break;
		}
		
		return res;		
	}
}
