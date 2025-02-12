import java.util.HashMap;

// superklassen er kjøretøy, men kan ikke lage objekter, kun subklasser
abstract class Kjøretøy {
	protected String id;
	protected String merke;
	protected String modell;
	protected int år;

	public Kjøretøy (String id, String merke, String modell, int år) {
		this.id = id;
		this.merke = merke;
		this.modell = modell;
		this.år = år;
		}

// henter id for alle subklasser
	protected String hentId() {
		return id;
	}

// setter egenskaper for alle subklasser
	protected void settId(String id) {
       		this.id = id;
	}

	protected void settMerke(String merke) {
       		this.merke = merke;
	}

	protected void settModell(String modell) {
		this.modell = modell;
	 }

	protected void settÅr(int år) {
		this.år = år;
	}
// utskrift av fellesegenskaper 
	protected String hentInfo() {
		return "idnummer er "+id + ", merke er " + merke + ", modell er " + modell + ", år er " + år;
	}

}

class Bil extends Kjøretøy {
	private int seter;
// deklarerer egenskaper til Bil objektet
	public Bil (String id, String merke, String modell, int år, int seter) {
	super(id, merke, modell, år);
	this.seter = seter;
	}

// ekspansjon av 2 nye metoder, hente og sette antall seter
	public int hentSeter () {
		return seter;
	}

	public void settSeter (int seter) {
		this.seter = seter;
	}
}

class Motorsykkel extends Kjøretøy {
	private String motortype;
// deklarerer egenskaper til Motorsykkel objektet
	public Motorsykkel (String id, String merke, String modell, int år, String motortype) {
		super (id, merke, modell, år);
		this.motortype = motortype;
	}

// ekspansjon av 2 nye metoder, hente og sette motortype
	public String hentMotortype () {
		return motortype;
	}

	public void settSeter (String motortype) {
		this.motortype = motortype;
	}

}

// Kjøring av alle klassene
class ProgramKjøretøy {
	public static void main (String [] args) {
		// opprettelse av objekter
		Bil ferrari = new Bil("007","ferrari","horse",2025,2);
		Bil lambo = new Bil("009","lambo","puma",2021,1);

		Motorsykkel tesla = new Motorsykkel("006","tesla","donky",2024,"V6");
		Motorsykkel lada = new Motorsykkel("005","lada","lama",2023,"V2");

		// opprettelse av HashMap og deklarerig av objekter til Hashmap
		HashMap<String,Kjøretøy> garasje = new HashMap<>();
		garasje.put(ferrari.hentId(), ferrari);
		garasje.put(lambo.hentId(), lambo);
		garasje.put(tesla.hentId(), tesla);
		garasje.put(lada.hentId(), lada);
		
		for (Kjøretøy k : garasje.values()) {
			if (k instanceof Bil) {
				Bil b = (Bil) k;
				System.out.println("Bilen har følgende egenskaper: " + b.hentInfo() +". Antall seter er "+b.hentSeter());
			}
			else if (k instanceof Motorsykkel) {
				Motorsykkel m = (Motorsykkel) k;
				System.out.println("Motorsykkelen har følgende egenskaper: " + m.hentInfo() +". Antall seter er "+ m.hentMotortype());

			}
		}
		
	}	

}
