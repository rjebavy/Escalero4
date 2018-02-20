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
