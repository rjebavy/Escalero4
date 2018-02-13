package test_wuerfeln;

public class Wuerfel {
	private String bild; 
	private int wert;
	private String kuerzel;
	
	public Wuerfel(String bild, int wert, String kuerzel) {
		super();
		this.bild = bild;
		this.wert = wert;
		this.kuerzel = kuerzel;
	}

	public String getBild() {
		return bild;
	}

	public void setBild(String bild) {
		this.bild = bild;
	}

	public int getWert() {
		return wert;
	}

	public void setWert(int wert) {
		this.wert = wert;
	}

	public String getKuerzel() {
		return kuerzel;
	}

	public void setKuerzel(String kuerzel) {
		this.kuerzel = kuerzel;
	}

	@Override
	public String toString() {
		return "Wuerfel [bild=" + bild + ", wert=" + wert + ", kuerzel=" + kuerzel + "]";
	} 
	
	

}
