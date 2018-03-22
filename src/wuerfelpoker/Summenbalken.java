package wuerfelpoker;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Summenbalken {
	private IntegerProperty[] summe = new SimpleIntegerProperty[3];

	
	public Summenbalken() {
		
	}

	// Standard Getter & Setter
	public IntegerProperty[] SummeProperty() {
		return summe;
	}

	public IntegerProperty[] getSumme() {
		return summe;
	}

	public void setSumme(IntegerProperty[] summe) {
		this.summe = summe;
	}
	
	
}
