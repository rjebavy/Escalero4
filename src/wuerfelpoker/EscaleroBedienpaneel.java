package wuerfelpoker;

import java.net.URI;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

import com.sun.javafx.css.converters.StringConverter;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
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
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;
import wuerfelpoker.Reihen;




public class EscaleroBedienpaneel extends Application {
	
	// Escalero4 Würfelpoker
	// Wifi Wien, Software Developer Java Projektarbeit, Q1/2018. 
	// 
	// ## Creator
	//
	//	**Reinhard Jebavy**
	//	* <https://github.com/rjebavy> 
	//
	/** 
	* @version 0.155
	* @author Reinhard Jebavy
	*/

	// Schema MVC - Model, View, Controller: 
	// Der FX Scene Graph und die GUI Node Komponenten hier im EscaleroBedienpaneel bilden die View(s). 
	// Java Klassen formen das Model, und be- bzw. verarbeiten Hintergrunddaten. Sind dabei zwangsweise jedoch auch 
	// gemischt mit themenbezogenen Controller-Methoden. 
	// Dies hier, das EscaleroBedienpaneel, fungiert zusätzlich als Main-Controller, enthält aber auch GUI unterstützende Methoden. 

	
	// KONSTANTEN
	static final boolean WAHLFREIER_EINTRAGEMODUS = false;
	static final boolean EXTRA_STREICHUNG = true; 
	static final String SPITZNAME_SPIELER_1 = "Rinaldo"; 
	static final String SPITZNAME_SPIELER_2 = "Spieler2"; 
	static final String SPITZNAME_SPIELER_3 = "Spieler3"; 
	static final String SPITZNAME_SPIELER_4 = "Spieler4"; 
	static final int ANZAHL_SPIELER = 4;
	static final int ANZAHL_RUNDEN = 11; // 11 mit einer Extrastreichung, normalerweise nur 10. 
	static final int ANZAHL_WUERFE = 3;
	
	
	// INSTANZVARIABLEN
	// Globale Zähler; Wurf, Würfelsatz (Würfeln); Wurfergebnis (Auswerten), Ergebnis (Eintragen), Spielstandtafeln (Anzeigen) initialisieren. 
	int aktueller_spieler = ANZAHL_SPIELER; // globaler Spielerzähler. 
	int aktuelle_runde = ANZAHL_RUNDEN; // globaler Rundenzähler
	int aktueller_wurf = ANZAHL_WUERFE; // globaler Wurfzähler
	Wurf wurf = new Wurf(aktueller_wurf); // Wurfzähler, Haltefeld, halten/freigeben, Zufallszahl berechen etc.  
	Wuerfel[] wsatz = wurf.initialisiereWuerfelsatz(); 
	Wurfergebnis ergebnis = new Wurfergebnis(wsatz); // Auswertemethoden; Bilderanzahl, Muster. 
	Rundenzaehler rundenzaehler = new Rundenzaehler(aktuelle_runde); 
	Ergebnisfeld ergebnisfeld = new Ergebnisfeld(); // Aktionskode der Bild- und Musterknöpfe, Eintragewert berechnen. 
	Button[] Reihe = new Button[3]; // Knopffelder für's EIntragen. 
	Button[] Bilder = new Button[6]; // Knopffelder für's EIntragen. 
	Button[] Muster = new Button[6]; // Knopffelder für's EIntragen. 
	GridPane[] Spielstandtafel = new GridPane[4]; // Für jeden der 4 Spieler eine Spielstandtafel. 
	// Da HashMap keine Arrays zuläßt keine Suberzeugermethode sondern hier im Main Haupt! 
	Reihen eintragen = new Reihen();
	HashMap<Integer, Integer[]> eintragetabelle1 = eintragen.erzeugeSpielstandTabellen(); // Leere HashMap 11 x 3, gefüllt mit null. 
	HashMap<Integer, Integer[]> eintragetabelle2 = eintragen.erzeugeSpielstandTabellen(); // Leere HashMap 11 x 3, gefüllt mit null. 
	HashMap<Integer, Integer[]> eintragetabelle3 = eintragen.erzeugeSpielstandTabellen(); // Leere HashMap 11 x 3, gefüllt mit null. 
	HashMap<Integer, Integer[]> eintragetabelle4 = eintragen.erzeugeSpielstandTabellen(); // Leere HashMap 11 x 3, gefüllt mit null. 
	// Da ObservableList keine Arrays zuläßt keine Suberzeugermethode sondern hier im Main Haupt! 
	Callback<Spielstandzeile, Observable[]> cb1 = (Spielstandzeile s)-> new Observable[] {s.reihe1Property(), s.reihe2Property(), s.reihe3Property()};
	ObservableList<Spielstandzeile> olist1 = FXCollections.observableArrayList(cb1); 
	Callback<Spielstandzeile, Observable[]> cb2 = (Spielstandzeile s)-> new Observable[] {s.reihe1Property(), s.reihe2Property(), s.reihe3Property()};
	ObservableList<Spielstandzeile> olist2 = FXCollections.observableArrayList(cb2); 
	Callback<Spielstandzeile, Observable[]> cb3 = (Spielstandzeile s)-> new Observable[] {s.reihe1Property(), s.reihe2Property(), s.reihe3Property()};
	ObservableList<Spielstandzeile> olist3 = FXCollections.observableArrayList(cb3); 
	Callback<Spielstandzeile, Observable[]> cb4 = (Spielstandzeile s)-> new Observable[] {s.reihe1Property(), s.reihe2Property(), s.reihe3Property()};
	ObservableList<Spielstandzeile> olist4 = FXCollections.observableArrayList(cb4); 
	// Die Koordinaten zum Eintragen
	Integer[] spielstand_X = new Integer[] {0, 0, 0}; 
	Integer spielstand_Y = new Integer(0);
	
	// Sonstiges 
	Meldung meldung = new Meldung(); // Inhalt für das Bedienfeld einer Meldeleiste, für Statusmeldungen oder Hinweise an den Spieler. 
	boolean servierung = false; // Indikator für Servierung, für Zuschlagsberechnung in den Ergebnismethoden der Musterknöpfe. 
	
	

