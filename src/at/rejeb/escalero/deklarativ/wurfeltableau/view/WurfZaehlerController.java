package at.rejeb.escalero.deklarativ.wurfeltableau.view;

import at.rejeb.escalero.deklarativ.wurfeltableau.WuerfeltableauMAIN;
import at.rejeb.escalero.deklarativ.wurfeltableau.model.Wuerfel;
import at.rejeb.escalero.deklarativ.wurfeltableau.model.WurfZaehler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class WurfZaehlerController {
	private static WurfZaehler wz = new WurfZaehler(); // zählt von 3 auf 0 runter, je Spieler 3 Würfe pro Runde. 
	
	
	@FXML
	private HBox wurfZaehlerBox;
	
	@FXML
	private Label wurfZaehler;


    // Reference to the main application.
    private WuerfeltableauMAIN mainApp;
   
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public WurfZaehlerController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the Wurfzähler with the Wurfzählerwert 3. 
    	this.initialisiereWurfZaehler();
		Integer wzw = wz.getZaehlerWert();
		String wzz = wzw.toString() + ".";
		System.out.println("initialize(); wzw: " + wzz);
		// gleich mittesten ob heruntergezählt wird? 2. 
		this.wurfRunterzaehlen();
		wurfZaehler.setText(wzz);

    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(WuerfeltableauMAIN mainApp) {
        this.mainApp = mainApp;
        
        // Add a Binding? from the Wurfzähler IntegerProperty to the Label? 
        	// wuerfel1.setText(mainApp.getwuerfelData().get(0).waehleKuerzel(mainApp.getwuerfelData().get(0).getWuerfelWert())); 
        	//wuerfel1.setTextFill(this.setzeTextfarbe((mainApp.getwuerfelData().get(0).getWuerfelWert()))); 
        	// System.out.println("Wuerfel1; setTextFill, Farbe? " + this.setzeTextfarbe((mainApp.getwuerfelData().get(0).getWuerfelWert())));
 

    }
    
    
    
    
	// Wurfzähler initialisieren sprich auf 3 setzen. 
	public void initialisiereWurfZaehler() {
		wz = new WurfZaehler(3);
		}   
  
	// Wurfzähler von 3 auf 0 runterzählen. 
	public void wurfRunterzaehlen() {
		System.out.println("Hallo hier wurfRunterzaehlen");
		if(wz.getZaehlerWert() > 0 && wz.getZaehlerWert() <= 3) { 
			wz.setZaehlerWert(wz.getZaehlerWert()-1);
			System.out.println("wurfRunterzaehlen; Wurfzähler: " + wz.getZaehlerWert());	
			}
		else {
			System.out.println("\nWurfzähler außerhalb erlaubten Bereichs (1-3)!");	
		}
	}


	public static int getZaehlerWert() {
		return wz.getZaehlerWert();
	}
    
	
	
	
}
