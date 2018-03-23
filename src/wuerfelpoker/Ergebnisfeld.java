package wuerfelpoker;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Ergebnisfeld {
	private final IntegerProperty eintragewert = new SimpleIntegerProperty();
	private final StringProperty eintragentext = new SimpleStringProperty();

	// Konstruktor 
	public Ergebnisfeld() {
	}

	// Ergebnisfeld leeren. 
	public void initialisiereErgebnisfeld() {
		setEintragewert(0);
		setEintragentext("noch kein Ergebnis. ");
	}
	
	// Text und Werte für Würfelbilder. 
	public void eintragenNeuner(Wurfergebnis ergebnis) {
		initialisiereErgebnisfeld();
		int anzahl = ergebnis.getNeun(); 
		setEintragentext(anzahl + " Neuner. ");
		setEintragewert(anzahl * 1);
	}

	public void eintragenZehner(Wurfergebnis ergebnis) {
		initialisiereErgebnisfeld();
		int anzahl = ergebnis.getZehn(); 
		setEintragentext(anzahl + " Zehner. ");
		setEintragewert(anzahl * 2);
	}

	public void eintragenBuben(Wurfergebnis ergebnis) {
		initialisiereErgebnisfeld();
		int anzahl = ergebnis.getBube(); 
		String text = "Buben. ";
		if(anzahl == 1) {
			text = "Bube. ";
		}
		setEintragentext(anzahl + " " + text);
		setEintragewert(anzahl * 3);
	}

	public void eintragenDamen(Wurfergebnis ergebnis) {
		initialisiereErgebnisfeld();
		int anzahl = ergebnis.getDame(); 
		String text = "Damen. ";
		if(anzahl == 1) {
			text = "Dame. ";
		}
		setEintragentext(anzahl + " " + text);
		setEintragewert(anzahl * 4);
	}

	public void eintragenKoenige(Wurfergebnis ergebnis) {
		initialisiereErgebnisfeld();
		int anzahl = ergebnis.getKoenig(); 
		String text = "Koenige. ";
		if(anzahl == 1) {
			text = "Koenig. ";
		}
		setEintragentext(anzahl + " " + text);
		setEintragewert(anzahl * 5);
	}

	public void eintragenAsse(Wurfergebnis ergebnis) {
		int anzahl = ergebnis.getAss(); 
		String text = "Asse. ";
		if(anzahl== 1) {
			text = "Ass. ";
		}
		setEintragentext(anzahl + " " + text);
		setEintragewert(anzahl * 6);
	}

	// Text und Werte für Würfelmuster. 
	public void eintragenStrasse(Wurfergebnis ergebnis, boolean serviert) {
		initialisiereErgebnisfeld();
		// Servierung auch prüfen
		int sv = 0; 
		String text = " ";
		if(serviert) {
			sv = 5; 
			text = "serviert. ";
		}
		setEintragentext("Straße " + text);
		setEintragewert(20 + sv);
	}

	public void eintragenFullhouse(Wurfergebnis ergebnis, boolean serviert) {
		initialisiereErgebnisfeld();
		// Servierung auch prüfen
		int sv = 0; 
		String text = " ";
		if(serviert) {
			sv = 5; 
			text = "serviert. ";
		}
		setEintragentext("FullHouse " + text);
		setEintragewert(30 + sv);
	}

	public void eintragenPoker(Wurfergebnis ergebnis, boolean serviert) {
		initialisiereErgebnisfeld();
		// Textfeld so breit, dass sich "FullHouse serviert. " darin ausgeht, also 20+2 = 22 Zeichen. 
		// Servierung auch prüfen
		int sv = 0; 
		String text = " ";
		if(serviert) {
			sv = 5; 
			text = "serviert. ";
		}
		setEintragentext("Poker " + text);
		setEintragewert(40 + sv);
	}

	public void eintragenGrande(Wurfergebnis ergebnis, boolean serviert) {
		initialisiereErgebnisfeld();
		// Servierung auch prüfen
		int sv = 0; 
		String text = " ";
		if(serviert) {
			sv = 5; 
			text = "serviert. ";
		}
		setEintragentext("Grande " + text);
		setEintragewert(50 + sv);
	}

	// Knopf [-]
	public void eintragenStreichung(Wurfergebnis ergebnis) {
		initialisiereErgebnisfeld();
		int anzahl = 1; 
		setEintragentext(anzahl + " Streichung");
		setEintragewert(anzahl * 255);
	}
	
	// Knopf [x]
	public void letztenEintragLoeschen() {
		// TODO
	}
	
	// Aktiviere Bilderknöpfe nach Wurfergebnis. 
	public void aktiviereBilderknoepfe(Wurfergebnis ergebnis, Button[] bilderKnopf) {
		if(ergebnis.getNeun() > 0) {
			bilderKnopf[0].setDisable(false);
			}
		if(ergebnis.getZehn() > 0) {
			bilderKnopf[1].setDisable(false);
			}
		if(ergebnis.getBube() > 0) {
			bilderKnopf[2].setDisable(false);
			}
		if(ergebnis.getDame() > 0) {
			bilderKnopf[3].setDisable(false);
			}
		if(ergebnis.getKoenig() > 0) {
			bilderKnopf[4].setDisable(false);
			}
		if(ergebnis.getAss() > 0) {
			bilderKnopf[5].setDisable(false);
			}
	}
	
	
	// Standard Getter & Setter
	public IntegerProperty eintragewertProperty() {
		return eintragewert;
	}

	public final int getEintragewert() {
		return eintragewert.get(); 
		}

	public final void setEintragewert(int eintragewert) {
		this.eintragewert.set(eintragewert);
	}
	
	
	public StringProperty eintragentextProperty() {
		return eintragentext;
	}

	public final String getEintragentext() {
		return eintragentext.get(); 
		}

	public final void setEintragentext(String eintragentext) {
		this.eintragentext.set(eintragentext);
	}
	
	
	@Override
	public String toString() {
		return "Ergebnisfeld [eintragewert=" + eintragewert + ", eintragentext=" + eintragentext + "]";
	}
		
}