	@Override
	public void start(Stage primaryStage) {


		//TESTS: 
			// Würfeln erfolgreich getestet; 18.2.2018. 
			// Auswerten erfolgreich getestet; 22.2.2018. 
			// Servierungen erkennen, erfolgreich getestet; 28.2.2018. 
			// wuerfeltableau MinSize aus Scene übernommen, erfolgreich getestet; 1.3.2018. 
			// GUI Klasse 'Wurfzellentabelle' nach 'EscaleroBedienpaneel' migriert, erfolgreich getestet; 1.3.2018. 
			// 1.3.5.3.1) BUG: Sevierung erkannt obwohl 4 gehalten!! behoben 1.3.18-00:41.  
			// 5.4.1) BUG:  Bei Wiederholung mit [Nochmal] werden die Haltefelder nicht initialisiert!!! behoben ?. 
			// 0.4.5.1) GIT-BRANCH einrichten!! Deklarative Oberfläche ab Version 0.115. 
			// Kodierschluss 11.3.18-02:45. Würfel- & Ergebnistableau können Neustarten. Dort fehlt noch Eintragewerte berechnen. 
		

// Top Level FX Node item: ESCALEROBEDIENPANEEL: 
		// Würfeltableau ist kein Dummy NODE! 
		GridPane wuerfeltableau = erzeugeWuerfeltableau();
		// Ergebnistableau ist kein Dummy NODE! 
		GridPane ergebnistableau = erzeugeErgebnistableau();
		// Bedientableau ist kein Dummy NODE! 
		BorderPane bedientableau = erzeugeBedientableau(); 
		// Spielstandtableau ist kein Dummy NODE! 
		VBox spielstandtableau = erzeugeSpielstandtableau(bedientableau); // hier wird das Bedientableau übergeben wegen Meldeleiste!!
		
		// Je Spieler eine 
		
		// Temporär um nicht immer schließen und starten zu müssen. 
		Button nochmal = new Button("Nochmal!"); 
		nochmal.setFont(Font.font("Tahoma", 10));
		nochmal.setMinSize(50, 16); 
		nochmal.setOnAction(event->aktionNochmal(wuerfeltableau, ergebnistableau));
		bedientableau.setCenter(nochmal);
		
		// TEST: Meldeleiste aktualisieren. 
		// meldung.setMeldung("das ist eine neue Meldung"); 
		// aktualisiereMeldeleiste((Label) bedientableau.getChildrenUnmodifiable().get(0)); 

		
		// teste, HashMap, Reihen aufsummieren. 
		eintragen.berechneReihensummen(eintragetabelle1);
		
		// Auslesen aus eintragetabelle1: 
		// alle: 
		System.out.println("\nAuslesen alle; aus eintragetabelle1 - "); 
		String bild = "";
		for(int z = 0; z < 12; z++) {
			bild = eintragen.waehleSchluesselbild(z);
			Integer[] rinhalt = eintragetabelle1.get(z);
			System.out.println("Schlüssel - " + bild + " mit Inhalt: " + rinhalt[0] + ", " + rinhalt[1] + ", " + rinhalt[2] + ".");	
		}

		// teste, laden der Spielstandzeile(n) in die observableList: 
		Spielstandzeile zeile = new Spielstandzeile(); 
		for(int z = 0; z < 12 ; z++) {
		Integer[] lade = eintragetabelle1.get(z); // lade das Integer-Array aus der HashMap eintragetabelle1. 
		zeile.setReihe1(String.valueOf(lade[0])); // zerpflücke IntegerArray in Array-Element n von 3, konvertiere auf String und setze Spielstandzeilenelement n von 3. 
		zeile.setReihe2(String.valueOf(lade[1])); // zerpflücke IntegerArray in Array-Element n von 3, konvertiere auf String und setze Spielstandzeilenelement n von 3. 
		zeile.setReihe3(String.valueOf(lade[2])); // zerpflücke IntegerArray in Array-Element n von 3, konvertiere auf String und setze Spielstandzeilenelement n von 3. 
		olist1.add(z, zeile); // setzte ObservableList-Eintrag an Index 0 auf Spielstandzeile; add(int index, Spielstandzeile element). 
		}

		// zeige, X- & Y-Koordinaten fürs Eintragen. 
		System.out.println("\nGlobale Variable, spielstand_X[0] = " + spielstand_X[0]); 
		System.out.println("Globale Variable, spielstand_X[1] = " + spielstand_X[1]); 
		System.out.println("Globale Variable, spielstand_X[2] = " + spielstand_X[2]); 
		System.out.println("Globale Variable, spielstand_Y = " + spielstand_Y); 
		
		
	
	


	// Top-top-level Container Escalero Bedienpaneel
	VBox escalerobedienpaneel = new VBox();
	escalerobedienpaneel.setMinSize(340, 666);
	escalerobedienpaneel.setAlignment(Pos.TOP_CENTER);
	escalerobedienpaneel.getChildren().addAll(spielstandtableau, wuerfeltableau, ergebnistableau, bedientableau);

	primaryStage.setScene(new Scene(escalerobedienpaneel, 340, 666));
	// Stylesheet Verbindugn funktioniert, aber mit dem aus dem JDK kopierten schaut es trotzdem anders aus. (nth)
	escalerobedienpaneel.getStylesheets().add(EscaleroBedienpaneel.class.getResource("/styles/escaleroStyle2.css").toExternalForm());
	primaryStage.setTitle("Escalero4 - Bedienpaneel");
	primaryStage.setResizable(true);
	primaryStage.show();
	}

	// Top Level FX Node item: ESCALEROBEDIENPANEEL, Ende. 


	
// BEDIENTABLEAU, FX-Nodes & Eigenschaften: 

	//TODO 
	public BorderPane erzeugeBedientableau() {
		BorderPane btableau = new BorderPane();
		btableau.getStyleClass().add("bedientableau");
		// btableau.setMinSize(340, 58);
		Label mleiste = erzeugeMeldeleiste();
		btableau.setBottom(mleiste);		
		// TODO
		return btableau; 	
	}
	
	// BEDIENTABLEAU, Ende. 
	
	// Hier drunter Methoden und Kode zu den einzelnen FX-Nodes vom BEDIENTABLEAU. 
	
	// temporärer Aktionskode für Knopf [Nochmal!]
		// TODO Wenn Endversion wieder entfernen. 
		public void aktionNochmal(GridPane wtableau, GridPane etableau) {
			neustartWuerfeltableau(wtableau);
			neustartErgebnistableau(etableau);
			ergebnisfeld.initialisiereErgebnisfeld();
		}
		
	public Label erzeugeMeldeleiste() {
		Label meldeleiste = new Label("Keine Meldung. "); 
		meldeleiste.getStyleClass().add("meldeleiste");
		// TODO
		return meldeleiste;
	}
	
	public void aktualisiereMeldeleiste(Label meldeleiste) {
		Label mleiste = meldeleiste; 
		// mleiste.accessibleTextProperty().bindBidirectional(meldung.meldungProperty());
		mleiste.textProperty().bindBidirectional(meldung.meldungProperty());
		System.out.println(mleiste.accessibleTextProperty());
		System.out.println(meldung.meldungProperty());
	}
	
