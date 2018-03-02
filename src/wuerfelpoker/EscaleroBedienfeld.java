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

public class EscaleroBedienfeld extends Application {
	
	// Escalero4 Würfelpoker
	// Wifi Wien, Software Developer Java Projektarbeit, Q1/2018. 
	// 
	// ## Creator
	//
	//	**Reinhard Jebavy**
	//	* <https://github.com/rjebavy> 
	//
	/** 
	* @version 0.110
	* @author Reinhard Jebavy
	*/

	// INSTANZVARIABLEN
	// Wurf, Würfelsatz & Ergebnis initialisieren
	Wurf wurf = new Wurf(3);
	Wuerfel[] wsatz = wurf.initialisiereWuerfelsatz();
	Wurfergebnis ergebnis = new Wurfergebnis(wsatz);	
	

	@Override
	public void start(Stage primaryStage) {


		//TESTS: 
			// Würfeln erfolgreich getestet; 18.2.2018. 
			// Auswerten erfolgreich getestet; 22.2.2018. 
			// Servierungen erkennen, erfolgreich getestet; 28.2.2018. 
			// wuerfeltableau MinSize aus Scene übernommen, erfolgreich getestet; 1.3.2018. 
			// GUI Klasse 'Wurfzellentabelle' nach 'EscaleroBedienfeld' migriert, erfolgreich getestet; 1.3.2018. 
		
			
			

		

	// ERGEBNISTABLEAU, FX-Nodes & Eigenschaften: 		
		GridPane ergebnistableau = new GridPane();
		ergebnistableau.setMinSize(340, 112);
		ergebnistableau.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1))));
		HBox reihenfeld = hinzufuegenReihenfeld(); 
		VBox eintrageknopffelder = hinzufuegenEintrageknopffelder(); 
		// GridPane.add(Node child, int columnIndex, int rowIndex, int colspan, int rowspan).
		ergebnistableau.add(reihenfeld, 0, 0, 1, 1);
		ergebnistableau.add(eintrageknopffelder, 0, 1, 1, 1);
		ergebnistableau.setAlignment(Pos.CENTER);
	
	// ERGEBNISTABLEAU, Ende. 		
		
		
	// Top Level FX Node item: ESCALEROBEDIENFELD
		// Temporäre Dummy-Tableaus
		VBox spielstandtableau = new VBox();
		spielstandtableau.setMinSize(340, 340); 
		// Würfeltableau ist kein Dummy NODE! 
		GridPane wuerfeltableau = erzeugeWuerfeltableau();
		VBox bedientableau = new VBox();
		bedientableau.setMinSize(340, 139);
		bedientableau.setAlignment(Pos.CENTER);
			// Temporär um nicht immer schließen und starten zu müssen. 
			Button nochmal = new Button("Nochmal!"); 
			nochmal.setFont(Font.font("Tahoma", 10));
			nochmal.setMinSize(50, 16); 
			nochmal.setOnAction(event->neustartWuerfeltableau(wuerfeltableau));
			bedientableau.getChildren().addAll(nochmal);
	

	// Top-top-level Container Escalero Bedienfeld
	VBox escalerobedienfeld = new VBox();
	escalerobedienfeld.setMinSize(340, 666);
	escalerobedienfeld.setAlignment(Pos.CENTER);
	escalerobedienfeld.getChildren().addAll(spielstandtableau, wuerfeltableau, ergebnistableau, bedientableau);

	primaryStage.setScene(new Scene(escalerobedienfeld, 340, 666));
	primaryStage.setTitle("Escalero4 - Bedienfeld");
	primaryStage.setResizable(true);
	primaryStage.show();
	}

	
	
