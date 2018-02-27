package wuerfeln;

public class Wurf {
	private int wurfzaehler; 
	private Wuerfel[] wuerfelsatz = new Wuerfel[5];
	private boolean[] nichtgehalten = new boolean[5]; 
	private boolean moeglicheServierung = false; 
	
	// Konstruktor hier nur mit Eigenschaft Wurfzähler
	public Wurf(int wurf) {
		this.wurfzaehler = wurf; 
	}
	
	// Würfelsatz mit Würfeln im ungesetzten, initialen Zustand erzeugen. 
	public Wuerfel[] initialisiereWuerfelsatz() {
		for(int w = 0; w < wuerfelsatz.length; w++) {
				wuerfelsatz[w] = new Wuerfel(0);
			 System.out.println("Würfelsatz, Würfel " + w + " - " + ", Wert = " + wuerfelsatz[w].getWert());
			}
			System.out.println("\n");
			return wuerfelsatz;
	}

	// Haltemaske im ungesetzten, initialen Zustand erzeugen. 
	public void initialisiereHaltemaske() {
		for(int h = 0; h < nichtgehalten.length; h++) {
			nichtgehalten[h] = true;
			 System.out.println("Haltemaske, Würfel " + h + " = " + nichtgehalten[h]);
			}
		// Da kein Würfel gehalten wird ist auch Servierung möglich.
		
		
	}	

	// Setzt Wurfeigenschaft moeglicheServierung auf wahr.
	public void setzeServierungMoeglich() {
		this.moeglicheServierung = true; 
	} 
	
	// Den angegebenen Würfel halten. 
	public boolean halten(int wuerfel) {
		if(wuerfel >= 0 && wuerfel <= 4) {
			nichtgehalten[wuerfel] = false;
			System.out.println("Haltemaske, Würfel " + wuerfel + " = " + nichtgehalten[wuerfel]);
			return true;
				}
		System.out.println("\nWürfelnummer außerhalb erlaubten Bereichs (0-4)!");
		return false;
	}	

	// Den angegebenen Würfel wieder freigeben. 
	public boolean freigeben(int wuerfel) {
		if(wuerfel >= 0 && wuerfel <= 4) {
			nichtgehalten[wuerfel] = true;
			System.out.println("Haltemaske, Würfel " + wuerfel + " = " + nichtgehalten[wuerfel]);
			return true;
				}
		System.out.println("\nWürfelnummer außerhalb erlaubten Bereichs (0-4)!");
		return false;
	}
	
	// Zufallszahl im Wertebereich 1-6 berechnen. 
	public int berechneZufallszahl() {
		int zahl = (int)(Math.random() * 6 + 1);
		return zahl; 
	} 
	
	// Würfeln aller nichtgehaltener Würfel. 
	public boolean wuerfleUngehaltene(Wuerfel[] wsatz) {
		if(wurfzaehler >= 0 && wurfzaehler < 3) {
			for(int w = 0; w < wsatz.length; w++) {
				if(nichtgehalten[w] == true) {
					wsatz[w].setWert(berechneZufallszahl());
					System.out.println("gewürfelt, Würfel " + w + " - " + "Wert = " + wsatz[w].getWert());
					}
		 		}
			return true;
		}
	System.out.println("\nwürfle; Wurfzähler ist nicht 0, 1 oder 2!");
	return false;
	}	
	
	// Wurfzähler von 3 auf 0 runterzählen. 
	public void wurfRunterzaehlen() {
		if(wurfzaehler > 0 && wurfzaehler <= 3) 
			wurfzaehler--;
		else
			System.out.println("\nWurfzähler außerhalb erlaubten Bereichs (1-3)!");	
	}

	// Wurfzähler auslesen
	public int getWurfzaehler() {
		return wurfzaehler;
	}
}
