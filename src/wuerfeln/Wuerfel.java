package wuerfeln;

public class Wuerfel {
	private int wert;
	
	// Konstruktor 
	public Wuerfel(int wert) {
		super();
		this.wert = wert;
		}


	// Je nach Wert ein Würfelbild auswählen
		public String waehleBild(int wuerfelwert) {
			String bild = null;
			switch (wuerfelwert) {
			case 0:
				bild = "ungesetzt";
				break;
			case 1:
				bild = "Neun";
				break;
			case 2:
				bild = "Zehn";
				break;
			case 3:
				bild = "Bube";
				break;
			case 4:
				bild = "Dame";
				break;
			case 5:
				bild = "Koenig";
				break;
			case 6:
				bild = "Ass";
				break;
			default: 
				bild = "Fehler!";
			}
			return bild; 
		}
		
		// Je nach Wert ein Würfelkürzel auswählen
		public String waehleKuerzel(int wuerfelwert) {
			String kuerzel = null;
			switch (wuerfelwert) {
			case 0:
				kuerzel = "X";
				break;
			case 1:
				kuerzel = "9";
				break;
			case 2:
				kuerzel = "10";
				break;
			case 3:
				kuerzel = "B";
				break;
			case 4:
				kuerzel = "D";
				break;
			case 5:
				kuerzel = "K";
				break;
			case 6:
				kuerzel = "A";
				break;
			default: 
				kuerzel = "--";
			}
			return kuerzel; 
		}

	
	// Standard Getter & Setter
	public int getWert() {
		return wert;
	}

	public void setWert(int wert) {
		this.wert = wert;
	}

	// Standard toString
	@Override
	public String toString() {
		return "Wuerfel [wert=" + wert + "]";
	}

}
