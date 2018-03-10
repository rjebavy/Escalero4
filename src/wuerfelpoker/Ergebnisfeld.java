package wuerfelpoker;

public class Ergebnisfeld {
	private int eintrageWert;
	private String eintragenText;

	// Konstruktor 
	public Ergebnisfeld() {
	}

	// Ergebnisfeld leeren. 
	public void initialisiereErgebnisfeld() {
		eintrageWert = 0;
		eintragenText = " ";
	}
	
	// Text und Werte für Würfelbilder. 
	public void eintragenNeuner(Wurfergebnis ergebnis) {
		int anzahl = ergebnis.getNeun(); 
		eintragenText = anzahl + " Neuner. ";
		eintrageWert = anzahl * 1;
	}

	public void eintragenZehner(Wurfergebnis ergebnis) {
		int anzahl = ergebnis.getZehn(); 
		eintragenText = anzahl + " Zehner. ";
		eintrageWert = anzahl * 2;
	}

	public void eintragenBuben(Wurfergebnis ergebnis) {
		int anzahl = ergebnis.getBube(); 
		String text = "Buben. ";
		if(anzahl == 1) {
			text = "Bube. ";
		}
		eintragenText = anzahl + " " + text;
		eintrageWert = anzahl * 3;
	}

	public void eintragenDamen(Wurfergebnis ergebnis) {
		int anzahl = ergebnis.getDame(); 
		String text = "Damen. ";
		if(anzahl == 1) {
			text = "Dame. ";
		}
		eintragenText = anzahl + " " + text;
		eintrageWert = anzahl * 4;
	}

	public void eintragenKoenige(Wurfergebnis ergebnis) {
		int anzahl = ergebnis.getKoenig(); 
		String text = "Koenige. ";
		if(anzahl == 1) {
			text = "Koenig. ";
		}
		eintragenText = anzahl + " " + text;
		eintrageWert = anzahl * 5;
	}

	public void eintragenAsse(Wurfergebnis ergebnis) {
		int anzahl = ergebnis.getAss(); 
		String text = "Asse. ";
		if(anzahl== 1) {
			text = "Ass. ";
		}
		eintragenText = anzahl + " " + text;
		eintrageWert = anzahl * 6;
	}

	// Text und Werte für Würfelmuster. 
	public void eintragenStrasse(Wurfergebnis ergebnis, boolean serviert) {
		// Servierung auch prüfen
		int sv = 0; 
		String text = " ";
		if(serviert) {
			sv = 5; 
			text = "serviert. ";
		}
		eintragenText = "Straße " + text;
		eintrageWert = 20 + sv;
	}

	public void eintragenFullhouse(Wurfergebnis ergebnis, boolean serviert) {
		// Servierung auch prüfen
		int sv = 0; 
		String text = " ";
		if(serviert) {
			sv = 5; 
			text = "serviert. ";
		}
		eintragenText = "FullHouse " + text;
		eintrageWert = 30 + sv;
	}

	public void eintragenPoker(Wurfergebnis ergebnis, boolean serviert) {
		// Textfeld so breit, dass sich "FullHouse serviert. " darin ausgeht, also 20+2 = 22 Zeichen. 
		// Servierung auch prüfen
		int sv = 0; 
		String text = " ";
		if(serviert) {
			sv = 5; 
			text = "serviert. ";
		}
		eintragenText = "Poker " + text;
		eintrageWert = 40 + sv;
	}

	public void eintragenGrande(Wurfergebnis ergebnis, boolean serviert) {
		// Servierung auch prüfen
		int sv = 0; 
		String text = " ";
		if(serviert) {
			sv = 5; 
			text = "serviert. ";
		}
		eintragenText = "Grande " + text;
		eintrageWert = 50 + sv;
	}

	public void eintragenStreichug(Wurfergebnis ergebnis) {
		int anzahl = 1; 
		eintragenText = anzahl + " Streichung";
		eintrageWert = anzahl * 0;
	}

	
	// Standard Getter & Setter
	public int getEintrageWert() {
		return eintrageWert;
	}

	public void setEintrageWert(int eintrageWert) {
		this.eintrageWert = eintrageWert;
	}

	public String getEintragenText() {
		return eintragenText;
	}

	public void setEintragenText(String eintragenText) {
		this.eintragenText = eintragenText;
	}

	@Override
	public String toString() {
		return "Ergebnisfeld [eintrageWert=" + eintrageWert + ", eintragenText=" + eintragenText + "]";
	}
		
}
