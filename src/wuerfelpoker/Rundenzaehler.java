package wuerfelpoker;

public class Rundenzaehler {
	private int runden; 
	
	// Konstruktor 
	public Rundenzaehler(int runden) {
		super();
		this.runden = runden;
		}

	
	// Zähler auf 10 setzen; Standardrundenanzahl bei Escalero.  
	public void initialisiereRundenzaehler() {
		this.setRunden(11);
		// ACHTUNG globale Variable aktuelle_Runde wird zu Beginn auf globale Konstante ANZAHL_RUNDEN = 11 gesetzt, Modus für Extrastreichung. 
		// TODO Kode für die Auswahl Spielmodus mit oder ohne Extrastreichung, sprich 10 oder 11, hinzufügen. (nth) 
	}
	
	//Rundenzähler von 10 auf 0 runterzählen. 
	public void rundenRunterzaehlen() {
		// TODO auch hier, Kode für die Auswahl Spielmodus mit oder ohne Extrastreichung anpassen. (nth)
		if(runden > 0 && runden <= 11) 
			runden--;
		else
			System.out.println("\nRundenzähler außerhalb erlaubten Bereichs (1-11)!");	
	}
	
	
	
	
	// Standard Getter & Setter
	public int getRunden() {
		return runden;
	}

	public void setRunden(int runden) {
		this.runden = runden;
	}

	@Override
	public String toString() {
		return "Rundenzaehler [runden=" + runden + "]";
	}
	
	

}
