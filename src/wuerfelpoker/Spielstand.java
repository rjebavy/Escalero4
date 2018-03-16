package wuerfelpoker;

import java.util.Arrays;
import java.util.HashMap;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.BorderPane;

public class Spielstand {
	private IntegerProperty[] reihe = new SimpleIntegerProperty[3];

	
	
	public Spielstand(Integer[] integers) {
		super();
	}

	// Standard Getter & Setter
	public IntegerProperty[] ReiheProperty() {
		return reihe;
	}

	public IntegerProperty[] getReihe() {
		return reihe;
	}

	public void setReihe(IntegerProperty[] reihe) {
		this.reihe = reihe;
	}


/*	
	public IntegerProperty Reihe2Property() {
		return reihe2;
	}

	public final int getReihe2() {
		return reihe2.get(); 
		}

	public final void setReihe2(int reihe2) {
		this.reihe2.set(reihe2);
	}

	
	public IntegerProperty Reihe3Property() {
		return reihe3;
	}

	public final int getReihe3() {
		return reihe3.get(); 
		}

	public final void setReihe3(int reihe3) {
		this.reihe3.set(reihe3);
	}
	*/
}
