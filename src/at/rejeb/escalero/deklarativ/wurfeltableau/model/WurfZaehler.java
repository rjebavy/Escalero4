package at.rejeb.escalero.deklarativ.wurfeltableau.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Model class for a Wurfzähler.
 *
 * @author Reinhard Jebavy
 */

public class WurfZaehler {
	private IntegerProperty zaehlerWert;

    /**
     * Default constructor.
     */
    public WurfZaehler() {
        
    }

    /**
     * Constructor with some initial data.
     * 
     * @param zaehlerWert
     * 
     */
	// Konstruktor 
	public WurfZaehler(int zaehlerWert) {
		this.zaehlerWert = new SimpleIntegerProperty(zaehlerWert);
		}
	

	
	// Standard Getter & Setter
	public int getZaehlerWert() {
		return zaehlerWert.get();
	}

	public void setZaehlerWert(int zaehlerWert) {
		this.zaehlerWert.set(zaehlerWert);;
	}
	
    public IntegerProperty zaehlerWertProperty() {
        return zaehlerWert;
    }
	
	

	// Standard toString
	@Override
	public String toString() {
		return "wurfZaehlerWert";
	}


	

}
