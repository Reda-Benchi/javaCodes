import java.util.ArrayList;

abstract class Robot {
	protected String navn;

	public Robot (String navn) {
		this.navn = navn;
	}
	public String hentNavn() {
		return navn;
	}

	public abstract void hentInfo();
}

class Gen1 extends Robot {
	
	public Gen1 (String navn) {
		super(navn);
	}
	
	public void hentInfo () {
	System.out.println("Navn på Gen1 robot: " + navn + "\n og har disse egenskapene: ");
	}
}

abstract class GPT extends Robot {
	protected ArrayList<String> fellesEgenskaper;


	public GPT (String navn) {
		super(navn);
		fellesEgenskaper = new ArrayList<>();
		fellesEgenskaper.add("Snakker med mennesker");
		fellesEgenskaper.add("Snakker med roboter");
	}
	abstract public void leggTilEgenskap(String egenskap);

	abstract public void fjernEgenskap(String egenskap);
	
	public boolean hentEgenskap(String n) {
		return fellesEgenskaper.contains(n);
	}

	public void hentFellesEgenskaper() {
		for (String e: fellesEgenskaper) {
			if (e == fellesEgenskaper.get(0)) {
				System.out.print(e);
			}
			else {
				System.out.print(", "+e);
			}
		}
	}

}

class GPT3 extends GPT {
	private ArrayList<String> unikeEgenskaper;

	public GPT3 (String navn) {
		super("GPT"+navn);
		unikeEgenskaper = new ArrayList<>();
		unikeEgenskaper.add("Barnepass");
		unikeEgenskaper.add("Vasking");
	}

	
	public void hentUnikeEgenskaper() {
		for (String e: unikeEgenskaper) {
			if (e == unikeEgenskaper.get(0)) {
				System.out.print(e);
			}
			else {
				System.out.print(", "+e);
			}
		}
	}
	

	public void leggTilEgenskap(String egenskap) {
		unikeEgenskaper.add(egenskap);
	}
	public void fjernEgenskap(String egenskap) {
		unikeEgenskaper.remove(egenskap);
	}


	public void hentInfo() {
	System.out.println("Navn på GPT robot: " + navn + "\n og har disse egenskapene: " + 	hentFellesEgenskaper() + ", " + hentUnikeEgenskaper());
	}	
}

class GPT35 extends GPT {
	private ArrayList<String> unikeEgenskaper;

	public GPT35 (String navn) {
		super("GPT"+navn);
		unikeEgenskaper = new ArrayList<>();
		unikeEgenskaper.add("Styrke");
		unikeEgenskaper.add("Låsesmed");
	}
	
	public void hentUnikeEgenskaper() {
		for (String e: unikeEgenskaper) {
			if (e == unikeEgenskaper.get(0)) {
				System.out.print(e);
			}
			else {
				System.out.print(", "+e);
			}
		}
	}

	public void leggTilEgenskap(String egenskap) {
		unikeEgenskaper.add(egenskap);
	}
	public void fjernEgenskap(String egenskap) {
		unikeEgenskaper.remove(egenskap);
	}


	public void hentInfo() {
	System.out.println("Navn på GPT robot: " + navn + "\nEgenskaper: ");
	hentFellesEgenskaper();
	hentUnikeEgenskaper();
	}
}

class GPT4 extends GPT {
	private ArrayList<String> unikeEgenskaper;

	public GPT4 (String navn) {
		super("GPT"+navn);
		unikeEgenskaper = new ArrayList<>();
		unikeEgenskaper.add("Kalkulator");
		unikeEgenskaper.add("Kokk");
	}
	
	public void hentUnikeEgenskaper() {
		for (String e: unikeEgenskaper) {
			if (e == unikeEgenskaper.get(0)) {
				System.out.print(e);
			}
			else {
				System.out.print(", "+e);
			}
		}
	}

	public void leggTilEgenskap(String egenskap) {
		unikeEgenskaper.add(egenskap);
	}
	public void fjernEgenskap(String egenskap) {
		unikeEgenskaper.remove(egenskap);
	}


	public void hentInfo() {
	System.out.println("Navn på GPT robot: " + navn + "\n og har disse egenskapene: ");
	hentFellesEgenskaper();
	hentUnikeEgenskaper();
	}
}
