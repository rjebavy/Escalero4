package wuerfelpoker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class EscaleroBedienpaneel extends Application {
	
	// Escalero4 W�rfelpoker
	// Wifi Wien, Software Developer Java Projektarbeit, Q1/2018. 
	// 
	// ## Creator
	//
	//	**Reinhard Jebavy**
	//	* <https://github.com/rjebavy> 
	//
	/** 
	* @version 0.141
	* @author Reinhard Jebavy
	*/

	// Schema MVC - Model, View, Controller: 
	// Der FX Scene Graph und die GUI Node Komponenten hier im EscaleroBedienpaneel bilden die View(s). 
	// Java Klassen formen das Model, und be- bzw. verarbeiten Hintergrunddaten. Sind dabei zwangsweise jedoch auch 
	// gemischt mit themenbezogenen Controller-Methoden. 
	// Dies hier, das EscaleroBedienpaneel, fungiert zus�tzlich als Main-Controller, enth�lt aber auch GUI unterst�tzende Methoden. 

	
	// KONSTANTEN
	
	
	// INSTANZVARIABLEN
	// Wurf, W�rfelsatz & Ergebnis initialisieren
	Wurf wurf = new Wurf(3);
	Wuerfel[] wsatz = wurf.initialisiereWuerfelsatz();
	Wurfergebnis ergebnis = new Wurfergebnis(wsatz); 
	Rundenzaehler rundenzaehler = new Rundenzaehler(10); 
	Ergebnisfeld ergebnisfeld = new Ergebnisfeld();
	Button[] Reihe = new Button[3];
	Button[] Bilder = new Button[6];
	Button[] Muster = new Button[6];
	

	@Override
	public void start(Stage primaryStage) {


		//TESTS: 
			// W�rfeln erfolgreich getestet; 18.2.2018. 
			// Auswerten erfolgreich getestet; 22.2.2018. 
			// Servierungen erkennen, erfolgreich getestet; 28.2.2018. 
			// wuerfeltableau MinSize aus Scene �bernommen, erfolgreich getestet; 1.3.2018. 
			// GUI Klasse 'Wurfzellentabelle' nach 'EscaleroBedienpaneel' migriert, erfolgreich getestet; 1.3.2018. 
			// 1.3.5.3.1) BUG: Sevierung erkannt obwohl 4 gehalten!! behoben 1.3.18-00:41.  
			// 5.4.1) BUG:  Bei Wiederholung mit [Nochmal] werden die Haltefelder nicht initialisiert!!! behoben ?. 
			// 0.4.5.1) GIT-BRANCH einrichten!! Deklarative Oberfl�che ab Version 0.115. 
			// Kodierschluss 11.3.18-02:45. W�rfel- & Ergebnistableau k�nnen Neustarten. Dort fehlt noch Eintragewerte berechnen. 
		

// Top Level FX Node item: ESCALEROBEDIENPANEEL
		// Tempor�re Dummy-Tableaus
		VBox spielstandtableau = new VBox();
		spielstandtableau.setMinSize(340, 340); 
		// W�rfeltableau ist kein Dummy NODE! 
		GridPane wuerfeltableau = erzeugeWuerfeltableau();
		// Ergebnistableau ist kein Dummy Tableau! 
		GridPane ergebnistableau = erzeugeErgebnistableau();

// >>>>		// Test initialisiereErgebnisknoepfe()

				
		
		VBox bedientableau = new VBox();
		bedientableau.setMinSize(340, 139);
		bedientableau.setAlignment(Pos.CENTER);
			// Tempor�r um nicht immer schlie�en und starten zu m�ssen. 
			Button nochmal = new Button("Nochmal!"); 
			nochmal.setFont(Font.font("Tahoma", 10));
			nochmal.setMinSize(50, 16); 
			nochmal.setOnAction(event->aktionNochmal(wuerfeltableau, ergebnistableau));
			bedientableau.getChildren().addAll(nochmal);
	

	// Top-top-level Container Escalero Bedienfeld
	VBox escalerobedienpaneel = new VBox();
	escalerobedienpaneel.setMinSize(340, 666);
	escalerobedienpaneel.setAlignment(Pos.CENTER);
	escalerobedienpaneel.getChildren().addAll(spielstandtableau, wuerfeltableau, ergebnistableau, bedientableau);

	primaryStage.setScene(new Scene(escalerobedienpaneel, 340, 666));
	primaryStage.setTitle("Escalero4 - Bedienpaneel");
	primaryStage.setResizable(true);
	primaryStage.show();
	}

	// Top Level FX Node item: ESCALEROBEDIENPANEEL, Ende. 


	
// BEDIENTABLEAU, FX-Nodes & Eigenschaften: 

	//TODO 
	
	// BEDIENTABLEAU, Ende. 
	
	// Hier drunter Methoden und Kode zu den einzelnen FX-Nodes vom BEDIENTABLEAU. 
	
	// tempor�rer Aktionskode f�r Knopf [Nochmal!]
		// TODO Wenn Endversion wieder entfernen. 
		public void aktionNochmal(GridPane wtableau, GridPane etableau) {
			neustartWuerfeltableau(wtableau);
			neustartErgebnistableau(etableau);
		}
	
	
	// Hier oberhalb Methoden und Kode zu den einzelnen FX-Nodes vom BEDIENTABLEAU. 	

	
	
// ERGEBNISTABLEAU, FX-Nodes & Eigenschaften: 
	// Erzeugermethode. 
	public GridPane erzeugeErgebnistableau() {
			// W�RFELTABLEAU, FX-Nodes & Eigenschaften: 
			// Alle FX Nodes erzeugen
		GridPane etableau = new GridPane();
		etableau.setMinSize(340, 112);
		etableau.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1))));
		etableau.setHgap(2);
		etableau.setVgap(10);
		// Das Reihenfeld mit Label (Rundenzahler), Label (Was?), Buttons [Reihe1], [Reihe2], [Reihe3].
		HBox reihenfeld = hinzufuegenReihenfeld(); 
			Label rundenZaehler = hinzufuegenRundenzaehler(rundenzaehler);
			Label eintragenWas = hinzufuegenEintragenWas(); 
			Button[] reihenKnopf = hinzufuegenReihenknoepfe();
			// TODO reihenKnopf[0].setOnAction(event->eintragenReihe1(ergebnisfeld.getEintrageWert()));
			// TODO reihenKnopf[1].setOnAction(event->eintragenReihe2(ergebnisfeld.getEintrageWert()));
			// TODO reihenKnopf[2].setOnAction(event->eintragenReihe3(ergebnisfeld.getEintrageWert()));
			reihenfeld.getChildren().addAll(rundenZaehler, eintragenWas, reihenKnopf[0], reihenKnopf[1], reihenKnopf[2]);
		VBox eintrageknopffelder = hinzufuegenEintrageknopffelder(); 
			eintrageknopffelder.setSpacing(5);
			HBox bilderfeld = hinzufuegenKnoepfeOben(); 
				Button[] bilderKnopf = hinzufuegenBilderknoepfe();
				// TODO bilderKnopf[0].setOnAction(event->ergebnisfeld.eintragenNeuner(ergebnis));
				// TODO bilderKnopf[1].setOnAction(event->ergebnisfeld.eintragenZehner(ergebnis));
				// TODO bilderKnopf[2].setOnAction(event->ergebnisfeld.eintragenBuben(ergebnis));
				// TODO bilderKnopf[3].setOnAction(event->ergebnisfeld.eintragenDamen(ergebnis));
				// TODO bilderKnopf[4].setOnAction(event->ergebnisfeld.eintragenKoenige(ergebnis));
				// TODO bilderKnopf[5].setOnAction(event->ergebnisfeld.eintragenAsse(ergebnis));
			bilderfeld.getChildren().addAll(Bilder[0], Bilder[1], Bilder[2], Bilder[3], Bilder[4], Bilder[5]);
			HBox musterfeld = hinzufuegenKnoepfeUnten(); 
				Button[] musterKnopf = hinzufuegenMusterknoepfe();
				// TODO musterKnopf[0].setOnAction(event->ergebnisfeld.eintragenStrasse(ergebnis, serviert)));
				// TODO bilderKnopf[1].setOnAction(event->ergebnisfeld.eintragenZehner(ergebnis));
				// TODO bilderKnopf[2].setOnAction(event->ergebnisfeld.eintragenBuben(ergebnis));
				// TODO bilderKnopf[3].setOnAction(event->ergebnisfeld.eintragenDamen(ergebnis));
				// TODO bilderKnopf[4].setOnAction(event->ergebnisfeld.eintragenKoenige(ergebnis));
				// TODO bilderKnopf[5].setOnAction(event->ergebnisfeld.eintragenAsse(ergebnis));
			musterfeld.getChildren().addAll(Muster[0], Muster[1], Muster[2], Muster[3], Muster[4], Muster[5]);
		eintrageknopffelder.getChildren().addAll(bilderfeld, musterfeld);			
		initialisiereErgebnisknoepfe(); 
		
		etableau.add(reihenfeld, 0, 0, 1, 1);
		etableau.add(eintrageknopffelder, 0, 1, 1, 1);
		etableau.setAlignment(Pos.CENTER);
		
		return etableau; 
	}
	
	// Aktualisierungsmethode. 	
	public void neustartErgebnistableau(GridPane ergebnistableau) {
		// Definierter Ausgangszustand, alle Kn�pfe im Ergebnistableau deaktiviert. 
		initialisiereErgebnisknoepfe(); 
		
	}

	
	
	// ERGEBNISTABLEAU, Ende. 		
	
	// Hier drunter Methoden und Kode zu den einzelnen FX-Nodes vom ERGEBNISTABLEAU. 
	// D a s  R e i h e n f e l d: 
	// F�r das Reihenfeld mit Label (Rundenz�hler), Label (Was?), Buttons [Reihe1], [Reihe2], [Reihe3]. 
	public HBox hinzufuegenReihenfeld() {
		HBox rfeld = new HBox(); 
		rfeld.setSpacing(5);
	return rfeld; 
	}

	// D e r  R u n d e n z � h l e r:
	// Da kommt er ans Licht. 	
	public Label hinzufuegenRundenzaehler(Rundenzaehler ruze) {
		Label rundz = new Label(); 
		rundz.setMinSize(20, 20);
		rundz.setAlignment(Pos.CENTER);
		rundz.setTextFill(Color.OLIVE);
		rundz.setFont(Font.font("Tahoma", 18));
		Integer rz = ruze.getRunden();
		String rzz = rz.toString() + ".";
		rundz.setText(rzz);
		return rundz;
	}

	// D a s  E i n t r a g e n W a s f e l d: 
	// Hier wird angezeigt was mit den Reihenkn�pfen eingetragen werden kann. 
	public Label hinzufuegenEintragenWas() {
		ergebnisfeld.initialisiereErgebnisfeld();
		String ltext = ergebnisfeld.getEintragenText();
		Label efeld = new Label(ltext); // f�r Ergebnistableau-Test stand da "FullHouse serviert." drin.   
		efeld.setPadding(new Insets(2, 2, 2, 2));
		efeld.setMinSize(120, 20);
		efeld.setFont(Font.font("Tahoma", 12));
		efeld.setAlignment(Pos.CENTER);
		efeld.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
		efeld.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID,null, new BorderWidths(1))));
		efeld.setTextFill(Color.BLACK);
		return efeld;
	}
	
	// D i e  R e i h e n k n � p f e: 
	// [Reihe1], [Reihe2] & [Reihe3].
	public Button[] hinzufuegenReihenknoepfe() {
		String text = " Reihe ";
		for(int b = 0; b < 3; b++) {
			int nbr = b + 1;
			String btext = text + " " + nbr;
			Reihe[b] = new Button(btext);
			Reihe[b].setMinSize(25, 18);
			Reihe[b].setFont(Font.font("Tahoma", 10));
			Reihe[b].setTextFill(Color.BLACK);
			// Reihe[b].setDisable(true);

		}	
		return Reihe; 
	}

	public void deaktiviereReihenknoepfe() {
		for(int b = 0; b < 3; b++) {
			Reihe[b].setDisable(true);
		}
	}
	
	public void eintragenReihe1(int wert) {
		// TODO
	}
	
	public void eintragenReihe2(int wert) {
		// TODO
	}
	
	public void eintragenReihe3(int wert) {
		// TODO
	}

	// D a s  E i n t r a g e k n o p f f e l d: 
	// Das Eintrageknopffeld mit zwei Knopffeldern oben und unten. 
	public VBox hinzufuegenEintrageknopffelder() {
		VBox etknfeld = new VBox();
		// etknfeld.setSpacing(10);		
		etknfeld.setAlignment(Pos.CENTER);
		return etknfeld; 
	}

	// Das Knopffeld oben mit sechs Kn�pfen [9][10][B][D][K][A]. 
	public HBox hinzufuegenKnoepfeOben() {
		HBox knoben = new HBox(); 
		knoben.setSpacing(10);
		knoben.setAlignment(Pos.CENTER);
		return knoben; 
	}	
	
	// Das Knopffeld unten mit sechs Kn�pfen [St][Fu][Po][Gr][-][x]. 
	public HBox hinzufuegenKnoepfeUnten() {
		HBox knunten = new HBox(); 
		knunten.setSpacing(10);
		knunten.setAlignment(Pos.CENTER);
		return knunten; 
	}

	// D i e  B i l d e r k n � p f e: 	
	// Obere Reihe, Bilderkn�pfe. 
	public Button[] hinzufuegenBilderknoepfe() {
		for(int b = 0; b < 6; b++) {
			int offset = 1; 
			String btext = wsatz[0].waehleBild(b+offset);
			String suffix = " "; 
			if(b == 0 || b == 1) {
				suffix = "er";
			}
			if(b == 2 || b == 3) {
				suffix = "n";
			}
			if(b == 4 || b == 5) {
				suffix = "e";
			}
			Bilder[b] = new Button(btext+suffix);
			Bilder[b].setMinSize(30, 18);
			Bilder[b].setFont(Font.font("Tahoma", 10));
			Bilder[b].setTextFill(Color.ROYALBLUE);
			// Bilder[b].setDisable(true);
		}	
		return Bilder; 
	}

	public void deaktiviereBilderknoepfe() {
		for(int b = 0; b < 6; b++) {
			Bilder[b].setDisable(true);
		}
	}
	
	// D i e  M u s t e r k n � p f e: 	
	// Untere Reihe, Musterkn�pfe
	public Button[] hinzufuegenMusterknoepfe() {
		String btext = "";
		for(int b = 0; b < 6; b++) {
			if(b == 0) {btext = "Stra�e";}
			if(b == 1) {btext = "FullHouse";}
			if(b == 2) {btext = "Poker";}
			if(b == 3) {btext = "Grande";}
			if(b == 4) {btext = "streiche";} // Streichergebnis eintragen, �ffnet Modaldialog. 
			// Eintrag l�schen mit [l�sche]; fall's sich wer's kurzfristig anders �berlegt. Geht aber nur in der aktuellen Runde. 
			// Wenn der Eintrag mit Bet�tigen eines Reihenknopfs abgeschlossen wurde, werden ohnehin alle Ergebniskn�pfe deaktiviert. 
			if(b == 5) {btext = "l�sche";} // L�scht den Inhalt des EintragenWasFeld und initialisiert die Eintragedaten.  
			Muster[b] = new Button(btext);
			Muster[b].setMinSize(30, 18);
			Muster[b].setFont(Font.font("Tahoma", 10));
			Muster[b].setTextFill(Color.ROYALBLUE);
			// Muster[b].setDisable(true);
		}	
		return Muster; 
	}

	public void deaktivierMusterknoepfe() {
		for(int b = 0; b < 6; b++) {
			Muster[b].setDisable(true);
		}
	}

	// Definierter Ausgangszustand, alle Kn�pfe im Ergebnistableau deaktiviert. 
	public void initialisiereErgebnisknoepfe() {
		for(int rb = 0; rb < 3; rb++) {
			Reihe[rb].setDisable(true);
		}	
		for(int bb = 0; bb < 6; bb++) {
			Bilder[bb].setDisable(true);
		}	
		for(int mb = 0; mb < 6; mb++) {
			Muster[mb].setDisable(true);
		}	
	}
	
	// Aktiviere Bilderkn�pfe nach Wurfergebnis. 
	public void aktiviereBilderknoepfe(Wurfergebnis ergebnis) {
		if(ergebnis.getNeun() > 0) {
			Bilder[0].setDisable(false);
			}
		if(ergebnis.getZehn() > 0) {
			Bilder[1].setDisable(false);
			}
		if(ergebnis.getBube() > 0) {
			Bilder[2].setDisable(false);
			}
		if(ergebnis.getDame() > 0) {
			Bilder[3].setDisable(false);
			}
		if(ergebnis.getKoenig() > 0) {
			Bilder[4].setDisable(false);
			}
		if(ergebnis.getAss() > 0) {
			Bilder[5].setDisable(false);
			}
	}
	
	// Aktiviere Musterkn�pfe nach Wurfergebnis. 
	public void aktiviereMusterknoepfe(Wurfergebnis ergebnis) {
		if(ergebnis.istKleineStrasse()) {
			Muster[0].setDisable(false);
			}
		if(ergebnis.istGrosseStrasse()) {
			Muster[0].setDisable(false);
			}
		if(ergebnis.istFullHouse()) {
			Muster[1].setDisable(false);
			}
		if(ergebnis.istPoker()) {
			Muster[2].setDisable(false);
			}
		if(ergebnis.istGrande()) {
			Muster[3].setDisable(false);
			}
		Muster[4].setDisable(false); // Knopf [streiche] hat kein Muster. 
		Muster[5].setDisable(false); // Knopf [l�sche] hat kein Muster.
	}
	
	// Hier oberhalb Methoden und Kode zu den einzelnen FX-Nodes vom ERGEBNISTABLEAU. 

	

