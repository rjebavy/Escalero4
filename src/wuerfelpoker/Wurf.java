package wuerfelpoker;

public class Wurf {
	private int wurfzaehler = 0; // z�hlt von 3 auf 0 runter, je Spieler 3 W�rfe pro Runde. 
	private Wuerfel[] wuerfelsatz = new Wuerfel[5]; // W�rfelsatz f�r W�rfelfeld mit f�nf W�rfeln. 
	private boolean[] nichtgehalten = new boolean[5]; // Boolean Array f�r Haltefeld mit f�nf CheckBoxen. 
	private boolean moeglicheServierung = false; // Wird jedesmal auf 'wahr' gesetzt wenn kein W�rfel gehalten ist. 
	private int gehalten = 0; // Z�hlt 1 rauf bei halten(), 1 runter bei freigeben(), Indikator f�r kein W�rfel gehalten.  
	
	// Dieser Kommentar nur f�r Git commit GitHub Remote Escalero4 Push/Pull Test. 27.2.18-20:20. 
	
	
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
		// Da kein W�rfel gehalten wird ist auch Servierung m�glich.
		this.setzeServierungMoeglich();
	}	

	// Setzt Wurfeigenschaft moeglicheServierung auf wahr.
	public void setzeServierungMoeglich() {
		this.moeglicheServierung = true; 
	} 
	
	// Den angegebenen W�rfel halten. 
	public boolean halten(int wuerfel) {
		if(wuerfel >= 0 && wuerfel <= 4) {
			nichtgehalten[wuerfel] = false;
			System.out.println("Haltemaske, W�rfel " + wuerfel + " = " + nichtgehalten[wuerfel]);
			// gehalten 1 raufz�hlen, als Indikator f�r m�gliche Servierung wenn kein W�rfel gehalten. 
			if(gehalten < 5) {
				gehalten++; 
				System.out.println("halten, W�rfel " + wuerfel + "; gehalten = " + gehalten);
			}
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
			// gehalten 1 runterz�hlen, wenn 0 - d.h. kein W�rfel gehalten - dann Servierung m�glich setzen. 
			if(gehalten > 0) {
				gehalten--; 
				System.out.println("freigeben, W�rfel " + wuerfel + "; gehalten = " + gehalten);
			}
			if(gehalten == 0){
				this.setzeServierungMoeglich();
			}
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

	
	
	// Standard Getter & Setter
	public boolean isMoeglicheServierung() {
		return moeglicheServierung;
	}

	public int getGehalten() {
		return gehalten;
	}

	public void setWurfzaehler(int wurfzaehler) {
		this.wurfzaehler = wurfzaehler;
	}
		
}
