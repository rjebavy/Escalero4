package wuerfelpoker;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.BiConsumer;


public class Reihen {
	private Integer[] reiheninhalt = new Integer[3];
	private HashMap<Integer, Integer[]> spielstandtabelle = new HashMap<Integer, Integer[]>();
	
	public Reihen() {
		
	}

	// Erzeuge eine Spielstandtabelle zu 12 Zeilen und 3 Reihen; 12 Zeile für Summe!!  
	public HashMap<Integer, Integer[]> erzeugeSpielstandTabellen() {
		 HashMap<Integer, Integer[]> sstabel = new HashMap<Integer, Integer[]>();
		 reiheninhalt[0] = 1;
		 reiheninhalt[1] = 2;
		 reiheninhalt[2] = 255;
		 // Und weil jetzt Schlüssel Integer und nicht Enum ist geht: 
			for(int r = 0; r < 12; r++) {
				sstabel.put(r, reiheninhalt);
			}
	return sstabel;
	}

	//Die Tabelle mit Schlüssel & Reiheinhalte anzeigen. 
	public HashMap<Integer, Integer[]> zeigeSpielstandTabellenReihen(HashMap<Integer, Integer[]> sstabel) {
		HashMap<Integer, Integer[]> sstb = sstabel; 
		String bild = "";
		for(int z = 0; z < 12; z++) {
			bild = waehleSchluesselbild(z);
			Integer[] reiheninhalt = sstb.get(3);
			System.out.println("Schlüssel - " + bild + " mit Inhalt: " + reiheninhalt[0] + ", " + reiheninhalt[1] + ", " + reiheninhalt[2] + ".");	
		}
		return sstb;
	}
	
	// Wähle das Schlüsselbild für Auflisten des Inhalts. 
	public String waehleSchluesselbild(int schluesselbild) {
		String bild = null;
		switch (schluesselbild) {
		case 0:
			bild = "Neun";
			break;
		case 1:
			bild = "Zehn";
			break;
		case 2:
			bild = "Bube";
			break;
		case 3:
			bild = "Dame";
			break;
		case 4:
			bild = "König";
			break;
		case 5:
			bild = "Ass";
			break;
		case 6:
			bild = "Straße";
			break;
		case 7:
			bild = "FullsHouse";
			break;
		case 8:
			bild = "Poker";
			break;
		case 9:
			bild = "Grande";
			break;
		case 10:
			bild = "Streichung";
			break;
		case 11:
			bild = "Summe";
			break;
		default: 
			bild = "Fehler!";
		}
		return bild; 
	}

	
	
	
	// Reihensummen berechnen und in Zeile 12 eintragen. 
	public void berechneReihensummen(HashMap<Integer, Integer[]> sstabel) {
		Integer[] summe = new Integer[3];
		Integer[] zeile = new Integer[3];
		Integer[] filter = new Integer[3];		
		HashMap<Integer, Integer[]> hm = sstabel; 
		// Summen auf 0 setzen. 
		for(int s = 0; s < 3; s++) {
			summe[s] = 0;
		}
		// Zeilen 1 -11 (index 0 - 10) aufaddieren
		for(int i = 0; i < 11; i++) {
			zeile = hm.get(i);
			// Streichung rausfiltern; Wert über 100! Wahrscheinlich nehm ich 255. 
				for(int j = 0; j < 3; j++) {
					if( zeile[j] > 100 ) {
						filter[j] = 0;
					}
					else {
						filter[j] = zeile[j];
					}
				}
			summe[0] = summe[0] + filter[0]; 
			summe[1] = summe[1] + filter[1]; 
			summe[2] = summe[2] + filter[2]; 
		}
		// Summe in Zeile 12 (index/key 11) eintragen. 
		hm.put(11, summe);
	}
	
	
	
	// Standard Getter & Setter	
	public HashMap<Integer, Integer[]> getSpielstandtabelle() {
		return spielstandtabelle;
	}

	public void setSpielstandtabelle(HashMap<Integer, Integer[]> spielstandtabelle) {
		this.spielstandtabelle = spielstandtabelle;
	}

	@Override
	public String toString() {
		return "Reihe [spielstandreihen=" + spielstandtabelle + "]";
	}

	public void zeigeSpielstandTabellenReihen(Reihen reihen) {
		// TODO Auto-generated method stub
		
	}

	
}

