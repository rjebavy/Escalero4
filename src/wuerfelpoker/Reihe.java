package wuerfelpoker;

import java.util.HashMap;


public class Reihe {
	// public enum bild {Neuner, Zehner, Bube, Dame, Koenig, Ass, Strasse, Fullhouse, Poker, Grande, Streichung}
	// private HashMap<Enum, Integer> spielstandreihe = new HashMap<Enum, Integer>();
	private HashMap<Integer, Integer> spielstandreihe = new HashMap<Integer, Integer>();
	
	public Reihe() {
		
	}

	/* public HashMap<Enum, Integer> erzeugeSpielstandReihe() {
		 HashMap<Enum, Integer> spielstandreihe = new HashMap<Enum, Integer>();
		 // Integer wert = 20;
		 spielstandreihe.put(bild.Neuner, null);
		 spielstandreihe.put(bild.Zehner, null);
		 spielstandreihe.put(bild.Bube, null);
		 spielstandreihe.put(bild.Dame, null);
		 spielstandreihe.put(bild.Koenig, null);
		 spielstandreihe.put(bild.Ass, null);
		 spielstandreihe.put(bild.Strasse, null);
		 spielstandreihe.put(bild.Fullhouse, null);
		 spielstandreihe.put(bild.Poker, null);
		 spielstandreihe.put(bild.Grande, null);
		 spielstandreihe.put(bild.Streichung, null);
	return spielstandreihe;
}
*/

	public HashMap<Integer, Integer> erzeugeSpielstandReihe() {
		 HashMap<Integer, Integer> spielstandreihe = new HashMap<Integer, Integer>();
		 // Integer wert = 20;
		 /*spielstandreihe.put(bild.Neuner, null);
		 spielstandreihe.put(bild.Zehner, null);
		 spielstandreihe.put(bild.Bube, null);
		 spielstandreihe.put(bild.Dame, null);
		 spielstandreihe.put(bild.Koenig, null);
		 spielstandreihe.put(bild.Ass, null);
		 spielstandreihe.put(bild.Strasse, null);
		 spielstandreihe.put(bild.Fullhouse, null);
		 spielstandreihe.put(bild.Poker, null);
		 spielstandreihe.put(bild.Grande, null);
		 spielstandreihe.put(bild.Streichung, null);
		 */ 
		 // Und weil jetzt Integer und nicht Enum geht: 
			for(int r = 0; r < 11; r++) {
				spielstandreihe.put(r, null);
			}
	return spielstandreihe;
}
	
	public HashMap<Integer, Integer> getSpielstandreihe() {
		return spielstandreihe;
	}

	public void setSpielstandreihe(HashMap<Integer, Integer> spielstandreihe) {
		this.spielstandreihe = spielstandreihe;
	}

	@Override
	public String toString() {
		return "Reihe [spielstandreihe=" + spielstandreihe + "]";
	}
	

	
	
}