// Hier drunter Methoden und Kode zu den einzelnen FX-Nodes vom ERGEBNISTABLEAU. 
	
	// Das Reihenfeld mit Label (Was?), Buttons [Reihe1], [Reihe2], [Reihe3]. 
	public HBox hinzufuegenReihenfeld() {
		HBox rfeld = new HBox(); 
	return rfeld; 

	}
	
	// Das Eintrageknopffeld mit zwei Knopffeldern oben und unten. 
	public VBox hinzufuegenEintrageknopffelder() {
		VBox etknfeld = new VBox(); 
		HBox etrgknoepfeoben = hinzufuegenKnoepfeOben(); 
		HBox etrgknoepfeunten = hinzufuegenKnoepfeUnten(); 
		return etknfeld; 
	}

	// Das Knopffeld oben mit sechs Knöpfen [9][10][B][D][K][A]. 
	public HBox hinzufuegenKnoepfeOben() {
		HBox knoben = new HBox(); 
		return knoben; 

	}	
	
	// Das Knopffeld oben mit fünf Knöpfen [St][Fu][Po][Gr][-]. 
	public HBox hinzufuegenKnoepfeUnten() {
		HBox knunten = new HBox(); 
		return knunten; 

	}

// Hier oberhalb Methoden und Kode zu den einzelnen FX-Nodes vom ERGEBNISTABLEAU. 
	

// GUI Komponente Würfeltableau. 
	public GridPane erzeugeWuerfeltableau() {
		// WÜRFELTABLEAU, FX-Nodes & Eigenschaften: 
		GridPane wtableau = new GridPane();
		// Alle FX Nodes erzeugen	
		Label wurfzaehler = hinzufuegenWurfzaehler(wurf);
		Label serviert = hinzufuegenServierfeld();
		Label wz1 = new Label();
		Label wz2 = new Label();
		Label wz3 = new Label();
		Label wz4 = new Label();
		Label wz5 = new Label();
		
		HBox wuerfelfeld = new HBox(); 
		wuerfelfeld.getChildren().addAll(wz1, wz2, wz3, wz4, wz5);
		
		HBox haltefeld = hinzufuegenHaltefeld(wurf, ergebnis, serviert); 
		
		Button wuerfeln = hinzufuegenWuerfelnKnopf();
		wuerfeln.setOnAction(event->wuerfleWurf(wurf, wurfzaehler, serviert, wuerfeln, haltefeld, wsatz, wuerfelfeld, ergebnis, wz1, wz2, wz3, wz4, wz5));

		wtableau.setMinSize(340, 84);
		wtableau.setPadding(new Insets(2, 2, 2, 2));
		wtableau.setHgap(5);
		wtableau.setVgap(5);
		wtableau.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1))));
		wtableau.add(wurfzaehler, 0, 0, 3, 3); // index 0 in der GridPane
		wtableau.add(serviert, 0, 3, 3, 1); // index 1 in der GridPane
		wtableau.add(wuerfelfeld, 3, 0, 15, 3); // index 2 in der GridPane
		wtableau.add(haltefeld, 3, 3, 15, 1); // index 3 in der GridPane
		wtableau.add(wuerfeln, 20, 0, 4, 4); // index 4 in der GridPane
		wtableau.setAlignment(Pos.CENTER);	
		
		aktualisiereWuerfeltableau(wtableau, wuerfelfeld, wz1, wz2, wz3, wz4, wz5, haltefeld); 
		System.out.println("erzeugeWuerfeltableau; wtableau, child-node at index 0 = " + wtableau.getChildrenUnmodifiable().get(0));
		System.out.println("erzeugeWuerfeltableau; wtableau, child-node at index 1 = " + wtableau.getChildrenUnmodifiable().get(1)); 
		System.out.println("erzeugeWuerfeltableau; wtableau, child-node at index 2 = " + wtableau.getChildrenUnmodifiable().get(2));
		System.out.println("erzeugeWuerfeltableau; wtableau, child-node at index 3 = " + wtableau.getChildrenUnmodifiable().get(3));
		System.out.println("erzeugeWuerfeltableau; wtableau, child-node at index 4 = " + wtableau.getChildrenUnmodifiable().get(4));
		return wtableau; 
	}
	
	public void aktualisiereWuerfeltableau(GridPane wtableau, HBox wfeld, Label wz1, Label wz2, Label wz3, Label wz4, Label wz5, HBox hfeld) {
		// Hier wird Würfelfeld erstmalig für Startansicht initialisiert (3. X X X X X)
		// aktualisiereWuerfelfeld(wsatz, wfeld, wz1, wz2, wz3, wz4, wz5);
		aktualisiereWuerfelfeld(wsatz, (HBox) wtableau.getChildrenUnmodifiable().get(2), wz1, wz2, wz3, wz4, wz5);
		// Haltemaske wird initialisiert alle nicht gehalten. 
		wurf.initialisiereHaltemaske();
		if(wurf.getWurfzaehler() == 3) hfeld.setDisable(true); 
	
	}	
	
	
	public void neustartWuerfeltableau(GridPane wtableau) {
		wurf.setWurfzaehler(3);
		// aktualisiereWuerfeltableau(wtableau, wuerfelfeld, wz1, wz2, wz3, wz4, wz5, haltefeld);
	}
	
