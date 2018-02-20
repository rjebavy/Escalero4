package wuerfeln;

public class Wurfergebnis {
	private Wuerfel[] wuerfelsatz = new Wuerfel[5];
	private int bild1; 
	private int bild2;
	private int bilder1;
	private int bilder2;
	private int gleich1;
	private int gleich2;
	private int ungleich;
	private int ass;
	private int neun;
	
	
	// Konstruktor hier nur mit Eigenschaft Würfelsatz
	public Wurfergebnis(Wuerfel[] wsatz) {
		this.wuerfelsatz = wsatz; 
	}

	
	// vergleiche alle Würfel paarweise.
	public void vergleicheAlle(Wuerfel[] wuerfelsatz) {
		this.vergleicheZwei(wuerfelsatz[1], wuerfelsatz[0]); 
		this.vergleicheZwei(wuerfelsatz[3], wuerfelsatz[4]); 
		this.vergleicheZwei(wuerfelsatz[1], wuerfelsatz[2]); 
		this.vergleicheZwei(wuerfelsatz[3], wuerfelsatz[2]); 
		this.vergleicheZwei(wuerfelsatz[2], wuerfelsatz[0]); 
		this.vergleicheZwei(wuerfelsatz[2], wuerfelsatz[4]); 
		this.vergleicheZwei(wuerfelsatz[0], wuerfelsatz[3]); 
		this.vergleicheZwei(wuerfelsatz[4], wuerfelsatz[1]); 
		this.vergleicheZwei(wuerfelsatz[3], wuerfelsatz[1]); 
		this.vergleicheZwei(wuerfelsatz[0], wuerfelsatz[4]); 
	} 

	// vergleiche zwei Würfel. 
	public void vergleicheZwei(Wuerfel w1, Wuerfel w2) {
		if(w1 == w2) {
			if(this.getGleich1() > 0 || this.getGleich2() > 0) {
				if(this.getBilder1() > 0 && this.getBilder2() > 0) {
					if(w1.getWert() == this.getBild1()) {
						this.weiteresBild1(w1.getWert());
					}
					if(w1.getWert() == this.getBild2()) {
						this.weiteresBild2(w1.getWert());
					}
					
				}
				if(this.getBilder1() > 0 && this.getBilder2() == 0) {
					this.weiteresBild2(w1.getWert());
				}
			}
			if(this.getGleich1() == 0 || this.getGleich2() == 0) {
				this.weiteresBild1(w1.getWert());
			}			
		} 
		if(w1 != w2) {
			this.nocheinUngleicher();
			this.gibtseinAss(w1, w2); 
			this.gibtseineNeun(w1, w2);
		}
	} 
	
	// vergleicheZwei hat ein neues oder wiederholtes Bild1 festgestellt.
	public void weiteresBild1(int wert) {
		this.bilder1++; 
		this.bild1 = wert; 
		this.gleich1++; 
	} 	
	
	
	// alle Auswerteeigenschaften auf 0 setzen. 
	public void initialisiereAuswerten() {
		this.setBild1(0);
		this.setBild2(0);
		this.setBilder1(0);
		this.setBilder2(0);
		this.setGleich1(0);
		this.setGleich2(0);
		this.setUngleich(0);
		this.setAss(0);
		this.setNeun(0);
	}
	
	

	// Standard Getter & Setter
	public int getBild1() {
		return bild1;
	}
	
	public void setBild1(int bild1) {
		this.bild1 = bild1;
	}

	public int getBild2() {
		return bild2;
	}

	public void setBild2(int bild2) {
		this.bild2 = bild2;
	}

	public int getBilder1() {
		return bilder1;
	}

	public void setBilder1(int bilder1) {
		this.bilder1 = bilder1;
	}

	public int getBilder2() {
		return bilder2;
	}

	public void setBilder2(int bilder2) {
		this.bilder2 = bilder2;
	}

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

	public int getAss() {
		return ass;
	}

	public void setAss(int ass) {
		this.ass = ass;
	}

	public int getNeun() {
		return neun;
	}

	public void setNeun(int neun) {
		this.neun = neun;
	}


	// Standard toString
	@Override
	public String toString() {
		return "Wurfergebnis [bild1=" + bild1 + ", bild2=" + bild2 + ", bilder1=" + bilder1 + ", bilder2=" + bilder2
				+ ", gleich1=" + gleich1 + ", gleich2=" + gleich2 + ", ungleich=" + ungleich + ", ass=" + ass
				+ ", neun=" + neun + "]";
	}

	
	
}
