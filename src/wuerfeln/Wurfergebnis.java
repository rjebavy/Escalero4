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

	
	
	// Konstruktor hier nur mit Eigenschaft W�rfelsatz
	public Wurfergebnis(Wuerfel[] wsatz) {
		this.wuerfelsatz = wsatz; 
	}


	// Elternmethode f�r's Auswerten ... 
	public void auswertenAlle(Wuerfel[] wuerfelsatz) {
		
	}

	// Schleife zum Z�hlen der Bilder des �bergebenen W�rfelsatzes. 
	public void zaehleAlleBilder(Wuerfel[] wsatz) {
		for(int w = 0; w < 5; w++){
			zaehleBilder(wsatz[w].getWert());
		}
	}
	
	// z�hlt das Bild des �bergebenen Wertes hoch. 
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
	


	// vergleicheZwei hat ein neue oder wiederholte Ungleichheit festgestellt.
	public void nocheinUngleicher() {
		this.ungleich++; 
	}  

	// vergleicheZwei sucht Asse, m�glicherweise gro�e Stra�e.
	public void gibtseinAss(int w1, int w2) {
		if(w1 == 6) {
			this.nocheinAss();
		}
		if(w2 == 6) {
			this.nocheinAss();
		}
	}  
	
	// vergleicheZwei sucht Neunen, m�glicherweise kleine Stra�e. 
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
		this.setGleich1(0);
		this.setGleich2(0);
		this.setUngleich(0);
		this.setNeun(0);
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