	// Hier oberhalb Methoden und Kode zu den einzelnen FX-Nodes vom BEDIENTABLEAU. 	

		
		
// SPIELSTANDTABLEAU, FX-Nodes & Eigenschaften: 
//TODO 
	// Erzeugermethode. 
	public VBox erzeugeSpielstandtableau(BorderPane bedientableau) {
		BorderPane btableau = bedientableau;
	// Alle FX Nodes erzeugen
		VBox sstableau = new VBox();
		sstableau.getStyleClass().add("spielstandtableau");
		//TODO
		sstableau.setMinSize(340, 384);
		sstableau.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1))));
		sstableau.setSpacing(10);
			// Den SpielstandKopf mit Label (Rundenzahler), Label (Was?), Buttons [Reihe1], [Reihe2], [Reihe3].
			HBox spielstandtableaukopf = hinzufuegenSpielstandKopf(btableau); 
				// TODO noch ein 5.! Element, einen "Willkommenschirm", noch unklar welchen Containertype? 
				// BorderPane mit Textarea Center?  
				// willkommenstafel
				BorderPane willkommenstafel = new BorderPane(); 
					// Hier die vertikalen Bilderbalken in den Spielstandtabellen: 
					VBox bilderbalken1 = hinzufuegenBilderBalken1();
					VBox bilderbalken2 = hinzufuegenBilderBalken2(); 
					VBox bilderbalken3 = hinzufuegenBilderBalken3(); 
					VBox bilderbalken4 = hinzufuegenBilderBalken4(); 
					// >>>> HIER MUSS DER INHALT KOMMEN!! die 4 TabelViews mit den dazugehörigen ObservableLists. 
					TableView<Spielstandzeile> spielstand1 = hinzufuegenSpielStand1(olist1);
					TableView<Spielstandzeile> spielstand2 = hinzufuegenSpielStand2(olist2);
					TableView<Spielstandzeile> spielstand3 = hinzufuegenSpielStand3(olist3);
					TableView<Spielstandzeile> spielstand4 = hinzufuegenSpielStand4(olist4);
					// Hier die horizontalen Summenbalken zuunterst in den Spielstandtabellen: 
					HBox summenbalken1 = hinzufuegenSummenBalken1();
					HBox summenbalken2 = hinzufuegenSummenBalken2();
					HBox summenbalken3 = hinzufuegenSummenBalken3();
					HBox summenbalken4 = hinzufuegenSummenBalken4();
			// Die 4 Spielstandtafeln, mit Bilderbalken links, Spielstand in der Mitte und Summenbalken unten. 
			BorderPane spielstandtafel1 = hinzufuegenSpielstandTafeln1(bilderbalken1, spielstand1, summenbalken1);
			BorderPane spielstandtafel2 = hinzufuegenSpielstandTafeln1(bilderbalken2, spielstand2, summenbalken2); 
			BorderPane spielstandtafel3 = hinzufuegenSpielstandTafeln1(bilderbalken3, spielstand3, summenbalken3); 
			BorderPane spielstandtafel4 = hinzufuegenSpielstandTafeln1(bilderbalken4, spielstand4, summenbalken4); 
		// Die Karteitafel, TabPane; als Container für 1 TableView und bis zu 3 SplitPanes.	
		TabPane spielstandansichten = hinzufuegenSpielstandAnsichten(); 
			spielstandansichten.setTabMaxHeight(16);
			spielstandansichten.setSide(Side.BOTTOM);
			ObservableList<Tab> spielstandansicht = spielstandansichten.getTabs(); 
			// System.out.println("spielstandansichten; ObservableList = " + spielstandansicht);
			spielstandansicht.get(0).setContent(willkommenstafel);
			spielstandansicht.get(1).setContent(spielstandtafel1);
			spielstandansicht.get(2).setContent(spielstandtafel2);
			spielstandansicht.get(3).setContent(spielstandtafel3);
			spielstandansicht.get(4).setContent(spielstandtafel4);
			// 

		
			// Aus unerfindlichen Gründen kann ich bei Tab bzw TabPane nicht wie sonst üblich mit GetCildren() ein Node 
			// aus einem Container herausholen!! Ich versuch's jetzt hier oberhalb direkt in die TabPane?  
				// Tab tbmeins = new Tab();
				// TabPane test = new Tab(); 
				// test = (Tab) spielstandansichten.getChildrenUnmodifiable().get(0);
				// System.out.println("spielstandansichten = " + spielstandansichten.getChildren().toArray().toString());
				// tbmeins = (Tab) spielstandansichtengetChildrenUnmodifiable().get(0);

			// TODO das hier rechts funkt nicht, method not visible!! :  spielstandansichten.getChildren().addAll(spielstandtafelmeins); 
			// TODO meins.setContent(?); 
		
		sstableau.getChildren().addAll(spielstandtableaukopf, spielstandansichten);
		sstableau.setAlignment(Pos.TOP_LEFT);
			
		return sstableau; 
	}
		
	// Aktualisierungsmethode. 	
	// public void neustartErgebnistableau(GridPane ergebnistableau) {
		// Definierter Ausgangszustand, alle Knöpfe im Ergebnistableau deaktiviert. 
		// initialisiereErgebnisknoepfe(); 
	// }		
		
	// SPIELSTANDTABLEAU, Ende. 

	
	// Hier drunter Methoden und Kode zu den einzelnen FX-Nodes vom SPIELSTANDTABLEAU

	public HBox hinzufuegenSpielstandKopf(BorderPane btableau) {
		BorderPane btab = btableau;
		HBox sskopf = new HBox(); 
		//TODO
		sskopf.setMinSize(340, 30);
		sskopf.setAlignment(Pos.CENTER);
		sskopf.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
		sskopf.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1))));
		Label spieledestages = hinzufuegenSpieleDesTages();
		Label spitzname = hinzufuegenSpitzname();
		Label modus = hinzufuegenModus(btab); 
		Label guckstdu = hinzufuegenErgebnisWert();
		Label spielervonspielern = hinzufuegenSpielerVonSpielern();
		sskopf.getChildren().addAll(spieledestages, spitzname, modus, guckstdu, spielervonspielern);
		return sskopf;
	} 

	public Label hinzufuegenSpieleDesTages() {
		Label spdt = new Label("#1"); 
		spdt.getStyleClass().add("spieledestages");
		//TODO
		spdt.setPadding(new Insets(2, 2, 2, 2));
		spdt.setMinSize(60, 24);
		// spdt.setTextFill(Color.OLIVE);
		spdt.setFont(Font.font("Tahoma", 12));
		spdt.setAlignment(Pos.CENTER);
		return spdt;
	}
	
	public Label hinzufuegenSpitzname() {
		// Label spitzn = new Label("Rinaldo"); 
		Label spitzn = new Label(); 
		if(aktueller_spieler == 4) {spitzn.setText(SPITZNAME_SPIELER_1);}
		if(aktueller_spieler == 3) {spitzn.setText(SPITZNAME_SPIELER_2);}
		if(aktueller_spieler == 2) {spitzn.setText(SPITZNAME_SPIELER_3);}
		if(aktueller_spieler == 1) {spitzn.setText(SPITZNAME_SPIELER_4);}
		spitzn.getStyleClass().add("spitzname");
		//TODO
		spitzn.setPadding(new Insets(2, 2, 2, 2));
		spitzn.setMinSize(160, 24);
		// spitzn.setTextFill(Color.ROYALBLUE);
		spitzn.setFont(Font.font("Tahoma", 12));
		spitzn.setAlignment(Pos.CENTER);
		return spitzn;
	}
	
	public Label hinzufuegenModus(BorderPane btab) {
		Label modus = new Label("W"); 
		modus.getStyleClass().add("modus");
		modus.setPadding(new Insets(2, 2, 2, 2));
		modus.setMinSize(40, 24);
		modus.setFont(Font.font("Tahoma", 12));
		if(WAHLFREIER_EINTRAGEMODUS) {
			modus.setText("W"); // wahlfreies Eintragen möglich. 
			modus.setTextFill(Color.BLACK);
			meldung.setMeldung("Spielmodus: wahlfreies eintragen. "); 
			aktualisiereMeldeleiste((Label) btab.getChildrenUnmodifiable().get(0));
			}
		if(!WAHLFREIER_EINTRAGEMODUS) {
			modus.setText("A"); // Eintragen nur in aufsteigender Reihenfolge möglich. 
			modus.setTextFill(Color.CRIMSON);
			meldung.setMeldung("Spielmodus: in aufsteigender Reihefolge eintragen. "); 
			aktualisiereMeldeleiste((Label) btab.getChildrenUnmodifiable().get(0));
			}
		modus.setAlignment(Pos.CENTER);
		return modus;
	} 

	public Label hinzufuegenErgebnisWert() {
		Label ewert = new Label("W"); 
		StringProperty sp = ewert.textProperty();
		IntegerProperty ip = ergebnisfeld.eintragewertProperty();
		NumberStringConverter converter = new NumberStringConverter();
		Bindings.bindBidirectional(sp, ip, converter);	
		ewert.getStyleClass().add("ewert");
		ewert.setPadding(new Insets(2, 2, 2, 2));
		ewert.setMinSize(40, 24);
		ewert.setFont(Font.font("Tahoma", 12));
		ewert.setTextFill(Color.CRIMSON);
		ewert.setAlignment(Pos.CENTER);
		return ewert;
	} 
	
	public Label hinzufuegenSpielerVonSpielern() {
		Label spvspl = new Label("1/1"); 
		spvspl.getStyleClass().add("spielervonspielern");
		//TODO
		spvspl.setPadding(new Insets(2, 2, 2, 2));
		spvspl.setMinSize(40, 20);
		// spvspl.setTextFill(Color.ROYALBLUE);
		spvspl.setFont(Font.font("Tahoma", 12));
		spvspl.setAlignment(Pos.CENTER);
		return spvspl;	
	}
	
	public TabPane hinzufuegenSpielstandAnsichten() {
		// TODO 
		Tab willkommen = new Tab(); 
		willkommen.setText("Willkommen!");
		willkommen.setClosable(false);
		Tab meins = new Tab(); 
			meins.setText(SPITZNAME_SPIELER_1);
			meins.setClosable(false);
		Tab eins = new Tab(); 
			eins.setText(SPITZNAME_SPIELER_2);
			eins.setClosable(false);
		Tab zwei = new Tab(); 
			zwei.setText(SPITZNAME_SPIELER_3);
			zwei.setClosable(false);
		Tab drei = new Tab(); 
			drei.setText(SPITZNAME_SPIELER_4);
			drei.setClosable(false);
		
		TabPane ssansichten = new TabPane();
		// TODO
			 ssansichten.getTabs().addAll(willkommen, meins, eins, zwei, drei);
		
		return ssansichten;		
	} 
	
	
	// Leider 4 Spielstandtafeln, TableView kann keine Arrays!! 
	public BorderPane hinzufuegenSpielstandTafeln1(VBox bilderbalken, TableView<Spielstandzeile> spielstand, HBox summenbalken) {
		BorderPane sstafeln = new BorderPane();
			sstafeln = new BorderPane();
			sstafeln.setMinSize(150, 300);
			sstafeln.setLeft(bilderbalken);
			sstafeln.setCenter(spielstand);
			sstafeln.setBottom(summenbalken);
		return sstafeln; 
	}
	
	public BorderPane hinzufuegenSpielstandTafeln2(VBox bilderbalken, TableView<Spielstandzeile> spielstand, HBox summenbalken) {
		BorderPane sstafeln = new BorderPane();
			sstafeln = new BorderPane();
			sstafeln.setMinSize(150, 300);
			sstafeln.setLeft(bilderbalken);
			sstafeln.setCenter(spielstand);
			sstafeln.setBottom(summenbalken);
		return sstafeln; 
	}
	
	public BorderPane hinzufuegenSpielstandTafeln3(VBox bilderbalken, TableView<Spielstandzeile> spielstand, HBox summenbalken) {
		BorderPane sstafeln = new BorderPane();
			sstafeln = new BorderPane();
			sstafeln.setMinSize(150, 300);
			sstafeln.setLeft(bilderbalken);
			sstafeln.setCenter(spielstand);
			sstafeln.setBottom(summenbalken);
		return sstafeln; 
	}
	
	public BorderPane hinzufuegenSpielstandTafeln4(VBox bilderbalken, TableView<Spielstandzeile> spielstand, HBox summenbalken) {
		BorderPane sstafeln = new BorderPane();
			sstafeln = new BorderPane();
			sstafeln.setMinSize(150, 300);
			sstafeln.setLeft(bilderbalken);
			sstafeln.setCenter(spielstand);
			sstafeln.setBottom(summenbalken);
		return sstafeln; 
	}

	
	// Leider 4 mal Bilderbalken, TableView kann keine Arrays!! 
	public VBox hinzufuegenBilderBalken1() {
		VBox bbalken = new VBox(); 
			bbalken = new VBox();
			// bbalken.setMinSize(60, 150);
			// bbalken.setSpacing(0);
			// bbalken.setAlignment(Pos.CENTER);
			bbalken.getStyleClass().add( "bildbalken");
			Label neun = new Label("9"); 
			neun.getStyleClass().add( "bildbalkenelement");
			Label zehn = new Label("10"); 
			zehn.getStyleClass().add( "bildbalkenelement");
			Label bube = new Label("B"); 
			bube.getStyleClass().add( "bildbalkenelement");
			Label dame = new Label("D"); 
			dame.getStyleClass().add( "bildbalkenelement");
			Label koenig = new Label("K"); 
			koenig.getStyleClass().add( "bildbalkenelement");
			Label ass = new Label("A"); 
			ass.getStyleClass().add( "bildbalkenelement");
			Label strasse = new Label("St"); 
			strasse.getStyleClass().add( "bildbalkenelement");
			Label full = new Label("Fu"); 
			full.getStyleClass().add( "bildbalkenelement");
			Label poker = new Label("Po"); 
			poker.getStyleClass().add( "bildbalkenelement");
			Label grande = new Label("Gr"); 
			grande.getStyleClass().add( "bildbalkenelement");
			if(EXTRA_STREICHUNG) {
				Label streich = new Label("X"); 
				streich.getStyleClass().add( "bildbalkenelement");
				bbalken.getChildren().addAll(neun, zehn, bube, dame, koenig, ass, strasse, full, poker, grande, streich);
				}
			if(!EXTRA_STREICHUNG) {bbalken.getChildren().addAll(neun, zehn, bube, dame, koenig, ass, strasse, full, poker, grande);
			}
		return bbalken;
	}
	
	public VBox hinzufuegenBilderBalken2() {
		VBox bbalken = new VBox(); 
			bbalken = new VBox();
			// bbalken.setMinSize(60, 150);
			// bbalken.setSpacing(0);
			// bbalken.setAlignment(Pos.CENTER);
			bbalken.getStyleClass().add( "bildbalken");
			Label neun = new Label("9"); 
			neun.getStyleClass().add( "bildbalkenelement");
			Label zehn = new Label("10"); 
			zehn.getStyleClass().add( "bildbalkenelement");
			Label bube = new Label("B"); 
			bube.getStyleClass().add( "bildbalkenelement");
			Label dame = new Label("D"); 
			dame.getStyleClass().add( "bildbalkenelement");
			Label koenig = new Label("K"); 
			koenig.getStyleClass().add( "bildbalkenelement");
			Label ass = new Label("A"); 
			ass.getStyleClass().add( "bildbalkenelement");
			Label strasse = new Label("St"); 
			strasse.getStyleClass().add( "bildbalkenelement");
			Label full = new Label("Fu"); 
			full.getStyleClass().add( "bildbalkenelement");
			Label poker = new Label("Po"); 
			poker.getStyleClass().add( "bildbalkenelement");
			Label grande = new Label("Gr"); 
			grande.getStyleClass().add( "bildbalkenelement");
			if(EXTRA_STREICHUNG) {
				Label streich = new Label("X"); 
				streich.getStyleClass().add( "bildbalkenelement");
				bbalken.getChildren().addAll(neun, zehn, bube, dame, koenig, ass, strasse, full, poker, grande, streich);
				}
			if(!EXTRA_STREICHUNG) {bbalken.getChildren().addAll(neun, zehn, bube, dame, koenig, ass, strasse, full, poker, grande);
			}
		return bbalken;
	}	

	public VBox hinzufuegenBilderBalken3() {
		VBox bbalken = new VBox(); 
			bbalken = new VBox();
			// bbalken.setMinSize(60, 150);
			// bbalken.setSpacing(0);
			// bbalken.setAlignment(Pos.CENTER);
			bbalken.getStyleClass().add( "bildbalken");
			Label neun = new Label("9"); 
			neun.getStyleClass().add( "bildbalkenelement");
			Label zehn = new Label("10"); 
			zehn.getStyleClass().add( "bildbalkenelement");
			Label bube = new Label("B"); 
			bube.getStyleClass().add( "bildbalkenelement");
			Label dame = new Label("D"); 
			dame.getStyleClass().add( "bildbalkenelement");
			Label koenig = new Label("K"); 
			koenig.getStyleClass().add( "bildbalkenelement");
			Label ass = new Label("A"); 
			ass.getStyleClass().add( "bildbalkenelement");
			Label strasse = new Label("St"); 
			strasse.getStyleClass().add( "bildbalkenelement");
			Label full = new Label("Fu"); 
			full.getStyleClass().add( "bildbalkenelement");
			Label poker = new Label("Po"); 
			poker.getStyleClass().add( "bildbalkenelement");
			Label grande = new Label("Gr"); 
			grande.getStyleClass().add( "bildbalkenelement");
			if(EXTRA_STREICHUNG) {
				Label streich = new Label("X"); 
				streich.getStyleClass().add( "bildbalkenelement");
				bbalken.getChildren().addAll(neun, zehn, bube, dame, koenig, ass, strasse, full, poker, grande, streich);
				}
			if(!EXTRA_STREICHUNG) {bbalken.getChildren().addAll(neun, zehn, bube, dame, koenig, ass, strasse, full, poker, grande);
			}
		return bbalken;
	}
	
	public VBox hinzufuegenBilderBalken4() {
		VBox bbalken = new VBox(); 
			bbalken = new VBox();
			// bbalken.setMinSize(60, 150);
			// bbalken.setSpacing(0);
			// bbalken.setAlignment(Pos.CENTER);
			bbalken.getStyleClass().add( "bildbalken");
			Label neun = new Label("9"); 
			neun.getStyleClass().add( "bildbalkenelement");
			Label zehn = new Label("10"); 
			zehn.getStyleClass().add( "bildbalkenelement");
			Label bube = new Label("B"); 
			bube.getStyleClass().add( "bildbalkenelement");
			Label dame = new Label("D"); 
			dame.getStyleClass().add( "bildbalkenelement");
			Label koenig = new Label("K"); 
			koenig.getStyleClass().add( "bildbalkenelement");
			Label ass = new Label("A"); 
			ass.getStyleClass().add( "bildbalkenelement");
			Label strasse = new Label("St"); 
			strasse.getStyleClass().add( "bildbalkenelement");
			Label full = new Label("Fu"); 
			full.getStyleClass().add( "bildbalkenelement");
			Label poker = new Label("Po"); 
			poker.getStyleClass().add( "bildbalkenelement");
			Label grande = new Label("Gr"); 
			grande.getStyleClass().add( "bildbalkenelement");
			if(EXTRA_STREICHUNG) {
				Label streich = new Label("X"); 
				streich.getStyleClass().add( "bildbalkenelement");
				bbalken.getChildren().addAll(neun, zehn, bube, dame, koenig, ass, strasse, full, poker, grande, streich);
				}
			if(!EXTRA_STREICHUNG) {bbalken.getChildren().addAll(neun, zehn, bube, dame, koenig, ass, strasse, full, poker, grande);
			}
		return bbalken;
	}

	
	// Endlich der Spielstandtabelleninhalt! Leider 4 mal, TableView kann keine Arrays!! 
	public TableView<Spielstandzeile> hinzufuegenSpielStand1(ObservableList<Spielstandzeile> olist1) {
		Spielstandzeile zeile = new Spielstandzeile(); 

		TableView<Spielstandzeile> sstand = new TableView<>();
			sstand.setMinSize(100, 150);
			TableColumn<Spielstandzeile, String> reiheEins = new TableColumn<>("Reihe 1");
			reiheEins.setCellValueFactory(new PropertyValueFactory<>("reihe1"));
			TableColumn<Spielstandzeile, String> reiheZwei = new TableColumn<>("Reihe 2");
			reiheZwei.setCellValueFactory(new PropertyValueFactory<>("reihe2"));
			TableColumn<Spielstandzeile, String> reiheDrei = new TableColumn<>("Reihe 3");
			reiheDrei.setCellValueFactory(new PropertyValueFactory<>("reihe3"));
			sstand.getColumns().addAll(reiheEins, reiheZwei, reiheDrei); 
			
		/*	
		// Lese HashMap und schreib in ObservableList	
		for(int z = 0; z < 11 ; z++) {
			Integer[] lade = eintragetabelle1.get(z); // lade das Integer-Array aus der HashMap eintragetabelle1. 
			zeile.setReihe1(String.valueOf(lade[0])); // zerpflücke IntegerArray in Array-Element n von 3, konvertiere auf String und setze Spielstandzeilenelement n von 3. 
			zeile.setReihe2(String.valueOf(lade[1])); // zerpflücke IntegerArray in Array-Element n von 3, konvertiere auf String und setze Spielstandzeilenelement n von 3. 
			zeile.setReihe3(String.valueOf(lade[2])); // zerpflücke IntegerArray in Array-Element n von 3, konvertiere auf String und setze Spielstandzeilenelement n von 3. 
			olist1.add(z, zeile); // setzte ObservableList-Eintrag an Index 0 auf Spielstandzeile; add(int index, Spielstandzeile element). 
		}
		*/ 	
			
		sstand.setItems(olist1);
		
 
		return sstand;
	}

	public TableView<Spielstandzeile> hinzufuegenSpielStand2(ObservableList<Spielstandzeile> olist2) {
		TableView<Spielstandzeile> sstand = new TableView<>();
			sstand.setMinSize(100, 150);
			TableColumn<Spielstandzeile, String> reiheEins = new TableColumn<>("Reihe 1");
			TableColumn<Spielstandzeile, String> reiheZwei = new TableColumn<>("Reihe 2");
			TableColumn<Spielstandzeile, String> reiheDrei = new TableColumn<>("Reihe 3");
			sstand.getColumns().addAll(reiheEins, reiheZwei, reiheDrei); 
		return sstand;
	}
	
	public TableView<Spielstandzeile> hinzufuegenSpielStand3(ObservableList<Spielstandzeile> olist3) {
		TableView<Spielstandzeile> sstand = new TableView<>();
			sstand.setMinSize(100, 150);
			TableColumn<Spielstandzeile, String> reiheEins = new TableColumn<>("Reihe 1");
			TableColumn<Spielstandzeile, String> reiheZwei = new TableColumn<>("Reihe 2");
			TableColumn<Spielstandzeile, String> reiheDrei = new TableColumn<>("Reihe 3");
			sstand.getColumns().addAll(reiheEins, reiheZwei, reiheDrei); 
		return sstand;
	}
	
	public TableView<Spielstandzeile> hinzufuegenSpielStand4(ObservableList<Spielstandzeile> olist4) {
		TableView<Spielstandzeile> sstand = new TableView<>();
			sstand.setMinSize(100, 150);
			TableColumn<Spielstandzeile, String> reiheEins = new TableColumn<>("Reihe 1");
			TableColumn<Spielstandzeile, String> reiheZwei = new TableColumn<>("Reihe 2");
			TableColumn<Spielstandzeile, String> reiheDrei = new TableColumn<>("Reihe 3");
			sstand.getColumns().addAll(reiheEins, reiheZwei, reiheDrei); 
		return sstand;
	}

	
	// Leider 4 mal Summenbalken, TableView kann keine Arrays!! 
	public HBox hinzufuegenSummenBalken1() {
		HBox sbalken = new HBox(); 
		Label summen = new Label(); 
		Label summereihe1 = new Label(); 
		Label summereihe2 = new Label(); 
		Label summereihe3 = new Label(); 
			sbalken = new HBox();
			sbalken.getStyleClass().add("summenbalken");
			sbalken.setMinSize(80, 20);
			sbalken.setSpacing(0);
				summen = new Label("Summen: "); 
				summen.getStyleClass().add("summen");
 				summen.setMinSize(90, 30);
				// summen[b].setAlignment(Pos.CENTER);
				summereihe1 = new Label("0"); 
				summereihe1.getStyleClass().add("summereihe1");
				summereihe1.setMinSize(83, 30);
				summereihe2 = new Label("0"); 
				summereihe2.getStyleClass().add("summereihe2");
				summereihe2.setMinSize(83, 30);
				summereihe3 = new Label("0"); 
				summereihe3.getStyleClass().add("summereihe3");
				summereihe3.setMinSize(83, 30);
			sbalken.getChildren().addAll(summen, summereihe1, summereihe2, summereihe3);
		return sbalken;
	}

	public HBox hinzufuegenSummenBalken2() {
		HBox sbalken = new HBox(); 
		Label summen = new Label(); 
		Label summereihe1 = new Label(); 
		Label summereihe2 = new Label(); 
		Label summereihe3 = new Label(); 
			sbalken = new HBox();
			sbalken.getStyleClass().add("summenbalken");
			sbalken.setMinSize(80, 20);
			sbalken.setSpacing(0);
				summen = new Label("Summen: "); 
				summen.getStyleClass().add("summen");
 				summen.setMinSize(90, 30);
				// summen[b].setAlignment(Pos.CENTER);
				summereihe1 = new Label("0"); 
				summereihe1.getStyleClass().add("summereihe1");
				summereihe1.setMinSize(83, 30);
				summereihe2 = new Label("0"); 
				summereihe2.getStyleClass().add("summereihe2");
				summereihe2.setMinSize(83, 30);
				summereihe3 = new Label("0"); 
				summereihe3.getStyleClass().add("summereihe3");
				summereihe3.setMinSize(83, 30);
			sbalken.getChildren().addAll(summen, summereihe1, summereihe2, summereihe3);
		return sbalken;
	}
	
	public HBox hinzufuegenSummenBalken3() {
		HBox sbalken = new HBox(); 
		Label summen = new Label(); 
		Label summereihe1 = new Label(); 
		Label summereihe2 = new Label(); 
		Label summereihe3 = new Label(); 
			sbalken = new HBox();
			sbalken.getStyleClass().add("summenbalken");
			sbalken.setMinSize(80, 20);
			sbalken.setSpacing(0);
				summen = new Label("Summen: "); 
				summen.getStyleClass().add("summen");
 				summen.setMinSize(90, 30);
				// summen[b].setAlignment(Pos.CENTER);
				summereihe1 = new Label("0"); 
				summereihe1.getStyleClass().add("summereihe1");
				summereihe1.setMinSize(83, 30);
				summereihe2 = new Label("0"); 
				summereihe2.getStyleClass().add("summereihe2");
				summereihe2.setMinSize(83, 30);
				summereihe3 = new Label("0"); 
				summereihe3.getStyleClass().add("summereihe3");
				summereihe3.setMinSize(83, 30);
			sbalken.getChildren().addAll(summen, summereihe1, summereihe2, summereihe3);
		return sbalken;
	}
	
	public HBox hinzufuegenSummenBalken4() {
		HBox sbalken = new HBox(); 
		Label summen = new Label(); 
		Label summereihe1 = new Label(); 
		Label summereihe2 = new Label(); 
		Label summereihe3 = new Label(); 
			sbalken = new HBox();
			sbalken.getStyleClass().add("summenbalken");
			sbalken.setMinSize(80, 20);
			sbalken.setSpacing(0);
				summen = new Label("Summen: "); 
				summen.getStyleClass().add("summen");
 				summen.setMinSize(90, 30);
				// summen[b].setAlignment(Pos.CENTER);
				summereihe1 = new Label("0"); 
				summereihe1.getStyleClass().add("summereihe1");
				summereihe1.setMinSize(83, 30);
				summereihe2 = new Label("0"); 
				summereihe2.getStyleClass().add("summereihe2");
				summereihe2.setMinSize(83, 30);
				summereihe3 = new Label("0"); 
				summereihe3.getStyleClass().add("summereihe3");
				summereihe3.setMinSize(83, 30);
			sbalken.getChildren().addAll(summen, summereihe1, summereihe2, summereihe3);
		return sbalken;
	}

	
	public HBox hinzufuegenTotalEins() {
		HBox teins = new HBox(); 
		// TODO 
		return teins;
	}
	
	public HBox hinzufuegenTotalZwei() {
		HBox tzwei = new HBox(); 
		// TODO 
		return tzwei;
	}
	
	public HBox hinzufuegenTotalDrei() {
		HBox tdrei = new HBox(); 
		// TODO 
		return tdrei;
	}
	
	// Hier oberhalb Methoden und Kode zu den einzelnen FX-Nodes vom SPIELSTANDTABLEAU. 	
	

			
