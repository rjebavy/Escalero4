package wuerfelpoker;

public class Wurf {
	private int wurfzaehler = 0; // zählt von 3 auf 0 runter, je Spieler 3 Würfe pro Runde. 
	private Wuerfel[] wuerfelsatz = new Wuerfel[5]; // Würfelsatz für Würfelfeld mit fünf Würfeln. 
	private boolean[] nichtgehalten = new boolean[5]; // Boolean Array für Haltefeld mit fünf CheckBoxen. 
	private boolean moeglicheServierung = false; // Wird jedesmal auf 'wahr' gesetzt wenn kein Würfel gehalten ist. 
	private int gehalten = 0; // Zählt 1 rauf bei halten(), 1 runter bei freigeben(), Indikator für kein Würfel gehalten.  
	
	// Dieser Kommentar nur für Git commit GitHub Remote Escalero4 Push/Pull Test. 27.2.18-20:20. 
	
	
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
		this.setzeServierungMoeglich();
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
			// gehalten 1 raufzählen, als Indikator für mögliche Servierung wenn kein Würfel gehalten. 
			if(gehalten < 5) {
				gehalten++; 
				System.out.println("halten, Würfel " + wuerfel + "; gehalten = " + gehalten);
			}
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
			// gehalten 1 runterzählen, wenn 0 - d.h. kein Würfel gehalten - dann Servierung möglich setzen. 
			if(gehalten > 0) {
				gehalten--; 
				System.out.println("freigeben, Würfel " + wuerfel + "; gehalten = " + gehalten);
			}
			if(gehalten == 0){
				this.setzeServierungMoeglich();
			}
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
