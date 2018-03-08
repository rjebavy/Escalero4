package at.rejeb.escalero.deklarativ.wurfeltableau.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Model class for a "Würfel".
 *
 * @author Reinhard Jebavy
 */


public class Wuerfel {
	private IntegerProperty wuerfelWert;
	/* TODO private BooleanProperty gehalten;  

    /**
     * Default constructor.
     */
    public Wuerfel() {
        
    }

    /**
     * Constructor with some initial data.
     * 
     * @param wuerfelWerte
     * 
     */
	// Konstruktor 
	public Wuerfel(int wuerfelWert) {
		this.wuerfelWert = new SimpleIntegerProperty(wuerfelWert);
		}


	// Je nach Wert ein Würfelbild auswählen
		public String waehleBild(int wuerfelwert) {
			String bild = null;
			switch (wuerfelwert) {
			case 0:
				bild = "ungesetzt";
				break;
			case 1:
				bild = "Neun";
				break;
			case 2:
				bild = "Zehn";
				break;
			case 3:
				bild = "Bube";
				break;
			case 4:
				bild = "Dame";
				break;
			case 5:
				bild = "Koenig";
				break;
			case 6:
				bild = "Ass";
				break;
			default: 
				bild = "Fehler!";
			}
			return bild; 
		}
		
		// Je nach Wert ein Würfelkürzel auswählen
		public String waehleKuerzel(int wuerfelwert) {
			String kuerzel = null;
			switch (wuerfelwert) {
			case 0:
				kuerzel = "X";
				break;
			case 1:
				kuerzel = "9";
				break;
			case 2:
				kuerzel = "10";
				break;
			case 3:
				kuerzel = "B";
				break;
			case 4:
				kuerzel = "D";
				break;
			case 5:
				kuerzel = "K";
				break;
			case 6:
				kuerzel = "A";
				break;
			default: 
				kuerzel = "--";
			}
			return kuerzel; 
		}

	
	// Standard Getter & Setter
	public int getWuerfelWert() {
		return wuerfelWert.get();
	}

	public void setWuerfelWert(int wuerfelWert) {
		this.wuerfelWert.set(wuerfelWert);
	}
	
    public IntegerProperty wuerfelWertProperty() {
        return wuerfelWert;
    }
    

	// Standard toString
	@Override
	public String toString() {
		return "Wuerfel [wuerfelWert=" + wuerfelWert + "]";
	}

}
