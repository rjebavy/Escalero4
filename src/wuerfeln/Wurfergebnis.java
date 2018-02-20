package wuerfeln;

public class Wurfergebnis {
	private Wuerfel[] wuerfelsatz = new Wuerfel[5];
	private int bild1; 
	private int bild2;
	private int bilder1;
	private int bilder2;
	private int gleich1;
	private int gleich2;
	private int ungleich;
	private int ass;
	private int neun;
	
	
	// Konstruktor hier nur mit Eigenschaft Würfelsatz
	public Wurfergebnis(Wuerfel[] wsatz) {
		this.wuerfelsatz = wsatz; 
	}


}
