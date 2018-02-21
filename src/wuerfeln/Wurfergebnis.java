package wuerfeln;

public class Wurfergebnis {
	private Wuerfel[] wuerfelsatz = new Wuerfel[5];
	private int gleich1;
	private int gleich2;
	private int ungleich;
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
		this.gibtseinTrio();	
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
	
	// alle Auswerteeigenschaften auf 0 setzen. 
	public void initialisiereAuswerten() {
		this.setGleich1(0);
		this.setGleich2(0);
		this.setUngleich(0);
		this.setNeun(0);
		this.setZehn(0);
		this.setBube(0);
		this.setDame(0);
		this.setKoenig(0);
		this.setAss(0);
	}
		

	// Standard Getter & Setter
	public int getGleich1() {
		return gleich1;
	}

	public void setGleich1(int gleich1) {
		this.gleich1 = gleich1;
	}

	public int getGleich2() {
		return gleich2;
	}

	public void setGleich2(int gleich2) {
		this.gleich2 = gleich2;
	}

	public int getUngleich() {
		return ungleich;
	}

	public void setUngleich(int ungleich) {
		this.ungleich = ungleich;
	}

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
		return "Wurfergebnis [gleich1=" + gleich1 + ", gleich2=" + gleich2 + ", ungleich=" + ungleich + ", neun=" + neun
				+ ", zehn=" + zehn + ", bube=" + bube + ", dame=" + dame + ", koenig=" + koenig + ", ass=" + ass + "]";
	}
	
	
}