// ERGEBNISTABLEAU, FX-Nodes & Eigenschaften: 
	// Erzeugermethode. 
	public GridPane erzeugeErgebnistableau() {
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
				bilderKnopf[0].setOnAction(event->aktionNeuner());
				bilderKnopf[1].setOnAction(event->aktionZehner());
				bilderKnopf[2].setOnAction(event->aktionBuben());
				bilderKnopf[3].setOnAction(event->aktionDamen());
				bilderKnopf[4].setOnAction(event->aktionKoenige());
				bilderKnopf[5].setOnAction(event->aktionAsse());
			bilderfeld.getChildren().addAll(Bilder[0], Bilder[1], Bilder[2], Bilder[3], Bilder[4], Bilder[5]);
			HBox musterfeld = hinzufuegenKnoepfeUnten(); 
				Button[] musterKnopf = hinzufuegenMusterknoepfe();
				musterKnopf[0].setOnAction(event->aktionStrasse());
				musterKnopf[1].setOnAction(event->aktionFullhouse());
				musterKnopf[2].setOnAction(event->aktionPoker());
				musterKnopf[3].setOnAction(event->aktionGrande());
				// TODO musterKnopf[4].setOnAction(event-><Aktionshmethode Löschen>);
				// TODO musterKnopf[5].setOnAction(event-><Aktionshmethode Streichen>);
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
		// Definierter Ausgangszustand, alle Knöpfe im Ergebnistableau deaktiviert. 
		initialisiereErgebnisknoepfe(); 

		
	}
	
	// ERGEBNISTABLEAU, Ende. 		

	
	// Hier drunter Methoden und Kode zu den einzelnen FX-Nodes vom ERGEBNISTABLEAU. 
	// D a s  R e i h e n f e l d: 
	// Für das Reihenfeld mit Label (Rundenzähler), Label (Was?), Buttons [Reihe1], [Reihe2], [Reihe3]. 
	public HBox hinzufuegenReihenfeld() {
		HBox rfeld = new HBox(); 
		rfeld.setSpacing(5);
	return rfeld; 
	}

	// D e r  R u n d e n z ä h l e r:
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
	// Hier wird angezeigt was mit den Reihenknöpfen eingetragen werden kann. 
	public Label hinzufuegenEintragenWas() {
		ergebnisfeld.initialisiereErgebnisfeld();
		String ltext = ergebnisfeld.getEintragentext();
		Label efeld = new Label(ltext); // für Ergebnistableau-Test stand da "FullHouse serviert." drin. 
		efeld.textProperty().bindBidirectional(ergebnisfeld.eintragentextProperty());
		efeld.setPadding(new Insets(2, 2, 2, 2));
		efeld.setMinSize(120, 20);
		efeld.setFont(Font.font("Tahoma", 12));
		efeld.setAlignment(Pos.CENTER);
		efeld.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(3), null)));
		efeld.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(1))));
		efeld.setTextFill(Color.BLACK);
		return efeld;
	}
	
	// D i e  R e i h e n k n ö p f e: 
	// [Reihe1], [Reihe2] & [Reihe3].
	public Button[] hinzufuegenReihenknoepfe() {
		String text = " Reihe ";
		for(int b = 0; b < 3; b++) {
			int nbr = b + 1;
			String btext = text + " " + nbr;
			Reihe[b] = new Button(btext);
			Reihe[b].getStyleClass().add("reihenknopf");
			Reihe[b].setMinSize(25, 18);
			Reihe[b].setFont(Font.font("Tahoma", 10));
			// Reihe[b].setTextFill(Color.BLACK);
			// Reihe[b].setDisable(true);

		}	
		return Reihe; 
	}

	public void deaktiviereReihenknoepfe() {
		for(int b = 0; b < 3; b++) {
			Reihe[b].setDisable(true);
		}
	}
	
	// Wenn ein Eintrageknopf geklickt wurde alle Reihenknöpfe eaktivieren. Wird im Aktionskode für jeden Eintrageknopf verwendet. 
	public void aktiviereReihenknoepfe() {
		for(int b = 0; b < 3; b++) {
			Reihe[b].setDisable(false);
		}
		// TODO Koordinaten nur für Test, in Endversion von hier entfernen. 
		// zeige, X- & Y-Koordinaten fürs Eintragen. 
		System.out.println("\nGlobale Variable, spielstand_X[0] = " + spielstand_X[0]); 
		System.out.println("Globale Variable, spielstand_X[1] = " + spielstand_X[1]); 
		System.out.println("Globale Variable, spielstand_X[2] = " + spielstand_X[2]); 
		System.out.println("Globale Variable, spielstand_Y = " + spielstand_Y); 
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

	// Das Knopffeld oben mit sechs Knöpfen [9][10][B][D][K][A]. 
	public HBox hinzufuegenKnoepfeOben() {
		HBox knoben = new HBox(); 
		knoben.setSpacing(10);
		knoben.setAlignment(Pos.CENTER);
		return knoben; 
	}	
	
	// Das Knopffeld unten mit sechs Knöpfen [St][Fu][Po][Gr][-][x]. 
	public HBox hinzufuegenKnoepfeUnten() {
		HBox knunten = new HBox(); 
		knunten.setSpacing(10);
		knunten.setAlignment(Pos.CENTER);
		return knunten; 
	}

	// D i e  B i l d e r k n ö p f e: 	
	// Obere Reihe, Bilderknöpfe. 
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
			Bilder[b].getStyleClass().add("bildknopf");
			Bilder[b].setMinSize(30, 18);
			Bilder[b].setFont(Font.font("Tahoma", 10));
			// Bilder[b].setTextFill(Color.ROYALBLUE);
			// Bilder[b].setDisable(true);
		}	
		return Bilder; 
	}

	// Aktionskode Knopf [Neuner]
	public void aktionNeuner() {
		ergebnisfeld.eintragenNeuner(ergebnis);
		spielstand_Y = 0;
		deaktiviereAlleKnoepfeBisAufLoesche();
		aktiviereReihenknoepfe();	
	}
	// Aktionskode Knopf [Zehner]	
	public void aktionZehner() {
		ergebnisfeld.eintragenZehner(ergebnis);
		spielstand_Y = 1;
		deaktiviereAlleKnoepfeBisAufLoesche();
		aktiviereReihenknoepfe();	
	}
	// Aktionskode Knopf [Buben]	
	public void aktionBuben() {
		ergebnisfeld.eintragenBuben(ergebnis);
		spielstand_Y = 2;
		deaktiviereAlleKnoepfeBisAufLoesche();
		aktiviereReihenknoepfe();	
	}
	// Aktionskode Knopf [Damen]	
	public void aktionDamen() {
		ergebnisfeld.eintragenDamen(ergebnis);
		spielstand_Y = 3;
		deaktiviereAlleKnoepfeBisAufLoesche();
		aktiviereReihenknoepfe();	
	}
	// Aktionskode Knopf [Könige]	
	public void aktionKoenige() {
		ergebnisfeld.eintragenKoenige(ergebnis);
		spielstand_Y = 4;
		deaktiviereAlleKnoepfeBisAufLoesche();
		aktiviereReihenknoepfe();	
	}
	// Aktionskode Knopf [Asse]	
	public void aktionAsse() {
		ergebnisfeld.eintragenAsse(ergebnis);
		spielstand_Y = 5;
		deaktiviereAlleKnoepfeBisAufLoesche();
		aktiviereReihenknoepfe();	
	}
	
	
	// D i e  M u s t e r k n ö p f e: 	
	// Untere Reihe, Musterknöpfe
	public Button[] hinzufuegenMusterknoepfe() {
		String btext = "";
		for(int b = 0; b < 6; b++) {
			if(b == 0) {btext = "Straße";}
			if(b == 1) {btext = "FullHouse";}
			if(b == 2) {btext = "Poker";}
			if(b == 3) {btext = "Grande";}
			if(b == 4) {btext = "streiche";} // Streichergebnis eintragen, öffnet Modaldialog. 
			// Eintrag löschen mit [lösche]; fall's sich wer's kurzfristig anders überlegt. Geht aber nur in der aktuellen Runde. 
			// Wenn der Eintrag mit Betätigen eines Reihenknopfs abgeschlossen wurde, werden ohnehin alle Ergebnisknöpfe deaktiviert. 
			if(b == 5) {btext = "lösche";} // Löscht den Inhalt des EintragenWasFeld und initialisiert die Eintragedaten.  
			Muster[b] = new Button(btext);
			Muster[b].getStyleClass().add("musterknopf");
			Muster[b].setMinSize(30, 18);
			Muster[b].setFont(Font.font("Tahoma", 10));
			// Muster[b].setTextFill(Color.ROYALBLUE);
			// Muster[b].setDisable(true);
		}	
		return Muster; 
	}

	// Aktionskode Knopf [Straße]
	public void aktionStrasse() {
		ergebnisfeld.eintragenStrasse(ergebnis, servierung);
		spielstand_Y = 6;
		deaktiviereAlleKnoepfeBisAufLoesche();
		aktiviereReihenknoepfe();
	}
	// Aktionskode Knopf [FullHouse]
	public void aktionFullhouse() {
		ergebnisfeld.eintragenFullhouse(ergebnis, servierung);
		spielstand_Y = 7;
		deaktiviereAlleKnoepfeBisAufLoesche();
		aktiviereReihenknoepfe();
	}
	// Aktionskode Knopf [Poker]
	public void aktionPoker() {
		ergebnisfeld.eintragenPoker(ergebnis, servierung);
		spielstand_Y = 8;
		deaktiviereAlleKnoepfeBisAufLoesche();
		aktiviereReihenknoepfe();
	}
	// Aktionskode Knopf [Grande]
	public void aktionGrande() {
		ergebnisfeld.eintragenGrande(ergebnis, servierung);
		spielstand_Y = 9;
		deaktiviereAlleKnoepfeBisAufLoesche();
		aktiviereReihenknoepfe();
	}
	
	// Ergebnisknöpfe allgemein. 
	public void deaktiviereBilderknoepfe() {
		for(int b = 0; b < 6; b++) {
			Bilder[b].setDisable(true);
		}
	}
	
	public void deaktiviereMusterknoepfe() {
		for(int b = 0; b < 6; b++) {
			Muster[b].setDisable(true);
		}
	}

	// Definierter Ausgangszustand, alle Knöpfe im Ergebnistableau deaktiviert. 
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
	
	// Aktiviere Bilderknöpfe nach Wurfergebnis. 
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
	
	// Aktiviere Musterknöpfe nach Wurfergebnis. 
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
		Muster[5].setDisable(false); // Knopf [lösche] hat kein Muster.
	}
	
	// Wenn ein Eintrageknopf geklickt wurde alle bis auf [lösche] deaktivieren. Wird im Aktionskode für jeden Eintrageknopf verwendet. 
	public void deaktiviereAlleKnoepfeBisAufLoesche() {
		deaktiviereBilderknoepfe();
		deaktiviereMusterknoepfe(); 
		Muster[5].setDisable(false);
	}
	
	// Hier oberhalb Methoden und Kode zu den einzelnen FX-Nodes vom ERGEBNISTABLEAU. 

	

