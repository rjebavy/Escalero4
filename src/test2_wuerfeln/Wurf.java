package test2_wuerfeln;

public class Wurf {
	private int wurfzaehler; 
	private int[] wuerfelsatz = new int[5];
	private boolean[] nichtgehalten = new boolean[5]; 
	
	public Wurf(int wurf) {
		this.wurfzaehler = wurf; 
	}
	
	public boolean initialisiereWuerfelsatz() {
		if(wurfzaehler == 3) {
		for(int i = 0; i < wuerfelsatz.length; i++) {
			wuerfelsatz[i] = 0; 
			System.out.println("\n W�rfelsatz, W�rfel " + i + " - " + ", Wert = " + wuerfelsatz[i]);
			}
			return true; 
		}
		System.out.println("\nWurfz�hler ist nicht 3!");
		return false;
	}

}
