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


	// Elternmethode für's Auswerten ... 
	public void auswertenAlle(Wuerfel[] wuerfelsatz) {
		
	}

	

	
	// vergleicheZwei hat ein neues oder wiederholtes Bild1 festgestellt.
	public void weiteresBild1(int wert) {
		this.bilder1++; 
		this.bild1 = wert; 
		this.gleich1++; 
	} 	

	// vergleicheZwei hat ein neues oder wiederholtes Bild2 festgestellt.
	public void weiteresBild2(int wert) {
		this.bilder2++; 
		this.bild2 = wert; 
		this.gleich2++; 
	} 

	// vergleicheZwei hat ein neue oder wiederholte Ungleichheit festgestellt.
	public void nocheinUngleicher() {
		this.ungleich++; 
	}  

	// vergleicheZwei sucht Asse, möglicherweise große Straße.
	public void gibtseinAss(int w1, int w2) {
		if(w1 == 6) {
			this.nocheinAss();
		}
		if(w2 == 6) {
			this.nocheinAss();
		}
	}  
	
	// vergleicheZwei sucht Neunen, möglicherweise kleine Straße. 
	public void gibtseineNeun(int w1, int w2) {
		if(w1 == 1) {
			this.nocheineNeun();
		}
		if(w2 == 1) {
			this.nocheineNeun();
		} 
	} 
	
	// gibtseinAss hat ein neues oder weiteres Ass festgestellt.
	public void nocheinAss() {
		this.ass++; 
	} 

	// gibtseineNeun hat eine neue oder weitere Neun festgestellt.
	public void nocheineNeun() {
		this.neun++; 
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
