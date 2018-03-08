package at.rejeb.escalero.deklarativ.wurfeltableau.view;

import at.rejeb.escalero.deklarativ.wurfeltableau.WuerfeltableauMAIN;
import at.rejeb.escalero.deklarativ.wurfeltableau.model.Wuerfel;
import at.rejeb.escalero.deklarativ.wurfeltableau.model.WurfZaehler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class WurfZaehlerController {
	private static WurfZaehler wz = new WurfZaehler(); // z�hlt von 3 auf 0 runter, je Spieler 3 W�rfe pro Runde. 
	
	
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
        // Initialize the Wurfz�hler with the Wurfz�hlerwert. 
    	this.initialisiereWurfZaehler();
    	// wurfZaehler.setText();

    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(WuerfeltableauMAIN mainApp) {
        this.mainApp = mainApp;
        
        // Add a Binding? from the Wurfz�hler IntegerProperty to the Label? 
        	// wuerfel1.setText(mainApp.getwuerfelData().get(0).waehleKuerzel(mainApp.getwuerfelData().get(0).getWuerfelWert())); 
        	//wuerfel1.setTextFill(this.setzeTextfarbe((mainApp.getwuerfelData().get(0).getWuerfelWert()))); 
        	// System.out.println("Wuerfel1; setTextFill, Farbe? " + this.setzeTextfarbe((mainApp.getwuerfelData().get(0).getWuerfelWert())));
 

    }
    
    
    
    
	// Wurfz�hler initialisieren sprich auf 3 setzen. 
	public void initialisiereWurfZaehler() {
		wz = new WurfZaehler(3);
		}   
  
	// Wurfz�hler von 3 auf 0 runterz�hlen. 
	public void wurfRunterzaehlen() {
		if(wz.getWurfZaehlerWert() > 0 && wz.getWurfZaehlerWert() <= 3) 
			wz.setWurfZaehlerWert(wz.getWurfZaehlerWert()-1);
		else
			System.out.println("\nWurfz�hler au�erhalb erlaubten Bereichs (1-3)!");	
	}


	public static int getZaehlerWert() {
		return wz.getWurfZaehlerWert();
	}
    
	
	
	
}
