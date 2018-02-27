package wuerfeln;

public class Wurf {
	private int wurfzaehler; 
	private Wuerfel[] wuerfelsatz = new Wuerfel[5];
	private boolean[] nichtgehalten = new boolean[5]; 
	private boolean moeglicheServierung = false; 
	
	// Konstruktor hier nur mit Eigenschaft Wurfz�hler
	public Wurf(int wurf) {
		this.wurfzaehler = wurf; 
	}
	
	// W�rfelsatz mit W�rfeln im ungesetzten, initialen Zustand erzeugen. 
	public Wuerfel[] initialisiereWuerfelsatz() {
		for(int w = 0; w < wuerfelsatz.length; w++) {
				wuerfelsatz[w] = new Wuerfel(0);
			 System.out.println("W�rfelsatz, W�rfel " + w + " - " + ", Wert = " + wuerfelsatz[w].getWert());
			}
			System.out.println("\n");
			return wuerfelsatz;
	}

	// Haltemaske im ungesetzten, initialen Zustand erzeugen. 
	public void initialisiereHaltemaske() {
		for(int h = 0; h < nichtgehalten.length; h++) {
			nichtgehalten[h] = true;
			 System.out.println("Haltemaske, W�rfel " + h + " = " + nichtgehalten[h]);
			}
	}	
	
	// Den angegebenen W�rfel halten. 
	public boolean halten(int wuerfel) {
		if(wuerfel >= 0 && wuerfel <= 4) {
			nichtgehalten[wuerfel] = false;
			System.out.println("Haltemaske, W�rfel " + wuerfel + " = " + nichtgehalten[wuerfel]);
			return true;
				}
		System.out.println("\nW�rfelnummer au�erhalb erlaubten Bereichs (0-4)!");
		return false;
	}	

	// Den angegebenen W�rfel wieder freigeben. 
	public boolean freigeben(int wuerfel) {
		if(wuerfel >= 0 && wuerfel <= 4) {
			nichtgehalten[wuerfel] = true;
			System.out.println("Haltemaske, W�rfel " + wuerfel + " = " + nichtgehalten[wuerfel]);
			return true;
				}
		System.out.println("\nW�rfelnummer au�erhalb erlaubten Bereichs (0-4)!");
		return false;
	}
	
	// Zufallszahl im Wertebereich 1-6 berechnen. 
	public int berechneZufallszahl() {
		int zahl = (int)(Math.random() * 6 + 1);
		return zahl; 
	} 
	
	// W�rfeln aller nichtgehaltener W�rfel. 
	public boolean wuerfleUngehaltene(Wuerfel[] wsatz) {
		if(wurfzaehler >= 0 && wurfzaehler < 3) {
			for(int w = 0; w < wsatz.length; w++) {
				if(nichtgehalten[w] == true) {
					wsatz[w].setWert(berechneZufallszahl());
					System.out.println("gew�rfelt, W�rfel " + w + " - " + "Wert = " + wsatz[w].getWert());
					}
		 		}
			return true;
		}
	System.out.println("\nw�rfle; Wurfz�hler ist nicht 0, 1 oder 2!");
	return false;
	}	
	
	// Wurfz�hler von 3 auf 0 runterz�hlen. 
	public void wurfRunterzaehlen() {
		if(wurfzaehler > 0 && wurfzaehler <= 3) 
			wurfzaehler--;
		else
			System.out.println("\nWurfz�hler au�erhalb erlaubten Bereichs (1-3)!");	
	}

	// Wurfz�hler auslesen
	public int getWurfzaehler() {
		return wurfzaehler;
	}
}
