package at.rejeb.escalero.deklarativ.wurfeltableau;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import at.rejeb.escalero.deklarativ.wurfeltableau.model.Wuerfel;
import at.rejeb.escalero.deklarativ.wurfeltableau.view.WuerfelFeldController;
import at.rejeb.escalero.deklarativ.wurfeltableau.view.WurfZaehlerController;


// Escalero4 W�rfelpoker
// Wifi Wien, Software Developer Java Projektarbeit, Q1/2018. 
// 
// ## Creator
//
//	**Reinhard Jebavy**
//	* <https://github.com/rjebavy> 
//
/** 
* @version 0.116
* @author Reinhard Jebavy
*/


public class WuerfeltableauMAIN extends Application {
	
	private Stage primaryStage;
	private GridPane wuerfelTableau; 
	private WuerfelFeldController wfc = new WuerfelFeldController();
	
    /**
     * The data as an observable list of Wuerfel.
     */
    private ObservableList<Wuerfel> wuerfelData = FXCollections.observableArrayList(); 


	
	Wuerfel[] wsatz = wfc.initialisiereWuerfelsatz();
	
    /**
     * Constructor
     * @return 
     */
    public WuerfeltableauMAIN() {
    	// Add some sample data
    		wuerfelData.addAll(wsatz);
    		// TODO zaehlerData
    }
    
    /**
     * Returns the data as an observable list of W�rfel. 
     * @return
     */
    public ObservableList<Wuerfel> getwuerfelData () {
        return wuerfelData;
    }

    
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage; 
		this.primaryStage.setTitle("Escalero deklarativ: W�rfeltableau. ");
		
		initWuerfelTableau();
		
		showWurfZaehler(); 
		showWuerfelFeld();
		// showWuerfleKnopf(); 
		
	}

    /**
     * Initializes the W�rfeltableau.
     */
	
    public void initWuerfelTableau() {
        try {
            // Load W�rfeltableau from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WuerfeltableauMAIN.class.getResource("view/WuerfelTableau.fxml"));
            wuerfelTableau = (GridPane) loader.load();

            // Show the scene containing the W�rfeltableau.
            Scene scene = new Scene(wuerfelTableau);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    /**
     * Shows the Wurfz�hler inside the W�rfeltableau.
     */
    
	public void showWurfZaehler() {
        try {
            // Load W�rfelfeld.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WuerfeltableauMAIN.class.getResource("view/WurfZaehler.fxml"));
            HBox wurfZaehlerBox = (HBox) loader.load();
            
            // Set W�rfelfeld into the center of W�rfeltableau layout.
            wuerfelTableau.add(wurfZaehlerBox, 1, 1);
            
            // Give the controller access to the main app.
            WurfZaehlerController controller = loader.getController();
            controller.setMainApp(this);  
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  


    /**
     * Shows the W�rfelfeld inside the W�rfeltableau.
     */
    
	public void showWuerfelFeld() {
        try {
            // Load W�rfelfeld.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(WuerfeltableauMAIN.class.getResource("view/WuerfelFeld.fxml"));
            HBox wuerfelFeld = (HBox) loader.load();

            // Set W�rfelfeld into the center of W�rfeltableau layout.
            wuerfelTableau.add(wuerfelFeld, 2, 1);
            
            // Give the controller access to the main app.
            WuerfelFeldController controller = loader.getController();
            controller.setMainApp(this);  
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  	
	

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }	
	
	public static void main(String[] args) {
		launch(args);
	}
}
