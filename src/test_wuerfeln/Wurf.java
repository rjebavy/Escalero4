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
			System.out.println("\n W�rfelsatz, W�rfel " + w.toString());
			}
		}
		System.out.println("\nWurfz�hler ist nicht 3!");
	}

	
	

}
