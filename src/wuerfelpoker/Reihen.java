package wuerfelpoker;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.function.BiConsumer;


public class Reihen {
	private Integer[] reiheninhalt = new Integer[3];
	private HashMap<Integer, Integer[]> spielstandtabelle = new HashMap<Integer, Integer[]>();
	
	public Reihen() {
		
	}

	// Erzeuge eine Spielstandtabelle zu 11 Zeilen und 3 Reihen. 
	public HashMap<Integer, Integer[]> erzeugeSpielstandTabellen() {
		 HashMap<Integer, Integer[]> sstabel = new HashMap<Integer, Integer[]>();
		 reiheninhalt[0] = null;
		 reiheninhalt[1] = null;
		 reiheninhalt[2] = null;
		 // Und weil jetzt Schlüssel Integer und nicht Enum ist geht: 
			for(int r = 0; r < 11; r++) {
				sstabel.put(r, reiheninhalt);
			}
	return sstabel;
	}

	//Die Tabelle mit Schlüssel & Reiheinhalte anzeigen. 
	public HashMap<Integer, Integer[]> zeigeSpielstandTabellenReihen(HashMap<Integer, Integer[]> sstabel) {
		HashMap<Integer, Integer[]> sstb = sstabel; 
		String bild = "";
		for(int z = 0; z < 11; z++) {
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
		default: 
			bild = "Fehler!";
		}
		return bild; 
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

