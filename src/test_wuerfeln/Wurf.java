package test_wuerfeln;

public class Wurf {
	private int wurfzaehler; 
	private Wuerfel[] wuerfelsatz = new Wuerfel[5];
	private boolean[] nichtgehalten = new boolean[5]; 
	
	public Wurf(int wurf) {
		this.wurfzaehler = wurf; 
	}
	
	public void initialisiereWS() {
		if(wurfzaehler == 3) {
		for(Wuerfel w : wuerfelsatz) {
			// w.setBild("ungesetzt");
			// w.setWert(0); 
			// w.setKuerzel("X"); 
			System.out.println("\n Würfelsatz, Würfel " + w.toString());
			}
		}
		System.out.println("\nWurfzähler ist nicht 3!");
	}

	
	

}