// GUI Komponente WÜRFELTABLEAU. 
	// Erzeugermethode. 
	public GridPane erzeugeWuerfeltableau() {
		// WÜRFELTABLEAU, FX-Nodes & Eigenschaften: 
		// Alle FX Nodes erzeugen
		GridPane wtableau = new GridPane();
		Label wurfzaehler = hinzufuegenWurfzaehler(wurf);
		Label serviert = hinzufuegenServierfeld();
		Label[] wz = new Label[5];
		HBox wuerfelfeld = hinzufuegenWuerfelfeld(wz); 
		HBox haltefeld = hinzufuegenHaltefeld(wurf, ergebnis, serviert); 
		wurf.initialisiereHaltemaske();
		// Haltemaske erst verfügbar nach mind. 1 Wurf, da von 3 runter sprich ab Wurfzählerstand = 2.
		if(wurf.getWurfzaehler() == 3) {deaktiviereHaltefeld(haltefeld);} 
		Button wuerfeln = hinzufuegenWuerfelnKnopf();
		Button schrift = hinzufuegenSchriftKnopf();
		// [Schrift] erst verfügbar nach mind. 1 Wurf, da von 3 runter sprich ab Wurfzählerstand = 2. 
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
		ergebnis.initialisiereAuswerten(); // Alle Würfelbildzähler auf 0. 
		wurf.initialisiereGehalten(); // Gehaltenzähler auf 0. 
		// FX Nodes initialisieren	
		aktualisiereWurfzaehler(wurf, (Label) wt.getChildrenUnmodifiable().get(0), (Button) wt.getChildrenUnmodifiable().get(4));
			System.out.println("aktualisiereWurfzaehler, wt.getChildren 0? " + (Label) wt.getChildrenUnmodifiable().get(0));
			System.out.println("aktualisiereWurfzaehler, wt.getChildren 4? " + (Button) wt.getChildrenUnmodifiable().get(4));
		wurf.initialisiereWuerfelsatz(); 
		wurf.initialisiereHaltemaske();
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
	
	// WÜRFELTABLEAU, Ende. 

	
	// Hier drunter Methoden und Kode zu den einzelnen FX-Nodes vom WÜRFELTABLEAU. 	
	// D e r  W u r f z ä h l e r:
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

	// Hier wird er manipuliert; spätestens nach dreimal Würfeln ist die Runde vorbei. 
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
		sfeld.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(2), null)));
		sfeld.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1))));
		sfeld.setTextFill(Color.LIGHTGRAY);
		// weiterer Kode für Bedingungen und Hintergrundfarbe
		return sfeld;
	}

	// Servierfeld entsprechend der Bedingungen - unmöglich, möglich, serviert  - gestalten. 
	public void aktualisiereServierfeld(Wurf w, Label sfeld, Wurfergebnis e) {
		// Servierung ist unmöglich, da mind. 1 Würfel gehalten, Hintergrundfarbe rot. 
		if(w.isMoeglicheServierung() == false || w.getGehalten() > 0){
			sfeld.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
			sfeld.setTextFill(Color.LIGHTGRAY);
			System.out.println("WÜRFELTABLEAU; aktualisiereServierfeld, Servierung unmöglich, rot; w.getGehalten(): " + w.getGehalten());
		}
		// Servierung möglich, da kein Würfel gehalten, Hintergrundfarbe gelb. 
		if(w.getGehalten() == 0 && e.validesMuster() == false){
			sfeld.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
			sfeld.setTextFill(Color.LIGHTGRAY);
			System.out.println("WÜRFELTABLEAU; aktualisiereServierfeld, Servierung möglich, gelb; w.getGehalten(): " + w.getGehalten());
		}
		// Servierung wurde erkannt, Hintergrundfarbe grün. 
		if(w.getGehalten() == 0 && e.validesMuster() == true){
		// TODO: Audiosignal; Tada.wav? 
			sfeld.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
			sfeld.setTextFill(Color.WHITE);
			// WICHTIG für Berechnung des Servierzuschlags in den Aktionsmethoden zu den Musterknöpfen im Ergebnisfeld!! 
					servierung = true;
			System.out.println("WÜRFELTABLEAU; aktualisiereServierfeld, Servierung erkannt, grün; w.getGehalten(): " + w.getGehalten() + ", Servierindikator = " + servierung);
		}
	}

	
	// D a s  H a l t e f e l d:
	// Haltefeld mit den fünf Check-Boxen anlegen. 
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
	
	// Aktionskode für eine Haltecheckbox. 
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

	// D a s  W ü r f e l f e l d:
	// Hier wird es angelegt. 
	public HBox hinzufuegenWuerfelfeld(Label[] wz) {
		HBox wfeld = new HBox(); 
		for(int i = 0; i < 5; i++) {
			wz[i] = new Label();
		}
		wfeld.getChildren().addAll(wz[0], wz[1], wz[2], wz[3], wz[4]);
		// Hier wird Würfelfeld erstmalig für Startansicht initialisiert (3. X X X X X)
		aktualisiereWuerfelfeld(wsatz, wfeld); 
		return wfeld; 
	}
	
	// Alle fünf Würfel werden manipuliert. 
	public void aktualisiereWuerfelfeld(Wuerfel[] ws, HBox wfeld) {
		wfeld.setPadding(new Insets(5, 5, 5, 5));
		wfeld.setSpacing(7);
		wfeld.setAlignment(Pos.CENTER);
		// Würfel 1
		int wuerfel = 0; 
		Label wuze1 = (Label) wfeld.getChildrenUnmodifiable().get(wuerfel);
		wuze1.setMinSize(38, 38); 
		wuze1.setAlignment(Pos.CENTER);
		wuze1.setFont(Font.font("Cambria", 22));
		setzeTextfarbe(ws[0], wuze1);
		wuze1.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(4), null)));
		wuze1.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(2))));
		wuze1.setText(ws[wuerfel].waehleKuerzel(ws[wuerfel].getWert()));
		// Würfel 2	
		wuerfel++; 
		Label wuze2 = (Label) wfeld.getChildrenUnmodifiable().get(wuerfel);
		wuze2.setMinSize(38, 38);
		wuze2.setAlignment(Pos.CENTER);
		wuze2.setFont(Font.font("Cambria", 22));
		setzeTextfarbe(ws[1], wuze2);
		wuze2.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(4), null)));
		wuze2.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(2))));		
		wuze2.setText(ws[wuerfel].waehleKuerzel(ws[wuerfel].getWert()));
		// Würfel 3		
		wuerfel++;  
		Label wuze3 = (Label) wfeld.getChildrenUnmodifiable().get(wuerfel);
		wuze3.setMinSize(38, 38);
		wuze3.setAlignment(Pos.CENTER);
		wuze3.setFont(Font.font("Cambria", 22));
		setzeTextfarbe(ws[2], wuze3);
		wuze3.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(4), null)));
		wuze3.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(2))));		
		wuze3.setText(ws[wuerfel].waehleKuerzel(ws[wuerfel].getWert()));
		// Würfel 4	
		wuerfel++;  
		Label wuze4 = (Label) wfeld.getChildrenUnmodifiable().get(wuerfel);
		wuze4.setMinSize(38, 38);
		wuze4.setAlignment(Pos.CENTER);
		wuze4.setFont(Font.font("Cambria", 22));
		setzeTextfarbe(ws[3], wuze4);
		wuze4.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(4), null)));
		wuze4.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(2))));		
		wuze4.setText(ws[wuerfel].waehleKuerzel(ws[wuerfel].getWert()));
		// Würfel 5	
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
	
	// Je nach Bild ändert sich die Bildfarbe der Würfel, wie im wirklichen Leben. 
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

	
	// D e r  W ü r f e l n k n o p f:	
	// Knopf Würfeln, [W]
	public Button hinzufuegenWuerfelnKnopf() {
		Button btw = new Button("W"); 
		btw.getStyleClass().add("wuerfelnknopf");
		btw.setFont(Font.font("Cambria", 18));
		// btw.setTextFill(Color.CRIMSON);
		btw.setMinSize(30, 24); 
		return btw;
	}

	public void deaktiviereWuerfelnKnopf(Button bt) {
		bt.setDisable(true);
	}

	public void aktiviereWuerfelnKnopf(Button bt) {
		bt.setDisable(false);
	}

	// Aktionskode für Würfelnknopf; na endlich wird hier mal gewürfelt ;) 
	public void wuerfleWurf(Wurf wurf, Label wurfzaehler, Label serviert, Button wuerfeln, Button schrift, HBox hf, Wuerfel[] wsatz, HBox wuerfelfeld, Wurfergebnis ergebnis) {
		wurf.wurfRunterzaehlen();
		aktualisiereWurfzaehler(wurf, wurfzaehler, wuerfeln);
		// Haltefeld nach Bedarf aktivieren bzw. deaktivieren! 
		if(wurf.getWurfzaehler() == 2 || wurf.getWurfzaehler() == 1) {aktiviereHaltefeld(hf);}
		if(wurf.getWurfzaehler() == 0) {deaktiviereHaltefeld(hf);}
		// Schriftknopf nach Bedarf aktivieren bzw. deaktivieren! 		
		if(wurf.getWurfzaehler() == 2 || wurf.getWurfzaehler() == 1 || wurf.getWurfzaehler() == 0) {aktiviereSchriftKnopf(schrift);}
		if(wurf.getWurfzaehler() == 3) {deaktiviereSchriftKnopf(schrift);}
		servierung = false;
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
		sch.getStyleClass().add("schriftknopf");
		sch.setFont(Font.font("Tahoma", 10));
		// sch.setTextFill(Color.ROYALBLUE);
		sch.setMinSize(30, 20); 
		return sch;
	}
	
	// Aktionskode für Schrift; Würfeln deaktivieren, Ergebnisknöpfe aktivieren. 
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
	
	
	// Hier oberhalb Methoden und Kode zu den einzelnen FX-Nodes vom WÜRFELTABLEAU. 

	
	
	public static void main(String[] args) {
		launch(args);
	}
}
