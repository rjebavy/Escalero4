package wuerfelpoker;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Meldung {
	private final StringProperty meldung = new SimpleStringProperty(); 
	
	public StringProperty meldungProperty() { 
		return meldung;
		}
	
	public final String getMeldung() {
		return meldung.get(); 
		}
	
	public final void setMeldung(String meldung) {
		this.meldung.set(meldung);
	}
	

}
