package test2_wuerfeln;

public class Wuerfel {
	private int wert;

	public Wuerfel(int wert) {
		super();
		this.wert = wert;
	}

	public int getWert() {
		return wert;
	}

	public void setWert(int wert) {
		this.wert = wert;
	}

	@Override
	public String toString() {
		return "Wuerfel [wert=" + wert + "]";
	}
	
	

}
