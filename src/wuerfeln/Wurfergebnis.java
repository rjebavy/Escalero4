package wuerfeln;

public class Wurfergebnis {
	private Wuerfel[] wuerfelsatz = new Wuerfel[5];
	private int neun;
	private int zehn;
	private int bube;
	private int dame;
	private int koenig;
	private int ass;

	
	
	// Konstruktor hier nur mit Eigenschaft Würfelsatz
	public Wurfergebnis(Wuerfel[] wsatz) {
		this.wuerfelsatz = wsatz; 
	}


	// Elternmethode für's Auswerten ... 
	public void auswertenAlle(Wuerfel[] wuerfelsatz) {
		this.zaehleAlleBilder(wuerfelsatz);
		System.out.println(this.toString());
		istGrosseStrasse();
	}

	// Schleife zum Zählen der Bilder des übergebenen Würfelsatzes. 
	public void zaehleAlleBilder(Wuerfel[] wsatz) {
		for(int w = 0; w < 5; w++){
			zaehleBilder(wsatz[w].getWert());
		}
	}
	
	// zählt das Bild des übergebenen Wertes hoch. 
	public void zaehleBilder(int wert) {
		switch (wert) {
		case 1:
			neun++;
			break;
		case 2:
			zehn++;
			break;
		case 3:
			bube++;
			break;
		case 4:
			dame++;
			break;
		case 5:
			koenig++;
			break;
		case 6:
			ass++;
			break;
		}
	}	
	
	// Gibt es genau zwei Würfel mit dem selben Bild? Hilfsmethode für FullHouse-Erkennung. 
	public boolean gibtseinPaar() {
		boolean paar = false; 
		int ctr = 0; 
		do { 
			if(neun == 2){paar = true;}
			if(zehn == 2){paar = true;}
			if(bube == 2){paar = true;}
			if(dame == 2){paar = true;}
			if(koenig == 2){paar = true;}
			if(ass == 2){paar = true;}
			ctr++; 
		} while (ctr != 1); 
		System.out.println("gibtseinPaar; counter: " + ctr + ", paar: " + paar); 
		return paar; 
	} 
	
	// Gibt es genau drei Würfel mit dem selben Bild? Hilfsmethode für FullHouse-Erkennung. 
	public boolean gibtseinTrio() {
		boolean trio = false; 
		int ctr = 0; 
		do { 
			if(neun == 3){trio = true;}
			if(zehn == 3){trio = true;}
			if(bube == 3){trio = true;}
			if(dame == 3){trio = true;}
			if(koenig == 3){trio = true;}
			if(ass == 3){trio = true;}
			ctr++; 
		} while (ctr != 1); 
		System.out.println("gibtseinTrio; counter: " + ctr + ", trio: " + trio); 
		return trio; 
	} 

	// Ermittelt ob das Muster [10][B][D][K] vorhanden ist. Hilfsmethode für Strassen-Erkennung. 
	public boolean istObenUnten() {
		boolean obenunten = false; 
		int zaehler = 0; 
		if(zehn == 1){zaehler++;}
		if(bube == 1){zaehler++;}
		if(dame == 1){zaehler++;}
		if(koenig == 1){zaehler++;}
		if(zaehler == 4) {obenunten = true;}
		if(zaehler == 3 && zehn == 2) {obenunten = true;}
		if(zaehler == 3 && bube == 2) {obenunten = true;}
		if(zaehler == 3 && dame == 2) {obenunten = true;}
		if(zaehler == 3 && koenig == 2) {obenunten = true;}
		System.out.println("istObenUnten; zaehler: " + zaehler + ", obenunten: " + obenunten); 
		return obenunten; 
	} 	
	
	// Ermittelt ob im Würfelbildmuster alle fünf verschieden sind. Hilfsmethode für Strassen-Erkennung. 
	public boolean sindFuenfVerschiedene() {
		boolean fuenfversch = false; 
		int zaehler = 0; 
		if(neun == 1){zaehler++;}
		if(zehn == 1){zaehler++;}
		if(bube == 1){zaehler++;}
		if(dame == 1){zaehler++;}
		if(koenig == 1){zaehler++;}
		if(ass == 1){zaehler++;}
		if(zaehler == 5) {fuenfversch = true;}
		System.out.println("sindFuenfVerschiedene; zaehler: " + zaehler + ", fuenfversch: " + fuenfversch); 
		return fuenfversch; 
	}
		