// GUI Komponente W�RFELTABLEAU. 
	// Erzeugermethode. 
	public GridPane erzeugeWuerfeltableau() {
		// W�RFELTABLEAU, FX-Nodes & Eigenschaften: 
		// Alle FX Nodes erzeugen
		GridPane wtableau = new GridPane();
		Label wurfzaehler = hinzufuegenWurfzaehler(wurf);
		Label serviert = hinzufuegenServierfeld();
		Label[] wz = new Label[5];
		HBox wuerfelfeld = hinzufuegenWuerfelfeld(wz); 
		HBox haltefeld = hinzufuegenHaltefeld(wurf, ergebnis, serviert); 
		wurf.initialisiereHaltemaske();
		// Haltemaske erst verf�gbar nach mind. 1 Wurf, da von 3 runter sprich ab Wurfz�hlerstand = 2.
		if(wurf.getWurfzaehler() == 3) {deaktiviereHaltefeld(haltefeld);} 
		Button wuerfeln = hinzufuegenWuerfelnKnopf();
		Button schrift = hinzufuegenSchriftKnopf();
		// [Schrift] erst verf�gbar nach mind. 1 Wurf, da von 3 runter sprich ab Wurfz�hlerstand = 2. 
		if(wurf.getWurfzaehler() == 3) { deaktiviereSchriftKnopf(schrift); } 
		schrift.setOnAction(event->aktionSchrift(wtableau));
		wuerfeln.setOnAction(event->wuerfleWurf(wurf, wurfzaehler, serviert, wuerfeln, schrift, haltefeld, wsatz, wuerfelfeld, ergebnis));
		wtableau.setMinSize(340, 84);
		wtableau.setPadding(new Insets(2, 2, 2, 2));
		wtableau.setHgap(5);
		// wtableau.setVgap(2);
		wtableau.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1))));
		wtableau.add(wurfzaehler, 0, 0, 3, 3);
		wtableau.add(serviert, 0, 3, 3, 1);
		wtableau.add(wuerfelfeld, 3, 0, 15, 3);
		wtableau.add(haltefeld, 3, 3, 15, 1);
		wtableau.add(wuerfeln, 20, 0, 4, 3);
		wtableau.add(schrift, 20, 3, 4, 1);
		wtableau.setAlignment(Pos.CENTER);	
		
		return wtableau; 
	}
	
	// Aktualisierungsmethode. 	
	public void neustartWuerfeltableau(GridPane wuerfeltableau) {
		GridPane wt = wuerfeltableau; 
		wurf.setWurfzaehler(3);
		ergebnis.initialisiereAuswerten(); // Alle W�rfelbildz�hler auf 0. 
		wurf.initialisiereGehalten(); // Gehaltenz�hler auf 0. 
		// FX Nodes initialisieren	
		aktualisiereWurfzaehler(wurf, (Label) wt.getChildrenUnmodifiable().get(0), (Button) wt.getChildrenUnmodifiable().get(4));
			System.out.println("aktualisiereWurfzaehler, wt.getChildren 0? " + (Label) wt.getChildrenUnmodifiable().get(0));
			System.out.println("aktualisiereWurfzaehler, wt.getChildren 4? " + (Button) wt.getChildrenUnmodifiable().get(4));
		wurf.initialisiereWuerfelsatz(); 
		wurf.initialisiereHaltemaske();
		// TODO Knopf [Schrift] bei Wurfz�hlerstand 3 deaktivieren. 
		Button sch = new Button(); 
		sch = (Button) wt.getChildrenUnmodifiable().get(5); 
		if(wurf.getWurfzaehler() == 3) {deaktiviereSchriftKnopf(sch);}
		aktualisiereWuerfelfeld(wsatz, (HBox) wt.getChildrenUnmodifiable().get(2)); 
		HBox haltefeld = (HBox) wt.getChildrenUnmodifiable().get(3); 
		Label serviert = (Label) wt.getChildrenUnmodifiable().get(1); 
		// neu ab 9.3.18-11:15. 
			loescheHaltefeld(wurf, haltefeld, ergebnis, serviert);
		aktualisiereServierfeld(wurf, serviert, ergebnis);
		
		}
	
	// W�RFELTABLEAU, Ende. 

	
	// Hier drunter Methoden und Kode zu den einzelnen FX-Nodes vom W�RFELTABLEAU. 	
	// D e r  W u r f z � h l e r:
	// Da kommt er ans Licht. 
	public Label hinzufuegenWurfzaehler(Wurf w) {
		Label wurfz = new Label(); 
		wurfz.setMinSize(20, 20);
		wurfz.setAlignment(Pos.CENTER);
		wurfz.setTextFill(Color.OLIVE);
		wurfz.setFont(Font.font("Tahoma", 18));
		Integer wz = w.getWurfzaehler();
		String wzz = wz.toString() + ".";
		wurfz.setText(wzz);
		return wurfz;
	}

	// Hier wird er manipuliert; sp�testens nach dreimal W�rfeln ist die Runde vorbei. 
	public void aktualisiereWurfzaehler(Wurf w, Label wurfz, Button bt) {
		Integer wz = w.getWurfzaehler();
		System.out.println("aktualisiereWurfzaehler; getWurfzahler = " + wz); 
		if(wz == 0) {
			deaktiviereWuerfelnKnopf(bt);
		}
		if(wz == 3) {
			aktiviereWuerfelnKnopf(bt);
		}
		String wzz = wz.toString() + ".";
		wurfz.setText(wzz);
	}
	
	// D a s  S e r v i e r f e l d:
	// Hier wird's angelegt. 
	public Label hinzufuegenServierfeld() {
		Label sfeld = new Label("serviert"); // 5 Smileys; Unicode [Alt]+[1]; leider nicht!  
		// sfeld.setPadding(new Insets(5, 5, 5, 5));
		sfeld.setMinSize(20, 10);
		sfeld.setFont(Font.font("Tahoma", 6));
		sfeld.setAlignment(Pos.CENTER);
		sfeld.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
		sfeld.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID,null, new BorderWidths(1))));
		sfeld.setTextFill(Color.LIGHTGRAY);
		// weiterer Kode f�r Bedingungen und Hintergrundfarbe
		return sfeld;
	}

	// Servierfeld entsprechend der Bedingungen - unm�glich, m�glich, serviert  - gestalten. 
	public void aktualisiereServierfeld(Wurf w, Label sfeld, Wurfergebnis e) {
		if(w.isMoeglicheServierung() == false || w.getGehalten() > 0){
			sfeld.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
			sfeld.setTextFill(Color.LIGHTGRAY);
			System.out.println("aktualisiereServierfeld, Servierung unm�glich, rot; w.getGehalten(): " + w.getGehalten());
		}
		if(w.getGehalten() == 0 && e.validesMuster() == false){
			sfeld.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
			sfeld.setTextFill(Color.LIGHTGRAY);
			System.out.println("aktualisiereServierfeld, Servierung m�glich, gelb; w.getGehalten(): " + w.getGehalten());
		}
		if(w.getGehalten() == 0 && e.validesMuster() == true){
		// TODO: Audiosignal; Tada.wav? 
			sfeld.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
			sfeld.setTextFill(Color.WHITE);
			System.out.println("aktualisiereServierfeld, Servierung erkannt, gr�n; w.getGehalten(): " + w.getGehalten());
		}
	}

	
	// D a s  H a l t e f e l d:
	// Haltefeld mit den f�nf Check-Boxen anlegen. 
	public HBox hinzufuegenHaltefeld(Wurf w, Wurfergebnis ergebnis, Label serviert) {
		HBox hfeld = new HBox();
		hfeld.setPadding(new Insets(5, 5, 5, 5));
		hfeld.setSpacing(10);	
		hfeld.setAlignment(Pos.CENTER);
		
		HBox hz1 = new HBox();
		Label lh1 = new Label("h: ");
		CheckBox cb1 = new CheckBox();
		aktualisiereHaltezelle(w, cb1, 0, ergebnis, serviert);
		hz1.setMinSize(34, 10);
		hz1.setAlignment(Pos.CENTER);
		hz1.getChildren().addAll(lh1, cb1);		
		
		HBox hz2 = new HBox();
		Label lh2 = new Label("h: ");
		CheckBox cb2 = new CheckBox();
		aktualisiereHaltezelle(w, cb2, 1, ergebnis, serviert);
		hz2.setMinSize(34, 10);
		hz2.setAlignment(Pos.CENTER);
		hz2.getChildren().addAll(lh2, cb2);	
		
		HBox hz3 = new HBox();
		Label lh3 = new Label("h: ");
		CheckBox cb3 = new CheckBox();
		aktualisiereHaltezelle(w, cb3, 2, ergebnis, serviert);
		hz3.setMinSize(34, 10);
		hz3.setAlignment(Pos.CENTER);
		hz3.getChildren().addAll(lh3, cb3);	
		
		HBox hz4 = new HBox();
		Label lh4 = new Label("h: ");
		CheckBox cb4 = new CheckBox();
		aktualisiereHaltezelle(w, cb4, 3, ergebnis, serviert);
		hz4.setMinSize(34, 10);
		hz4.setAlignment(Pos.CENTER);
		hz4.getChildren().addAll(lh4, cb4);	

		HBox hz5 = new HBox();
		Label lh5 = new Label("h: ");
		CheckBox cb5 = new CheckBox();
		aktualisiereHaltezelle(w, cb5, 4, ergebnis, serviert);
		hz5.setMinSize(34, 10);
		hz5.setAlignment(Pos.CENTER);
		hz5.getChildren().addAll(lh5, cb5);	
			
		hfeld.getChildren().addAll(hz1, hz2, hz3, hz4, hz5);
		
		return hfeld;
	}
	
	// Aktionskode f�r eine Haltecheckbox. 
	public void aktualisiereHaltezelle(Wurf w, CheckBox cb, int i, Wurfergebnis ergebnis, Label serviert) {
		cb.setOnAction(e->{
			if(cb.isSelected()) {
				w.halten(i);
				aktualisiereServierfeld(w, serviert, ergebnis);
			}
			if(!cb.isSelected()) {
				w.freigeben(i);
				aktualisiereServierfeld(w, serviert, ergebnis);
			}
		});
	}
	
	// Alle Check-Boxen des Haltefeld werden deselektiert.  
	public void loescheHaltefeld(Wurf w, HBox hfeld, Wurfergebnis ergebnis, Label serviert) {
		for(int h = 0; h < 5; h++) {
			HBox hb = (HBox) hfeld.getChildrenUnmodifiable().get(h);
			CheckBox cb = (CheckBox) hb.getChildrenUnmodifiable().get(1); 
			// aktualisiereHaltezelle( w,  cb, h,  ergebnis, serviert);
			cb.setSelected(false);
			System.out.println("Hier bei aktualisiereHaltefeld");
		}
	}
	
	// Das Haltefeld und damit alle Check-Boxen werden deaktiviert. 
	public void deaktiviereHaltefeld(HBox haltefeld) {
		haltefeld.setDisable(true);
	}
	
	// Das Haltefeld und damit alle Check-Boxen werden deaktiviert. 
	public void aktiviereHaltefeld(HBox haltefeld) {
		haltefeld.setDisable(false);
	}

	// D a s  W � r f e l f e l d:
	// Hier wird es angelegt. 
	public HBox hinzufuegenWuerfelfeld(Label[] wz) {
		HBox wfeld = new HBox(); 
		for(int i = 0; i < 5; i++) {
			wz[i] = new Label();
		}
		wfeld.getChildren().addAll(wz[0], wz[1], wz[2], wz[3], wz[4]);
		// Hier wird W�rfelfeld erstmalig f�r Startansicht initialisiert (3. X X X X X)
		aktualisiereWuerfelfeld(wsatz, wfeld); 
		return wfeld; 
	}
	
	// Alle f�nf W�rfel werden manipuliert. 
	public void aktualisiereWuerfelfeld(Wuerfel[] ws, HBox wfeld) {
		wfeld.setPadding(new Insets(5, 5, 5, 5));
		wfeld.setSpacing(7);
		wfeld.setAlignment(Pos.CENTER);
		// W�rfel 1
		int wuerfel = 0; 
		Label wuze1 = (Label) wfeld.getChildrenUnmodifiable().get(wuerfel);
		wuze1.setMinSize(38, 38); 
		wuze1.setAlignment(Pos.CENTER);
		wuze1.setFont(Font.font("Cambria", 22));
		setzeTextfarbe(ws[0], wuze1);
		wuze1.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(4), null)));
		wuze1.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(2))));
		wuze1.setText(ws[wuerfel].waehleKuerzel(ws[wuerfel].getWert()));
		// W�rfel 2	
		wuerfel++; 
		Label wuze2 = (Label) wfeld.getChildrenUnmodifiable().get(wuerfel);
		wuze2.setMinSize(38, 38);
		wuze2.setAlignment(Pos.CENTER);
		wuze2.setFont(Font.font("Cambria", 22));
		setzeTextfarbe(ws[1], wuze2);
		wuze2.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(4), null)));
		wuze2.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(2))));		
		wuze2.setText(ws[wuerfel].waehleKuerzel(ws[wuerfel].getWert()));
		// W�rfel 3		
		wuerfel++;  
		Label wuze3 = (Label) wfeld.getChildrenUnmodifiable().get(wuerfel);
		wuze3.setMinSize(38, 38);
		wuze3.setAlignment(Pos.CENTER);
		wuze3.setFont(Font.font("Cambria", 22));
		setzeTextfarbe(ws[2], wuze3);
		wuze3.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(4), null)));
		wuze3.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(2))));		
		wuze3.setText(ws[wuerfel].waehleKuerzel(ws[wuerfel].getWert()));
		// W�rfel 4	
		wuerfel++;  
		Label wuze4 = (Label) wfeld.getChildrenUnmodifiable().get(wuerfel);
		wuze4.setMinSize(38, 38);
		wuze4.setAlignment(Pos.CENTER);
		wuze4.setFont(Font.font("Cambria", 22));
		setzeTextfarbe(ws[3], wuze4);
		wuze4.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(4), null)));
		wuze4.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(2))));		
		wuze4.setText(ws[wuerfel].waehleKuerzel(ws[wuerfel].getWert()));
		// W�rfel 5	
		wuerfel++;  
		Label wuze5 = (Label) wfeld.getChildrenUnmodifiable().get(wuerfel);
		wuze5.setMinSize(38, 38);
		wuze5.setAlignment(Pos.CENTER);
		wuze5.setFont(Font.font("Cambria", 22));
		setzeTextfarbe(ws[4], wuze5);
		wuze5.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(4), null)));
		wuze5.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(2))));		
		wuze5.setText(ws[wuerfel].waehleKuerzel(ws[wuerfel].getWert()));
	}
	
	// Je nach Bild �ndert sich die Bildfarbe der W�rfel, wie im wirklichen Leben. 
	public void setzeTextfarbe(Wuerfel ws, Label wz) {
		switch (ws.getWert()) {
		case 1:
			wz.setTextFill(Color.BLACK);
			break;
		case 2:
			wz.setTextFill(Color.CRIMSON);
			break;
		case 3:
			wz.setTextFill(Color.BLACK);
			break;
		case 4:
			wz.setTextFill(Color.ROYALBLUE);
			break;
		case 5:
			wz.setTextFill(Color.CRIMSON);
			break;
		case 6:
			wz.setTextFill(Color.BLACK);
			break;
		default: 
			wz.setTextFill(Color.BLACK);
		}
	}

	
	// D e r  W � r f e l n k n o p f:	
	// Knopf W�rfeln, [W]
	public Button hinzufuegenWuerfelnKnopf() {
		Button btw = new Button("W"); 
		btw.setFont(Font.font("Cambria", 18));
		btw.setTextFill(Color.CRIMSON);
		btw.setMinSize(30, 24); 
		return btw;
	}

	public void deaktiviereWuerfelnKnopf(Button bt) {
		bt.setDisable(true);
	}

	public void aktiviereWuerfelnKnopf(Button bt) {
		bt.setDisable(false);
	}

	// Aktionskode f�r W�rfelnknopf; na endlich wird hier mal gew�rfelt ;) 
	public void wuerfleWurf(Wurf wurf, Label wurfzaehler, Label serviert, Button wuerfeln, Button schrift, HBox hf, Wuerfel[] wsatz, HBox wuerfelfeld, Wurfergebnis ergebnis) {
		wurf.wurfRunterzaehlen();
		aktualisiereWurfzaehler(wurf, wurfzaehler, wuerfeln);
		// Haltefeld nach Bedarf aktivieren bzw. deaktivieren! 
		if(wurf.getWurfzaehler() == 2 || wurf.getWurfzaehler() == 1) {aktiviereHaltefeld(hf);}
		if(wurf.getWurfzaehler() == 0) {deaktiviereHaltefeld(hf);}
		// Schriftknopf nach Bedarf aktivieren bzw. deaktivieren! 		
		if(wurf.getWurfzaehler() == 2 || wurf.getWurfzaehler() == 1 || wurf.getWurfzaehler() == 0) {aktiviereSchriftKnopf(schrift);}
		if(wurf.getWurfzaehler() == 3) {deaktiviereSchriftKnopf(schrift);}		
		wurf.wuerfleUngehaltene(wsatz);
		aktualisiereWuerfelfeld(wsatz, wuerfelfeld);
		ergebnis.initialisiereAuswerten();
		ergebnis.auswertenAlle(wsatz);
		System.out.println(ergebnis.toString());
		aktualisiereServierfeld(wurf, serviert, ergebnis); 
	}

	
	// D e r  S c h r i f t k n o p f:	
	// Knopf [Schrift], Etwas eintragen, auch vor drittem Wurf! 
	public Button hinzufuegenSchriftKnopf() {
		Button sch = new Button("Schrift");
		sch.setFont(Font.font("Tahoma", 10));
		sch.setTextFill(Color.ROYALBLUE);
		sch.setMinSize(30, 20); 
		return sch;
	}
	
	// Aktionskode f�r Schrift; W�rfeln deaktivieren, Ergebniskn�pfe aktivieren. 
	public void aktionSchrift(GridPane wtableau) {
		//TODO Aktionskode
		Button bt = new Button();
		bt = (Button) wtableau.getChildrenUnmodifiable().get(4);
		deaktiviereWuerfelnKnopf(bt);
		// TODO deaktiviere auch Haltefeld! 
			HBox hf = new HBox(); 
			hf = (HBox) wtableau.getChildrenUnmodifiable().get(3);
			deaktiviereHaltefeld(hf);
		aktiviereBilderknoepfe(ergebnis); 
		aktiviereMusterknoepfe(ergebnis);

	}

	public void deaktiviereSchriftKnopf(Button sch) {
		sch.setDisable(true);
	}
	
	public void aktiviereSchriftKnopf(Button sch) {
		sch.setDisable(false);
	}
	
	
	// Hier oberhalb Methoden und Kode zu den einzelnen FX-Nodes vom W�RFELTABLEAU. 

	
	
	public static void main(String[] args) {
		launch(args);
	}
}
