package wuerfelpoker;

import java.util.Arrays;
import java.util.HashMap;

import javafx.beans.property.MapProperty;
import javafx.scene.layout.BorderPane;

public class Spielstand {
	Reihe[] dreireihen = new Reihe[3];
	private int spieler = 0; 

	
	// Konstruktor hier wird vom Aufrufer die globale Konstante ANZAHL_SPIELER übergeben. 
	public Spielstand(int spieler) {
		super();
		this.spieler = spieler;
	}

	
	public Reihe[] erzeugeSpielstandTabelle() {
		Reihe[] dreireihen = new Reihe[3];
		for(int r = 0; r < 3; r++) {
			dreireihen[r] = new Reihe();
		}
		return dreireihen;
	}



	public Reihe[] getDreireihen() {
		return dreireihen;
	}


	public void setDreireihen(Reihe[] dreireihen) {
		this.dreireihen = dreireihen;
	}

	
	@Override
	public String toString() {
		return "Spielstand [dreireihen=" + Arrays.toString(dreireihen) + "]";
	}

}