// WÜRFELTABLEAU, Ende. 
	
	
	
// Hier drunter Methoden und Kode zu den einzelnen FX-Nodes vom WÜRFELTABLEAU. 	
	public Button hinzufuegenWuerfelnKnopf() {
		Button btw = new Button("W"); 
		btw.setFont(Font.font("Cambria", 24));
		btw.setMinSize(30, 30); 
		return btw;
	}

	public void deaktiviereWuerfelnKnopf(Button bt) {
		bt.setDisable(true);
	}
	
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

	public void aktualisiereWurfzaehler(Wurf w, Label wurfz, Button bt) {
		Integer wz = w.getWurfzaehler();
		if(wz == 0) 
			deaktiviereWuerfelnKnopf(bt);
		String wzz = wz.toString() + ".";
		wurfz.setText(wzz);
	}
	
	public Label hinzufuegenServierfeld() {
		Label sfeld = new Label("serviert"); // 5 Smileys; Unicode [Alt]+[1]; leider nicht!  
		// sfeld.setPadding(new Insets(5, 5, 5, 5));
		sfeld.setMinSize(20, 10);
		sfeld.setFont(Font.font("Tahoma", 6));
		sfeld.setAlignment(Pos.CENTER);
		sfeld.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
		sfeld.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID,null, new BorderWidths(1))));
		sfeld.setTextFill(Color.LIGHTGRAY);
		// weiterer Kode für Bedingungen und Hintergrundfarbe
		return sfeld;
	}

	// Servierfeld entsprechend der Bedingungen - unmöglich, möglich, serviert  - gestalten. 
	public void aktualisiereServierfeld(Wurf w, Label sfeld, Wurfergebnis e) {
		if(w.isMoeglicheServierung() == false || w.getGehalten() > 0){
			sfeld.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
			sfeld.setTextFill(Color.LIGHTGRAY);
			System.out.println("aktualisiereServierfeld, Servierung unmöglich, rot");
		}
		if(w.getGehalten() == 0 && e.validesMuster() == false){
			sfeld.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
			sfeld.setTextFill(Color.LIGHTGRAY);
			System.out.println("aktualisiereServierfeld, Servierung möglich, gelb");
		}
		if(w.getGehalten() == 0 && e.validesMuster() == true){
		// TODO: Audiosignal; Tada.wav? 
			sfeld.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
			sfeld.setTextFill(Color.WHITE);
			System.out.println("aktualisiereServierfeld, Servierung erkannt, grün");
		}
	}
	
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

	public void aktualisiereWuerfelfeld(Wuerfel[] ws, HBox wfeld, Label wz1, Label wz2, Label wz3, Label wz4, Label wz5) {
		wfeld.setPadding(new Insets(5, 5, 5, 5));
		wfeld.setSpacing(10);
		wfeld.setAlignment(Pos.CENTER);
		// Würfel 1
		int wuerfel = 0; 
		wz1.setMinSize(34, 34);
		wz1.setAlignment(Pos.CENTER);
		wz1.setFont(Font.font("Cambria", 22));
		setzeTextfarbe(ws[0], wz1);
		wz1.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(4), null)));
		wz1.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(2))));
		wz1.setText(ws[wuerfel].waehleKuerzel(ws[wuerfel].getWert()));
		// Würfel 2	
		wuerfel++; 
		wz2.setMinSize(34, 34);
		wz2.setAlignment(Pos.CENTER);
		wz2.setFont(Font.font("Cambria", 22));
		setzeTextfarbe(ws[1], wz2);
		wz2.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(4), null)));
		wz2.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(2))));		
		wz2.setText(ws[wuerfel].waehleKuerzel(ws[wuerfel].getWert()));
		// Würfel 3		
		wuerfel++;  
		wz3.setMinSize(34, 34);
		wz3.setAlignment(Pos.CENTER);
		wz3.setFont(Font.font("Cambria", 22));
		setzeTextfarbe(ws[2], wz3);
		wz3.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(4), null)));
		wz3.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(2))));		
		wz3.setText(ws[wuerfel].waehleKuerzel(ws[wuerfel].getWert()));
		// Würfel 4	
		wuerfel++;  
		wz4.setMinSize(34, 34);
		wz4.setAlignment(Pos.CENTER);
		wz4.setFont(Font.font("Cambria", 22));
		setzeTextfarbe(ws[3], wz4);
		wz4.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(4), null)));
		wz4.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(2))));		
		wz4.setText(ws[wuerfel].waehleKuerzel(ws[wuerfel].getWert()));
		// Würfel 5	
		wuerfel++;  
		wz5.setMinSize(34, 34);
		wz5.setAlignment(Pos.CENTER);
		wz5.setFont(Font.font("Cambria", 22));
		setzeTextfarbe(ws[4], wz5);
		wz5.setBackground(new Background(new BackgroundFill(Color.BEIGE, new CornerRadii(4), null)));
		wz5.setBorder(new Border(new BorderStroke(Color.OLIVE, BorderStrokeStyle.SOLID, new CornerRadii(4), new BorderWidths(2))));		
		wz5.setText(ws[wuerfel].waehleKuerzel(ws[wuerfel].getWert()));
		
	}

	public void setzeTextfarbe(Wuerfel ws, Label wz) {
		switch (ws.getWert()) {
		case 1:
			wz.setTextFill(Color.BLACK);
			break;
		case 2:
			wz.setTextFill(Color.RED);
			break;
		case 3:
			wz.setTextFill(Color.BLACK);
			break;
		case 4:
			wz.setTextFill(Color.BLUE);
			break;
		case 5:
			wz.setTextFill(Color.RED);
			break;
		case 6:
			wz.setTextFill(Color.BLACK);
			break;
		default: 
			wz.setTextFill(Color.BLACK);
		}
	}
	
	public void wuerfleWurf(Wurf wurf, Label wurfzaehler, Label serviert, Button wuerfeln, HBox hf, Wuerfel[] wsatz, HBox wuerfelfeld, Wurfergebnis ergebnis, Label wz1, Label wz2, Label wz3, Label wz4, Label wz5) {
		wurf.wurfRunterzaehlen();
		aktualisiereWurfzaehler(wurf, wurfzaehler, wuerfeln);
		if(wurf.getWurfzaehler() == 2 || wurf.getWurfzaehler() == 1) hf.setDisable(false);
		if(wurf.getWurfzaehler() == 0) hf.setDisable(true);
		wurf.wuerfleUngehaltene(wsatz);
		aktualisiereWuerfelfeld(wsatz, wuerfelfeld, wz1, wz2, wz3, wz4, wz5);
		ergebnis.initialisiereAuswerten();
		System.out.println(ergebnis.toString());
		ergebnis.auswertenAlle(wsatz);
		aktualisiereServierfeld(wurf, serviert, ergebnis); 
	}
	// Hier oberhalb Methoden und Kode zu den einzelnen FX-Nodes vom WÜRFELTABLEAU. 

	
	
	public static void main(String[] args) {
		launch(args);
	}
}