	// Ermittelt ob das Würfelbildmuster eine kleine Strasse ist. Auch verwendet um Servierung zu erkennen. 
	public boolean istKleineStrasse() {
		boolean kleinestr = false; 
		if(this.istObenUnten() && neun == 1) {
		kleinestr = true; 
		System.out.println("istKleineStrasse; kleinestr: " + kleinestr);
		return kleinestr; 
		}
	System.out.println("istKleineStrasse; kleinestr: " + kleinestr);
	return kleinestr; 
	}
	
	// Ermittelt ob das Würfelbildmuster eine große Strasse ist. Auch verwendet um Servierung zu erkennen. 
	public boolean istGrosseStrasse() {
		boolean grossestr = false; 
		if(this.istObenUnten() && ass == 1) {
		grossestr = true; 
		System.out.println("istGrosseStrasse; grossestr: " + grossestr);
		return grossestr; 
		}
	System.out.println("istGrosseStrasse; grossestr: " + grossestr);
	return grossestr; 
	}
	
	// Ermittelt ob das Würfelbildmuster ein FullHouse ist. Auch verwendet um Servierung zu erkennen. 
	public boolean istFullHouse() {
		boolean fullhouse = false; 
		if(this.gibtseinTrio() && this.gibtseinPaar()) {
		fullhouse = true; 
		System.out.println("istFullHouse; fullhouse: " + fullhouse);
		return fullhouse; 
		}
	System.out.println("istFullHouse; fullhouse: " + fullhouse);
	return fullhouse; 
	}

	// Ermittelt ob das Würfelbildmuster ein Poker ist. Auch verwendet um Servierung zu erkennen. 
	public boolean istPoker() {
		boolean poker = false; 
		int ctr = 0; 
		do { 
			if(neun == 4){poker = true;}
			if(zehn == 4){poker = true;}
			if(bube == 4){poker = true;}
			if(dame == 4){poker = true;}
			if(koenig == 4){poker = true;}
			if(ass == 4){poker = true;}
			ctr++; 
		} while (ctr != 1); 
		System.out.println("istPoker; counter: " + ctr + ", poker: " + poker); 
		return poker; 
	} 
		
	// Ermittelt ob das Würfelbildmuster ein Grande ist. Auch verwendet um Servierung zu erkennen. 
	public boolean istGrande() {
		boolean grande = false; 
		int ctr = 0; 
		do { 
			if(neun == 5){grande = true;}
			if(zehn == 5){grande = true;}
			if(bube == 5){grande = true;}
			if(dame == 5){grande = true;}
			if(koenig == 5){grande = true;}
			if(ass == 5){grande = true;}
			ctr++; 
		} while (ctr != 1); 
		System.out.println("istGrande; counter: " + ctr + ", grande: " + grande); 
		return grande; 
	} 
	
	// Ermittelt ob es ein wertbares Würfelbildmuster aller Würfel gibt. Auch verwendet um Servierung zu erkennen. 
	public boolean validesMuster() {
		if(this.istGrande() || this.istPoker() || this.istFullHouse() || this.istGrosseStrasse() || this.istKleineStrasse()) {
		return true; 
		}
	return false; 
	} 
		
	
	// alle Auswerteeigenschaften auf 0 setzen. 
	public void initialisiereAuswerten() {
		this.setNeun(0);
		this.setZehn(0);
		this.setBube(0);
		this.setDame(0);
		this.setKoenig(0);
		this.setAss(0);
	}
		

	// Standard Getter & Setter
	public int getNeun() {
		return neun;
	}

	public void setNeun(int neun) {
		this.neun = neun;
	}
	
	public int getZehn() {
		return zehn;
	}

	public void setZehn(int zehn) {
		this.zehn = zehn;
	}

	public int getBube() {
		return bube;
	}

	public void setBube(int bube) {
		this.bube = bube;
	}

	public int getDame() {
		return dame;
	}

	public void setDame(int dame) {
		this.dame = dame;
	}

	public int getKoenig() {
		return koenig;
	}

	public void setKoenig(int koenig) {
		this.koenig = koenig;
	}

	public int getAss() {
		return ass;
	}

	public void setAss(int ass) {
		this.ass = ass;
	}

	
	// Standard toString
	@Override
	public String toString() {
		return "Wurfergebnis [neun=" + neun + ", zehn=" + zehn + ", bube=" + bube + ", dame=" + dame + ", koenig="
				+ koenig + ", ass=" + ass + "]";
	}

	
}
