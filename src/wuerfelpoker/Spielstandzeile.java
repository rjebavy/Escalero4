package wuerfelpoker;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Spielstandzeile {
	private final StringProperty reihe1 = new SimpleStringProperty(); 
	private final StringProperty reihe2 = new SimpleStringProperty(); 
	private final StringProperty reihe3 = new SimpleStringProperty(); 
	
	// Standard Getter/Setter
	// Für Reihe1 
	public StringProperty reihe1Property() { 
		return reihe1;
		}
	
	public final String getReihe1() {
		return reihe1.get(); 
		}
	
	public final void setReihe1(String reihe1) {
		this.reihe1.set(reihe1);
	}
	// Für Reihe2 	
	public StringProperty reihe2Property() { 
		return reihe2;
		}
	
	public final String getReihe2() {
		return reihe2.get(); 
		}
	
	public final void setReihe2(String reihe2) {
		this.reihe2.set(reihe2);
	}
	// Für Reihe3 
	public StringProperty reihe3Property() { 
		return reihe3;
		}
	
	public final String getReihe3() {
		return reihe3.get(); 
		}
	
	public final void setReihe3(String reihe3) {
		this.reihe3.set(reihe3);
	}

	@Override
	public String toString() {
		return "Spielstandzeile [reihe1=" + reihe1 + ", reihe2=" + reihe2 + ", reihe3=" + reihe3 + "]";
	}

	
	
	
}
