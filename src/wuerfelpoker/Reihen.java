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
	// "Leere" Spielstandtabelle alle Zellen auf 0.  
	public HashMap<Integer, Integer[]> erzeugeSpielstandTabellen() {
		 HashMap<Integer, Integer[]> sstabel = new HashMap<Integer, Integer[]>();
		 reiheninhalt[0] = 0;
		 reiheninhalt[1] = 0;
		 reiheninhalt[2] = 0;
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
			bild = "FullHouse";
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
	
	// Eintragen eines Ergebniswertes über die Reihenknöpfe. 
	public void eintragenReihe(HashMap<Integer, Integer[]> eintragtabelle, Integer spielstand_Y, Integer spielstand_X, Integer wert, Reihen eintragen) {
		// Der Aktionskode des Reihenknopfes übergibt die Eintragetabelle des jeweiligen Spielers, sowie die Y-Koordinate, entspricht der Tabellenzeile.  
		// Aber auch die X-Koordinate, welche dem Knopf entspricht: Index 0 bei [Reihe1], Index 1 bei [Reihe2] & Index 2 bei [Reihe3]
		boolean leer = false; 
		boolean gestrichen = true; 
		Integer[] eintragezeile = eintragtabelle.get(spielstand_Y); // Hole die 3 Zellen in Zeile Y. 
		// Hier wird geprüft ob die mit den übergebenen Koordinaten (Y Zeile, X Reihe bzw. Spalte) angegebene Tabellenzelle frei bzw. nicht bereits gestrichen ist. 
		if(eintragezeile[spielstand_X] == 0) {
				leer = true;
				gestrichen = false;
				}; 
		if(eintragezeile[spielstand_X] > 0 && eintragezeile[spielstand_X] <= 100 ) {
					leer = false;
					gestrichen = false;
					// TODO Alarm Message-Box Eintragezelle nicht frei!!
					System.out.println("eintragenReihe; Zelle mit Y-Koordinate " + spielstand_Y + " und X-Koordinate " + spielstand_X + " ist nicht leer! " + eintragezeile[spielstand_X]); 
				}; 
		if(eintragezeile[spielstand_X] == 255) {
				leer = false;
				gestrichen = true;
				// TODO Alarm Message-Box Eintragezelle bereits gestrichen!! 
				System.out.println("eintragenReihe; Zelle mit Y-Koordinate " + spielstand_Y + " und X-Koordinate " + spielstand_X + " wurde bereits gestrichen! " + eintragezeile[spielstand_X]); 
				}; 
		// Wenn bis dahin die Zelle spielstand_Y:spielstand_X leer und nicht gestrichen ist wird eingetragen sonst Fehlermeldung. 
		if(leer & !gestrichen) {
			eintragezeile[spielstand_X] = wert; 
			eintragtabelle.put(spielstand_Y, eintragezeile);
			System.out.println("eintragenReihe; es wurde der Wert " + wert + " in die Zelle mit Y-Koordinate " + spielstand_Y + " und X-Koordinate " + spielstand_X + " eingestragen! "); 
		}
		// TODO else Alarm Message-Box falls nicht schon oberhalb geklärt. 
		// TODO temporär zur Eintragekontrolle und weil die TableView, das Drecksding, nix anzeigt!! 
				// Dazu auc temporär 1 weitere Übergabeparameter: Reihen eintragen
		// Auslesen aus eintragetabelle1: 
		// alle: 
		System.out.println("\nAuslesen alle; aus eintragetabelle1 - "); 
		String bild = "";
		Integer[] rinhalt = new Integer[] {0, 0, 0};
		for(int z = 0; z < 12; z++) {
			bild = eintragen.waehleSchluesselbild(z);
			rinhalt = eintragtabelle.get(z);
			System.out.println("Schlüssel - " + bild + " mit Inhalt: " + rinhalt[0] + ", " + rinhalt[1] + ", " + rinhalt[2] + ".");	
		}
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

