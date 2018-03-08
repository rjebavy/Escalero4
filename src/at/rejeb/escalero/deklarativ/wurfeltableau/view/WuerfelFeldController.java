package at.rejeb.escalero.deklarativ.wurfeltableau.view;

import at.rejeb.escalero.deklarativ.wurfeltableau.WuerfeltableauMAIN;
import at.rejeb.escalero.deklarativ.wurfeltableau.model.Wuerfel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class WuerfelFeldController {
	private int wurfzaehler = 2; // WurfZaehlerController.getWurfzaehler(); 
	private Wuerfel[] wuerfelsatz = new Wuerfel[5]; // W�rfelsatz f�r W�rfelfeld mit f�nf W�rfeln. 
	private boolean[] nichtgehalten = new boolean[5]; // Boolean Array f�r Haltefeld mit f�nf CheckBoxen. 
	private int gehalten = 0; // Z�hlt 1 rauf bei halten(), 1 runter bei freigeben(), Indikator f�r kein W�rfel gehalten.  
	private boolean moeglicheServierung = false; // Wird jedesmal auf 'wahr' gesetzt wenn kein W�rfel gehalten ist. 
	
	@FXML
	private HBox wuerfelFeld;
	
	@FXML
	private Label wuerfel1;
	@FXML
	private Label wuerfel2;
	@FXML
	private Label wuerfel3;
	@FXML
	private Label wuerfel4;
	@FXML
	private Label wuerfel5;
	
    // Reference to the main application.
    private WuerfeltableauMAIN mainApp;
   

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public WuerfelFeldController() {
    }

    
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the W�rfelfeld with the 5 W�rfel. 
    	this.initialisiereWuerfelsatz();
       	wuerfel1.setText(wuerfelsatz[0].waehleKuerzel(wuerfelsatz[0].getWuerfelWert()));
    	wuerfel2.setText(wuerfelsatz[1].waehleKuerzel(wuerfelsatz[1].getWuerfelWert())); 
    	wuerfel3.setText(wuerfelsatz[2].waehleKuerzel(wuerfelsatz[2].getWuerfelWert())); 
    	wuerfel4.setText(wuerfelsatz[3].waehleKuerzel(wuerfelsatz[3].getWuerfelWert())); 
    	wuerfel5.setText(wuerfelsatz[4].waehleKuerzel(wuerfelsatz[4].getWuerfelWert())); 
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(WuerfeltableauMAIN mainApp) {
        this.mainApp = mainApp;
        
        // Add observable list data to all of the 5 Labels
        wuerfel1.setText(mainApp.getwuerfelData().get(0).waehleKuerzel(mainApp.getwuerfelData().get(0).getWuerfelWert())); 
        wuerfel1.setTextFill(this.setzeTextfarbe((mainApp.getwuerfelData().get(0).getWuerfelWert()))); 
        System.out.println("Wuerfel1; setTextFill, Farbe? " + this.setzeTextfarbe((mainApp.getwuerfelData().get(0).getWuerfelWert())));
        wuerfel2.setText(mainApp.getwuerfelData().get(1).waehleKuerzel(mainApp.getwuerfelData().get(1).getWuerfelWert())); 
        wuerfel3.setText(mainApp.getwuerfelData().get(2).waehleKuerzel(mainApp.getwuerfelData().get(2).getWuerfelWert())); 
        wuerfel4.setText(mainApp.getwuerfelData().get(3).waehleKuerzel(mainApp.getwuerfelData().get(3).getWuerfelWert())); 
        wuerfel5.setText(mainApp.getwuerfelData().get(4).waehleKuerzel(mainApp.getwuerfelData().get(4).getWuerfelWert())); 

    }
    
    
    
	// W�rfelsatz mit W�rfeln im ungesetzten, initialen Zustand erzeugen. 
	public Wuerfel[] initialisiereWuerfelsatz() {
		for(int w = 0; w < wuerfelsatz.length; w++) {
				wuerfelsatz[w] = new Wuerfel(0);
				System.out.println("W�rfelsatz, W�rfel " + w + " - " + ", Wert = " + wuerfelsatz[w].getWuerfelWert());
			}
			wuerfelsatz[0].setWuerfelWert(5); // Test ob Farbschaltung funkt? 8.3.18-01:38. 
			System.out.println("\n");
			return wuerfelsatz; 
		}

	// Haltemaske im ungesetzten, initialen Zustand erzeugen. 
	public void initialisiereHaltemaske() {
		for(int h = 0; h < nichtgehalten.length; h++) {
			nichtgehalten[h] = true;
			 System.out.println("Haltemaske, W�rfel " + h + " = " + nichtgehalten[h]);
			}
		// Da kein W�rfel gehalten wird ist auch Servierung m�glich.
		this.setzeServierungMoeglich();
	}
    
    
	// Den angegebenen W�rfel halten. 
	public boolean halten(int wuerfel) {
		if(wuerfel >= 0 && wuerfel <= 4) {
			nichtgehalten[wuerfel] = false;
			System.out.println("Haltemaske, W�rfel " + wuerfel + " = " + nichtgehalten[wuerfel]);
			// gehalten 1 raufz�hlen, als Indikator f�r m�gliche Servierung wenn kein W�rfel gehalten. 
			if(gehalten < 5) {
				gehalten++; 
				System.out.println("halten, W�rfel " + wuerfel + "; gehalten = " + gehalten);
			}
			return true;
				}
		System.out.println("\nW�rfelnummer au�erhalb erlaubten Bereichs (0-4)!");
		return false;
	}	

	// Den angegebenen W�rfel wieder freigeben. 
	public boolean freigeben(int wuerfel) {
		if(wuerfel >= 0 && wuerfel <= 4) {
			nichtgehalten[wuerfel] = true;
			System.out.println("Haltemaske, W�rfel " + wuerfel + " = " + nichtgehalten[wuerfel]);
			// gehalten 1 runterz�hlen, wenn 0 - d.h. kein W�rfel gehalten - dann Servierung m�glich setzen. 
			if(gehalten > 0) {
				gehalten--; 
				System.out.println("freigeben, W�rfel " + wuerfel + "; gehalten = " + gehalten);
			}
			if(gehalten == 0){
				this.setzeServierungMoeglich();
			}
			return true;
				}
		System.out.println("\nW�rfelnummer au�erhalb erlaubten Bereichs (0-4)!");
		return false;
	}
	
	// Gibt alle 5 W�rfel des W�rfelsatzes frei, alle nicht gehalten. 
	public void freigebenAlle() {
		for(int w = 0; w < 5; w++) {
			nichtgehalten[w] = true;;
		}
	}
	
	// Zufallszahl im Wertebereich 1-6 berechnen. 
	public int berechneZufallszahl() {
		int zahl = (int)(Math.random() * 6 + 1);
		return zahl; 
	} 
	
	// W�rfeln aller nichtgehaltener W�rfel. 
	public boolean wuerfleUngehaltene(Wuerfel[] wsatz) {
		if(wurfzaehler >= 0 && wurfzaehler < 3) {
			for(int w = 0; w < wsatz.length; w++) {
				if(nichtgehalten[w] == true) {
					wsatz[w].setWuerfelWert(berechneZufallszahl());
					System.out.println("gew�rfelt, W�rfel " + w + " - " + "Wert = " + wsatz[w].getWuerfelWert());
					}
		 		}
			return true;
		}
	System.out.println("\nw�rfle; Wurfz�hler ist nicht 0, 1 oder 2!");
	return false;
	}	
	
	// Setzt Wurfeigenschaft moeglicheServierung auf wahr.
	public void setzeServierungMoeglich() {
		this.moeglicheServierung = true; 
	} 
	

	public Color setzeTextfarbe(int wert) {
		String farbe = "unklar";
		Color farbwert = Color.BLACK; 
		if(wert == 1 || wert == 3 || wert == 6) {farbwert = Color.BLACK;}
		if(wert == 2 || wert == 5 ) {farbwert = Color.RED;}		
		if(wert == 4 ) {farbwert = Color.BLUE;}	
		if(farbwert == Color.BLACK) {farbe = "SCHWARZ";}
		if(farbwert == Color.RED) {farbe = "ROT";}
		if(farbwert == Color.BLUE) {farbe = "BLAU";}
		
		/* switch (wert) {
		case 1:
			farbe = (Color.BLACK);
			break; 
		case 2:
			farbe = (Color.RED);
			break; 
		case 3:
			farbe = (Color.BLACK);
			break; 
		case 4:
			farbe = (Color.BLUE);
			break; 
		case 5:
			farbe = (Color.RED);
			break; 
		case 6:
			farbe = (Color.BLACK);
			break; 
		default: 
			farbe = (Color.BLACK);
			}
			*/
		System.out.println("setzeTextfarbe; Returnfarbe? " + farbwert + " - d.h. Farbe: " + farbe);;
		return farbwert;
	}
	
	
	
	// Standard Getter & Setter
	public boolean isMoeglicheServierung() {
		return moeglicheServierung;
	}

	public int getGehalten() {
		return gehalten;
	}


}
