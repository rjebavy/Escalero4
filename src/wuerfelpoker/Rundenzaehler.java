package wuerfelpoker;

public class Rundenzaehler {
	private int runden; 
	
	// Konstruktor 
	public Rundenzaehler(int runden) {
		super();
		this.runden = runden;
		}

	
	// Gehalten Zähler auf 10 setzen; Standardrundenanzahl bei Escalero.  
	public void initialisiereRundenzaehler() {
		this.setRunden(10);
	}
	
	//Rundenzähler von 10 auf 0 runterzählen. 
	public void rundenRunterzaehlen() {
		if(runden > 0 && runden <= 10) 
			runden--;
		else
			System.out.println("\nRundenzähler außerhalb erlaubten Bereichs (1-10)!");	
	}
	
	
	
	
	// Standard Getter & Setter
	public int getRunden() {
		return runden;
	}

	public void setRunden(int runden) {
		this.runden = runden;
	}

	@Override
	public String toString() {
		return "Rundenzaehler [runden=" + runden + "]";
	}
	
	

}
